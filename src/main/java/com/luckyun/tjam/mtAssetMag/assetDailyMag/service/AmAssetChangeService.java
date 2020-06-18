package com.luckyun.tjam.mtAssetMag.assetDailyMag.service;

import com.luckyun.base.provider.feign.BaseSysDeptProvider;
import com.luckyun.base.provider.feign.BaseSysUserProvider;
import com.luckyun.core.annotation.TransactionalException;
import com.luckyun.core.data.BaseMapper;
import com.luckyun.core.data.BaseService;
import com.luckyun.core.data.filter.AuthInfo;
import com.luckyun.core.tool.OssPathHelperUtils;
import com.luckyun.model.dept.SysDept;
import com.luckyun.model.user.SysAccount;
import com.luckyun.tjam.mtAssetMag.assetDailyMag.mapper.AmAssetChangeAttMapper;
import com.luckyun.tjam.mtAssetMag.assetDailyMag.mapper.AmAssetChangeDtMapper;
import com.luckyun.tjam.mtAssetMag.assetDailyMag.mapper.AmAssetChangeMapper;
import com.luckyun.tjam.mtAssetMag.assetDailyMag.model.AmAssetChange;
import com.luckyun.tjam.mtAssetMag.assetDailyMag.model.AmAssetChangeAtt;
import com.luckyun.tjam.mtAssetMag.assetDailyMag.model.AmAssetChangeDt;
import com.luckyun.tjam.mtAssetMag.assetDailyMag.param.AmAssetChangeParam;
import com.luckyun.tjam.prophaseMag.acceptance.common.DatadicKeys;
import com.luckyun.tjam.prophaseMag.acceptance.helper.BaseServiceHelperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class AmAssetChangeService extends BaseService<AmAssetChange> {

    @Autowired
    private AmAssetChangeMapper amAssetChangeMapper;

    @Autowired
    private AmAssetChangeDtMapper amAssetChangeDtMapper;

    @Autowired
    private AmAssetChangeAttMapper amAssetChangeAttMapper;

    @Autowired
    private BaseServiceHelperImpl baseServiceHelperImpl;

    @Autowired
    private BaseSysUserProvider baseSysUserProvider;

    @Autowired
    private BaseSysDeptProvider baseSysDeptProvider;

    @Autowired
    private OssPathHelperUtils ossPathHelperUtils;

    @Override
    public BaseMapper<AmAssetChange> getMapper() {
        return amAssetChangeMapper;
    }

    /**
     * 条件查询所有
     * @param condition
     * @return
     */
    @SuppressWarnings("unlikely-arg-type")
	public List<AmAssetChange> findAll(AmAssetChangeParam condition){
        List<AmAssetChange> all = amAssetChangeMapper.findAll(condition);
        if (!CollectionUtils.isEmpty(all)){
            all.forEach(e->{
              /*  int count = amAssetChangeDtMapper.findCount();
                e.setIqty(count);*/
                if (e.getSregid() != null && baseSysUserProvider.findFSysUserByIndocno(e.getSregid()) != null){
                    e.setSregnm(baseSysUserProvider.findFSysUserByIndocno(e.getSregid()).getSname());
                }
                // 所属线路
                if(null != e.getIlineid() && !"".equals(e.getIlineid())) {
                    e.setSlinenm(baseServiceHelperImpl.getDatadicName(DatadicKeys.CAP_ACCEPTANCE_EQUIP_ILINEID,
                            e.getIlineid() != null ? e.getIlineid() : ""));
                }
                // 管理部门
                if(null != e.getIdeptid() && !"".equals(e.getIdeptid())) {
                    SysDept sysDept = this.baseSysDeptProvider.findByIndocno(e.getIdeptid());
                    if(null != sysDept) {
                        e.setSdeptsm(sysDept.getSname());
                    }
                }
              /*  //审批状态
                if(null != e.getIapprovalstate() && !"".equals(e.getIapprovalstate())) {
                    e.setSapprovalstatenm(baseServiceHelperImpl.getDatadicName(DatadicKeys.AM_BASIC_INF_ICATEGORYID_IAPPROVALSTATE,
                            e.getIapprovalstate() != null ? e.getIapprovalstate() : ""));
                }*/
            });
        }
        return all;
    }



    /***
     *
     * 删除
     *
     */
    @TransactionalException
  /*  public AmTransferPlanDt delOpr(AmTransferPlanDt condition){
        Long indocno = condition.getIndocno();
        AmTransferPlanDt one = amTransferPlanDtMapper.findOne(indocno);
        if(!StringUtils.isEmpty(one)){
            amTransferPlanDtMapper.delete(one);
        }
        return one;
    }*/
    public void delOpr(AmAssetChangeParam condition){
        List<AmAssetChange> delList = condition.getDelList();
        if (!CollectionUtils.isEmpty(delList)){
            delList.forEach(e->{
                amAssetChangeMapper.delete(e);
            });
        }
    }

    /**
     *  查询明细表明细
     * @param condition
     * @return
     */
    @SuppressWarnings("unlikely-arg-type")
	public List<AmAssetChangeDt> findOnes(Long indocno){
        List<AmAssetChangeDt> amTransferPlanDtList = amAssetChangeDtMapper.findAllByIlinkno(indocno);
        if (!CollectionUtils.isEmpty(amTransferPlanDtList)){
            amTransferPlanDtList.forEach(e->{
                if (e.getIdutyid() != null && baseSysUserProvider.findFSysUserByIndocno(e.getIdutyid()) != null){
                    e.setSdutynm(baseSysUserProvider.findFSysUserByIndocno(e.getIdutyid()).getSname());
                }
                if (e.getIuserid() != null && baseSysUserProvider.findFSysUserByIndocno(e.getIuserid()) != null){
                    e.setSusernm(baseSysUserProvider.findFSysUserByIndocno(e.getIuserid()).getSname());
                }
                if (e.getIdutyidNew() != null && baseSysUserProvider.findFSysUserByIndocno(e.getIdutyidNew()) != null){
                    e.setSdutynmNew(baseSysUserProvider.findFSysUserByIndocno(e.getIdutyidNew()).getSname());
                }
                if (e.getIuseridNew() != null && baseSysUserProvider.findFSysUserByIndocno(e.getIuseridNew()) != null){
                    e.setSusernmNew(baseSysUserProvider.findFSysUserByIndocno(e.getIuseridNew()).getSname());
                }
                if(null != e.getImanagedeptidNew() && !"".equals(e.getImanagedeptidNew())) {
                    SysDept sysDept = this.baseSysDeptProvider.findByIndocno(e.getImanagedeptidNew());
                    if(null != sysDept) {
                        e.setSmanagedeptnmNew(sysDept.getSname());
                    }
                }
                if(null != e.getIlineid() && !"".equals(e.getIlineid())) {
                    e.setSlinenm(baseServiceHelperImpl.getDatadicName(DatadicKeys.CAP_ACCEPTANCE_EQUIP_ILINEID,
                            e.getIlineid() != null ? e.getIlineid() : ""));
                }
                // 管理部门
                if(null != e.getImanagedeptid() && !"".equals(e.getImanagedeptid())) {
                    SysDept sysDept = this.baseSysDeptProvider.findByIndocno(e.getImanagedeptid());
                    if(null != sysDept) {
                        e.setSmanagedeptnm(sysDept.getSname());
                    }
                }
            });
        }
        return amTransferPlanDtList;
    }

    /**
     *  查询附件表明细
     * @param condition
     * @return
     */
    public List<AmAssetChangeAtt> findOneas(Long indocno){
        List<AmAssetChangeAtt> attList = amAssetChangeAttMapper.findAllByIlinkno(indocno);
        if(!CollectionUtils.isEmpty(attList)){
            attList.forEach(e->{
                AuthInfo authInfo = getAuthInfo();
                // 图片地址
                if (null != e.getSpath()) {
                    e.setNspath(ossPathHelperUtils.generateDownloadFileNoAuth(authInfo.getSloginid()
                            , "tjam", e.getSpath(), e.getSname()));
                }
                //上传人
                if(null != e.getSregid()) {
                    SysAccount sysAccount = this.baseSysUserProvider.findFSysUserByIndocno(e.getSregid());
                    e.setSregnm(sysAccount.getSname());
                }
            });
        }
        return attList;
    }

    /**
     *  查询明细
     * @param condition
     * @return
     */
    @SuppressWarnings("unlikely-arg-type")
	public AmAssetChange findOne(Long indocno){
        AmAssetChange e = amAssetChangeMapper.findOne(indocno);
        if (!StringUtils.isEmpty(e)){
          /*  int count = amAssetChangeDtMapper.findCount();
            e.setIqty(count);*/
            if (e.getSregid() != null && baseSysUserProvider.findFSysUserByIndocno(e.getSregid()) != null){
                e.setSregnm(baseSysUserProvider.findFSysUserByIndocno(e.getSregid()).getSname());
            }
            // 所属线路
            if(null != e.getIlineid() && !"".equals(e.getIlineid())) {
                e.setSlinenm(baseServiceHelperImpl.getDatadicName(DatadicKeys.CAP_ACCEPTANCE_EQUIP_ILINEID,
                        e.getIlineid() != null ? e.getIlineid() : ""));
            }
            // 管理部门
            if(null != e.getIdeptid() && !"".equals(e.getIdeptid())) {
                SysDept sysDept = this.baseSysDeptProvider.findByIndocno(e.getIdeptid());
                if(null != sysDept) {
                    e.setSdeptsm(sysDept.getSname());
                }
            }
         /*   //审批状态
            if(null != e.getIapprovalstate() && !"".equals(e.getIapprovalstate())) {
                e.setSapprovalstatenm(baseServiceHelperImpl.getDatadicName(DatadicKeys.AM_BASIC_INF_ICATEGORYID_IAPPROVALSTATE,
                        e.getIapprovalstate() != null ? e.getIapprovalstate() : ""));
            }*/
        }
        return e;
    }

    /**
     * 添加
     *
     */
    @TransactionalException
    public AmAssetChange add(AmAssetChange entity){
        AuthInfo authInfo = super.getAuthInfo();
        entity.setSregid(authInfo.getIndocno());
        entity.setDregt(new Date());
        String sfcode = generateSappmtno();
        entity.setSfcode(sfcode);
        super.insert(entity);
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
     *修改
     **/
    @TransactionalException
    public void updateBasic(AmAssetChange entity) {
        amAssetChangeMapper.update(entity);
    }


}
