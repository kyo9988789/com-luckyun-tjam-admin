package com.luckyun.tjam.prophaseMag.verification.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.luckyun.tjam.prophaseMag.verification.bpm.BpmAmWorthAppmtBatchService;
import com.luckyun.tjam.prophaseMag.verification.bpm.BpmTaskParam;
import com.luckyun.tjam.prophaseMag.verification.utils.DoubleUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luckyun.base.provider.feign.BaseSysDeptProvider;
import com.luckyun.base.provider.feign.BaseSysUserProvider;
import com.luckyun.core.annotation.TransactionalException;
import com.luckyun.core.data.BaseMapper;
import com.luckyun.core.data.BaseService;
import com.luckyun.core.data.filter.AuthInfo;
import com.luckyun.model.dept.SysDept;
import com.luckyun.model.user.SysAccount;
import com.luckyun.tjam.prophaseMag.acceptance.common.DatadicKeys;
import com.luckyun.tjam.prophaseMag.acceptance.helper.BaseServiceHelperImpl;
import com.luckyun.tjam.prophaseMag.verification.mapper.AmWorthAppmtBatchMapper;
import com.luckyun.tjam.prophaseMag.verification.mapper.AmWorthAppmtFiassetMapper;
import com.luckyun.tjam.prophaseMag.verification.mapper.AmWorthAppmtProMapper;
import com.luckyun.tjam.prophaseMag.verification.model.AmWorthAppmtBatch;
import com.luckyun.tjam.prophaseMag.verification.model.AmWorthAppmtFiasset;
import com.luckyun.tjam.prophaseMag.verification.model.AmWorthAppmtPro;
import com.luckyun.tjam.prophaseMag.verification.param.AmWorthAppmtBatchParam;

@Service
public class AmWorthAppmtBatchService extends BaseService<AmWorthAppmtBatch> {

	@Autowired
	private AmWorthAppmtBatchMapper amWorthAppmtBatchMapper;

	@Autowired
	private AmWorthAppmtProMapper amWorthAppmtProMapper;

	@Autowired
	private AmWorthAppmtFiassetMapper amWorthAppmtFiassetMapper;

	@Autowired
	private BaseSysUserProvider baseSysUserProvider;

	@Autowired
	private BaseSysDeptProvider baseSysDeptProvider;

	@Autowired
	private BaseServiceHelperImpl baseServiceHelperImpl;

	@Autowired
	private BpmAmWorthAppmtBatchService bpmAmWorthAppmtBatchService;

	@Override
	public BaseMapper<AmWorthAppmtBatch> getMapper() {
		return amWorthAppmtBatchMapper;
	}

	/**
	 * 条件查询所有
	 * 
	 * @param condition
	 * @return
	 */
	@SuppressWarnings("unlikely-arg-type")
	public List<AmWorthAppmtBatch> findAll(AmWorthAppmtBatchParam condition) {
		List<AmWorthAppmtBatch> list = amWorthAppmtBatchMapper.findAll(condition);
		for (AmWorthAppmtBatch e : list) {

			// 创建部门
			if (null != e.getIdeptid() && !"".equals(e.getIdeptid())) {
				SysDept sysDept = this.baseSysDeptProvider.findByIndocno(e.getIdeptid());
				if (null != sysDept) {
					e.setSdeptnm(sysDept.getSname());
				}
			}
			
			// 创建人名称
			if(null != e.getSregid() && !"".equals(e.getSregid())) {
				SysAccount sysAccount = this.baseSysUserProvider.findFSysUserByIndocno(e.getSregid());
				if(null != sysAccount) e.setSregnm(sysAccount.getSname()); 
			}
			e.setSapprovalstate(baseServiceHelperImpl.getDatadicName(DatadicKeys.BPM_IAPPROVALSTATE,
					e.getIapprovalstate() != null ? e.getIapprovalstate():""));
		}
		return list;

	}

