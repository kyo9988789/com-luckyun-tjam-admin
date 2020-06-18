package com.luckyun.tjam.mtAssetMag.asledag.service;


import com.luckyun.core.annotation.TransactionalException;
import com.luckyun.core.data.BaseMapper;
import com.luckyun.core.data.BaseService;
import com.luckyun.tjam.mtAssetMag.asledag.mapper.AmBasicManagementInfMapper;
import com.luckyun.tjam.mtAssetMag.asledag.model.AmBasicManagementInf;
import com.luckyun.tjam.mtAssetMag.asledag.param.AmBasicManagementInfParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class AmBasicManagementInfService extends BaseService<AmBasicManagementInf> {

    @Autowired
    private AmBasicManagementInfMapper amBasicManagementInfMapper;

    @Override
    public BaseMapper<AmBasicManagementInf> getMapper() {
        return amBasicManagementInfMapper;
    }

    /**
     * 添加
     *
     */
    @TransactionalException
    public void add(AmBasicManagementInf entity){
       super.insert(entity);
    }

    /**
     *修改
     **/
    @TransactionalException
    public void updateBasic(AmBasicManagementInfParam entity) {
        List<AmBasicManagementInf> delList = entity.getDelList();
        if(!CollectionUtils.isEmpty(delList)){
            delList.forEach(e->{
                amBasicManagementInfMapper.update(e);
            });
        }
    }

    /***
     *
     * 删除
     *
     */
    @TransactionalException
    public void delOpr(AmBasicManagementInfParam condition){
        List<AmBasicManagementInf> delList = condition.getDelList();
        if (!CollectionUtils.isEmpty(delList)){
            delList.forEach(e->{
                amBasicManagementInfMapper.delete(e);
            });
        }
    }
}
