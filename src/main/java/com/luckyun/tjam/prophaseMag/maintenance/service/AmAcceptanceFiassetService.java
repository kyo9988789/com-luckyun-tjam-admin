package com.luckyun.tjam.prophaseMag.maintenance.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luckyun.base.provider.feign.BaseSysDeptProvider;
import com.luckyun.base.provider.feign.BaseSysMajorProvider;
import com.luckyun.base.provider.feign.BaseSysMsgProvider;
import com.luckyun.base.provider.feign.BaseSysUserProvider;
import com.luckyun.core.annotation.TransactionalException;
import com.luckyun.core.data.BaseMapper;
import com.luckyun.core.data.BaseService;
import com.luckyun.core.data.ExcelBatchReceiver;
import com.luckyun.core.data.ExcelBatchSender;
import com.luckyun.core.data.filter.AuthInfo;
import com.luckyun.core.km.keyhelper.IDGenerate;
import com.luckyun.core.tool.OssPathHelperUtils;
import com.luckyun.model.dept.SysDept;
import com.luckyun.model.major.SysMajor;
import com.luckyun.model.msg.SysMsg;
import com.luckyun.model.user.SysAccount;
import com.luckyun.tjam.prophaseMag.acceptance.common.DatadicKeys;
import com.luckyun.tjam.prophaseMag.acceptance.helper.BaseServiceHelperImpl;
import com.luckyun.tjam.prophaseMag.acceptance.mapper.InfTransferStateMapper;
import com.luckyun.tjam.prophaseMag.acceptance.model.InfTransferState;
import com.luckyun.tjam.prophaseMag.maintenance.mapper.AmAcceptanceFiassetMapper;
import com.luckyun.tjam.prophaseMag.maintenance.mapper.AmArcClassMapper;
import com.luckyun.tjam.prophaseMag.maintenance.model.AmAcceptanceFiasset;
import com.luckyun.tjam.prophaseMag.maintenance.model.AmArcClass;
import com.luckyun.tjam.prophaseMag.maintenance.param.AmAcceptanceFiassetParam;
import com.luckyun.tjam.prophaseMag.verification.mapper.AmVrfPlanDtFiassetMapper;
import com.luckyun.tjam.prophaseMag.verification.mapper.AmVrfPlanMapper;
import com.luckyun.tjam.prophaseMag.verification.model.AmVrfPlan;
import com.luckyun.tjam.prophaseMag.verification.model.AmVrfPlanDtFiasset;
import com.luckyun.tjam.prophaseMag.verification.param.AmVrfPlanParam;
import com.luckyun.tjam.prophaseMag.verification.service.AmVrfPlanService;

@Service
public class AmAcceptanceFiassetService extends BaseService<AmAcceptanceFiasset>{

	@Autowired
	private AmAcceptanceFiassetMapper amAcceptanceFiassetMapper;

	@Override
	public BaseMapper<AmAcceptanceFiasset> getMapper() {
		return amAcceptanceFiassetMapper;
	}

	@Autowired
	private BaseSysUserProvider baseSysUserProvider;

	@Autowired
	private BaseSysDeptProvider baseSysDeptProvider;

	@Autowired
	private BaseServiceHelperImpl baseServiceHelperImpl;

	@Autowired
	private BaseSysMajorProvider baseSysMajorProvider;

	@Autowired
	private OssPathHelperUtils ossPathHelperUtils;

	@Autowired
	private BaseSysMsgProvider baseSysMsgProvider;

	@Autowired
	private AmVrfPlanService amVrfPlanService;

	@Autowired
	private AmVrfPlanMapper amVrfPlanMapper;

	@Autowired
	private AmVrfPlanDtFiassetMapper amVrfPlanDtFiassetMapper;

	@Autowired
	private AmArcClassMapper amArcClassMapper;

	@Autowired
	private InfTransferStateMapper infTransferStateMapper;