	/**
	 * 查询明细
	 */

	@SuppressWarnings("unlikely-arg-type")
	public AmWorthAppmtBatch findOne(Long indocno) {
		AmWorthAppmtBatch e = amWorthAppmtBatchMapper.findOne(indocno);
		e.setSapprovalstate(baseServiceHelperImpl.getDatadicName(DatadicKeys.BPM_IAPPROVALSTATE,
				e.getIapprovalstate() != null ? e.getIapprovalstate():""));
		// 创建部门
		if (null != e.getIdeptid() && !"".equals(e.getIdeptid())) {
			SysDept sysDept = this.baseSysDeptProvider.findByIndocno(e.getIdeptid());
			if (null != sysDept) {
				e.setSdeptnm(sysDept.getSname());
			}
		}

		List<AmWorthAppmtFiasset> fiassetList = amWorthAppmtFiassetMapper.findAllByIlinkno(e.getIndocno());
		if (null != fiassetList && !fiassetList.isEmpty()) {
			for (AmWorthAppmtFiasset e2 : fiassetList) {
				// 管理部门
				if (null != e2.getImanagedeptid() && !"".equals(e2.getImanagedeptid())) {
					SysDept sysDept = this.baseSysDeptProvider.findByIndocno(e2.getImanagedeptid());
					if (null != sysDept) {
						e2.setSmanagedeptnm(sysDept.getSname());
					}
				}

				// 责任人
				if (null != e2.getIdutyid() && !"".equals(e2.getIdutyid())) {
					SysAccount sysAccount1 = this.baseSysUserProvider.findFSysUserByIndocno(e2.getIdutyid());
					if (null != sysAccount1) {
						e2.setSdutynm(sysAccount1.getSname());
					}
				}

				// 所属线路
				if (null != e2.getIlineid() && !"".equals(e2.getIlineid())) {
					e2.setSlinenm(baseServiceHelperImpl.getDatadicName(DatadicKeys.CAP_ACCEPTANCE_EQUIP_ILINEID,
							e2.getIlineid() != null ? e2.getIlineid() : ""));
				}
			}
			e.setFiassetList(fiassetList);
		}
		List<AmWorthAppmtPro> proList = amWorthAppmtProMapper.findAllByIlinkno(e.getIndocno());
		if (null != proList && !proList.isEmpty()) {
			for (AmWorthAppmtPro e1 : proList) {
				// 分摊方式
				if (null != e1.getIappmttype() && !"".equals(e1.getIappmttype())) {
					e1.setSappmttypenm(
							baseServiceHelperImpl.getDatadicName(DatadicKeys.CAP_VERIFICATION_PLAN_IAPPMTTYPE,
									e1.getIappmttype() != null ? e1.getIappmttype() : ""));
				}

				// 所属线路
				if (null != e1.getIlineid() && !"".equals(e1.getIlineid())) {
					e1.setSlinenm(baseServiceHelperImpl.getDatadicName(DatadicKeys.CAP_ACCEPTANCE_EQUIP_ILINEID,
							e1.getIlineid() != null ? e1.getIlineid() : ""));
				}

				// 处理状态(就是分摊状态)
				if (null != e1.getIdealstatus() && !"".equals(e1.getIdealstatus())) {
					e1.setSdealstatunm(
							baseServiceHelperImpl.getDatadicName(DatadicKeys.CAP_VERIFICATION_PLAN_IDEALSTATUS,
									e1.getIdealstatus() != null ? e1.getIdealstatus() : ""));
				}
			}
			e.setProList(proList);
		}
		BpmTaskParam bpmParams = bpmAmWorthAppmtBatchService.createBpmParams(new SimpleDateFormat("yyyy-MM-dd").format(new Date())
				+ "资产价值分摊申请-" + baseSysUserProvider.findFSysUserByIndocno(getAuthInfo().getIndocno()).getSname(), e.getIndocno());
		e.setBpmTaskParam(bpmParams);
		return e;
	}

