package com.luckyun.tjam.mtAssetMag.amTranPlan.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.luckyun.core.annotation.TransactionalException;
import com.luckyun.core.data.BaseMapper;
import com.luckyun.core.data.BaseService;
import com.luckyun.core.data.filter.AuthInfo;
import com.luckyun.tjam.mtAssetMag.amTranPlan.mapper.AmTransferPlanDtCompMapper;
import com.luckyun.tjam.mtAssetMag.amTranPlan.model.AmTransferPlanDtComp;
import com.luckyun.tjam.mtAssetMag.amTranPlan.param.AmTransferPlanDtCompParam;

@Service
public class AmTransferPlanDtCompService extends BaseService<AmTransferPlanDtComp> {

    @Autowired
    private AmTransferPlanDtCompMapper amTransferPlanDtCompMapper;

    @Override
    public BaseMapper<AmTransferPlanDtComp> getMapper() {
        return amTransferPlanDtCompMapper;
    }

    /***
     *
     * 删除
     *
     */
    @TransactionalException
    public void delOpr(AmTransferPlanDtCompParam condition){
        List<AmTransferPlanDtComp> delList = condition.getDelList();
        if (!CollectionUtils.isEmpty(delList)){
            delList.forEach(e->{
                amTransferPlanDtCompMapper.delete(e);
            });
        }
    }

    /**
     *  查询明细
     * @param condition
     * @return
     */
    public AmTransferPlanDtComp findOne(Long indocno){
        AmTransferPlanDtComp one = amTransferPlanDtCompMapper.findOne(indocno);
        return one;
    }

    /**
     * 添加
     *
     */
    @TransactionalException
    public AmTransferPlanDtComp add(AmTransferPlanDtComp entity){
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
    public void updateBasic(AmTransferPlanDtComp entity) {
        amTransferPlanDtCompMapper.update(entity);
    }

    @Transactional
    public List<AmTransferPlanDtComp> findAllByIlinkno(String sfcode){
        List<AmTransferPlanDtComp> allByIlinkno = amTransferPlanDtCompMapper.findSonByIparent(sfcode);
        return allByIlinkno;
    }
}