	/**
	 *  查询所有
	 * @return
	 */
	@SuppressWarnings("unlikely-arg-type")
	public List<AmAcceptanceFiasset> findAll(AmAcceptanceFiassetParam condition){
		if (null != condition && condition.getIndocnos() != "" && condition.getIndocnos() != null){
			String[] split = condition.getIndocnos().split(",");
			List<Long> collect = Arrays.stream(split).map(e -> Long.parseLong(e.trim())).collect(Collectors.toList());
			condition.setIndocnoList(collect);
		}

		// 责任人版：通过用户主键 + 分配状态过滤(idiststatue != '' || 未核验 - 0)
		condition.setIcuruserid(getAuthInfo().getIndocno());

		/*
		 * // 管理员版：如果当前用户角色包含“资产管理员”，则不过滤数据 SysAccount sysAccount =
		 * this.baseSysUserProvider.findFSysUserByIndocno(getAuthInfo().getIndocno());
		 * if(null != sysAccount) { List<SysRole> sysRoles = sysAccount.getSysRoles();
		 * if(null != sysRoles && !sysRoles.isEmpty()) { for(SysRole e : sysRoles) {
		 * if(null != e.getIndocno() && !"".equals(e.getIndocno()) &&
		 * e.getIndocno().compareTo(62L) == 0) { condition.setIiscapadmin(1); } } } }
		 * if(null == condition.getIiscapadmin() ||
		 * "".equals(condition.getIiscapadmin())) { condition.setIiscapadmin(0); }
		 */

		List<AmAcceptanceFiasset> capAcEqList = this.amAcceptanceFiassetMapper.findAll(condition);
		for(AmAcceptanceFiasset e:capAcEqList){
			// 是否含有重要组成
			List<AmAcceptanceFiasset> sonInventoryList = this.amAcceptanceFiassetMapper.findSonByIparent(e.getSfcode());
			if(null != sonInventoryList && sonInventoryList.size() > 0) {
				e.setIhasSonInventory(1);	// 含有重要组成
			}else {
				e.setIhasSonInventory(0);	// 无重要组成
			}
			if(null != e.getIstrans() && !"".equals(e.getIstrans())) {
				e.setSstrans(baseServiceHelperImpl.getDatadicName(DatadicKeys.CAP_ACCEPTANCE_EQUIP_ISTRANS, e.getIstrans() != null ? e.getIstrans() : ""));
			}

			/**
			 * 是否已分配责任人
			 * 如果已分配责任人，并且责任人已同意，则：return -> 1
			 * else(未分配责任人 + 责任人未处理 + 责任人拒绝) ： return -> 0
			 * 		通过分配状态控制即可(idiststatus = 1 ：已同意)
			 */


			// 移交验收人
			if(null != e.getIcheckuserid() && !"".equals(e.getIcheckuserid())) {
				SysAccount sysAccount1 = this.baseSysUserProvider.findFSysUserByIndocno(e.getIcheckuserid());
				if(null != sysAccount1) {
					e.setScheckusernm(sysAccount1.getSname());
				}
			}

			// 是否重要组成
			e.setShasSonInventory(baseServiceHelperImpl.getDatadicName(DatadicKeys.CAP_ACCEPTANCE_EQUIP_IHASSONINVENTORY,
					e.getIhasSonInventory() != null ? e.getIhasSonInventory() : ""));

			// 管理部门
			if(null != e.getImanagedeptid() && !"".equals(e.getImanagedeptid())) {
				SysDept sysDept = this.baseSysDeptProvider.findByIndocno(e.getImanagedeptid());
				if(null != sysDept) {
					e.setSmanagedeptnm(sysDept.getSname());
				}
			}

			// 专业
			if(null != e.getImajorid() && !"".equals(e.getImajorid())) {
				SysMajor sysMajor = this.baseSysMajorProvider.findFMajorById(e.getImajorid());
				if(null != sysMajor) {
					e.setSmajornm(sysMajor.getSname());
				}
			}

			// 责任人
			if(null != e.getIdutyid() && !"".equals(e.getIdutyid())) {
				SysAccount sysAccount1 = this.baseSysUserProvider.findFSysUserByIndocno(e.getIdutyid());
				if(null != sysAccount1) {
					e.setSdutynm(sysAccount1.getSname());
				}
			}

			// 分配状态
			if(null != e.getIdiststatus() && !"".equals(e.getIdiststatus())) {
				e.setSdiststatusnm(baseServiceHelperImpl.getDatadicName(DatadicKeys.CAP_ACCEPTANCE_EQUIP_IDISTSTATUS,
						e.getIdiststatus() != null ? e.getIdiststatus() : ""));
			}

			// 所属线路
			if (null != e.getIlineid() && !"".equals(e.getIlineid())) {
				e.setSlinenm(baseServiceHelperImpl.getDatadicName(DatadicKeys.CAP_ACCEPTANCE_EQUIP_ILINEID,
						e.getIlineid() != null ? e.getIlineid() : ""));
			}

			// 分类名称
			if(null != e.getSeqclassno()) {
				List<AmArcClass> capArcClassList = this.amArcClassMapper.findBySclassno(e.getSeqclassno());
				if(null != capArcClassList && !capArcClassList.isEmpty()) {
					AmArcClass capArcClass = capArcClassList.get(0);
					e.setSclassstructurenm(capArcClass.getSclassnm());
				}
			}
			// 是否可生成核验计划
			if(null == e.getIdatastate() || (null != e.getIdatastate() && 0 == e.getIdatastate()) 
			|| (null != e.getIdatastate() && 1 == e.getIdatastate() && null != e.getIupdatestate() && !"".equals(e.getIupdatestate()))) {
				e.setIgenevrfstateid(1);
			}else {
				e.setIgenevrfstateid(0);
			}
			e.setSgenevrfstatenm(baseServiceHelperImpl.getDatadicName(DatadicKeys.CAP_ACCEPTANCE_EQUIP_ISCARD,
					e.getIgenevrfstateid() != null ? e.getIgenevrfstateid() : ""));
		}
		return capAcEqList;
	}

	/**
	 * 查询未被引用的主资产数据
	 * @return
	 */
	public List<AmAcceptanceFiasset> readAllHasNotQuoted(){
		List<AmAcceptanceFiasset> list = this.amAcceptanceFiassetMapper.readAllHasNotQuoted();
		list.forEach(e -> {
			// 管理部门
			if(null != e.getImanagedeptid()) {
				SysDept sysDept = this.baseSysDeptProvider.findByIndocno(e.getImanagedeptid());
				if(null != sysDept) {
					e.setSmanagedeptnm(sysDept.getSname());
				}
			}

			// 责任人
			if(null != e.getIdutyid()) {
				SysAccount sysAccount1 = this.baseSysUserProvider.findFSysUserByIndocno(e.getIdutyid());
				if(null != sysAccount1) {
					e.setSdutynm(sysAccount1.getSname());
				}
			}

			// 所属线路
			if (null != e.getIlineid()) {
				e.setSlinenm(baseServiceHelperImpl.getDatadicName(DatadicKeys.CAP_ACCEPTANCE_EQUIP_ILINEID,
						e.getIlineid() != null ? e.getIlineid() : ""));
			}
			
			// 分类名称
			if(null != e.getSeqclassno() && !"".equals(e.getSeqclassno())) {
				List<AmArcClass> capArcClassList = this.amArcClassMapper.findBySclassno(e.getSeqclassno());
				if(null != capArcClassList && !capArcClassList.isEmpty()) {
					AmArcClass capArcClass = capArcClassList.get(0);
					e.setSclassstructurenm(capArcClass.getSclassnm());
				}
			}
		});
		return list;
	}
	
