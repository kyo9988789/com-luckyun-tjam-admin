package com.luckyun.tjam.prophaseMag.maintenance.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
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
import com.luckyun.tjam.prophaseMag.maintenance.mapper.AmAcceptanceSpareMapper;
import com.luckyun.tjam.prophaseMag.maintenance.model.AmAcceptanceSpare;
import com.luckyun.tjam.prophaseMag.maintenance.param.AmAcceptanceSpareParam;
import com.luckyun.tjam.prophaseMag.verification.mapper.AmVrfPlanDtSpareMapper;
import com.luckyun.tjam.prophaseMag.verification.mapper.AmVrfPlanMapper;
import com.luckyun.tjam.prophaseMag.verification.model.AmVrfPlan;
import com.luckyun.tjam.prophaseMag.verification.model.AmVrfPlanDtSpare;
import com.luckyun.tjam.prophaseMag.verification.param.AmVrfPlanParam;
import com.luckyun.tjam.prophaseMag.verification.service.AmVrfPlanService;

@Service
public class AmAcceptanceSpareService extends BaseService<AmAcceptanceSpare>{

	@Autowired
	private AmAcceptanceSpareMapper amAcceptanceSpareMapper;

	@Override
	public BaseMapper<AmAcceptanceSpare> getMapper() {
		return amAcceptanceSpareMapper;
	}

	@Autowired
	private BaseServiceHelperImpl baseServiceHelperImpl;

	@Autowired
	private BaseSysUserProvider baseSysUserProvider;

	@Autowired
	private BaseSysDeptProvider baseSysDeptProvider;

	@Autowired
	private BaseSysMajorProvider baseSysMajorProvider;

	@Autowired
	private OssPathHelperUtils ossPathHelperUtils;

	@Autowired
	private BaseSysMsgProvider baseSysMsgProvider;

	@Autowired
	private AmVrfPlanMapper amVrfPlanMapper;

	@Autowired
	private AmVrfPlanService amVrfPlanService;

	@Autowired
	private AmVrfPlanDtSpareMapper amVrfPlanDtSpareMapper;

