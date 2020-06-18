package com.luckyun.tjam.mtAssetMag.amAssetEnable.service;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
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
import com.luckyun.tjam.mtAssetMag.amAssetEnable.mapper.AmAssetEnableDtMapper;
import com.luckyun.tjam.mtAssetMag.amAssetEnable.model.AmAssetEnableDt;
import com.luckyun.tjam.mtAssetMag.amAssetEnable.param.AmAssetEnableDtParam;
import com.luckyun.tjam.mtAssetMag.asledag.mapper.AmBasicInfMapper;
import com.luckyun.tjam.mtAssetMag.asledag.model.AmBasicInf;
import com.luckyun.tjam.prophaseMag.acceptance.common.DatadicKeys;
import com.luckyun.tjam.prophaseMag.acceptance.helper.BaseServiceHelperImpl;

@Service
public class AmAssetEnableDtService extends BaseService<AmAssetEnableDt> {

    @Autowired
    private AmAssetEnableDtMapper amAssetEnableDtMapper;

    @Autowired
    private AmBasicInfMapper amBasicInfMapper;

    @Autowired
    private BaseServiceHelperImpl baseServiceHelperImpl;

    @Autowired
    private BaseSysUserProvider baseSysUserProvider;

    @Override
    public BaseMapper<AmAssetEnableDt> getMapper() {
        return amAssetEnableDtMapper;
    }

    /**
     * 条件查询所有
     * @param condition
     * @return
     */
    public List<AmAssetEnableDt> findAll(AmAssetEnableDtParam condition){
        List<AmAssetEnableDt> all = amAssetEnableDtMapper.findAll(condition);
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
    public void delOpr(AmAssetEnableDtParam condition){
        List<AmAssetEnableDt> delList = condition.getDelList();
        if (!CollectionUtils.isEmpty(delList)){
            delList.forEach(e->{
                amAssetEnableDtMapper.delete(e);
            });
        }
    }

    /**
     *  查询明细
     * @param condition
     * @return
     */
    public AmAssetEnableDt findOne(Long indocno){
        AmAssetEnableDt e = amAssetEnableDtMapper.findOne(indocno);
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
    public void add(AmAssetEnableDtParam entity)  {
        List<AmAssetEnableDt> addList = entity.getAddList();
        if (!CollectionUtils.isEmpty(addList)){
            addList.forEach(e->{
                AmBasicInf byIamstate = amBasicInfMapper.findByIamstate();
                if(!StringUtils.isEmpty(byIamstate)){
                    LocalDate startDate = byIamstate.getDstartfreetime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    LocalDate endDate = LocalDate.now();
                    Period period = Period.between(startDate, endDate);
                    period.getMonths();
                    e.setSfreetime(String.valueOf(period.getMonths()));
                }
                super.insert(e);
            });
        }
    }

    /**
     *修改
     **/
    @TransactionalException
    public void updateBasic(AmAssetEnableDtParam entity) {
        List<AmAssetEnableDt> delList = entity.getDelList();
        if(!CollectionUtils.isEmpty(delList)){
            delList.forEach(e->{
                amAssetEnableDtMapper.update(e);
            });
        }
    }

}
