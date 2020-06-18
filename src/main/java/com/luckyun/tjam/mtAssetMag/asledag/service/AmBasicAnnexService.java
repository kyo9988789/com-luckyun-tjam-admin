package com.luckyun.tjam.mtAssetMag.asledag.service;


import com.luckyun.core.annotation.TransactionalException;
import com.luckyun.core.data.BaseMapper;
import com.luckyun.core.data.BaseService;
import com.luckyun.core.data.filter.AuthInfo;
import com.luckyun.tjam.mtAssetMag.asledag.mapper.AmBasicAnnexMapper;
import com.luckyun.tjam.mtAssetMag.asledag.model.AmBasicAnnex;
import com.luckyun.tjam.mtAssetMag.asledag.param.AmBasicAnnexParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

@Service
public class AmBasicAnnexService extends BaseService<AmBasicAnnex> {

    @Autowired
    private AmBasicAnnexMapper amBasicAnnexMapper;

    @Override
    public BaseMapper<AmBasicAnnex> getMapper() {
        return amBasicAnnexMapper;
    }

    /**
     * 添加
     *
     */
    @TransactionalException
    public AmBasicAnnex add(AmBasicAnnex entity){
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
    public void updateBasic(AmBasicAnnexParam entity) {
        List<AmBasicAnnex> delList = entity.getDelList();
        if(!CollectionUtils.isEmpty(delList)){
            delList.forEach(e->{
                amBasicAnnexMapper.update(e);
            });
        }
    }

    /***
     *
     * 删除
     *
     */
    @TransactionalException
    public void delOpr(AmBasicAnnexParam condition){
        List<AmBasicAnnex> delList = condition.getDelList();
        if (!CollectionUtils.isEmpty(delList)){
            delList.forEach(e->{
                amBasicAnnexMapper.delete(e);
            });
        }
    }
}
