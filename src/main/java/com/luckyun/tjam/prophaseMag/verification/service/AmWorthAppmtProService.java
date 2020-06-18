package com.luckyun.tjam.prophaseMag.verification.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luckyun.core.annotation.TransactionalException;
import com.luckyun.core.data.BaseMapper;
import com.luckyun.core.data.BaseService;
import com.luckyun.core.data.filter.AuthInfo;
import com.luckyun.core.km.keyhelper.IDGenerate;
import com.luckyun.tjam.prophaseMag.acceptance.common.DatadicKeys;
import com.luckyun.tjam.prophaseMag.acceptance.helper.BaseServiceHelperImpl;
import com.luckyun.tjam.prophaseMag.verification.mapper.AmWaitAppmtMapper;
import com.luckyun.tjam.prophaseMag.verification.mapper.AmWorthAppmtProMapper;
import com.luckyun.tjam.prophaseMag.verification.model.AmWaitAppmt;
import com.luckyun.tjam.prophaseMag.verification.model.AmWorthAppmtBatch;
import com.luckyun.tjam.prophaseMag.verification.model.AmWorthAppmtPro;
import com.luckyun.tjam.prophaseMag.verification.param.AmWorthAppmtProParam;

@Service
public class AmWorthAppmtProService extends BaseService<AmWorthAppmtPro> {

	@Autowired
	private AmWorthAppmtProMapper amWorthAppmtProMapper;

	@Autowired
	private BaseServiceHelperImpl baseServiceHelperImpl;

	@Autowired
	private AmWaitAppmtMapper amWaitAppmtMapper;
	
	@Override
	public BaseMapper<AmWorthAppmtPro> getMapper() {
		return amWorthAppmtProMapper;
	}

	/**
	 * 条件查询所有
	 * 
	 * @param condition
	 * @return
	 */
	@SuppressWarnings("unlikely-arg-type")
	public List<AmWorthAppmtPro> findAll(AmWorthAppmtProParam condition) {
		List<AmWorthAppmtPro> list = amWorthAppmtProMapper.findAll(condition);
		for (AmWorthAppmtPro e : list) {
			// 分摊方式
			if (null != e.getIappmttype() && !"".equals(e.getIappmttype())) {
				e.setSappmttypenm(baseServiceHelperImpl.getDatadicName(DatadicKeys.CAP_VERIFICATION_PLAN_IAPPMTTYPE,
						e.getIappmttype() != null ? e.getIappmttype() : ""));
			}
			// 所属线路
			if (null != e.getIlineid() && !"".equals(e.getIlineid())) {
				e.setSlinenm(baseServiceHelperImpl.getDatadicName(DatadicKeys.CAP_ACCEPTANCE_EQUIP_ILINEID,
						e.getIlineid() != null ? e.getIlineid() : ""));
			}
			// 处理状态(就是分摊状态)
			if (null != e.getIdealstatus() && !"".equals(e.getIdealstatus())) {
				e.setSdealstatunm(baseServiceHelperImpl.getDatadicName(DatadicKeys.AM_WAIT_APPMT_IDEALSTATUS,
						e.getIdealstatus() != null ? e.getIdealstatus() : ""));
			}
		}
		return list;
	}

	/**
	 * 查询主表查询全部待分摊项
	 */
	public List<AmWorthAppmtPro> readAllByIlinkno(Long ilinkno){
		List<AmWorthAppmtPro> awaProList = this.amWorthAppmtProMapper.findAllByIlinkno(ilinkno);
		awaProList.forEach(e -> {
			// 处理状态(就是分摊状态)
			if (null != e.getIdealstatus()) {
				e.setSdealstatunm(baseServiceHelperImpl.getDatadicName(DatadicKeys.AM_WAIT_APPMT_IDEALSTATUS,
						e.getIdealstatus() != null ? e.getIdealstatus() : ""));
			}
			if (null != e.getIlineid()) {
				e.setSlinenm(baseServiceHelperImpl.getDatadicName(DatadicKeys.CAP_ACCEPTANCE_EQUIP_ILINEID,
						e.getIlineid() != null ? e.getIlineid() : ""));
			}
			e.setSappmttypenm(baseServiceHelperImpl.getDatadicName(DatadicKeys.CAP_VERIFICATION_PLAN_IAPPMTTYPE,
					e.getIappmttype() != null?e.getIappmttype():""));

		});
		return awaProList;
	}
	
