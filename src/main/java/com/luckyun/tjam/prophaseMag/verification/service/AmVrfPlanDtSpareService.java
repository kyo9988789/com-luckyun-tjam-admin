
package com.luckyun.tjam.prophaseMag.verification.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luckyun.core.annotation.TransactionalException;
import com.luckyun.core.data.BaseMapper;
import com.luckyun.core.data.BaseService;
import com.luckyun.tjam.prophaseMag.acceptance.common.DatadicKeys;
import com.luckyun.tjam.prophaseMag.acceptance.helper.BaseServiceHelperImpl;
import com.luckyun.tjam.prophaseMag.maintenance.mapper.AmAcceptanceSpareMapper;
import com.luckyun.tjam.prophaseMag.maintenance.model.AmAcceptanceSpare;
import com.luckyun.tjam.prophaseMag.verification.mapper.AmVrfPlanDtSpareMapper;
import com.luckyun.tjam.prophaseMag.verification.model.AmVrfPlanDtSpare;
import com.luckyun.tjam.prophaseMag.verification.param.AmVrfPlanDtSpareParam;

@Service
public class AmVrfPlanDtSpareService extends BaseService<AmVrfPlanDtSpare>{

	@Autowired
	private AmVrfPlanDtSpareMapper amVrfPlanDtSpareMapper;
	
	@Autowired
	private AmAcceptanceSpareMapper amAcceptanceSpareMapper;

	@Autowired
	private BaseServiceHelperImpl baseServiceHelperImpl;
	
	@Override
	public BaseMapper<AmVrfPlanDtSpare> getMapper() {
		return amVrfPlanDtSpareMapper;
	}

	/**
	  *  条件查询所有
	 * @param condition
	 * @return
	 */
	public List<AmVrfPlanDtSpare> findAll(AmVrfPlanDtSpareParam condition){
		List<AmVrfPlanDtSpare> list = this.amVrfPlanDtSpareMapper.findAll(condition);
		return list;
	}

	/**
	  *  查询明细
	 * @returns
	 */
	public AmVrfPlanDtSpare findOne(Long indocno) {
		AmVrfPlanDtSpare info = this.amVrfPlanDtSpareMapper.findOne(indocno);
		return info;
	}
	
	/**
	  * 通过主表主键查询所有计划明细 + 组成部分
	 */
	public List<AmVrfPlanDtSpare> readAmVrfPlanDtSpare(Long ilinkno) {
		List<AmVrfPlanDtSpare> spareList = this.amVrfPlanDtSpareMapper.findAllByIbillid(ilinkno);
		spareList.forEach(e -> {
			String sstrans = baseServiceHelperImpl.getDatadicName(DatadicKeys.CAP_ACCEPTANCE_EQUIP_ISTRANS,
					e.getIstrans() != null ? e.getIstrans() : "");
			e.setSstrans(sstrans);
		});
		return spareList;
	}
	
	/**
	  * 单条删除
	 */
	@TransactionalException
	public int delDtSpare(AmVrfPlanDtSpareParam condition) {
		List<AmVrfPlanDtSpare> delList = condition.getDelList();
		delList.forEach(e -> {
			AmVrfPlanDtSpare info = this.amVrfPlanDtSpareMapper.findOne(e.getIndocno());
			AmAcceptanceSpare sourceInfo = this.amAcceptanceSpareMapper.findOne(info.getIamaccspareid());
			if(null != sourceInfo) {
				sourceInfo.setIdatastate(0);
				this.amAcceptanceSpareMapper.update(sourceInfo);
			}
			this.amVrfPlanDtSpareMapper.delete(info);
		});
		return 1;
	}
}