	/**
	 *  查询明细
	 * @param condition
	 * @return
	 */
	@SuppressWarnings("unlikely-arg-type")
	public AmAcceptanceFiasset findOne(AmAcceptanceFiassetParam condition) {

		AmAcceptanceFiasset capAcEqInfo = this.amAcceptanceFiassetMapper.findOne(condition.getIndocno());

		if(null != capAcEqInfo) {
			// 是否外部系统
			if(null != capAcEqInfo.getSexternalid() && !"".equals(capAcEqInfo.getSexternalid())) {
				capAcEqInfo.setIsOutSys("是");
			}else {
				capAcEqInfo.setIsOutSys("否");
			}

			AuthInfo authInfo = getAuthInfo();

			// 图片地址
			if(null != capAcEqInfo.getSpath()) {
				capAcEqInfo.setNspath(ossPathHelperUtils.generateDownloadFileNoAuth(authInfo.getSloginid()
						, "tjam", capAcEqInfo.getSpath(), capAcEqInfo.getSname()));
			}

			// 组成清单
			if(null != capAcEqInfo.getSfcode() && !"".equals(capAcEqInfo.getSfcode())) {
				List<AmAcceptanceFiasset> inventoryList = this.amAcceptanceFiassetMapper.findSonByIparent(capAcEqInfo.getSfcode());

				if(null != inventoryList && inventoryList.size() > 0) {
					for(AmAcceptanceFiasset e : inventoryList) {
						// 核验状态
						if(null != e.getIstrans() && !"".equals(e.getIstrans())) {
							e.setSstrans(baseServiceHelperImpl.getDatadicName(DatadicKeys.CAP_ACCEPTANCE_EQUIP_ISTRANS,
									e.getIstrans() != null ? e.getIstrans() : ""));
						}
					}
				}

				capAcEqInfo.setInventoryList(inventoryList);
			}

			// 核验状态
			if(null != capAcEqInfo.getIstrans() && !"".equals(capAcEqInfo.getIstrans())) {
				capAcEqInfo.setSstrans(baseServiceHelperImpl.getDatadicName(DatadicKeys.CAP_ACCEPTANCE_EQUIP_ISTRANS,
						capAcEqInfo.getIstrans() != null ? capAcEqInfo.getIstrans() : ""));
			}

			// 状态变更人
			if(null != capAcEqInfo.getIchangeuserid() && !"".equals(capAcEqInfo.getIchangeuserid())) {
				SysAccount sysAccount = this.baseSysUserProvider.findFSysUserByIndocno(capAcEqInfo.getIchangeuserid());
				if(null != sysAccount) {
					capAcEqInfo.setSchangeusernm(sysAccount.getSname());
				}
			}

			// 管理部门
			if(null != capAcEqInfo.getImanagedeptid() && !"".equals(capAcEqInfo.getImanagedeptid())) {
				SysDept sysDept = this.baseSysDeptProvider.findByIndocno(capAcEqInfo.getImanagedeptid());
				if(null != sysDept) {
					capAcEqInfo.setSmanagedeptnm(sysDept.getSname());
				}
			}

			// 专业
			if(null != capAcEqInfo.getImajorid() && !"".equals(capAcEqInfo.getImajorid())) {
				SysMajor sysMajor = this.baseSysMajorProvider.findFMajorById(capAcEqInfo.getImajorid());
				if(null != sysMajor) {
					capAcEqInfo.setSmajornm(sysMajor.getSname());
				}
			}

			// 责任人
			if(null != capAcEqInfo.getIdutyid() && !"".equals(capAcEqInfo.getIdutyid())) {
				SysAccount sysAccount = this.baseSysUserProvider.findFSysUserByIndocno(capAcEqInfo.getIdutyid());
				if(null != sysAccount) {
					capAcEqInfo.setSdutynm(sysAccount.getSname());
				}
			}

			// 分配人
			if(null != capAcEqInfo.getIdistruserid() && !"".equals(capAcEqInfo.getIdistruserid())) {
				SysAccount sysAccount = this.baseSysUserProvider.findFSysUserByIndocno(capAcEqInfo.getIdistruserid());
				if(null != sysAccount) {
					capAcEqInfo.setSdistrusernm(sysAccount.getSname());
				}
			}

			// 移交验收人
			if(null != capAcEqInfo.getIcheckuserid() && !"".equals(capAcEqInfo.getIcheckuserid())) {
				SysAccount sysAccount = this.baseSysUserProvider.findFSysUserByIndocno(capAcEqInfo.getIcheckuserid());
				if(null != sysAccount) {
					capAcEqInfo.setScheckusernm(sysAccount.getSname());
				}
			}

			// 已打卡标签
			if(null != capAcEqInfo.getIscard() && !"".equals(capAcEqInfo.getIscard())) {
				capAcEqInfo.setSscard(baseServiceHelperImpl.getDatadicName(DatadicKeys.CAP_ACCEPTANCE_EQUIP_ISCARD,
						capAcEqInfo.getIscard() != null ? capAcEqInfo.getIscard() : ""));
			}

			// 数据来源
			if(null != capAcEqInfo.getIsource() && !"".equals(capAcEqInfo.getIsource())) {
				capAcEqInfo.setSsource(baseServiceHelperImpl.getDatadicName(DatadicKeys.CAP_ACCEPTANCE_EQUIP_ISOURCE,
						capAcEqInfo.getIsource() != null ? capAcEqInfo.getIsource() : ""));
			}

			// 数据接入
			if(null != capAcEqInfo.getIaccess() && !"".equals(capAcEqInfo.getIaccess())) {
				capAcEqInfo.setSaccess(baseServiceHelperImpl.getDatadicName(DatadicKeys.CAP_ACCEPTANCE_EQUIP_IACCESS,
						capAcEqInfo.getIaccess() != null ? capAcEqInfo.getIaccess() : ""));
			}

			if(null != capAcEqInfo.getIlineid() && !"".equals(capAcEqInfo.getIlineid())) {
				capAcEqInfo.setSlinenm(baseServiceHelperImpl.getDatadicName(DatadicKeys.CAP_ACCEPTANCE_EQUIP_ILINEID,
						capAcEqInfo.getIlineid() != null ? capAcEqInfo.getIlineid() : ""));
			}

			// 所属线路
			if (null != capAcEqInfo.getIlineid() && !"".equals(capAcEqInfo.getIlineid())) {
				capAcEqInfo.setSlinenm(baseServiceHelperImpl.getDatadicName(DatadicKeys.CAP_ACCEPTANCE_EQUIP_ILINEID,
						capAcEqInfo.getIlineid() != null ? capAcEqInfo.getIlineid() : ""));
			}

			// 分类名称
			if(null != capAcEqInfo.getSeqclassno()) {
				List<AmArcClass> capArcClassList = this.amArcClassMapper.findBySclassno(capAcEqInfo.getSeqclassno());
				if(null != capArcClassList && !capArcClassList.isEmpty()) {
					AmArcClass capArcClass = capArcClassList.get(0);
					capAcEqInfo.setSclassstructurenm(capArcClass.getSclassnm());
				}
			}

			// 核验记录后面反更新
		}

		return capAcEqInfo;
	}

