package com.luckyun.tjam.mtAssetMag.assetLowValueTransfer.service;

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
import com.luckyun.model.dept.SysDept;
import com.luckyun.model.user.SysAccount;
import com.luckyun.tjam.mtAssetMag.assetLowValueTransfer.mapper.AmDurLvConsTransferDtMapper;
import com.luckyun.tjam.mtAssetMag.assetLowValueTransfer.model.AmDurLvConsTransferDt;
import com.luckyun.tjam.mtAssetMag.assetLowValueTransfer.param.AmDurLvConsTransferDtParam;
import com.luckyun.tjam.prophaseMag.acceptance.common.DatadicKeys;
import com.luckyun.tjam.prophaseMag.acceptance.helper.BaseServiceHelperImpl;

@Service
public class AmDurLvConsTransferDtService extends BaseService<AmDurLvConsTransferDt> {

    @Autowired
    private AmDurLvConsTransferDtMapper amDurLvConsTransferDtMapper;

    @Autowired
    private BaseServiceHelperImpl baseServiceHelperImpl;

    @Autowired
    private BaseSysUserProvider baseSysUserProvider;

    @Autowired
    private BaseSysDeptProvider baseSysDeptProvider;

    @Override
    public BaseMapper<AmDurLvConsTransferDt> getMapper() {
        return amDurLvConsTransferDtMapper;
    }

    /**
     * 条件查询所有
     * @param condition
     * @return
     */
    public List<AmDurLvConsTransferDt> findAll(AmDurLvConsTransferDtParam condition){
        List<AmDurLvConsTransferDt> all = amDurLvConsTransferDtMapper.findAll(condition);
        if (!CollectionUtils.isEmpty(all)){
            all.forEach(e->{
                // 所属线路
                if(null != e.getIlineid()) {
                    e.setSlinenm(baseServiceHelperImpl.getDatadicName(DatadicKeys.CAP_ACCEPTANCE_EQUIP_ILINEID,
                            e.getIlineid() != null ? e.getIlineid() : ""));
                }
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
                //使用人
                if(null != e.getIuserid()) {
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
    public void delOpr(AmDurLvConsTransferDtParam condition){
        List<AmDurLvConsTransferDt> delList = condition.getDelList();
        if (!CollectionUtils.isEmpty(delList)){
            delList.forEach(e->{
                amDurLvConsTransferDtMapper.delete(e);
            });
        }
    }

    /**
     *  查询明细
     * @param condition
     * @return
     */
    public AmDurLvConsTransferDt findOne(Long indocno){
        AmDurLvConsTransferDt e = amDurLvConsTransferDtMapper.findOne(indocno);
        if (!StringUtils.isEmpty(e)){
            // 所属线路
            if(null != e.getIlineid()) {
                e.setSlinenm(baseServiceHelperImpl.getDatadicName(DatadicKeys.CAP_ACCEPTANCE_EQUIP_ILINEID,
                        e.getIlineid() != null ? e.getIlineid() : ""));
            }
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
            //使用人
            if(null != e.getIuserid()) {
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
    public void add(AmDurLvConsTransferDtParam entity){
        List<AmDurLvConsTransferDt> addList = entity.getAddList();
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
    public void updateBasic(AmDurLvConsTransferDtParam entity) {
        List<AmDurLvConsTransferDt> delList = entity.getDelList();
        if(!CollectionUtils.isEmpty(delList)){
            delList.forEach(e->{
                amDurLvConsTransferDtMapper.update(e);
            });
        }
    }
}
