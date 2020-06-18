
package com.luckyun.tjam.prophaseMag.verification.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luckyun.core.annotation.TransactionalException;
import com.luckyun.core.data.BaseMapper;
import com.luckyun.core.data.BaseService;
import com.luckyun.tjam.prophaseMag.acceptance.common.DatadicKeys;
import com.luckyun.tjam.prophaseMag.acceptance.helper.BaseServiceHelperImpl;
import com.luckyun.tjam.prophaseMag.maintenance.mapper.AmAcceptanceFiassetMapper;
import com.luckyun.tjam.prophaseMag.maintenance.mapper.AmArcClassMapper;
import com.luckyun.tjam.prophaseMag.maintenance.model.AmAcceptanceFiasset;
import com.luckyun.tjam.prophaseMag.maintenance.model.AmArcClass;
import com.luckyun.tjam.prophaseMag.verification.mapper.AmVrfPlanDtFiassetMapper;
import com.luckyun.tjam.prophaseMag.verification.model.AmVrfPlanDtFiasset;
import com.luckyun.tjam.prophaseMag.verification.param.AmVrfPlanDtFiassetParam;

@Service
public class AmVrfPlanDtFiassetService extends BaseService<AmVrfPlanDtFiasset>{

	@Autowired
	private AmVrfPlanDtFiassetMapper amVrfPlanDtFiassetMapper;
	
	@Autowired
	private AmAcceptanceFiassetMapper amAcceptanceFiassetMapper;

	@Autowired
	private BaseServiceHelperImpl baseServiceHelperImpl;

	@Autowired
	private AmArcClassMapper amArcClassMapper;
	
	@Override
	public BaseMapper<AmVrfPlanDtFiasset> getMapper() {
		return amVrfPlanDtFiassetMapper;
	}

	/**
	  *  条件查询所有
	 * @param condition
	 * @return
	 */
	public List<AmVrfPlanDtFiasset> findAll(AmVrfPlanDtFiassetParam condition){
		List<AmVrfPlanDtFiasset> list = this.amVrfPlanDtFiassetMapper.findAll(condition);
		return list;
	}
	
	/**
	  *  查询明细
	 * @returns
	 */
	public AmVrfPlanDtFiasset findOne(Long indocno) {
		AmVrfPlanDtFiasset info = this.amVrfPlanDtFiassetMapper.findOne(indocno);
		return info;
	}
	
	/**
	  *  通过资产编号查询所有组成部分
	 * @param sparent
	 * @return
	 */
	public List<AmVrfPlanDtFiasset> findAllBySparent(String sparentno) {
		if (null != sparentno && !"".equals(sparentno)) {
			List<AmVrfPlanDtFiasset> list = this.amVrfPlanDtFiassetMapper.findAllBySparent(sparentno);
			list.forEach(e -> {
				if(null != e.getSeqclassno()) {
					List<AmArcClass> capArcClassList = this.amArcClassMapper.findBySclassno(e.getSeqclassno());
					if(null != capArcClassList && !capArcClassList.isEmpty()) {
						AmArcClass capArcClass = capArcClassList.get(0);
						e.setSclassstructurenm(capArcClass.getSclassnm());
					}
					String sstrans = baseServiceHelperImpl.getDatadicName(DatadicKeys.CAP_ACCEPTANCE_EQUIP_ISTRANS,
							e.getIstrans() != null ? e.getIstrans() : "");
					e.setSstrans(sstrans);
				}
			});
			return list;
		}
		return null;
	}
	
	/**
	  * 通过主表主键查询所有计划明细 + 组成部分
	 */
	public List<AmVrfPlanDtFiasset> readAmVrfPlanDtFiasset(Long ilinkno) {
		List<AmVrfPlanDtFiasset> fiaList = this.amVrfPlanDtFiassetMapper.findAllByIbillid(ilinkno);
		if(null != fiaList && fiaList.size() > 0) {
			fiaList.forEach(e -> {
				e.setIstoragestate(1);
				
				String sstrans = baseServiceHelperImpl.getDatadicName(DatadicKeys.CAP_ACCEPTANCE_EQUIP_ISTRANS,
						e.getIstrans() != null ?e.getIstrans() : "");
				e.setSstrans(sstrans);
				
				// 分类名称
				if(null != e.getSeqclassno()) {
					List<AmArcClass> capArcClassList = this.amArcClassMapper.findBySclassno(e.getSeqclassno());
					if(null != capArcClassList && !capArcClassList.isEmpty()) {
						AmArcClass capArcClass = capArcClassList.get(0);
						e.setSclassstructurenm(capArcClass.getSclassnm());
					}
				}
				
			});
		}
		return fiaList;
	}
	
	/**
	  * 单条删除 + 删除组成部分
	 */
	@TransactionalException
	public int delDtFiasset(AmVrfPlanDtFiassetParam condition) {
		List<AmVrfPlanDtFiasset> delList = condition.getDelList();
		delList.forEach(e -> {
			AmVrfPlanDtFiasset info = this.amVrfPlanDtFiassetMapper.findOne(e.getIndocno());
			if(null != info) {
				if(null != info.getSfcode() && !"".equals(info.getSfcode())) {
					List<AmVrfPlanDtFiasset> sonList = this.amVrfPlanDtFiassetMapper.findAllBySparent(info.getSfcode());
					if(sonList.size() > 0) {
						sonList.forEach(e1 -> {
							AmAcceptanceFiasset sourceSonInfo = this.amAcceptanceFiassetMapper.findOne(e1.getIamaccfiassetid());
							if(null != sourceSonInfo) {
								sourceSonInfo.setIdatastate(0);
								this.amAcceptanceFiassetMapper.update(sourceSonInfo);
							}
							super.delete(e1);
						});
					}
				}
				AmAcceptanceFiasset sourceInfo = this.amAcceptanceFiassetMapper.findOne(info.getIamaccfiassetid());
				if(null != sourceInfo) {
					sourceInfo.setIdatastate(0);
					this.amAcceptanceFiassetMapper.update(sourceInfo);
				}
				super.delete(info);
			}
		});
		return 1;
	}
}

