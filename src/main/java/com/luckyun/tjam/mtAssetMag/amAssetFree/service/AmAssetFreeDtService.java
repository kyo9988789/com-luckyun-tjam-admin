package com.luckyun.tjam.mtAssetMag.amAssetFree.service;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.List;

import com.luckyun.tjam.mtAssetMag.asledag.mapper.AmBasicInfMapper;
import com.luckyun.tjam.mtAssetMag.asledag.model.AmBasicInf;
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
import com.luckyun.tjam.mtAssetMag.amAssetFree.mapper.AmAssetFreeDtMapper;
import com.luckyun.tjam.mtAssetMag.amAssetFree.model.AmAssetFreeDt;
import com.luckyun.tjam.mtAssetMag.amAssetFree.param.AmAssetFreeDtParam;
import com.luckyun.tjam.prophaseMag.acceptance.common.DatadicKeys;
import com.luckyun.tjam.prophaseMag.acceptance.helper.BaseServiceHelperImpl;

@Service
public class AmAssetFreeDtService extends BaseService<AmAssetFreeDt> {

    @Autowired
    private AmAssetFreeDtMapper amAssetFreeDtMapper;

    @Autowired
    private BaseServiceHelperImpl baseServiceHelperImpl;

    @Autowired
    private BaseSysUserProvider baseSysUserProvider;

    @Autowired
    private BaseSysDeptProvider baseSysDeptProvider;

    @Autowired
    private AmBasicInfMapper amBasicInfMapper;

    @Override
    public BaseMapper<AmAssetFreeDt> getMapper() {
        return amAssetFreeDtMapper;
    }

    /**
     * 条件查询所有
     * @param condition
     * @return
     */
    public List<AmAssetFreeDt> findAll(AmAssetFreeDtParam condition){
        List<AmAssetFreeDt> all = amAssetFreeDtMapper.findAll(condition);
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
    public void delOpr(AmAssetFreeDtParam condition){
        List<AmAssetFreeDt> delList = condition.getDelList();
        if (!CollectionUtils.isEmpty(delList)){
            delList.forEach(e->{
                amAssetFreeDtMapper.delete(e);
            });
        }
    }

    /**
     *  查询明细
     * @param condition
     * @return
     */
    public AmAssetFreeDt findOne(Long indocno){
        AmAssetFreeDt e = amAssetFreeDtMapper.findOne(indocno);
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
        }
        return e;
    }

    /**
     * 添加
     *
     */
    @TransactionalException
    public void add(AmAssetFreeDtParam entity){
        List<AmAssetFreeDt> addList = entity.getAddList();
        if (!CollectionUtils.isEmpty(addList)){
            addList.forEach(e->{
                List<AmBasicInf> bydstartList = amBasicInfMapper.findBydstart();
                if (!CollectionUtils.isEmpty(bydstartList)){
                    for (AmBasicInf bydstart:bydstartList){
                        LocalDate startDate = bydstart.getDstart().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                        LocalDate endDate = LocalDate.now();
                        Period period = Period.between(startDate, endDate);
                        period.getMonths();
                        e.setServicetime(String.valueOf(period.getMonths()));
                    }
                }
                super.insert(e);
            });
        }
    }

    /**
     *修改
     **/
    @TransactionalException
    public void updateBasic(AmAssetFreeDtParam entity) {
        List<AmAssetFreeDt> delList = entity.getDelList();
        if(!CollectionUtils.isEmpty(delList)){
            delList.forEach(e->{
                AmAssetFreeDt one1 = amAssetFreeDtMapper.findOne(e.getIndocno());
                if (!StringUtils.isEmpty(one1.getIsourceid())){
                    AmBasicInf one = amBasicInfMapper.findOne(one1.getIsourceid());
                    one.setIamstate(3);
                    amBasicInfMapper.update(one);
                }
                amAssetFreeDtMapper.update(e);
            });
        }
    }

}