	/**
	 * update
	 * @return
	 */
	@TransactionalException
	public AmAcceptanceFiasset updateEquip(AmAcceptanceFiasset entity) {

		super.update(entity);
		return entity;
	}

	/**
	 *  分配管理权限(可批量操作)
	 */
	@TransactionalException
	public void distResponsibility(AmAcceptanceFiassetParam condition) {
		List<AmAcceptanceFiasset> distList = condition.getDistList();
		List<AmAcceptanceFiasset> rtnList = new ArrayList<>();
		AuthInfo authInfo = getAuthInfo();
		List<SysAccount> reservices = new ArrayList<>();
		SysAccount sysAccount = new SysAccount();
		sysAccount.setIndocno(condition.getIdutyid());
		reservices.add(sysAccount);
		if(distList!=null && !distList.isEmpty()) {
			for(AmAcceptanceFiasset e : distList) {
				AmAcceptanceFiasset capAcEqInfo = this.amAcceptanceFiassetMapper.findOne(e.getIndocno());
				if(capAcEqInfo.getImessageid() != null) {
					baseSysMsgProvider.readFSysMsg(capAcEqInfo.getImessageid(), capAcEqInfo.getIdutyid(), 0);
				}

				capAcEqInfo.setImanagedeptid(condition.getImanagedeptid());	// 管理部门
				capAcEqInfo.setImajorid(condition.getImajorid());				// 专业
				capAcEqInfo.setIdutyid(condition.getIdutyid());					//责任人
				capAcEqInfo.setIdistruserid(authInfo.getIndocno());
				capAcEqInfo.setDdisttime(new Date());

				// 反更新分配状态(0.待处理；1.同意；2.拒绝)
				capAcEqInfo.setIdiststatus(0);

				// 连同组成部分一起处理
				if(null != capAcEqInfo.getSfcode() && !"".equals(capAcEqInfo.getSfcode())) {
					List<AmAcceptanceFiasset> sonList = this.amAcceptanceFiassetMapper.findSonByIparent(capAcEqInfo.getSfcode());
					if(null != sonList && sonList.size() > 0) {
						sonList.forEach(e1 -> {
							e1.setImanagedeptid(condition.getImanagedeptid());	// 管理部门
							e1.setIdutyid(condition.getIdutyid());				//责任人
							e1.setIdistruserid(authInfo.getIndocno());
							e1.setDdisttime(new Date());
							e1.setIdiststatus(0);
							super.update(e1);
						});
					}
				}
				super.update(capAcEqInfo);
				rtnList.add(capAcEqInfo);
			}
			
			// 一次分配多条数据，消息聚合
			generateMsg(rtnList,condition.getIdutyid());
		}
	}

	/**
	 * 一次分配多条数据，消息聚合
	 */
	@TransactionalException
	private void generateMsg(List<AmAcceptanceFiasset> rtnList,Long idutyid) {
		AuthInfo authInfo = getAuthInfo();
		List<SysAccount> reservices = new ArrayList<>();
		SysAccount sysAccount = new SysAccount();
		sysAccount.setIndocno(idutyid);
		reservices.add(sysAccount);
		
		Long imessageid = handleAddMsg(null,reservices,authInfo.getIndocno(),rtnList.size());
		rtnList.forEach(e -> {
			e.setImessageid(imessageid);
			super.update(e);
		});
	}
	
	/**
	 *  修改移交状态，联动更新组成部分的“核验状态”
	 */
	@TransactionalException
	public AmAcceptanceFiasset updateTransferStatus(AmAcceptanceFiassetParam condition) {
		List<AmAcceptanceFiasset> transferList = condition.getTransferList();
		transferList.forEach(e -> {
			e.setIstrans(condition.getIstrans());
			e.setScon(condition.getScon());
			e.setIchangeuserid(getAuthInfo().getIndocno());	// 状态变更人
			e.setDchangetime(new Date());					// 状态变更时间
			super.update(e);
			
			if(null != e.getSfcode() && !"".equals(e.getSfcode())) {
				List<AmAcceptanceFiasset> sonList = this.amAcceptanceFiassetMapper.findSonByIparent(e.getSfcode());
				if(sonList.size() > 0) {
					sonList.forEach(e1 -> {
						e1.setIstrans(condition.getIstrans());
						super.update(e1);
					});
				}
			}
			
		});
		return null;
	}

	/**
	 *  获取除当前条及子资产以外的所有资产编号
	 * @param sfcode
	 * @return
	 */
	public List<AmAcceptanceFiasset> findParentAssetnumIgnoreNeed(String sfcode ){

		List<AmAcceptanceFiasset> parentCapAcEqList = this.amAcceptanceFiassetMapper.findParentAssetnumIgnoreNeed(sfcode );
		return parentCapAcEqList;
	}


