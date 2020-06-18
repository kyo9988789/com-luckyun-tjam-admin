package com.luckyun.tjam.mtAssetMag.asledag.service;


import com.luckyun.core.annotation.TransactionalException;
import com.luckyun.core.data.BaseMapper;
import com.luckyun.core.data.BaseService;
import com.luckyun.tjam.mtAssetMag.asledag.mapper.AmBasicValueInfMapper;
import com.luckyun.tjam.mtAssetMag.asledag.model.AmBasicValueInf;
import com.luckyun.tjam.mtAssetMag.asledag.param.AmBasicValueInfParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class AmBasicValueInfService extends BaseService<AmBasicValueInf> {

    @Autowired
    private AmBasicValueInfMapper amBasicValueInfMapper;

    @Override
    public BaseMapper<AmBasicValueInf> getMapper() {
        return amBasicValueInfMapper;
    }



    /**
     * 添加
     *
     */
    @TransactionalException
    public void add(AmBasicValueInf entity){
       super.insert(entity);
    }

    /**
     *修改
     **/
    @TransactionalException
    public void updateBasic(AmBasicValueInfParam entity) {
        List<AmBasicValueInf> delList = entity.getDelList();
        if(!CollectionUtils.isEmpty(delList)){
            delList.forEach(e->{
                amBasicValueInfMapper.update(e);
            });
        }
    }

    /***
     *
     * 删除
     *
     */
    @TransactionalException
    public void delOpr(AmBasicValueInfParam condition){
        List<AmBasicValueInf> delList = condition.getDelList();
        if (!CollectionUtils.isEmpty(delList)){
            delList.forEach(e->{
                amBasicValueInfMapper.delete(e);
            });
        }
    }
}
