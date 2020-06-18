package com.luckyun.tjam.mtAssetMag.amAssetEnable.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.luckyun.base.provider.feign.BaseSysDeptProvider;
import com.luckyun.base.provider.feign.BaseSysUserProvider;
import com.luckyun.core.annotation.TransactionalException;
import com.luckyun.core.data.BaseMapper;
import com.luckyun.core.data.BaseService;
import com.luckyun.core.data.filter.AuthInfo;
import com.luckyun.core.tool.OssPathHelperUtils;
import com.luckyun.model.dept.SysDept;
import com.luckyun.model.user.SysAccount;
import com.luckyun.tjam.mtAssetMag.amAssetEnable.mapper.AmAssetEnableAttMapper;
import com.luckyun.tjam.mtAssetMag.amAssetEnable.mapper.AmAssetEnableDtMapper;
import com.luckyun.tjam.mtAssetMag.amAssetEnable.mapper.AmAssetEnableMapper;
import com.luckyun.tjam.mtAssetMag.amAssetEnable.model.AmAssetEnable;
import com.luckyun.tjam.mtAssetMag.amAssetEnable.model.AmAssetEnableAtt;
import com.luckyun.tjam.mtAssetMag.amAssetEnable.model.AmAssetEnableDt;
import com.luckyun.tjam.mtAssetMag.amAssetEnable.param.AmAssetEnableParam;
import com.luckyun.tjam.prophaseMag.acceptance.common.DatadicKeys;
import com.luckyun.tjam.prophaseMag.acceptance.helper.BaseServiceHelperImpl;

@Service
public class AmAssetEnableService extends BaseService<AmAssetEnable> {

    @Autowired
    private AmAssetEnableMapper amAssetEnableMapper;

    @Autowired
    private AmAssetEnableDtMapper amAssetEnableDtMapper;

    @Autowired
    private AmAssetEnableAttMapper amAssetEnableAttMapper;

    @Autowired
    private BaseServiceHelperImpl baseServiceHelperImpl;

    @Autowired
    private BaseSysUserProvider baseSysUserProvider;

    @Autowired
    private BaseSysDeptProvider baseSysDeptProvider;

    @Autowired
    private OssPathHelperUtils ossPathHelperUtils;

    @Override
    public BaseMapper<AmAssetEnable> getMapper() {
        return amAssetEnableMapper;
    }

    /**
     * 条件查询所有
     * @param condition
     * @return
     */
    public List<AmAssetEnable> findAll(AmAssetEnableParam condition){
        List<AmAssetEnable> all = amAssetEnableMapper.findAll(condition);
        if (!CollectionUtils.isEmpty(all)){
            all.forEach(e->{
                if (e.getSregid() != null && baseSysUserProvider.findFSysUserByIndocno(e.getSregid()) != null){
                    e.setSregnm(baseSysUserProvider.findFSysUserByIndocno(e.getSregid()).getSname());
                }
                // 所属线路
                if(null != e.getIlineid()) {
                    e.setSlinenm(baseServiceHelperImpl.getDatadicName(DatadicKeys.CAP_ACCEPTANCE_EQUIP_ILINEID,
                            e.getIlineid() != null ? e.getIlineid() : ""));
                }
                // 管理部门
                if(null != e.getIdeptid()) {
                    SysDept sysDept = this.baseSysDeptProvider.findByIndocno(e.getIdeptid());
                    if(null != sysDept) {
                        e.setSdeptsm(sysDept.getSname());
                    }
                }
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
    public void delOpr(AmAssetEnableParam condition){
        List<AmAssetEnable> delList = condition.getDelList();
        if (!CollectionUtils.isEmpty(delList)){
            delList.forEach(e->{
                amAssetEnableMapper.delete(e);
            });
        }
    }

    /**
     *  查询明细表明细
     * @param condition
     * @return
     */
    public List<AmAssetEnableDt> findOnes(Long indocno){
        List<AmAssetEnableDt> amTransferPlanDtList = amAssetEnableDtMapper.findAllByIlinkno(indocno);
        return amTransferPlanDtList;
    }

    /**
     *  查询附件表明细
     * @param condition
     * @return
     */
    public List<AmAssetEnableAtt> findOneas(Long indocno){
        List<AmAssetEnableAtt> attList = amAssetEnableAttMapper.findAllByIlinkno(indocno);
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
    public AmAssetEnable findOne(Long indocno){
        AmAssetEnable e = amAssetEnableMapper.findOne(indocno);
        if (!StringUtils.isEmpty(e)){
            int count = amAssetEnableDtMapper.findCount();
            e.setIqty(count);
            if (e.getSregid() != null && baseSysUserProvider.findFSysUserByIndocno(e.getSregid()) != null){
                e.setSregnm(baseSysUserProvider.findFSysUserByIndocno(e.getSregid()).getSname());
            }
            // 所属线路
            if(null != e.getIlineid()) {
                e.setSlinenm(baseServiceHelperImpl.getDatadicName(DatadicKeys.CAP_ACCEPTANCE_EQUIP_ILINEID,
                        e.getIlineid() != null ? e.getIlineid() : ""));
            }
            // 管理部门
            if(null != e.getIdeptid()) {
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
    public AmAssetEnable add(AmAssetEnable entity){
        AuthInfo authInfo = super.getAuthInfo();
        entity.setSregid(authInfo.getIndocno());
        entity.setDregt(new Date());
        super.insert(entity);
        return entity;
    }

    /**
     *修改
     **/
    @TransactionalException
    public void updateBasic(AmAssetEnable entity) {
        amAssetEnableMapper.update(entity);
    }

}