	/**
	 * 查询所有
	 * @return
	 */
	@SuppressWarnings("unlikely-arg-type")
	public List<AmAcceptanceSpare> findAll(AmAcceptanceSpareParam condition){
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

		List<AmAcceptanceSpare> capAcSpList = this.amAcceptanceSpareMapper.findAll(condition);
		for(AmAcceptanceSpare e : capAcSpList) {

			// 核验状态
			if (null != e.getIstrans() && !"".equals(e.getIstrans())) {
				e.setSstrans(baseServiceHelperImpl.getDatadicName(DatadicKeys.CAP_ACCEPTANCE_EQUIP_ISTRANS,
						e.getIstrans() != null ? e.getIstrans() : ""));
			}

			if("门到位开关组件13".equals(e.getSsparenm())) {
				System.out.println(123);
			}
			
			/**
			 * 是否已分配责任人 如果已分配责任人，并且责任人已同意，则：return -> 1 else(未分配责任人 + 责任人未处理 + 责任人拒绝) ：
			 * return -> 0 通过分配状态控制即可(idiststatus = 1 ：已同意)
			 */

			// 所属线路
			if (null != e.getIlineid() && !"".equals(e.getIlineid())) {
				e.setSlinenm(baseServiceHelperImpl.getDatadicName(DatadicKeys.CAP_ACCEPTANCE_EQUIP_ILINEID,
						e.getIlineid() != null ? e.getIlineid() : ""));
			}

			// 管理部门
			if (null != e.getImanagedeptid() && !"".equals(e.getImanagedeptid())) {
				SysDept sysDept = this.baseSysDeptProvider.findByIndocno(e.getImanagedeptid());
				if(null != sysDept) {
					e.setSmanagedeptnm(sysDept.getSname());
				}
			}

			// 责任人
			if (null != e.getIdutyid() && !"".equals(e.getIdutyid())) {
				SysAccount sysAccount1 = this.baseSysUserProvider.findFSysUserByIndocno(e.getIdutyid());
				if(null != sysAccount1) {
					e.setSdutynm(sysAccount1.getSname());
				}
			}

			// 分配状态
			if (null != e.getIdiststatus() && !"".equals(e.getIdiststatus())) {
				e.setSdiststatusnm(baseServiceHelperImpl.getDatadicName(DatadicKeys.CAP_ACCEPTANCE_EQUIP_IDISTSTATUS,
						e.getIdiststatus() != null ? e.getIdiststatus() : ""));
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
		return capAcSpList;
	}

	/**
	 * 查询明细
	 * @param condition
	 * @return
	 */
	@SuppressWarnings("unlikely-arg-type")
	public AmAcceptanceSpare findOne(AmAcceptanceSpareParam condition) {
		AmAcceptanceSpare capAcSpInfo = this.amAcceptanceSpareMapper.findOne(condition.getIndocno());

		AuthInfo authInfo = getAuthInfo();
		// 图片地址
		if(null != capAcSpInfo.getSpath()) {
			capAcSpInfo.setNspath(ossPathHelperUtils.generateDownloadFileNoAuth(authInfo.getSloginid()
					, "tjam", capAcSpInfo.getSpath(), capAcSpInfo.getSname()));
		}

		// 核验状态
		if (null != capAcSpInfo.getIstrans() && !"".equals(capAcSpInfo.getIstrans())) {
			capAcSpInfo.setSstrans(baseServiceHelperImpl.getDatadicName(DatadicKeys.CAP_ACCEPTANCE_EQUIP_ISTRANS,
					capAcSpInfo.getIstrans() != null ? capAcSpInfo.getIstrans() : ""));
		}

		/**
		 * 是否已分配责任人 如果已分配责任人，并且责任人已同意，则：return -> 1 else(未分配责任人 + 责任人未处理 + 责任人拒绝) ：
		 * return -> 0 通过分配状态控制即可(idiststatus = 1 ：已同意)
		 */

		// 所属线路
		if (null != capAcSpInfo.getIlineid() && !"".equals(capAcSpInfo.getIlineid())) {
			capAcSpInfo.setSlinenm(baseServiceHelperImpl.getDatadicName(DatadicKeys.CAP_ACCEPTANCE_EQUIP_ILINEID,
					capAcSpInfo.getIlineid() != null ? capAcSpInfo.getIlineid() : ""));
		}

		// 是否外部系统
		if(null != capAcSpInfo.getSexternalid() && !"".equals(capAcSpInfo.getSexternalid())) {
			capAcSpInfo.setIsOutSys("是");
		}else {
			capAcSpInfo.setIsOutSys("否");
		}

		// 状态变更人
		if(null != capAcSpInfo.getIchangeuserid() && !"".equals(capAcSpInfo.getIchangeuserid())) {
			SysAccount sysAccount = this.baseSysUserProvider.findFSysUserByIndocno(capAcSpInfo.getIchangeuserid());
			if(null != sysAccount) {
				capAcSpInfo.setSchangeusernm(sysAccount.getSname());
			}
		}

		// 管理部门
		if (null != capAcSpInfo.getImanagedeptid() && !"".equals(capAcSpInfo.getImanagedeptid())) {
			SysDept sysDept = this.baseSysDeptProvider.findByIndocno(capAcSpInfo.getImanagedeptid());
			if(null != sysDept) {
				capAcSpInfo.setSmanagedeptnm(sysDept.getSname());
			}
		}

		// 专业
		if(null != capAcSpInfo.getImajorid() && !"".equals(capAcSpInfo.getImajorid())) {
			SysMajor sysMajor = this.baseSysMajorProvider.findFMajorById(capAcSpInfo.getImajorid());
			if(null != sysMajor) {
				capAcSpInfo.setSmajornm(sysMajor.getSname());
			}
		}

		// 责任人
		if(null != capAcSpInfo.getIdutyid() && !"".equals(capAcSpInfo.getIdutyid())) {
			SysAccount sysAccount = this.baseSysUserProvider.findFSysUserByIndocno(capAcSpInfo.getIcheckuserid());
			if(null != sysAccount) {
				capAcSpInfo.setSdutynm(sysAccount.getSname());
			}
		}

		// 分配人
		if(null != capAcSpInfo.getIdistruserid() && !"".equals(capAcSpInfo.getIdistruserid())) {
			SysAccount sysAccount = this.baseSysUserProvider.findFSysUserByIndocno(capAcSpInfo.getIdistruserid());
			if(null != sysAccount) {
				capAcSpInfo.setSdistrusernm(sysAccount.getSname());
			}
		}

		// 移交验收人
		if(null != capAcSpInfo.getIcheckuserid() && !"".equals(capAcSpInfo.getIcheckuserid())) {
			SysAccount sysAccount = this.baseSysUserProvider.findFSysUserByIndocno(capAcSpInfo.getIcheckuserid());
			if(null != sysAccount) {
				capAcSpInfo.setScheckusernm(sysAccount.getSname());
			}
		}

		// 已打卡标签
		if(null != capAcSpInfo.getIscard() && !"".equals(capAcSpInfo.getIscard())) {
			capAcSpInfo.setSscard(baseServiceHelperImpl.getDatadicName(DatadicKeys.CAP_ACCEPTANCE_EQUIP_ISCARD,
					capAcSpInfo.getIscard() != null ? capAcSpInfo.getIscard() : ""));
		}

		// 数据来源
		if(null != capAcSpInfo.getIsource() && !"".equals(capAcSpInfo.getIsource())) {
			capAcSpInfo.setSsource(baseServiceHelperImpl.getDatadicName(DatadicKeys.CAP_ACCEPTANCE_EQUIP_ISOURCE,
					capAcSpInfo.getIsource() != null ? capAcSpInfo.getIsource() : ""));
		}

		// 数据接入
		if(null != capAcSpInfo.getIaccess() && !"".equals(capAcSpInfo.getIaccess())) {
			capAcSpInfo.setSaccess(baseServiceHelperImpl.getDatadicName(DatadicKeys.CAP_ACCEPTANCE_EQUIP_IACCESS,
					capAcSpInfo.getIaccess() != null ? capAcSpInfo.getIaccess() : ""));
		}

		// 核验记录后面反更新

		return capAcSpInfo;
	}

	/**
	 * update
	 * @return
	 */
	@TransactionalException
	public AmAcceptanceSpare updateSpare(AmAcceptanceSpare entity) {

		super.update(entity);
		return entity;
	}

	/**
	 * 分配管理权限(可批量操作)
	 */
	@TransactionalException
	public List<AmAcceptanceSpare> distResponsibility(AmAcceptanceSpareParam condition) {
		List<AmAcceptanceSpare> distList = condition.getDistList();
		AuthInfo authInfo = getAuthInfo();
		List<AmAcceptanceSpare> rtnList = new ArrayList<>();
		if(distList!=null && !distList.isEmpty()) {
			for(AmAcceptanceSpare e : distList) {
				AmAcceptanceSpare capAcSpInfo = this.amAcceptanceSpareMapper.findOne(e.getIndocno());
				if(capAcSpInfo.getImessageid() != null) {
					baseSysMsgProvider.readFSysMsg(capAcSpInfo.getImessageid(), capAcSpInfo.getIdutyid(), 0);
				}

				capAcSpInfo.setImanagedeptid(condition.getImanagedeptid());	// 管理部门
				capAcSpInfo.setIdutyid(condition.getIdutyid());				// 责任人
				capAcSpInfo.setIdistruserid(authInfo.getIndocno());
				capAcSpInfo.setDdisttime(new Date());

				// 反更新分配状态(0.待处理；1.同意；2.拒绝)
				capAcSpInfo.setIdiststatus(0);
				super.update(capAcSpInfo);
				rtnList.add(capAcSpInfo);
			}
			
			// 一次分配多条数据，消息聚合
			generateMsg(rtnList,condition.getIdutyid());
		}
		return rtnList;
	}

	/**
	 * 一次分配多条数据，消息聚合
	 */
	@TransactionalException
	private void generateMsg(List<AmAcceptanceSpare> rtnList,Long idutyid) {
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
	 * 修改移交状态
	 */
	@TransactionalException
	public AmAcceptanceSpare updateTransferStatus(AmAcceptanceSpareParam condition) {
		List<AmAcceptanceSpare> transferList = condition.getTransferList();
		transferList.forEach(e -> {
			e.setIstrans(condition.getIstrans());
			e.setScon(condition.getScon());
			e.setIchangeuserid(getAuthInfo().getIndocno());	// 状态变更人
			e.setDchangetime(new Date());					// 状态变更时间
			super.update(e);
		});
		return null;
	}

	/**
	 * 责任人同意或拒绝
	 * @param condition
	 */
	@TransactionalException
	public void agreeOrRefuse(AmAcceptanceSpareParam condition) {
		List<AmAcceptanceSpare> oprDataList = condition.getOprDataList();
		for(AmAcceptanceSpare e : oprDataList) {
			AmAcceptanceSpare info = this.amAcceptanceSpareMapper.findOne(e.getIndocno());
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
			}
		}
	}

	public Long handleAddMsg(Long indocno,List<SysAccount> reservices,Long userid,int dataCount) {
		SysMsg sysMsg = new SysMsg();
		//AmAcceptanceSpare capAcceptanceSpare = amAcceptanceSpareMapper.findOne(indocno);
		sysMsg.setStitle("您有" + dataCount + "条备品备件移交数据待处理");
		sysMsg.setItype(0);
		sysMsg.setSmsg("里长");
		sysMsg.setImsgType(0);
		//sysMsg.setIfrombillid(capAcceptanceSpare.getIndocno());
		sysMsg.setSfrombillnm("实物备品备件");
		sysMsg.setSfrombillurl("/prophasemag/maintenance/spareresponsibility");
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
	 * 查询所有"未生成核验计划明细"的备品备件数据
	 * @param condition
	 * @return
	 */
	@SuppressWarnings("unlikely-arg-type")
	public List<AmAcceptanceSpare> findAllHasNotGreVerification(AmAcceptanceSpareParam condition){
		List<AmAcceptanceSpare> list = this.amAcceptanceSpareMapper.findAllHasNotGreVerification(condition);
		if(null != list && !list.isEmpty()) {
			for(AmAcceptanceSpare e : list) {
				// 核验状态
				if(null != e.getIstrans() && !"".equals(e.getIstrans())) {
					e.setSstrans(baseServiceHelperImpl.getDatadicName(DatadicKeys.CAP_ACCEPTANCE_EQUIP_ISTRANS,
							e.getIstrans() != null ? e.getIstrans() : ""));
				}
			}
		}
		return list;
	}

	/**
	 *   生成备品备件核验计划
	 *   备品备件核验计划编号：TJAM-SWBPBJ-YYYYMMDD-001
	 备品备件核验计划名称：实物备品备件核验-YYYY年M月份-001
	 *  @param condition
	 */
	@SuppressWarnings("unlikely-arg-type")
	@TransactionalException
	public String generateVerification(AmAcceptanceSpareParam condition) {
		List<AmAcceptanceSpare> dataList = condition.getGenerateDataList();

		String res = "";

		// 过滤掉已生成核验计划的数据
		List<AmAcceptanceSpare> list = new ArrayList<AmAcceptanceSpare>();
		dataList.forEach(acSpare -> {
			if(null != acSpare.getIdatastate() && !"".equals(acSpare.getIdatastate()) && 1 == acSpare.getIdatastate()) {
				list.add(acSpare);
			}
		});

		dataList.removeAll(list);

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
			plan.setIplantype(1);

			// 计划状态，未下发
			plan.setIplanstate(0);

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
			for(AmAcceptanceSpare e : dataList) {
				AmAcceptanceSpare info = this.amAcceptanceSpareMapper.findOne(e.getIndocno());

				// 如果当前条数据已经生成过核验计划，则跳过
				if(null != info.getIdatastate() && !"".equals(info.getIdatastate()) && 1 == info.getIdatastate()) {
					continue;
				}
				generateSonData(info,planIndocno);
			}
		}
		return res;
	}

	/**
	 *  将组成部分的维护数据也生成计划，但是不赋予主键(不需要与主数据关联)
	 * @param info2
	 */
	public void generateSonData(AmAcceptanceSpare info2, Long ibillid) {
		AmVrfPlanDtSpare planDetail = new AmVrfPlanDtSpare();
		planDetail.setIndocno(IDGenerate.getKey(planDetail));
		planDetail.setIlinkno(ibillid);
		planDetail.setIamaccspareid(info2.getIndocno());
		planDetail.setSspareno(info2.getSspareno());
		planDetail.setSsparenm(info2.getSsparenm());
		planDetail.setIstrans(0);	// 默认未核验
		planDetail.setSspec(info2.getSspec());
		planDetail.setSsuppliernm(info2.getSsuppliernm());
		planDetail.setIprice(info2.getIprice());
		planDetail.setIqty(info2.getIqty());
		planDetail.setImoney(info2.getImoney());
		planDetail.setScurrencyunit(info2.getScurrencyunit());
		planDetail.setSmajornm(info2.getSmajornm());
		planDetail.setSassetno(info2.getSassetno());
		planDetail.setSexternalid(info2.getSexternalid());
		planDetail.setIlineid(info2.getIlineid());
		planDetail.setSownerunit(info2.getSownerunit());
		planDetail.setSoperatunitnm(info2.getSoperatunitnm());
		planDetail.setScontdesc(info2.getScontdesc());
		planDetail.setScontno(info2.getScontno());
		planDetail.setIjscost(info2.getIjscost());
		planDetail.setIdel(0);
		planDetail.setSregid(getAuthInfo().getIndocno());
		planDetail.setDregt(new Date());
		planDetail.setIplanstate(0);
		planDetail.setIgeneratetype(0);
		planDetail.setIlinkno(ibillid);

		this.amVrfPlanDtSpareMapper.insert(planDetail);

		// 反编译设备设施资产状态为"已生成核验计划"
		info2.setIdatastate(1);
		super.update(info2);
		
		// 如果此条数据已经生成过核验计划，但是被第三方修改后，再次生成核验计划，则需要将“已修改状态”置空，表示这条数据被第三方修改后已经生成了核验计划，不可以再次生成
		if(null != info2.getIupdatestate()) {
			this.amAcceptanceSpareMapper.updateIupdateState(info2.getIndocno());
		}
	}

	/**
	 *  生成核验计划名称
	 * @return
	 */
	private String generateSplannm() {
		int curYear = Calendar.getInstance().get(Calendar.YEAR);
		int curMonth = Calendar.getInstance().get(Calendar.MONTH);
		String splannm = "实物备品备件核验" + "-" + curYear + "年" + curMonth + "月份";
		AmVrfPlanParam condition = new AmVrfPlanParam();
		condition.setSplannm(splannm);
		condition.setIplantype(1);
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
		String splanno = "TJAM-SWBPBJ-" + date;
		AmVrfPlanParam condition = new AmVrfPlanParam();
		condition.setSplanno(splanno);
		condition.setIplantype(1);
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

	//实物资产备品备件导入
	@SuppressWarnings("unused")
	@TransactionalException
    public ExcelBatchSender importSendCheck(ExcelBatchReceiver<AmAcceptanceSpare> batchReceiver) {
		String[] sstrans = {"未核验","同意接收","拒绝接收"};
		ExcelBatchSender batchSender = new ExcelBatchSender(1);
		List<AmAcceptanceSpare> content = batchReceiver.getContent();
		List<ExcelBatchSender.ExcelBatchSenderError> batchSenderErrorIndexs = new LinkedList<>();
		for (int i = 0; i < content.size(); i++) {
			AmAcceptanceSpare amAcceptanceFiasset = content.get(i);
			if (null == amAcceptanceFiasset
					|| amAcceptanceFiasset.getSsparenm() == null || amAcceptanceFiasset.getSsparenm() == ""
					|| amAcceptanceFiasset.getSassetno() == null || amAcceptanceFiasset.getSassetno() == ""
					|| amAcceptanceFiasset.getSstrans() == null || amAcceptanceFiasset.getSstrans() == "" || !Arrays.asList(sstrans).contains(amAcceptanceFiasset.getSstrans())
					|| amAcceptanceFiasset.getIprice() == null
					){
				addErrorIndex(batchSender, batchSenderErrorIndexs, i);
			}else {
				try{
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
					this.getCode(amAcceptanceFiasset);
					amAcceptanceSpareMapper.insert(amAcceptanceFiasset);
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

	/**
	 * 获取当天申请单号:如果该数据为当天第一条数据，则最后三位流水号初始化；否则找到最大主键所在的编号，最后三位流水号+1
	 * @param entity
	 * @return
	 */
	private String getCode(AmAcceptanceSpare entity) {
		String field = new SimpleDateFormat("yyyyMMdd").format(new Date());
		Long maxIndocno = this.amAcceptanceSpareMapper.findByDregt();
		if(maxIndocno == null) {
			entity.setSspareno("BPBJ-" + field  + "-001");
		}else {
			AmAcceptanceSpare ua = this.amAcceptanceSpareMapper.findOne(maxIndocno);
			String scode = (ua.getSspareno()).substring((ua.getSspareno()).length()-4);
			Long code = (Long.valueOf(scode)+1);
			if(code>=1 && code<10) {
				entity.setSspareno("BPBJ-" + field + "-000" + code.toString());
			}else if(code>=10 && code<100) {
				entity.setSspareno("BPBJ-" + field + "-00" + code.toString());
			}else if(code>=100 && code<1000) {
				entity.setSspareno("BPBJ-" + field + "-0" + code.toString());
			}else if(code>=1000 && code<=9999) {
				entity.setSspareno("BPBJ-" + field + "-" + code.toString());
			}else if (code>9999){
				entity.setSspareno("BPBJ-" + field + "-0001");
			}
		}
		return null;
	}
}