	/**
	 * 责任人同意或拒绝
	 * @param condition
	 */
	@TransactionalException
	public void agreeOrRefuse(AmAcceptanceFiassetParam condition) {
		List<AmAcceptanceFiasset> oprDataList = condition.getOprDataList();
		for(AmAcceptanceFiasset e : oprDataList) {
			AmAcceptanceFiasset info = this.amAcceptanceFiassetMapper.findOne(e.getIndocno());
			if(null != info.getIdiststatus() && 1 == info.getIdiststatus()) {	// 状态为"已同意"，略过
				AuthInfo authInfo = getAuthInfo();
				baseSysMsgProvider.readFSysMsg(info.getImessageid(), authInfo.getIndocno(), 0);
				continue;
			}
			if(null != info) {
				info.setIdiststatus(condition.getIdiststatus());
				super.update(info);
				AuthInfo authInfo = getAuthInfo();
				baseSysMsgProvider.readFSysMsg(info.getImessageid(), authInfo.getIndocno(), 0);
				
				// 连同组成部分一起处理
				if(null != info.getSfcode() && !"".equals(info.getSfcode())) {
					List<AmAcceptanceFiasset> sonList = this.amAcceptanceFiassetMapper.findSonByIparent(info.getSfcode());
					if(null != sonList && sonList.size() > 0) {
						sonList.forEach(e1 -> {
							e1.setIdiststatus(condition.getIdiststatus());
							super.update(e1);
						});
					}
				}
			}
		}
	}

	/**
	 *   生成设备设施核验计划
	 *   实物资产核验计划编号：TJAM-SWZC-YYYYMMDD-01
	 实物资产核验计划名称：实物资产核验-YYYY年M月份-01；
	 * @param condition
	 */
	@SuppressWarnings("unlikely-arg-type")
	@TransactionalException
	public String generateVerification(AmAcceptanceFiassetParam condition) {
		List<AmAcceptanceFiasset> dataList = condition.getGenerateDataList();

		String res = "";

		if(null != dataList && dataList.size() > 0) {
			AmVrfPlan plan = new AmVrfPlan();
			Long planIndocno = IDGenerate.getKey(plan);
			plan.setIndocno(planIndocno);

			// 生成最大计划名称
			String splannm = this.generateSplannm();
			plan.setSplannm(splannm);

			// 生成最大计划编号
			String splanno = this.generateSplanno();
			plan.setSplanno(splanno);

			// 当前年度
			int curYear = Calendar.getInstance().get(Calendar.YEAR);
			plan.setIvrfyear(curYear);

			// 下发状态：默认未下发
			plan.setIplanstate(0);

			// 设备设施计划(区分计划类型：设备设施/备品备件)
			plan.setIplantype(0);

			// 计划状态，未下发
			plan.setIplantype(0);

			plan.setIdel(0);
			plan.setSregid(getAuthInfo().getIndocno());
			plan.setDregt(new Date());

			// 核验部门、创建部门
			SysAccount sysAccount = this.baseSysUserProvider.findFSysUserByIndocno(getAuthInfo().getIndocno());
			if(null != sysAccount && null != sysAccount.getSysUserInfo()) {
				Long ideptid = sysAccount.getSysUserInfo().getIdeptid();
				plan.setIcreatedeptid(ideptid);
			}

			// 核验计划明细生成方式：0.实物资产模块生成;1.自增
			plan.setIgeneratetype(0);

			this.amVrfPlanService.insert(plan);

			res = splanno;

			/**
			 *  生成核验计划明细
			 */
			for(AmAcceptanceFiasset e : dataList) {
				AmAcceptanceFiasset info = this.amAcceptanceFiassetMapper.findOne(e.getIndocno());
				if(null != info && null != info.getSfcode()) {
					// 查找计划详情的组成部门，并一起带过来
					List<AmAcceptanceFiasset> sonList = this.amAcceptanceFiassetMapper.findSonByIparent(info.getSfcode());
					if(null != sonList && sonList.size() > 0) {
						for(AmAcceptanceFiasset info2 : sonList) {
							generateSonData(info2,null);
						}
					}

					AmVrfPlanDtFiasset planDetail = new AmVrfPlanDtFiasset();
					planDetail.setIndocno(IDGenerate.getKey(planDetail));
					planDetail.setIlinkno(planIndocno);
					planDetail.setIamaccfiassetid(info.getIndocno());	// 实物资产主键
					planDetail.setSfcode(info.getSfcode());
					planDetail.setSfname(info.getSfname());
					planDetail.setSeqclassno(info.getSeqclassno());
					planDetail.setIstrans(0);	// 默认未核验
					planDetail.setSmajornm(info.getSmajornm());
					planDetail.setSlinestage(info.getSlinestage());
					planDetail.setSsuppliernm(info.getSsuppliernm());
					planDetail.setSproductnmAct(info.getSproductnmAct());
					planDetail.setSbrandAct(info.getSbrandAct());
					planDetail.setSspecAct(info.getSspecAct());
					planDetail.setSlinestartpost(info.getSlinestartpost());
					planDetail.setSlineendpost(info.getSlineendpost());
					planDetail.setIprice(info.getIprice());
					planDetail.setSestimatedlife(info.getSestimatedlife());
					planDetail.setSparentno(info.getSparentno());
					planDetail.setSownerunit(info.getSownerunit());
					planDetail.setIsource(info.getIsource());
					planDetail.setSexternalid(info.getSexternalid());
					planDetail.setSnoteOne(info.getSnoteOne());
					planDetail.setSnoteTwo(info.getSnoteTwo());
					planDetail.setSnoteThree(info.getSnoteThree());
					planDetail.setIdel(0);
					planDetail.setSregid(getAuthInfo().getIndocno());
					planDetail.setDregt(new Date());
					planDetail.setIgeneratetype(0);		// 核验计划明细生成方式：0.实物资产模块生成;1.自增

					this.amVrfPlanDtFiassetMapper.insert(planDetail);

					// 反编译设备设施资产状态为"已生成核验计划"
					info.setIdatastate(1);
					super.update(info);
					
					// 如果此条数据已经生成过核验计划，但是被第三方修改后，再次生成核验计划，则需要将“已修改状态”置空，表示这条数据被第三方修改后已经生成了核验计划，不可以再次生成
					if(null != info.getIupdatestate() && !"".equals(info.getIupdatestate())) {
						this.amAcceptanceFiassetMapper.updateIupdateState(info.getIndocno());
					}
				}
			}
		}
		return res;
	}

