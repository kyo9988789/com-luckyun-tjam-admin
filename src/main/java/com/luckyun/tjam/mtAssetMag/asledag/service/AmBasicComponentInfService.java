package com.luckyun.tjam.mtAssetMag.asledag.service;

import com.luckyun.core.annotation.TransactionalException;
import com.luckyun.core.data.BaseMapper;
import com.luckyun.core.data.BaseService;
import com.luckyun.tjam.mtAssetMag.asledag.mapper.AmBasicComponentInfMapper;
import com.luckyun.tjam.mtAssetMag.asledag.model.AmBasicComponentInf;
import com.luckyun.tjam.mtAssetMag.asledag.param.AmBasicComponentInfParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class AmBasicComponentInfService extends BaseService<AmBasicComponentInf> {


    @Autowired
    private AmBasicComponentInfMapper amBasicComponentInfMapper;

    @Override
    public BaseMapper<AmBasicComponentInf> getMapper() {
        return amBasicComponentInfMapper;
    }

    /**
     * 添加
     *
     */
    @TransactionalException
    public void add(AmBasicComponentInf entity){
        super.insert(entity);
    }

    /**
     *修改
     **/
    @TransactionalException
    public void updateBasic(AmBasicComponentInfParam entity) {
        List<AmBasicComponentInf> delList = entity.getDelList();
        if(!CollectionUtils.isEmpty(delList)){
            delList.forEach(e->{
                amBasicComponentInfMapper.update(e);
            });
        }
    }

    /***
     *
     * 删除
     *
     */
    @TransactionalException
    public void delOpr(AmBasicComponentInfParam condition){
        List<AmBasicComponentInf> delList = condition.getDelList();
        if (!CollectionUtils.isEmpty(delList)){
            delList.forEach(e->{
                amBasicComponentInfMapper.delete(e);
            });
        }
    }
}
