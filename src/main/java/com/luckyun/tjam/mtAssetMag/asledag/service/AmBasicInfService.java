package com.luckyun.tjam.mtAssetMag.asledag.service;

import com.luckyun.base.provider.feign.BaseSysDeptProvider;
import com.luckyun.base.provider.feign.BaseSysUserProvider;
import com.luckyun.core.annotation.TransactionalException;
import com.luckyun.core.data.BaseMapper;
import com.luckyun.core.data.BaseService;
import com.luckyun.core.data.filter.AuthInfo;
import com.luckyun.core.km.keyhelper.IDGenerate;
import com.luckyun.core.tool.OssPathHelperUtils;
import com.luckyun.model.dept.SysDept;
import com.luckyun.model.user.SysAccount;
import com.luckyun.tjam.mtAssetMag.amAssetFree.mapper.AmAssetFreeDtMapper;
import com.luckyun.tjam.mtAssetMag.amAssetFree.mapper.AmAssetFreeMapper;
import com.luckyun.tjam.mtAssetMag.amAssetFree.model.AmAssetFree;
import com.luckyun.tjam.mtAssetMag.amAssetFree.model.AmAssetFreeDt;
import com.luckyun.tjam.mtAssetMag.amAssetFree.param.AmAssetFreeParam;
import com.luckyun.tjam.mtAssetMag.amAssetLeave.mapper.AmAssetLeaveDtMapper;
import com.luckyun.tjam.mtAssetMag.amAssetLeave.mapper.AmAssetLeaveMapper;
import com.luckyun.tjam.mtAssetMag.amAssetLeave.model.AmAssetLeave;
import com.luckyun.tjam.mtAssetMag.amAssetLeave.model.AmAssetLeaveDt;
import com.luckyun.tjam.mtAssetMag.amAssetLeave.param.AmAssetLeaveParam;
import com.luckyun.tjam.mtAssetMag.amTranPlan.mapper.AmTransferPlanDtCompMapper;
import com.luckyun.tjam.mtAssetMag.amTranPlan.mapper.AmTransferPlanDtMapper;
import com.luckyun.tjam.mtAssetMag.amTranPlan.mapper.AmTransferPlanMapper;
import com.luckyun.tjam.mtAssetMag.amTranPlan.model.AmTransferPlan;
import com.luckyun.tjam.mtAssetMag.amTranPlan.model.AmTransferPlanDt;
import com.luckyun.tjam.mtAssetMag.amTranPlan.model.AmTransferPlanDtComp;
import com.luckyun.tjam.mtAssetMag.amTranPlan.param.AmTransferPlanParam;
import com.luckyun.tjam.mtAssetMag.asledag.bpm.BpmAmBasicInfService;
import com.luckyun.tjam.mtAssetMag.asledag.mapper.*;
import com.luckyun.tjam.mtAssetMag.asledag.model.*;
import com.luckyun.tjam.mtAssetMag.asledag.param.AmBasicInfParam;
import com.luckyun.tjam.mtAssetMag.assetDailyMag.mapper.AmAssetChangeDtMapper;
import com.luckyun.tjam.mtAssetMag.assetDailyMag.mapper.AmAssetChangeMapper;
import com.luckyun.tjam.mtAssetMag.assetDailyMag.model.AmAssetChange;
import com.luckyun.tjam.mtAssetMag.assetDailyMag.model.AmAssetChangeDt;
import com.luckyun.tjam.mtAssetMag.assetDailyMag.param.AmAssetChangeParam;
import com.luckyun.tjam.prophaseMag.acceptance.common.DatadicKeys;
import com.luckyun.tjam.prophaseMag.acceptance.helper.BaseServiceHelperImpl;
import com.luckyun.tjam.prophaseMag.maintenance.mapper.AmArcClassMapper;
import com.luckyun.tjam.prophaseMag.maintenance.model.AmArcClass;
import com.luckyun.tjam.prophaseMag.maintenance.service.AmArcClassService;
import com.luckyun.tjam.prophaseMag.verification.bpm.BpmTaskParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AmBasicInfService extends BaseService<AmBasicInf> {


    @Autowired
    private AmBasicInfMapper amBasicInfMapper;

    @Autowired
    private AmAssetLeaveMapper amAssetLeaveMapper;

    @Autowired
    private AmBasicValueInfMapper amBasicValueInfMapper;

    @Autowired
    private AmAssetLeaveDtMapper amAssetLeaveDtMapper;

    @Autowired
    private AmBasicTenureInfMapper amBasicTenureInfMapper;

    @Autowired
    private BaseServiceHelperImpl baseServiceHelperImpl;

    @Autowired
    private AmBasicContractInfMapper amBasicContractInfMapper;

    @Autowired
    private AmBasicCreateFincInfMapper amBasicCreateFincInfMapper;

    @Autowired
    private AmBasicDepreciationMapper amBasicDepreciationMapper;

    @Autowired
    private AmBasicLocAreaMapper amBasicLocAreaMapper;

    @Autowired
    private AmBasicManagementInfMapper amBasicManagementInfMapper;

    @Autowired
    private AmBasicComponentInfMapper amBasicComponentInfMapper;

    @Autowired
    private AmAssetFreeMapper amAssetFreeMapper;

    @Autowired
    private AmTransferPlanDtCompMapper amTransferPlanDtCompMapper;


    @Autowired
    private BaseSysDeptProvider baseSysDeptProvider;

    @Autowired
    private BaseSysUserProvider baseSysUserProvider;

    @Autowired
    private AmArcClassMapper amArcClassMapper;

    @Autowired
    private AmTransferPlanDtMapper amTransferPlanDtMapper;

    @Autowired
    private AmBasicAnnexMapper amBasicAnnexMapper;

    @Autowired
    private OssPathHelperUtils ossPathHelperUtils;


    @Autowired
    private AmArcClassService amArcClassService;

    @Autowired
    private AmAssetChangeMapper amAssetChangeMapper;

    @Autowired
    private AmAssetChangeDtMapper amAssetChangeDtMapper;

    @Autowired
    private AmAssetFreeDtMapper amAssetFreeDtMapper;

    @Autowired
    private AmTransferPlanMapper amTransferPlanMapper;

    @Autowired
    private BpmAmBasicInfService bpmAmBasicInfService;

    @Override
    public BaseMapper<AmBasicInf> getMapper() {
        return amBasicInfMapper;
    }

    /**
     *  查询所有
     * @param condition
     * @return
     */
    @SuppressWarnings("unlikely-arg-type")
	public List<AmBasicInf> findAll(AmBasicInfParam condition){
        List<AmBasicInf> list = amBasicInfMapper.findAll(condition);
        if(null != list && !list.isEmpty()){
            for(AmBasicInf e:list){
                if(null != e.getIamarcclassid()) {
                    AmArcClass amArcClassInfo = amArcClassMapper.findByIndocno(e.getIamarcclassid());
                    e.setSamarcclassnm(amArcClassInfo.getSclassnm());
                }

                // 分类名称
                if(null != e.getSeqclassno()) {
                    List<AmArcClass> capArcClassList = this.amArcClassMapper.findBySclassno(e.getSeqclassno());
                    if(null != capArcClassList && !capArcClassList.isEmpty()) {
                        AmArcClass capArcClass = capArcClassList.get(0);
                        e.setSclassstructurenm(capArcClass.getSclassnm());
                    }
                }
                // 责任人
                if(null != e.getIdutyid()) {
                    SysAccount sysAccount1 = this.baseSysUserProvider.findFSysUserByIndocno(e.getIdutyid());
                    if(null != sysAccount1) {
                        e.setSdutynm(sysAccount1.getSname());
                    }
                }
                // 管理部门
                if(null != e.getImanagedeptid()) {
                    SysDept sysDept = this.baseSysDeptProvider.findByIndocno(e.getImanagedeptid());
                    if(null != sysDept) {
                        e.setSmanagedeptnm(sysDept.getSname());
                    }
                }
                // 管理部门
                if(null != e.getIuserdeptid()) {
                    SysDept sysDept = this.baseSysDeptProvider.findByIndocno(e.getIuserdeptid());
                    if(null != sysDept) {
                        e.setSuserdeptnm(sysDept.getSname());
                    }
                }
                //使用人
                if(null != e.getIuserid() && !"".equals(e.getIuserid())) {
                    SysAccount sysAccount = this.baseSysUserProvider.findFSysUserByIndocno(e.getIuserid());
                    e.setSusernm(sysAccount.getSname());
                }

                // 线路
                if(null != e.getIlineid() && !"".equals(e.getIlineid())) {
                    e.setSlinenm(baseServiceHelperImpl.getDatadicName(DatadicKeys.CAP_ACCEPTANCE_EQUIP_ILINEID,
                            e.getIlineid() != null ? e.getIlineid() : ""));
                }
                // 类别
                if(null != e.getIcategoryid() && !"".equals(e.getIcategoryid())) {
                    e.setScategorynm(baseServiceHelperImpl.getDatadicName(DatadicKeys.AM_BASIC_INF_ICATEGORYID,
                            e.getIcategoryid() != null ? e.getIcategoryid() : ""));
                }
                //运营类别
                if(null != e.getIopergoryid() && !"".equals(e.getIopergoryid())) {
                    e.setSopergorynm(baseServiceHelperImpl.getDatadicName(DatadicKeys.AM_BASIC_INF_ICATEGORYID_IOPERGORYID,
                            e.getIopergoryid() != null ? e.getIopergoryid() : ""));
                }
                //资产状态
                if(null != e.getIamstate() && !"".equals(e.getIamstate())) {
                    e.setSamstatenm(baseServiceHelperImpl.getDatadicName(DatadicKeys.AM_BASIC_INF_ICATEGORYID_IAMSTATE,
                            e.getIamstate() != null ? e.getIamstate() : ""));
                }
                //资产类别
                if(null != e.getIamclassid() && !"".equals(e.getIamclassid())) {
                    e.setSamclassnm(baseServiceHelperImpl.getDatadicName(DatadicKeys.AM_BASIC_INF_ICATEGORYID_IAMCLASSID,
                            e.getIamclassid() != null ? e.getIamclassid() : ""));
                }
                //重要等级
                if(null != e.getIimportlevelid() && !"".equals(e.getIimportlevelid())) {
                    e.setSimportlevelnm(baseServiceHelperImpl.getDatadicName(DatadicKeys.AM_BASIC_INF_ICATEGORYID_IIMPORTLEVELID,
                            e.getIimportlevelid() != null ? e.getIimportlevelid() : ""));
                }
                e.setSapprovalstate(baseServiceHelperImpl.getDatadicName(DatadicKeys.BPM_IAPPROVALSTATE,
                        e.getIapprovalstate() != null ? e.getIapprovalstate():""));
            }
        }

        return list;
    }

    /**
     *  查询所有
     * @param condition
     * @return
     */
    @SuppressWarnings("unlikely-arg-type")
	public List<AmBasicInf> findAllHasNotQuoted(){
        List<AmBasicInf> list = amBasicInfMapper.readAllHasNotQuoted();
        if(null != list && !list.isEmpty()){
            for(AmBasicInf e:list){
                if(null != e.getIamarcclassid() && !"".equals(e.getIamarcclassid())){
                    AmArcClass amArcClassInfo = amArcClassMapper.findByIndocno(e.getIamarcclassid());
                    e.setSamarcclassnm(amArcClassInfo.getSclassnm());
                }
                // 线路
                if(null != e.getIlineid() && !"".equals(e.getIlineid())) {
                    e.setSlinenm(baseServiceHelperImpl.getDatadicName(DatadicKeys.CAP_ACCEPTANCE_EQUIP_ILINEID,
                            e.getIlineid() != null ? e.getIlineid() : ""));
                }
                // 类别
                if(null != e.getIcategoryid() && !"".equals(e.getIcategoryid())) {
                    e.setScategorynm(baseServiceHelperImpl.getDatadicName(DatadicKeys.AM_BASIC_INF_ICATEGORYID,
                            e.getIcategoryid() != null ? e.getIcategoryid() : ""));
                }
                //运营类别
                if(null != e.getIopergoryid() && !"".equals(e.getIopergoryid())) {
                    e.setSopergorynm(baseServiceHelperImpl.getDatadicName(DatadicKeys.AM_BASIC_INF_ICATEGORYID_IOPERGORYID,
                            e.getIopergoryid() != null ? e.getIopergoryid() : ""));
                }
                //资产状态
                if(null != e.getIamstate() && !"".equals(e.getIamstate())) {
                    e.setSamstatenm(baseServiceHelperImpl.getDatadicName(DatadicKeys.AM_BASIC_INF_ICATEGORYID_IAMSTATE,
                            e.getIamstate() != null ? e.getIamstate() : ""));
                }
                //资产类别
                if(null != e.getIamclassid() && !"".equals(e.getIamclassid())) {
                    e.setSamclassnm(baseServiceHelperImpl.getDatadicName(DatadicKeys.AM_BASIC_INF_ICATEGORYID_IAMCLASSID,
                            e.getIamclassid() != null ? e.getIamclassid() : ""));
                }
                //重要等级
                if(null != e.getIimportlevelid() && !"".equals(e.getIimportlevelid())) {
                    e.setSimportlevelnm(baseServiceHelperImpl.getDatadicName(DatadicKeys.AM_BASIC_INF_ICATEGORYID_IIMPORTLEVELID,
                            e.getIimportlevelid() != null ? e.getIimportlevelid() : ""));
                }
            }
        }

        return list;
    }

    /**
     *  查询字表一明细
     * @param condition
     * @return
     */
    @SuppressWarnings("unlikely-arg-type")
	public List<AmBasicValueInf> findOnes(Long indocno){
        List<AmBasicValueInf> valueInfs = amBasicValueInfMapper.findAllByIbillid(indocno);
        if (!CollectionUtils.isEmpty(valueInfs)){
            valueInfs.forEach(e->{
                //寿命计量单位
                if(null != e.getIdesignunit() && !"".equals(e.getIdesignunit())) {
                    e.setSdesignunitnm(baseServiceHelperImpl.getDatadicName(DatadicKeys.AM_BASIC_INF_ICATEGORYID_SDESIGNUNIT,
                            e.getIdesignunit() != null ? e.getIdesignunit() : ""));
                }
                //融资状态
                if(null != e.getIfinancstate() && !"".equals(e.getIfinancstate())) {
                    e.setSfinancstatenm(baseServiceHelperImpl.getDatadicName(DatadicKeys.AM_BASIC_INF_ICATEGORYID_IFINANCSTATE,
                            e.getIfinancstate() != null ? e.getIfinancstate() : ""));
                }
                //分摊方式
                if(null !=e.getIappmttype() && !"".equals(e.getIappmttype())) {
                    e.setSappmttypenm(baseServiceHelperImpl.getDatadicName(DatadicKeys.CAP_VERIFICATION_PLAN_IAPPMTTYPE,
                            e.getIappmttype() !=null? e.getIappmttype() : ""));
                }
            });
        }
        return valueInfs;
    }

    /**
     *  查询权属表明细
     * @param condition
     * @return
     */
    public List<AmBasicTenureInf> findOnet(Long indocno){
        List<AmBasicTenureInf> allByIbillid = amBasicTenureInfMapper.findAllByIbillid(indocno);
        return allByIbillid;
    }

    /**
     *  管理信息表
     * @param condition
     * @return
     */
    @SuppressWarnings("unlikely-arg-type")
	public List<AmBasicManagementInf> findOnem(Long indocno){
        List<AmBasicManagementInf> managementInfs = amBasicManagementInfMapper.findAllByIbillid(indocno);
        if (!CollectionUtils.isEmpty(managementInfs)){
            managementInfs.forEach(e->{
                //使用状态
                if (null != e.getIusestate() && !"".equals(e.getIusestate())) {
                    e.setSusestatenm(baseServiceHelperImpl.getDatadicName(DatadicKeys.AM_BASIC_INF_ICATEGORYID_IUSESTATE,
                            e.getIusestate() != null ? e.getIusestate() : ""));
                }
                // 管理部门
                if(null != e.getImanagedeptid() && !"".equals(e.getImanagedeptid())) {
                    SysDept sysDept = this.baseSysDeptProvider.findByIndocno(e.getImanagedeptid());
                    if(null != sysDept) {
                        e.setSmanagedeptnm(sysDept.getSname());
                    }
                }
                //责任人
                if(null != e.getIdutyid() && !"".equals(e.getIdutyid())) {
                    SysAccount sysAccount = this.baseSysUserProvider.findFSysUserByIndocno(e.getIdutyid());
                    e.setSdutynm(sysAccount.getSname());
                }

                // 核算部门
                if(null != e.getIaccountdeptid() && !"".equals(e.getIaccountdeptid())) {
                    SysDept sysDept = this.baseSysDeptProvider.findByIndocno(e.getIaccountdeptid());
                    if(null != sysDept) {
                        e.setSaccountdeptnm(sysDept.getSname());
                    }
                }
                //使用人
                if(null != e.getIuserid() && !"".equals(e.getIuserid())) {
                    SysAccount sysAccount = this.baseSysUserProvider.findFSysUserByIndocno(e.getIuserid());
                    e.setSusernm(sysAccount.getSname());
                }

                //使用部门
                if(null != e.getIuserdeptid() && !"".equals(e.getIuserdeptid())) {
                    SysDept sysDept = this.baseSysDeptProvider.findByIndocno(e.getIuserdeptid());
                    if(null != sysDept) {
                        e.setSuserdeptnm(sysDept.getSname());
                    }
                }
            });
        }
        return managementInfs;
    }

    /**
     *  查询位置信息表明细
     * @param condition
     * @return
     */
    public List<AmBasicLocArea> findOnel(Long indocno){
        List<AmBasicLocArea> allByIbillid = amBasicLocAreaMapper.findAllByIbillid(indocno);
        return allByIbillid;
    }

    /**
     *  查询折旧流水表明细
     * @param condition
     * @return
     */
    public List<AmBasicDepreciation> findOned(Long indocno){
        List<AmBasicDepreciation> allByIbillid = amBasicDepreciationMapper.findAllByIbillid(indocno);
        return allByIbillid;
    }

    /**
     *  查询建立财务信息表明细
     * @param condition
     * @return
     */
    public List<AmBasicCreateFincInf> findOnec(Long indocno){
        List<AmBasicCreateFincInf> createFincInfs = amBasicCreateFincInfMapper.findAllByIbillid(indocno);
        if (!CollectionUtils.isEmpty(createFincInfs)){
            createFincInfs.forEach(e->{
                //折旧方法
                if(null != e.getIdeprecmethod()) {
                    e.setSdeprecmethodnm(baseServiceHelperImpl.getDatadicName(DatadicKeys.AM_BASIC_INF_ICATEGORYID_SDEPRECMETHOD,
                            e.getIdeprecmethod() != null ? e.getIdeprecmethod() : ""));
                }
            });
        }
        return createFincInfs;
    }

    /**
     *  查询合同信息表明细
     * @param condition
     * @return
     */
    public List<AmBasicContractInf> findOneCo(Long indocno){
        List<AmBasicContractInf> contractInfs = amBasicContractInfMapper.findAllByIbillid(indocno);
        if (!CollectionUtils.isEmpty(contractInfs)){
            contractInfs.forEach(e->{
                /**是否总价合同,0：否，1：是*/
                if(null != e.getIcontsumflag()) {
                    e.setScontsumflagnm(baseServiceHelperImpl.getDatadicName(DatadicKeys.AM_BASIC_INF_ICATEGORYID_ICONTSUMFLAG,
                            e.getIcontsumflag() != null ? e.getIcontsumflag() : ""));
                }
                if(null !=e.getDcontsign() && !"".equals(e.getDcontsign())){
                    Date d = new Date(Long.valueOf(e.getDcontsign()));
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String format = dateFormat.format(d);
                    e.setDdcontsign(format);
                }
            });
        }
        return contractInfs;
    }

    /**
     *  资产卡片组成部分表
     * @param condition
     * @return
     */
    public List<AmBasicComponentInf> findOneCom(Long indocno) {
        List<AmBasicComponentInf> componentInfs = amBasicComponentInfMapper.findAllByIbillid(indocno);
        if (null != componentInfs && !componentInfs.isEmpty()) {
            for (AmBasicComponentInf e1 : componentInfs) {
                // 数据来源
                if (null != e1.getIsource()) {
                    e1.setSsource(baseServiceHelperImpl.getDatadicName(DatadicKeys.CAP_ACCEPTANCE_EQUIP_ISOURCE,
                            e1.getIsource() != null ? e1.getIsource() : ""));
                }
            }
        }
        return componentInfs;
    }
        /**
         *  资产卡片附件图片表
         * @param condition
         * @return
         */
        public List<AmBasicAnnex> findOneA(Long indocno){
            List<AmBasicAnnex> allByIbillid = amBasicAnnexMapper.findAllByIbillid(indocno);
            if (!CollectionUtils.isEmpty(allByIbillid)){
                allByIbillid.forEach(e1->{
                    AuthInfo authInfo = getAuthInfo();
                    // 图片地址
                    if (null != e1.getSpath()) {
                        e1.setNspath(ossPathHelperUtils.generateDownloadFileNoAuth(authInfo.getSloginid()
                                , "tjam", e1.getSpath(), e1.getSname()));
                    }
                    //上传人
                    if(null != e1.getSregid()) {
                        SysAccount sysAccount = this.baseSysUserProvider.findFSysUserByIndocno(e1.getSregid());
                        e1.setSregnm(sysAccount.getSname());
                    }
                });
            }
            return allByIbillid;
        }


    /**
     *  查询明细
     * @param condition
     * @return
     */
    @SuppressWarnings("unlikely-arg-type")
	public AmBasicInf findOne(Long indocno){
        AmBasicInf e = amBasicInfMapper.findOne(indocno);
        if (null != e && !"".equals(e)){
            // 线路
            if(null != e.getIlineid() && !"".equals(e.getIlineid())) {
                e.setSlinenm(baseServiceHelperImpl.getDatadicName(DatadicKeys.CAP_ACCEPTANCE_EQUIP_ILINEID,
                        e.getIlineid() != null ? e.getIlineid() : ""));
            }
            // 类别
            if(null != e.getIcategoryid() && !"".equals(e.getIcategoryid())) {
                e.setScategorynm(baseServiceHelperImpl.getDatadicName(DatadicKeys.AM_BASIC_INF_ICATEGORYID,
                        e.getIcategoryid() != null ? e.getIcategoryid() : ""));
            }
            //运营类别
            if(null != e.getIopergoryid() && !"".equals(e.getIopergoryid())) {
                e.setSopergorynm(baseServiceHelperImpl.getDatadicName(DatadicKeys.AM_BASIC_INF_ICATEGORYID_IOPERGORYID,
                        e.getIopergoryid() != null ? e.getIopergoryid() : ""));
            }
            //资产状态
            if(null != e.getIamstate() && !"".equals(e.getIamstate())) {
                e.setSamstatenm(baseServiceHelperImpl.getDatadicName(DatadicKeys.AM_BASIC_INF_ICATEGORYID_IAMSTATE,
                        e.getIamstate() != null ? e.getIamstate() : ""));
            }

            //资产类别
            if(null != e.getIamclassid() && !"".equals(e.getIamclassid())) {
                e.setSamclassnm(baseServiceHelperImpl.getDatadicName(DatadicKeys.AM_BASIC_INF_ICATEGORYID_IAMCLASSID,
                        e.getIamclassid() != null ? e.getIamclassid() : ""));
            }
            //重要等级
            if(null != e.getIimportlevelid() && !"".equals(e.getIimportlevelid())) {
                e.setSimportlevelnm(baseServiceHelperImpl.getDatadicName(DatadicKeys.AM_BASIC_INF_ICATEGORYID_IIMPORTLEVELID,
                        e.getIimportlevelid() != null ? e.getIimportlevelid() : ""));
            }
            //卡片类型
            if(null != e.getIcardtype() && !"".equals(e.getIcardtype())) {
                e.setScardtypenm(baseServiceHelperImpl.getDatadicName(DatadicKeys.AM_BASIC_INF_ICARDTYPE,
                        e.getIcardtype() != null ? e.getIcardtype() : ""));
            }

            if(null != e.getIamarcclassid() && !"".equals(e.getIamarcclassid())){
                AmArcClass amArcClassInfo = amArcClassMapper.findByIndocno(e.getIamarcclassid());
                e.setSamarcclassnm(amArcClassInfo.getSclassnm());
            }

            e.setSapprovalstate(baseServiceHelperImpl.getDatadicName(DatadicKeys.BPM_IAPPROVALSTATE,
                    e.getIapprovalstate() != null ? e.getIapprovalstate():""));

            // 分类名称
            if(null != e.getSeqclassno()) {
                List<AmArcClass> capArcClassList = this.amArcClassMapper.findBySclassno(e.getSeqclassno());
                if(null != capArcClassList && !capArcClassList.isEmpty()) {
                    AmArcClass capArcClass = capArcClassList.get(0);
                    e.setSclassstructurenm(capArcClass.getSclassnm());
                }
            }

            BpmTaskParam bpmParams = bpmAmBasicInfService.createBpmParams(new SimpleDateFormat("yyyy-MM-dd").format(new Date())
                    + "资产卡片申请-" + baseSysUserProvider.findFSysUserByIndocno(getAuthInfo().getIndocno()).getSname(), e.getIndocno());
            e.setBpmTaskParam(bpmParams);
        }
        return e;
    }

    /**
     * 新增
     * */
    @TransactionalException
    public AmBasicInf add(AmBasicInf entity){
        AuthInfo authInfo = super.getAuthInfo();
        entity.setSregid(authInfo.getIndocno());
        entity.setDregt(new Date());
        String byIn = amArcClassService.findByIn(entity.getIamarcclassid());
        entity.setSidcc(byIn);
        String sclassno = amArcClassMapper.findSclassno(entity.getIamarcclassid());
        entity.setSeqclassno(sclassno);
        String sfcode = generateSappmtno();
        entity.setSfcode(sfcode);
        super.insert(entity);
        BpmTaskParam bpmParams = bpmAmBasicInfService.createBpmParams(new SimpleDateFormat("yyyy-MM-dd").format(new Date())
                + "资产卡片申请-" + baseSysUserProvider.findFSysUserByIndocno(getAuthInfo().getIndocno()).getSname(), entity.getIndocno());
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
        AmBasicInfParam condition = new AmBasicInfParam();
        condition.setSfcode(sfcode);
        List<AmBasicInf> list = this.amBasicInfMapper.findMaxSappmtno(condition);
        if (null == list || list.size() == 0) {
            sfcode = sfcode + "-" + "001";
        } else if (null != list && list.size() > 0) {
            AmBasicInf maxPlanInfo = list.get(0);
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

    //修改
    @TransactionalException
    public AmBasicInf updateBasic(AmBasicInf entity) {
        String byIn = amArcClassService.findByIn(entity.getIamarcclassid());
        entity.setSidcc(byIn);
        String sclassno = amArcClassMapper.findSclassno(entity.getIamarcclassid());
        entity.setSeqclassno(sclassno);
        super.update(entity);
        BpmTaskParam bpmParams = bpmAmBasicInfService.createBpmParams(new SimpleDateFormat("yyyy-MM-dd").format(new Date())
                + "资产卡片申请-" + baseSysUserProvider.findFSysUserByIndocno(getAuthInfo().getIndocno()).getSname(), entity.getIndocno());
        entity.setBpmTaskParam(bpmParams);
        return entity;
    }


    @TransactionalException
    public void delOpr(AmBasicInfParam condition){
        List<AmBasicInf> delList = condition.getDelList();
        if (!CollectionUtils.isEmpty(delList)){
            delList.forEach(e->{
                 amBasicInfMapper.delete(e);
                });
            }
        }


    /**
     * 查询基本信息和组成部分
     * */
    public List<AmBasicComponentInf> findAlls(AmBasicInfParam condition) {
        if (null != condition && condition.getIndocnos() != null && condition.getIndocnos() != "") {
            String[] split = condition.getIndocnos().split(",");
            List<Long> collect = Arrays.stream(split).map(e -> Long.parseLong(e.trim())).collect(Collectors.toList());
            condition.setIndocnoList(collect);
            ArrayList<AmBasicComponentInf> resultList = new ArrayList<>();
            for (Long e : condition.getIndocnoList()) {
                List<AmBasicComponentInf> amBasicComponentInfList = amBasicComponentInfMapper.findAllByIbillid(e);
                resultList.addAll(amBasicComponentInfList);
            }
            return resultList;
        }

        return null;

    }

    /**
     *   批量变更
     *   实物资产核验计划编号：TJAM-SWZC-YYYYMMDD-01
     实物资产核验计划名称：实物资产核验-YYYY年M月份-01；
     * @param condition
     */
    @TransactionalException
    public String generateVerification(AmBasicInfParam condition){
        List<AmBasicInf> generateDataList = condition.getGenerateDataList();
        String res = "";
        if (!CollectionUtils.isEmpty(generateDataList)){
            AmAssetChange amAssetChange = new AmAssetChange();
            Long planIndocno = IDGenerate.getKey(amAssetChange);
            amAssetChange.setIndocno(planIndocno);

            // 生成名称
            String splannm = this.generateSplannm();
            amAssetChange.setSfname(splannm);

            // 生成编号
            String splanno = this.generateSappmtnox();
            amAssetChange.setSfcode(splanno);
            AuthInfo authInfo = super.getAuthInfo();
            amAssetChange.setSregid(authInfo.getIndocno());
            amAssetChange.setDregt(new Date());
            amAssetChangeMapper.insert(amAssetChange);

            res = splanno;
            /**
             *  生成资产变更明细明细
             */
            for (AmBasicInf e:generateDataList){
                AmBasicInf one = amBasicInfMapper.findOne(e.getIndocno());
                if (!StringUtils.isEmpty(one)){
                    AmAssetChangeDt amAssetChangeDt = new AmAssetChangeDt();
                    amAssetChangeDt.setIndocno(IDGenerate.getKey(amAssetChangeDt));
                    amAssetChangeDt.setIlinkno(planIndocno);
                    amAssetChangeDt.setIsourceid(one.getIndocno());
                    amAssetChangeDt.setSfname(one.getSfname());
                    amAssetChangeDt.setSfcode(one.getSfcode());
                    amAssetChangeDt.setIlineid(one.getIlineid());
                    amAssetChangeDt.setImanagedeptid(one.getImanagedeptid());
                    amAssetChangeDt.setSmajornm(one.getSmajornm());
                    amAssetChangeDt.setSspec(one.getSspec());
                    amAssetChangeDt.setSeqclassno(one.getSeqclassno());
                    List<AmBasicLocArea> amBasicLocAreaList = amBasicLocAreaMapper.findAllByIbillid(one.getIndocno());
                    for (AmBasicLocArea loc:amBasicLocAreaList){
                        amAssetChangeDt.setSposarea(loc.getSposarea());
                        amAssetChangeDt.setSposbuild(loc.getSposbuild());
                        amAssetChangeDt.setSposroom(loc.getSposroom());
                    }
                    List<AmBasicManagementInf> amBasicManagementInfList = amBasicManagementInfMapper.findAllByIbillid(one.getIndocno());
                    for (AmBasicManagementInf man:amBasicManagementInfList){
                        amAssetChangeDt.setIdutyid(man.getIdutyid());
                        amAssetChangeDt.setIuserid(man.getIuserid());
                    }
                    amAssetChangeDtMapper.insert(amAssetChangeDt);
                    /*// 反编译资产状态"
                    one.setIamstate(1);
                    super.update(one);*/

                }
            }
        }
            return res;
        }

        /**
         *  生成核验计划名称
         * @return
         */
    private String generateSplannm() {
        int curYear = Calendar.getInstance().get(Calendar.YEAR);
        int curMonth = Calendar.getInstance().get(Calendar.MONTH);
        String splannm = "资产卡片" + "-" + curYear + "年" + curMonth + "月份";
        AmAssetChangeParam condition = new AmAssetChangeParam();
        condition.setSfname(splannm);
        List<AmAssetChange> list = this.amAssetChangeMapper.findMaxSname(condition);
        if(null == list || list.size() == 0) {
            splannm = splannm + "-" + "001";
        }else if(null != list && list.size() > 0){
            AmAssetChange maxPlanInfo = list.get(0);
            String maxSplannm = maxPlanInfo.getSfname();
            String lastString = maxSplannm.substring(maxSplannm.lastIndexOf("-")+1,maxSplannm.length());
            if (!StringUtils.isEmpty(lastString)){
                Integer lastInteger = Integer.valueOf(lastString) + 1;
                if(lastInteger.toString().length() == 1) {
                    splannm = splannm + "-00" + lastInteger;
                }else if(lastInteger.toString().length() == 2) {
                    splannm = splannm + "-0" + lastInteger;
                }else if(lastInteger.toString().length() == 3) {
                    splannm = splannm + "-" + lastInteger;
                }
            }
        }
        return splannm;
    }

    /**
     * 生成编号
     *
     * @return
     */
    private String generateSappmtnox() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String date = sdf.format(new Date());
        String sfcode = date;
        AmAssetChangeParam condition = new AmAssetChangeParam();
        condition.setSfcode(sfcode);
        List<AmAssetChange> list = this.amAssetChangeMapper.findMaxSappmtno(condition);
        if (null == list || list.size() == 0) {
            sfcode = sfcode + "-" + "001";
        } else if (null != list && list.size() > 0) {
            AmAssetChange maxPlanInfo = list.get(0);
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
     *   批量调出
     *   实物资产核验计划编号：TJAM-SWZC-YYYYMMDD-01
     实物资产核验计划名称：实物资产核验-YYYY年M月份-01；
     * @param condition
     */
    @TransactionalException
    public String generateVerificationes(AmBasicInfParam condition){
        List<AmBasicInf> generateDataList = condition.getGenerateDataList();
        String res = "";
        if (!CollectionUtils.isEmpty(generateDataList)){
            AmAssetLeave amAssetLeave = new AmAssetLeave();
            Long planIndocno = IDGenerate.getKey(amAssetLeave);
            amAssetLeave.setIndocno(planIndocno);

            // 生成名称
            String splannm = this.generateSplannmDc();
            amAssetLeave.setSfname(splannm);

            // 生成编号
            String splanno = this.generateSappmtnoxDc();
            amAssetLeave.setSfcode(splanno);
            AuthInfo authInfo = super.getAuthInfo();
            amAssetLeave.setSregid(authInfo.getIndocno());
            amAssetLeave.setDregt(new Date());
            amAssetLeaveMapper.insert(amAssetLeave);

            res = splanno;
            /**
             *  生成资产变更明细明细
             */
            for (AmBasicInf e:generateDataList){
                AmBasicInf one = amBasicInfMapper.findOne(e.getIndocno());
                if (!StringUtils.isEmpty(one)){
                    AmAssetLeaveDt amAssetLeaveDt = new AmAssetLeaveDt();
                    amAssetLeaveDt.setIndocno(IDGenerate.getKey(amAssetLeaveDt));
                    amAssetLeaveDt.setIlinkno(planIndocno);
                    amAssetLeaveDt.setIsourceid(one.getIndocno());
                    amAssetLeaveDt.setSfname(one.getSfname());
                    amAssetLeaveDt.setSfcode(one.getSfcode());
                    //amAssetLeaveDt.setIlineid(one.getIlineid());
                    amAssetLeaveDt.setSeqclassno(one.getSeqclassno());
                    amAssetLeaveDtMapper.insert(amAssetLeaveDt);
                    /*// 反编译资产状态"
                    one.setIamstate(1);
                    super.update(one);*/

                }
            }
        }
        return res;
    }



    /**
     *  生成核验计划名称
     * @return
     */
    public String generateSplannmDc() {
        int curYear = Calendar.getInstance().get(Calendar.YEAR);
        int curMonth = Calendar.getInstance().get(Calendar.MONTH);
        String splannm = "资产卡片" + "-" + curYear + "年" + curMonth + "月份";
        AmAssetLeaveParam condition = new AmAssetLeaveParam();
        condition.setSfname(splannm);
        List<AmAssetLeave> list = this.amAssetLeaveMapper.findMaxSname(condition);
        if(null == list || list.size() == 0) {
            splannm = splannm + "-" + "001";
        }else if(null != list && list.size() > 0){
            AmAssetLeave maxPlanInfo = list.get(0);
            String maxSplannm = maxPlanInfo.getSfname();
            String lastString = maxSplannm.substring(maxSplannm.lastIndexOf("-")+1,maxSplannm.length());
            if (!StringUtils.isEmpty(lastString)){
                Integer lastInteger = Integer.valueOf(lastString) + 1;
                if(lastInteger.toString().length() == 1) {
                    splannm = splannm + "-00" + lastInteger;
                }else if(lastInteger.toString().length() == 2) {
                    splannm = splannm + "-0" + lastInteger;
                }else if(lastInteger.toString().length() == 3) {
                    splannm = splannm + "-" + lastInteger;
                }
            }
        }
        return splannm;
    }

    /**
     * 生成编号
     *
     * @return
     */
    public String generateSappmtnoxDc() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String date = sdf.format(new Date());
        String sfcode = date;
        AmAssetLeaveParam condition = new AmAssetLeaveParam();
        condition.setSfcode(sfcode);
        List<AmAssetLeave> list = this.amAssetLeaveMapper.findMaxSappmtno(condition);
        if (null == list || list.size() == 0) {
            sfcode = sfcode + "-" + "001";
        } else if (null != list && list.size() > 0) {
            AmAssetLeave maxPlanInfo = list.get(0);
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
     *   批量闲置
     *   实物资产核验计划编号：TJAM-SWZC-YYYYMMDD-01
     实物资产核验计划名称：实物资产核验-YYYY年M月份-01；
     * @param condition
     */
    @TransactionalException
    public String generateVerificationas(AmBasicInfParam condition){
        List<AmBasicInf> generateDataList = condition.getGenerateDataList();
        String res = "";
        if (!CollectionUtils.isEmpty(generateDataList)){
            AmAssetFree amAssetFree = new AmAssetFree();
            Long planIndocno = IDGenerate.getKey(amAssetFree);
            amAssetFree.setIndocno(planIndocno);

            // 生成名称
            String splannm = this.generateSplannmXz();
            amAssetFree.setSfname(splannm);

            // 生成编号
            String splanno = this.generateSappmtnoxXz();
            amAssetFree.setSfcode(splanno);
            AuthInfo authInfo = super.getAuthInfo();
            amAssetFree.setSregid(authInfo.getIndocno());
            amAssetFree.setDregt(new Date());
            amAssetFreeMapper.insert(amAssetFree);

            res = splanno;
            /**
             *  生成资产变更明细明细
             */
            for (AmBasicInf e:generateDataList){
                AmBasicInf one = amBasicInfMapper.findOne(e.getIndocno());
                if (!StringUtils.isEmpty(one)){
                    AmAssetFreeDt amAssetFreeDt = new AmAssetFreeDt();
                    amAssetFreeDt.setIndocno(IDGenerate.getKey(amAssetFreeDt));
                    amAssetFreeDt.setIlinkno(planIndocno);
                    amAssetFreeDt.setIsourceid(one.getIndocno());
                    amAssetFreeDt.setSfname(one.getSfname());
                    amAssetFreeDt.setSfcode(one.getSfcode());
                    //amAssetFreeDt.setIlineid(one.getIlineid());
                    amAssetFreeDt.setSeqclassno(one.getSeqclassno());
                    amAssetFreeDt.setIamstate(one.getIamstate());
                    List<AmBasicInf> bydstartList = amBasicInfMapper.findBydstart();
                    if (!CollectionUtils.isEmpty(bydstartList)){
                        for (AmBasicInf bydstart:bydstartList){
                            LocalDate startDate = bydstart.getDstart().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                            LocalDate endDate = LocalDate.now();
                            Period period = Period.between(startDate, endDate);
                            period.getMonths();
                            amAssetFreeDt.setServicetime(String.valueOf(period.getMonths()));
                        }
                    }

                    amAssetFreeDtMapper.insert(amAssetFreeDt);
                    /*// 反编译资产状态"
                    one.setIamstate(1);
                    super.update(one);*/

                }
            }
        }
        return res;
    }

    /**
     *  生成核验计划名称
     * @return
     */
    public String generateSplannmXz() {
        int curYear = Calendar.getInstance().get(Calendar.YEAR);
        int curMonth = Calendar.getInstance().get(Calendar.MONTH);
        String splannm = "资产卡片" + "-" + curYear + "年" + curMonth + "月份";
        AmAssetFreeParam condition = new AmAssetFreeParam();
        condition.setSfname(splannm);
        List<AmAssetFree> list = this.amAssetFreeMapper.findMaxSname(condition);
        if(null == list || list.size() == 0) {
            splannm = splannm + "-" + "001";
        }else if(null != list && list.size() > 0){
            AmAssetFree maxPlanInfo = list.get(0);
            String maxSplannm = maxPlanInfo.getSfname();
            String lastString = maxSplannm.substring(maxSplannm.lastIndexOf("-")+1,maxSplannm.length());
            if (!StringUtils.isEmpty(lastString)){
                Integer lastInteger = Integer.valueOf(lastString) + 1;
                if(lastInteger.toString().length() == 1) {
                    splannm = splannm + "-00" + lastInteger;
                }else if(lastInteger.toString().length() == 2) {
                    splannm = splannm + "-0" + lastInteger;
                }else if(lastInteger.toString().length() == 3) {
                    splannm = splannm + "-" + lastInteger;
                }
            }
        }
        return splannm;
    }

    /**
     * 生成编号
     *
     * @return
     */
    public String generateSappmtnoxXz() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String date = sdf.format(new Date());
        String sfcode = date;
        AmAssetFreeParam condition = new AmAssetFreeParam();
        condition.setSfcode(sfcode);
        List<AmAssetFree> list = this.amAssetFreeMapper.findMaxSappmtno(condition);
        if (null == list || list.size() == 0) {
            sfcode = sfcode + "-" + "001";
        } else if (null != list && list.size() > 0) {
            AmAssetFree maxPlanInfo = list.get(0);
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
     *   批量计划下发
     *   实物资产核验计划编号：TJAM-SWZC-YYYYMMDD-01
     实物资产核验计划名称：实物资产核验-YYYY年M月份-01；
     * @param condition
     */
    @TransactionalException
    public String generateVerificationXf(AmBasicInfParam condition){
        List<AmBasicInf> generateDataList = condition.getGenerateDataList();
        String res = "";
        if (!CollectionUtils.isEmpty(generateDataList)){
            AmTransferPlan amTransferPlan = new AmTransferPlan();
            Long planIndocno = IDGenerate.getKey(amTransferPlan);
            amTransferPlan.setIndocno(planIndocno);

            // 生成名称
            String splannm = this.generateSplannmXf();
            amTransferPlan.setSfname(splannm);

            // 生成编号


            getCode(amTransferPlan);


            AuthInfo authInfo = super.getAuthInfo();
            amTransferPlan.setSregid(authInfo.getIndocno());
            amTransferPlan.setDregt(new Date());
            amTransferPlanMapper.insert(amTransferPlan);

            res =  amTransferPlan.getSfcode();
            /**
             *  生成资产变更明细明细
             */
            for (AmBasicInf e:generateDataList) {
                AmBasicInf one = amBasicInfMapper.findOne(e.getIndocno());
                if (!StringUtils.isEmpty(one)) {
                    /**
                     *  将组成部分的维护数据也生成计划，但是不赋予主键(不需要与主数据关联)
                     * @param info2
                     */
                    AmTransferPlanDtComp planDetail = new AmTransferPlanDtComp();
                    List<AmBasicComponentInf> componentInfLists = amBasicComponentInfMapper.findAllByIbillid(one.getIndocno());
                    if (!CollectionUtils.isEmpty(componentInfLists)){
                        for (AmBasicComponentInf comn:componentInfLists){
                            planDetail.setIndocno(IDGenerate.getKey(planDetail));
                            planDetail.setSfcode(comn.getSfcode());
                            planDetail.setSfname(comn.getSfname());
                            planDetail.setSmajornm(comn.getSmajornm());
                            planDetail.setSbrandnm(comn.getSbrandnm());
                            planDetail.setSspec(comn.getSspec());
                            planDetail.setIsourceid(comn.getIndocno());
                            planDetail.setSinspos(comn.getSinspos());
                            planDetail.setIqty(comn.getIqty());
                            planDetail.setSparentno(comn.getSparentno());
                            //planDetail.setIlinkno(null);
                            this.amTransferPlanDtCompMapper.insert(planDetail);

                            // 反编译设备设施资产状态为"已生成核验计划"
                            comn.setIgenetpstateid(1);
                            amBasicComponentInfMapper.update(comn);

                        }
                    }


                //插入清单表
                AmTransferPlanDt amTransferPlanDt = new AmTransferPlanDt();
                amTransferPlanDt.setIndocno(IDGenerate.getKey(amTransferPlanDt));
                amTransferPlanDt.setIlinkno(planIndocno);
                amTransferPlanDt.setIsourceid(one.getIndocno());
                amTransferPlanDt.setSfname(one.getSfname());
                amTransferPlanDt.setSfcode(one.getSfcode());
                amTransferPlanDt.setSbrandnm(one.getSbrandnm());
                amTransferPlanDt.setSspec(one.getSspec());
                List<AmBasicLocArea> amBasicLocAreaList = amBasicLocAreaMapper.findAllByIbillid(one.getIndocno());
                if (!CollectionUtils.isEmpty(amBasicLocAreaList)) {
                    for (AmBasicLocArea lo : amBasicLocAreaList) {
                        amTransferPlanDt.setSinspos(lo.getSinspos());
                    }
                }
                List<AmBasicValueInf> valueInfList = amBasicValueInfMapper.findAllByIbillid(one.getIndocno());
                if (!CollectionUtils.isEmpty(valueInfList)) {
                    for (AmBasicValueInf va : valueInfList) {
                        amTransferPlanDt.setIqty(va.getIqty());
                    }
                }

                amTransferPlanDtMapper.insert(amTransferPlanDt);
                // 反编译资产状态为"已生成计划"
                one.setIgenetpstateid(1);
                super.update(one);
            }
            }

        }
        return res;
    }


    /**
     *  生成核验计划名称
     * @return
     */
    public String generateSplannmXf() {
        int curYear = Calendar.getInstance().get(Calendar.YEAR);
        int curMonth = Calendar.getInstance().get(Calendar.MONTH);
        String splannm = "资产卡片" + "-" + curYear + "年" + curMonth + "月份";
        AmTransferPlanParam condition = new AmTransferPlanParam();
        condition.setSfname(splannm);
        List<AmTransferPlan> list = this.amTransferPlanMapper.findMaxSname(condition);
        if(null == list || list.size() == 0) {
            splannm = splannm + "-" + "001";
        }else if(null != list && list.size() > 0){
            AmTransferPlan maxPlanInfo = list.get(0);
            String maxSplannm = maxPlanInfo.getSfname();
            String lastString = maxSplannm.substring(maxSplannm.lastIndexOf("-")+1,maxSplannm.length());
            if (!StringUtils.isEmpty(lastString)){
                Integer lastInteger = Integer.valueOf(lastString) + 1;
                if(lastInteger.toString().length() == 1) {
                    splannm = splannm + "-00" + lastInteger;
                }else if(lastInteger.toString().length() == 2) {
                    splannm = splannm + "-0" + lastInteger;
                }else if(lastInteger.toString().length() == 3) {
                    splannm = splannm + "-" + lastInteger;
                }
            }
        }
        return splannm;
    }

    /**
     * 生成编号
     *
     * @return
     */
    private String getCode(AmTransferPlan entity) {
        String field = new SimpleDateFormat("yyyyMMdd").format(new Date());
        Long maxIndocno = this.amTransferPlanMapper.findByDregt();
        if(maxIndocno == null) {
            entity.setSfcode("XCGZ-" + field  + "-001");
        }else {
            AmTransferPlan ua = this.amTransferPlanMapper.findOne(maxIndocno);
            String[] split = ua.getSfcode().split("-");
            Long code = null;
            if (split.length>=2){
                code = Long.valueOf(split[split.length-1])+1;
            }
            if(code>=1 && code<10) {
                entity.setSfcode("XCGZ-" + field + "-00" + code.toString());
            }
            if(code>=10 && code<100) {
                entity.setSfcode("XCGZ-" + field + "-0" + code.toString());
            }
            if(code>=100 && code<=999) {
                entity.setSfcode("XCGZ-" + field + "-" + code.toString());
            }
            if(code>999) {
                entity.setSfcode("XCGZ-" + field + "-001");
            }
        }
        return null;
    }


}