	/**
	 *  将组成部分的维护数据也生成计划，但是不赋予主键(不需要与主数据关联)
	 * @param info2
	 */
	@SuppressWarnings("unlikely-arg-type")
	public void generateSonData(AmAcceptanceFiasset info2, Long ilinkno) {
		AmVrfPlanDtFiasset planDetail = new AmVrfPlanDtFiasset();
		planDetail.setIndocno(IDGenerate.getKey(planDetail));
		planDetail.setIamaccfiassetid(info2.getIndocno());
		planDetail.setSfcode(info2.getSfcode());
		planDetail.setSeqclassno(info2.getSeqclassno());
		planDetail.setSfname(info2.getSfname());
		planDetail.setIstrans(0);	// 默认未核验
		planDetail.setSmajornm(info2.getSmajornm());
		planDetail.setSlinestage(info2.getSlinestage());
		planDetail.setSsuppliernm(info2.getSsuppliernm());
		planDetail.setSproductnmAct(info2.getSproductnmAct());
		planDetail.setSbrandAct(info2.getSbrandAct());
		planDetail.setSspecAct(info2.getSspecAct());
		planDetail.setSlinestartpost(info2.getSlinestartpost());
		planDetail.setSlineendpost(info2.getSlineendpost());
		planDetail.setIprice(info2.getIprice());
		planDetail.setSestimatedlife(info2.getSestimatedlife());
		planDetail.setSparentno(info2.getSparentno());
		planDetail.setSownerunit(info2.getSownerunit());
		planDetail.setIsource(info2.getIsource());
		planDetail.setSexternalid(info2.getSexternalid());
		planDetail.setSnoteOne(info2.getSnoteOne());
		planDetail.setSnoteTwo(info2.getSnoteTwo());
		planDetail.setSnoteThree(info2.getSnoteThree());
		planDetail.setIdel(0);
		planDetail.setSregid(getAuthInfo().getIndocno());
		planDetail.setDregt(new Date());
		planDetail.setIlinkno(ilinkno);

		this.amVrfPlanDtFiassetMapper.insert(planDetail);

		// 反编译设备设施资产状态为"已生成核验计划"
		info2.setIdatastate(1);
		super.update(info2);
		
		if(null != info2.getIupdatestate() && !"".equals(info2.getIupdatestate())) {
			this.amAcceptanceFiassetMapper.updateIupdateState(info2.getIndocno());
		}
	}

	/**
	 *  生成核验计划名称
	 * @return
	 */
	private String generateSplannm() {
		int curYear = Calendar.getInstance().get(Calendar.YEAR);
		int curMonth = Calendar.getInstance().get(Calendar.MONTH);
		String splannm = "实物资产核验" + "-" + curYear + "年" + curMonth + "月份";
		AmVrfPlanParam condition = new AmVrfPlanParam();
		condition.setSplannm(splannm);
		condition.setIplantype(0);
		List<AmVrfPlan> list = this.amVrfPlanMapper.findMaxSplannm(condition);
		if(null == list || list.size() == 0) {
			splannm = splannm + "-" + "001";
		}else if(null != list && list.size() > 0){
			AmVrfPlan maxPlanInfo = list.get(0);
			String maxSplannm = maxPlanInfo.getSplannm();
			String lastString = maxSplannm.substring(maxSplannm.lastIndexOf("-")+1,maxSplannm.length());
			Integer lastInteger = Integer.valueOf(lastString) + 1;
			if(lastInteger.toString().length() == 1) {
				splannm = splannm + "-00" + lastInteger;
			}else if(lastInteger.toString().length() == 2) {
				splannm = splannm + "-0" + lastInteger;
			}else if(lastInteger.toString().length() == 3) {
				splannm = splannm + "-" + lastInteger;
			}
		}
		return splannm;
	}

