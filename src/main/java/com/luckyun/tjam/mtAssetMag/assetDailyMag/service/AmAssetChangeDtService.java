package com.luckyun.tjam.mtAssetMag.assetDailyMag.service;

import java.util.List;

import com.luckyun.tjam.mtAssetMag.asledag.mapper.AmBasicInfMapper;
import com.luckyun.tjam.mtAssetMag.asledag.mapper.AmBasicLocAreaMapper;
import com.luckyun.tjam.mtAssetMag.asledag.mapper.AmBasicManagementInfMapper;
import com.luckyun.tjam.mtAssetMag.asledag.model.AmBasicInf;
import com.luckyun.tjam.mtAssetMag.asledag.model.AmBasicLocArea;
import com.luckyun.tjam.mtAssetMag.asledag.model.AmBasicManagementInf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.luckyun.base.provider.feign.BaseSysDeptProvider;
import com.luckyun.base.provider.feign.BaseSysUserProvider;
import com.luckyun.core.annotation.TransactionalException;
import com.luckyun.core.data.BaseMapper;
import com.luckyun.core.data.BaseService;
import com.luckyun.model.dept.SysDept;
import com.luckyun.model.user.SysAccount;
import com.luckyun.tjam.mtAssetMag.assetDailyMag.mapper.AmAssetChangeDtMapper;
import com.luckyun.tjam.mtAssetMag.assetDailyMag.model.AmAssetChangeDt;
import com.luckyun.tjam.mtAssetMag.assetDailyMag.param.AmAssetChangeDtParam;
import com.luckyun.tjam.prophaseMag.acceptance.common.DatadicKeys;
import com.luckyun.tjam.prophaseMag.acceptance.helper.BaseServiceHelperImpl;

@Service
public class AmAssetChangeDtService extends BaseService<AmAssetChangeDt> {

    @Autowired
    private AmAssetChangeDtMapper amAssetChangeDtMapper;

    @Autowired
    private BaseServiceHelperImpl baseServiceHelperImpl;

    @Autowired
    private BaseSysUserProvider baseSysUserProvider;

    @Autowired
    private BaseSysDeptProvider baseSysDeptProvider;

    @Autowired
    private AmBasicInfMapper amBasicInfMapper;

    @Autowired
    private AmBasicLocAreaMapper amBasicLocAreaMapper;

    @Autowired
    private AmBasicManagementInfMapper amBasicManagementInfMapper;

    @Override
    public BaseMapper<AmAssetChangeDt> getMapper() {
        return amAssetChangeDtMapper;
    }


    /**
     * 条件查询所有
     * @param condition
     * @return
     */
    @SuppressWarnings("unlikely-arg-type")
	public List<AmAssetChangeDt> findAll(AmAssetChangeDtParam condition){
        List<AmAssetChangeDt> all = amAssetChangeDtMapper.findAll(condition);
        if (!CollectionUtils.isEmpty(all)){
            all.forEach(e->{
                // 所属线路
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
                // 责任人
                if(null != e.getIdutyid() && !"".equals(e.getIdutyid())) {
                    SysAccount sysAccount1 = this.baseSysUserProvider.findFSysUserByIndocno(e.getIdutyid());
                    if(null != sysAccount1) {
                        e.setSdutynm(sysAccount1.getSname());
                    }
                }
                //使用人
                if(null != e.getIuserid() && !"".equals(e.getIuserid())) {
                    SysAccount sysAccount = this.baseSysUserProvider.findFSysUserByIndocno(e.getIuserid());
                    e.setSusernm(sysAccount.getSname());
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
  /*  public AmTransferPlanDt delOpr(AmTransferPlanDt condition){
        Long indocno = condition.getIndocno();
        AmTransferPlanDt one = amTransferPlanDtMapper.findOne(indocno);
        if(!StringUtils.isEmpty(one)){
            amTransferPlanDtMapper.delete(one);
        }
        return one;
    }*/
    public void delOpr(AmAssetChangeDtParam condition){
        List<AmAssetChangeDt> delList = condition.getDelList();
        if (!CollectionUtils.isEmpty(delList)){
            delList.forEach(e->{
                amAssetChangeDtMapper.delete(e);
            });
        }
    }

    /**
     *  查询明细
     * @param condition
     * @return
     */
    @SuppressWarnings("unlikely-arg-type")
	public AmAssetChangeDt findOne(Long indocno){
        AmAssetChangeDt e = amAssetChangeDtMapper.findOne(indocno);
        if (!StringUtils.isEmpty(e)){
            // 所属线路
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
            // 责任人
            if(null != e.getIdutyid() && !"".equals(e.getIdutyid())) {
                SysAccount sysAccount1 = this.baseSysUserProvider.findFSysUserByIndocno(e.getIdutyid());
                if(null != sysAccount1) {
                    e.setSdutynm(sysAccount1.getSname());
                }
            }
            //使用人
            if(null != e.getIuserid() && !"".equals(e.getIuserid())) {
                SysAccount sysAccount = this.baseSysUserProvider.findFSysUserByIndocno(e.getIuserid());
                e.setSusernm(sysAccount.getSname());
            }
        }
        return e;
    }

    /**
     * 添加
     *
     */
    @TransactionalException
    public void add(AmAssetChangeDtParam entity){
        List<AmAssetChangeDt> addList = entity.getAddList();
        if (!CollectionUtils.isEmpty(addList)){
            addList.forEach(e->{
                super.insert(e);
            });
        }
    }

    /**
     *修改
     **/
    @TransactionalException
    public void updateBasic(AmAssetChangeDtParam entity) {
        List<AmAssetChangeDt> delList = entity.getDelList();
        if(!CollectionUtils.isEmpty(delList)){
            delList.forEach(e->{
                amAssetChangeDtMapper.update(e);
                AmAssetChangeDt one1 = amAssetChangeDtMapper.findOne(e.getIndocno());
                if (!StringUtils.isEmpty(one1.getIsourceid())){
                    AmBasicInf one = amBasicInfMapper.findOne(one1.getIsourceid());
                    List<AmBasicLocArea> amBasicLocAreaList = amBasicLocAreaMapper.findAllByIbillid(one.getIndocno());
                    if (!CollectionUtils.isEmpty(amBasicLocAreaList)){
                        for (AmBasicLocArea lo: amBasicLocAreaList){
                            lo.setSposarea(e.getSposareaNew());
                            lo.setSposbuild(e.getSposbuildNew());
                            lo.setSposroom(e.getSposroomNew());
                            amBasicLocAreaMapper.update(lo);
                        }
                    }
                    List<AmBasicManagementInf> managementInfList = amBasicManagementInfMapper.findAllByIbillid(one.getIndocno());
                    if (!CollectionUtils.isEmpty(managementInfList)){
                        for (AmBasicManagementInf man:managementInfList){
                            man.setIdutyid(e.getIdutyidNew());
                            man.setIuserid(e.getIuseridNew());
                            amBasicManagementInfMapper.update(man);
                        }
                    }
                }
            });
        }
    }

    /**
     *批量变更
     **/
    @TransactionalException
    public void updateShu(AmAssetChangeDt entity){
        amAssetChangeDtMapper.updateShu(entity);
    }
}
