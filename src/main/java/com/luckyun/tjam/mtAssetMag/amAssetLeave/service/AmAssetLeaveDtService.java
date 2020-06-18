package com.luckyun.tjam.mtAssetMag.amAssetLeave.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.luckyun.base.provider.feign.BaseSysUserProvider;
import com.luckyun.core.annotation.TransactionalException;
import com.luckyun.core.data.BaseMapper;
import com.luckyun.core.data.BaseService;
import com.luckyun.model.user.SysAccount;
import com.luckyun.tjam.mtAssetMag.amAssetLeave.mapper.AmAssetLeaveDtMapper;
import com.luckyun.tjam.mtAssetMag.amAssetLeave.model.AmAssetLeaveDt;
import com.luckyun.tjam.mtAssetMag.amAssetLeave.param.AmAssetLeaveDtParam;
import com.luckyun.tjam.mtAssetMag.asledag.mapper.AmBasicInfMapper;
import com.luckyun.tjam.mtAssetMag.asledag.model.AmBasicInf;
import com.luckyun.tjam.prophaseMag.acceptance.common.DatadicKeys;
import com.luckyun.tjam.prophaseMag.acceptance.helper.BaseServiceHelperImpl;

@Service
public class AmAssetLeaveDtService extends BaseService<AmAssetLeaveDt> {

    @Autowired
    private AmAssetLeaveDtMapper amAssetLeaveDtMapper;

    @Autowired
    private BaseServiceHelperImpl baseServiceHelperImpl;

    @Autowired
    private BaseSysUserProvider baseSysUserProvider;

    @Autowired
    private AmBasicInfMapper amBasicInfMapper;


    @Override
    public BaseMapper<AmAssetLeaveDt> getMapper() {
        return amAssetLeaveDtMapper;
    }

    /**
     * 条件查询所有
     * @param condition
     * @return
     */
    public List<AmAssetLeaveDt> findAll(AmAssetLeaveDtParam condition){
        List<AmAssetLeaveDt> all = amAssetLeaveDtMapper.findAll(condition);
        if (!CollectionUtils.isEmpty(all)){
            all.forEach(e->{
                // 所属线路
                if(null != e.getIlineid()) {
                    e.setSlinenm(baseServiceHelperImpl.getDatadicName(DatadicKeys.CAP_ACCEPTANCE_EQUIP_ILINEID,
                            e.getIlineid() != null ? e.getIlineid() : ""));
                }
                // 责任人
                if(null != e.getIdutyid()) {
                    SysAccount sysAccount1 = this.baseSysUserProvider.findFSysUserByIndocno(e.getIdutyid());
                    if(null != sysAccount1) {
                        e.setSdutynm(sysAccount1.getSname());
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
    public void delOpr(AmAssetLeaveDtParam condition){
        List<AmAssetLeaveDt> delList = condition.getDelList();
        if (!CollectionUtils.isEmpty(delList)){
            delList.forEach(e->{
                amAssetLeaveDtMapper.delete(e);
            });
        }
    }

    /**
     *  查询明细
     * @param condition
     * @return
     */
    public AmAssetLeaveDt findOne(Long indocno){
        AmAssetLeaveDt e = amAssetLeaveDtMapper.findOne(indocno);
        if (!StringUtils.isEmpty(e)){
            // 所属线路
            if(null != e.getIlineid()) {
                e.setSlinenm(baseServiceHelperImpl.getDatadicName(DatadicKeys.CAP_ACCEPTANCE_EQUIP_ILINEID,
                        e.getIlineid() != null ? e.getIlineid() : ""));
            }
            // 责任人
            if(null != e.getIdutyid()) {
                SysAccount sysAccount1 = this.baseSysUserProvider.findFSysUserByIndocno(e.getIdutyid());
                if(null != sysAccount1) {
                    e.setSdutynm(sysAccount1.getSname());
                }
            }
        }
        return e;
    }

    /**
     * 添加
     *
     */
    @TransactionalException
    public void add(AmAssetLeaveDtParam entity){
        List<AmAssetLeaveDt> addList = entity.getAddList();
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
    public void updateBasic(AmAssetLeaveDtParam entity) {
        List<AmAssetLeaveDt> delList = entity.getDelList();
        if(!CollectionUtils.isEmpty(delList)){
            delList.forEach(e->{
                AmAssetLeaveDt one1 = amAssetLeaveDtMapper.findOne(e.getIndocno());
                if (!StringUtils.isEmpty(one1.getIsourceid())){
                    AmBasicInf one = amBasicInfMapper.findOne(one1.getIsourceid());
                    one.setIamstate(4);
                    amBasicInfMapper.update(one);
                }
                amAssetLeaveDtMapper.update(e);
            });
        }

    }

}
