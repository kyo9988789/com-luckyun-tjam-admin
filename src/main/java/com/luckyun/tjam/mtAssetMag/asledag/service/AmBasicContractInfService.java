package com.luckyun.tjam.mtAssetMag.asledag.service;


import com.luckyun.core.annotation.TransactionalException;
import com.luckyun.core.data.BaseMapper;
import com.luckyun.core.data.BaseService;
import com.luckyun.tjam.mtAssetMag.asledag.mapper.AmBasicContractInfMapper;
import com.luckyun.tjam.mtAssetMag.asledag.model.AmBasicContractInf;
import com.luckyun.tjam.mtAssetMag.asledag.param.AmBasicContractInfParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class AmBasicContractInfService extends BaseService<AmBasicContractInf> {


    @Autowired
    private AmBasicContractInfMapper amBasicContractInfMapper;

    @Override
    public BaseMapper<AmBasicContractInf> getMapper() {
        return amBasicContractInfMapper;
    }

    /**
     * 添加
     *
     */
    @TransactionalException
    public void add(AmBasicContractInf entity){
        super.insert(entity);
    }

    /**
     *修改
     **/
    @TransactionalException
    public void updateBasic(AmBasicContractInfParam entity) {
        List<AmBasicContractInf> delList = entity.getDelList();
        if(!CollectionUtils.isEmpty(delList)){
            delList.forEach(e->{
                amBasicContractInfMapper.update(e);
            });
        }
    }

    /***
     *
     * 删除
     *
     */
    @TransactionalException
    public void delOpr(AmBasicContractInfParam condition){
        List<AmBasicContractInf> delList = condition.getDelList();
        if (!CollectionUtils.isEmpty(delList)){
            delList.forEach(e->{
                amBasicContractInfMapper.delete(e);
            });
        }
    }
}