	/**
	 * 查询明细
	 */
	@SuppressWarnings("unlikely-arg-type")
	public AmWorthAppmtPro findOne(Long indocno) {
		AmWorthAppmtPro e = amWorthAppmtProMapper.findOne(indocno);
		// 分摊方式
		if (null != e.getIappmttype() && !"".equals(e.getIappmttype())) {
			e.setSappmttypenm(baseServiceHelperImpl.getDatadicName(DatadicKeys.CAP_VERIFICATION_PLAN_IAPPMTTYPE,
					e.getIappmttype() != null ? e.getIappmttype() : ""));
		}
		// 所属线路
		if (null != e.getIlineid() && !"".equals(e.getIlineid())) {
			e.setSlinenm(baseServiceHelperImpl.getDatadicName(DatadicKeys.CAP_ACCEPTANCE_EQUIP_ILINEID,
					e.getIlineid() != null ? e.getIlineid() : ""));
		}
		// 处理状态(就是分摊状态)
		if (null != e.getIdealstatus() && !"".equals(e.getIdealstatus())) {
			e.setSdealstatunm(baseServiceHelperImpl.getDatadicName(DatadicKeys.CAP_VERIFICATION_PLAN_IDEALSTATUS,
					e.getIdealstatus() != null ? e.getIdealstatus() : ""));
		}
		return e;
	}

	/**
	 * 批量删除
	 */
	@TransactionalException
	public void del(AmWorthAppmtProParam condition) {
		List<AmWorthAppmtPro> delList = condition.getDelList();
		if (null != delList && !delList.isEmpty()) {
			for (AmWorthAppmtPro e : delList) {
				if(null != e.getIsourceid()) {
					AmWaitAppmt sourceInfo = this.amWaitAppmtMapper.findOne(e.getIsourceid());
					if(null != sourceInfo) {
						sourceInfo.setIquotestate(0);
						this.amWaitAppmtMapper.update(sourceInfo);
					}
				}
				super.delete(e);
			}
		}
	}

	/**
	 * 修改
	 */

	@TransactionalException
	public void updateAm(AmWorthAppmtPro entity) {
		super.update(entity);
	}

	/**
	 * 新增
	 * 
	 * @param entity
	 */
	@TransactionalException
	public void add(AmWorthAppmtPro entity) {

		AuthInfo authInfo = super.getAuthInfo();
		Long indocno = IDGenerate.getKey(entity);
		entity.setIndocno(indocno);
		entity.setIdealstatus(0);
		entity.setIdel(0);
		entity.setSregid(authInfo.getIndocno());
		entity.setDregt(new Date());
		super.insert(entity);
	}

	/**
	 * 新增子表明细
	 * 
	 * @param entity
	 */
	@TransactionalException
	public void addAwaPro(AmWorthAppmtBatch entity) {
		List<AmWorthAppmtPro> proList = entity.getAddProList();
		proList.forEach(e -> {
			e.setIsourceid(e.getIndocno());
			AuthInfo authInfo = super.getAuthInfo();
			e.setIdealstatus(0);
			e.setIdel(0);
			e.setSregid(authInfo.getIndocno());
			e.setDregt(new Date());
			e.setIlinkno(entity.getIndocno());
			Long indocno = IDGenerate.getKey(e);
			e.setIndocno(indocno);
			super.insert(e);
			
			AmWaitAppmt sourceInfo = this.amWaitAppmtMapper.findOne(e.getIsourceid());
			if(null != sourceInfo) {
				this.amWaitAppmtMapper.updateIquotestate(e.getIsourceid());
			}
			
		});

	}

	// 修改分摊金额
	@TransactionalException
	public AmWorthAppmtPro updateIappmttype(AmWorthAppmtProParam entity) {
		List<AmWorthAppmtPro> updList = entity.getUpdList();
		updList.forEach(e->{
			amWorthAppmtProMapper.update(e);
		});
		return entity;
	}

}