	/**
	 * 批量删除
	 */
	@TransactionalException
	public void del(AmWorthAppmtBatchParam condition) {
		List<AmWorthAppmtBatch> delList = condition.getDelList();
		if (null != delList && !delList.isEmpty()) {
			for (AmWorthAppmtBatch e : delList) {
				super.delete(e);
			}
		}
	}

	/**
	 * 修改
	 */
	@TransactionalException
	public AmWorthAppmtBatch updateAm(AmWorthAppmtBatch entity) {
		super.update(entity);
//		if (null != entity.getProList() && !entity.getProList().isEmpty()) {
//			handleProList(entity);
//		}
//		if (null != entity.getFiassetList() && !entity.getFiassetList().isEmpty()) {
//			handleFiassetList(entity);
//		}
		BpmTaskParam bpmParams = bpmAmWorthAppmtBatchService.createBpmParams(new SimpleDateFormat("yyyy-MM-dd").format(new Date())
				+ "资产价值分摊申请-" + baseSysUserProvider.findFSysUserByIndocno(getAuthInfo().getIndocno()).getSname(), entity.getIndocno());
		entity.setBpmTaskParam(bpmParams);
		return entity;
	}

	// 处理字表一
	public void handleProList(AmWorthAppmtBatch entity) {
		// 删除
		List<AmWorthAppmtPro> proList = amWorthAppmtProMapper.findAllByIlinkno(entity.getIndocno());
		if (null != proList && !proList.isEmpty()) {
			for (AmWorthAppmtPro e : proList) {
				amWorthAppmtProMapper.delete(e);
			}
		}
		// 新增
		List<AmWorthAppmtPro> proLists = entity.getProList();
		for (AmWorthAppmtPro e : proLists) {
			e.setIlinkno(entity.getIndocno());
			e.setIndocno(null);
			amWorthAppmtProMapper.insert(e);
		}
	}

	// 处理字表二
	public void handleFiassetList(AmWorthAppmtBatch entity) {
		// 删除
		List<AmWorthAppmtFiasset> fiassetList = amWorthAppmtFiassetMapper.findAllByIlinkno(entity.getIndocno());
		if (null != fiassetList && !fiassetList.isEmpty()) {
			for (AmWorthAppmtFiasset e : fiassetList) {
				amWorthAppmtFiassetMapper.delete(e);
			}
		}
		// 新增
		List<AmWorthAppmtFiasset> fiassetList1 = entity.getFiassetList();
		for (AmWorthAppmtFiasset e : fiassetList1) {
			e.setIlinkno(entity.getIndocno());
			e.setIndocno(null);
			amWorthAppmtFiassetMapper.insert(e);
		}

	}

	/**
	 * 新增
	 */
	@TransactionalException
	public AmWorthAppmtBatch add(AmWorthAppmtBatch entity) {
		AuthInfo authInfo = super.getAuthInfo();
		entity.setSregid(authInfo.getIndocno());
		String sfcode = generateSappmtno();
		entity.setSfcode(sfcode);
		entity.setDregt(new Date());
		SysAccount sysAccount = this.baseSysUserProvider.findFSysUserByIndocno(authInfo.getIndocno());
		if (null != sysAccount && null != sysAccount.getSysUserInfo()) {
			entity.setIdeptid(sysAccount.getSysUserInfo().getIdeptid()); // 创建部门
		}
		super.insert(entity);
		BpmTaskParam bpmParams = bpmAmWorthAppmtBatchService.createBpmParams(new SimpleDateFormat("yyyy-MM-dd").format(new Date())
				+ "资产价值分摊申请-" + baseSysUserProvider.findFSysUserByIndocno(getAuthInfo().getIndocno()).getSname(), entity.getIndocno());
		entity.setBpmTaskParam(bpmParams);
		return entity;
	}