	/**
	 *    生成核验计划编号
	 *  @return
	 */
	private String generateSplanno() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String date = sdf.format(new Date());
		String splanno = "TJAM-SWZC-" + date;
		AmVrfPlanParam condition = new AmVrfPlanParam();
		condition.setSplanno(splanno);
		condition.setIplantype(0);
		List<AmVrfPlan> list = this.amVrfPlanMapper.findMaxSplanno(condition);
		if(null == list || list.size() == 0) {
			splanno = splanno + "-" + "001";
		}else if(null != list && list.size() > 0){
			AmVrfPlan maxPlanInfo = list.get(0);
			String maxSplanno = maxPlanInfo.getSplanno();
			String lastString = maxSplanno.substring(maxSplanno.lastIndexOf("-")+1,maxSplanno.length());
			Integer lastInteger = Integer.valueOf(lastString) + 1;
			if(lastInteger.toString().length() == 1) {
				splanno = splanno + "-00" + lastInteger;
			}else if(lastInteger.toString().length() == 2) {
				splanno = splanno + "-0" + lastInteger;
			}else if(lastInteger.toString().length() == 3) {
				splanno = splanno + "-" + lastInteger;
			}
		}
		return splanno;
	}
	
	public Long handleAddMsg(Long indocno,List<SysAccount> reservices,Long userid,int dataCount) {
		SysMsg sysMsg = new SysMsg();
		//AmAcceptanceFiasset capAcceptanceEquip = amAcceptanceFiassetMapper.findOne(indocno);
		sysMsg.setStitle("您有" + dataCount + "条实物资产移交数据待处理");
		sysMsg.setItype(0);
		sysMsg.setSmsg("里长");
		sysMsg.setImsgType(0);
		//sysMsg.setIfrombillid(capAcceptanceEquip.getIndocno());
		sysMsg.setSfrombillnm("某块一");
		sysMsg.setSfrombillurl("/prophasemag/maintenance/equipresponsibility");
		sysMsg.setIstate(0);
		sysMsg.setSregid(userid);
		sysMsg.setSregnm("张天");
		sysMsg.setDregt(new Date());
		sysMsg.setScontent("消息");
		sysMsg.setSprojectno("base");
		sysMsg.setUserCount(20);
		sysMsg.setSendAccounts(reservices);
		sysMsg = baseSysMsgProvider.addFSysMsg(sysMsg);
		return sysMsg.getIndocno();
	}

	/**
	 * 查询所有"未生成核验计划明细"且"不是组成部分"的设备设施数据
	 * @param condition
	 * @return
	 */
	@SuppressWarnings("unlikely-arg-type")
	public List<AmAcceptanceFiasset> findAllHasNotGreVerification(AmAcceptanceFiassetParam condition){
		List<AmAcceptanceFiasset> list = this.amAcceptanceFiassetMapper.findAllHasNotGreVerification(condition);
		if(null != list && !list.isEmpty()) {
			for(AmAcceptanceFiasset e : list) {
				// 核验状态
				if(null != e.getIstrans() && !"".equals(e.getIstrans())) {
					e.setSstrans(baseServiceHelperImpl.getDatadicName(DatadicKeys.CAP_ACCEPTANCE_EQUIP_ISTRANS,
							e.getIstrans() != null ? e.getIstrans() : ""));
				}

				// 分类名称
				if(null != e.getSeqclassno()) {
					List<AmArcClass> capArcClassList = this.amArcClassMapper.findBySclassno(e.getSeqclassno());
					if(null != capArcClassList && !capArcClassList.isEmpty()) {
						AmArcClass capArcClass = capArcClassList.get(0);
						e.setSclassstructurenm(capArcClass.getSclassnm());
					}
				}
			}
		}
		return list;
	}

	/**
	 *  查找组成部分(son.sparent = sassetnum)
	 * @param sassetnum
	 * @return
	 */
	public List<AmAcceptanceFiasset> findSonByIparent(String sassetnum){
		List<AmAcceptanceFiasset> list = this.amAcceptanceFiassetMapper.findSonByIparent(sassetnum);
		
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

	@TransactionalException
	public Integer syncInfTran(){
		this.insertAllDealtWith();
		this.updateAllDealtWith();
		return 1;
	}

	/**
	  *  把所有的实物资产数据(不仅包含同意 + 拒绝的)插入到inf_transfer_state表I(做修改操作)
	 * */
	public void updateAllDealtWith(){
		List<AmAcceptanceFiasset> updateDetail = this.amAcceptanceFiassetMapper.findUpAllDealtWith();
		Map<Long,InfTransferState> InfTranMap = new HashMap<>();
		List<InfTransferState> updateTransfer = infTransferStateMapper.findAll();
		updateTransfer.forEach(e->{
			InfTranMap.put(e.getIlinkno(),e);
		});
		updateDetail.forEach(a->{
			InfTransferState infTransferState = InfTranMap.get(a.getIndocno());
			if (null != infTransferState){
				setValue(a,infTransferState);
				infTransferStateMapper.update(infTransferState);
			}
		});
	}

	/**
	 * 把所有的实物资产数据(不仅包含同意 + 拒绝的)插入到inf_transfer_state表I(做添加操作)
	 * */
	public void insertAllDealtWith(){
		List<AmAcceptanceFiasset> AddDetail = this.amAcceptanceFiassetMapper.findAllDealtWith();
		AddDetail.forEach(e->{
			InfTransferState infTransferState = new InfTransferState();
			setValue(e,infTransferState);
			infTransferStateMapper.insert(infTransferState);
			AmAcceptanceFiasset cap = new AmAcceptanceFiasset();
			cap.setIndocno(e.getIndocno());
			cap.setIuploadstate(1);
			amAcceptanceFiassetMapper.update(cap);
		});
	}

	@SuppressWarnings("unlikely-arg-type")
	public void setValue(AmAcceptanceFiasset capAcceptanceEquip,InfTransferState infTransferState){
		infTransferState.setIlinkno(capAcceptanceEquip.getIndocno());
		infTransferState.setSfname(capAcceptanceEquip.getSfname());
		infTransferState.setSfcode(capAcceptanceEquip.getSfcode());
		infTransferState.setSexternalid(capAcceptanceEquip.getSexternalid());
		if(null != capAcceptanceEquip.getIstrans()) {
			infTransferState.setSstransnm(baseServiceHelperImpl.getDatadicName(DatadicKeys.CAP_ACCEPTANCE_EQUIP_ISTRANS,
					capAcceptanceEquip.getIstrans() != null ? capAcceptanceEquip.getIstrans() : ""));
		}
		// 所属线路6
		if (null != capAcceptanceEquip.getIlineid() && capAcceptanceEquip.getIlineid()==6) {
			String ilineid1 = "0" + capAcceptanceEquip.getIlineid();
			infTransferState.setSlinenm(ilineid1);
		}else{
			infTransferState.setSlinenm(null != capAcceptanceEquip.getIlineid() && !"".equals(capAcceptanceEquip.getIlineid()) ? capAcceptanceEquip.getIlineid().toString() : "");
		}

		infTransferState.setDvrf(capAcceptanceEquip.getDchangetime());
		infTransferState.setSdescribe(capAcceptanceEquip.getSdescribe());
		infTransferState.setSbrandAct(capAcceptanceEquip.getSbrandAct());
		infTransferState.setSinslocationnoAct(capAcceptanceEquip.getSinslocationnoAct());
		infTransferState.setSlineendpost(capAcceptanceEquip.getSlineendpost());
		infTransferState.setSlinestartpost(capAcceptanceEquip.getSlinestartpost());
		infTransferState.setSproductnmAct(capAcceptanceEquip.getSproductnmAct());
		infTransferState.setSspecAct(capAcceptanceEquip.getSspecAct());
		infTransferState.setIqty(String.valueOf(capAcceptanceEquip.getIqtyAct()));
		infTransferState.setSrepairunitnm(capAcceptanceEquip.getSrepairunitnm());
		infTransferState.setUnreceivedcause(capAcceptanceEquip.getSunreceivedcause());
		infTransferState.setIsource(capAcceptanceEquip.getIsource());
		infTransferState.setSlinestage(capAcceptanceEquip.getSlinestage());
		infTransferState.setStransferno(capAcceptanceEquip.getStransferno());
		infTransferState.setSrepairdeptnm(capAcceptanceEquip.getSrepairdeptnm());
		infTransferState.setItransferid(capAcceptanceEquip.getItransferid());
		infTransferState.setIdel(capAcceptanceEquip.getIdel());
		infTransferState.setDregt(new Date());
	}

	public Date simpledate(String date){
		if (date == null){
			return null;
		}
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Double doubleCast(String str){
		if (null != str && "" != str){
			try {
				Double dou = Double.valueOf(str);
				return dou;
			} catch (NumberFormatException e) {
				e.printStackTrace();
				return null;
			}
		}else {
			return null;
		}
	}

	public Integer intCast(String str){
		if (null != str && "" != str){
			try {

				Integer integer = Integer.valueOf(str);
				return integer;
			} catch (NumberFormatException e) {
				e.printStackTrace();
				return null;
			}
		}else {
			return null;
		}
	}

	/**
	 *  实物资产的导入
	 *
	 * @param batchReceiver
	 */
	@TransactionalException
	public ExcelBatchSender importSendCheck(ExcelBatchReceiver<AmAcceptanceFiasset> batchReceiver) {
		String[] sstrans = {"未核验","同意接收","拒绝接收"};
		String[] slinenms = {"大兴机场线","公司本部"};
		ExcelBatchSender batchSender = new ExcelBatchSender(1);
		List<AmAcceptanceFiasset> content = batchReceiver.getContent();
		List<ExcelBatchSender.ExcelBatchSenderError> batchSenderErrorIndexs = new LinkedList<>();
		for (int i = 0; i < content.size(); i++) {
			AmAcceptanceFiasset amAcceptanceFiasset = content.get(i);
			if (null == amAcceptanceFiasset
					|| amAcceptanceFiasset.getSfname() == null || amAcceptanceFiasset.getSfname() == ""
					|| amAcceptanceFiasset.getSfcode() == null || amAcceptanceFiasset.getSfcode() == ""
					|| amAcceptanceFiasset.getSstrans() == null || amAcceptanceFiasset.getSstrans() == "" || !Arrays.asList(sstrans).contains(amAcceptanceFiasset.getSstrans())
					|| amAcceptanceFiasset.getSlinenm() == null || amAcceptanceFiasset.getSlinenm() == "" || !Arrays.asList(slinenms).contains(amAcceptanceFiasset.getSlinenm())
					|| amAcceptanceFiasset.getSlinestartpost() == null || amAcceptanceFiasset.getSlinestartpost() == ""
					|| amAcceptanceFiasset.getSspecAct() == null || amAcceptanceFiasset.getSspecAct() == ""
					|| amAcceptanceFiasset.getIqtyAct() == null || amAcceptanceFiasset.getIqtyAct() == ""
					){
				addErrorIndex(batchSender, batchSenderErrorIndexs, i);
			}else {
				try {
					switch (amAcceptanceFiasset.getSlinenm()){
						case "大兴机场线":
							amAcceptanceFiasset.setIlineid(88);
							amAcceptanceFiasset.setSlinenm(null);
							break;
						case "公司本部":
							amAcceptanceFiasset.setIlineid(0);
							amAcceptanceFiasset.setSlinenm(null);
							break;
					}
					switch (amAcceptanceFiasset.getSstrans()){
						case "未核验":
							amAcceptanceFiasset.setIstrans(0);
							amAcceptanceFiasset.setSstrans(null);
							break;
						case "同意接收":
							amAcceptanceFiasset.setIstrans(1);
							amAcceptanceFiasset.setSstrans(null);
							break;
						case "拒绝接收":
							amAcceptanceFiasset.setIstrans(2);
							amAcceptanceFiasset.setSstrans(null);
							break;
					}
					amAcceptanceFiassetMapper.insert(amAcceptanceFiasset);
				} catch (Exception e) {
					addErrorIndex(batchSender, batchSenderErrorIndexs, i);
				}
			}
		}
		batchSender.setBatchErrorIndex(batchSenderErrorIndexs);
		return batchSender;
	}
	/**
	 *  获取导入失败数据
	 * @param batchSender
	 * @param batchSenderErrorIndexs
	 * @param i
	 * @return
	 */
	private List<ExcelBatchSender.ExcelBatchSenderError> addErrorIndex(ExcelBatchSender batchSender,
																	   List<ExcelBatchSender.ExcelBatchSenderError> batchSenderErrorIndexs, Integer i) {
		ExcelBatchSender.ExcelBatchSenderError batchSenderError = batchSender.new ExcelBatchSenderError();
		batchSenderError.setIndex(i);
		batchSenderErrorIndexs.add(batchSenderError);
		return batchSenderErrorIndexs;
	}

}