	/**
	 * 生成编号
	 * 
	 * @return
	 */
	private String generateSappmtno() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String date = sdf.format(new Date());
		String sfcode = date;
		AmWorthAppmtBatchParam condition = new AmWorthAppmtBatchParam();
		condition.setSfcode(sfcode);
		List<AmWorthAppmtBatch> list = this.amWorthAppmtBatchMapper.findMaxSappmtno(condition);
		if (null == list || list.size() == 0) {
			sfcode = sfcode + "-" + "001";
		} else if (null != list && list.size() > 0) {
			AmWorthAppmtBatch maxPlanInfo = list.get(0);
			String maxSfcode = maxPlanInfo.getSfcode();
			String lastString = maxSfcode.substring(maxSfcode.lastIndexOf("-") + 1, maxSfcode.length());
			Integer lastInteger = Integer.valueOf(lastString) + 1;
			if (lastInteger.toString().length() == 1) {
				sfcode = sfcode + "-00" + lastInteger;
			} else if (lastInteger.toString().length() == 2) {
				sfcode = sfcode + "-0" + lastInteger;
			} else if (lastInteger.toString().length() == 3) {
				sfcode = sfcode + "-" + lastInteger;
			}
		}
		return sfcode;
	}

	/**
	 * 分摊计算
	 */
	@TransactionalException
	public AmWorthAppmtBatch compute(AmWorthAppmtBatch condition) {
//		AmWorthAppmtBatch e = amWorthAppmtBatchMapper.findOne(condition.getIndocno());
//		// 查子表一
//		List<AmWorthAppmtPro> amWorthAppmtPro = amWorthAppmtProMapper.findAllByIlinkno(e.getIndocno());
		List<AmWorthAppmtPro> amWorthAppmtPro = condition.getAddProList();
		double count = 0;
		double a = 0;
		// 几条分摊信息（字表一的）
		for (AmWorthAppmtPro e1 : amWorthAppmtPro) {
			e1.setSappmttypenm(baseServiceHelperImpl.getDatadicName(DatadicKeys.CAP_VERIFICATION_PLAN_IAPPMTTYPE,
					e1.getIappmttype() != null?e1.getIappmttype():""));
			if (null != e1.getIappmttype() && 0 == e1.getIappmttype()) {
				count = DoubleUtil.add(count, e1.getIappmtmoney() != null ? e1.getIappmtmoney() : 0);
			}
			if (null != e1.getIappmttype() && 1 == e1.getIappmttype()) {
				a = DoubleUtil.add(a, e1.getIappmtmoney() != null ? e1.getIappmtmoney() : 0);
			}
		}
		// 查子表二
//		List<AmWorthAppmtFiasset> amWorthAppmtFiasset = amWorthAppmtFiassetMapper.findAllByIlinkno(e.getIndocno());
		List<AmWorthAppmtFiasset> amWorthAppmtFiasset = condition.getAddFiassetList();
		double zicheng = 0;
		double iprice = 0;
		// 几条资产信息(子表2的)
		int size2 = amWorthAppmtFiasset.size();
		double average = DoubleUtil.divide(count, size2, 2);
		for (AmWorthAppmtFiasset e2 : amWorthAppmtFiasset) {
			// 单价
			zicheng = DoubleUtil.add(zicheng, e2.getIprice() != null ? e2.getIprice() : 0);
		}
		for (AmWorthAppmtFiasset e3 : amWorthAppmtFiasset) {
			// 单价
			iprice = e3.getIprice() != null ? e3.getIprice() : 0;
			double divide = DoubleUtil.divide(iprice, zicheng, 2);
			double biliprice = DoubleUtil.mul(divide, a);
			// biliprice+average;
			e3.setIappmtmoney(DoubleUtil.add(biliprice , average));
		}
		condition.setAddFiassetList(amWorthAppmtFiasset);
		if (condition.getIndocno() == null){
			condition.setIndocno(1L);
		}
		return condition;
	}

}
