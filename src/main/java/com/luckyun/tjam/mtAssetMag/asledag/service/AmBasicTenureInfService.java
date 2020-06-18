package com.luckyun.tjam.mtAssetMag.asledag.service;

import com.luckyun.core.annotation.TransactionalException;
import com.luckyun.core.data.BaseMapper;
import com.luckyun.core.data.BaseService;
import com.luckyun.tjam.mtAssetMag.asledag.mapper.AmBasicTenureInfMapper;
import com.luckyun.tjam.mtAssetMag.asledag.model.AmBasicTenureInf;
import com.luckyun.tjam.mtAssetMag.asledag.param.AmBasicTenureInfParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class AmBasicTenureInfService extends BaseService<AmBasicTenureInf> {

    @Autowired
    private AmBasicTenureInfMapper amBasicTenureInfMapper;

    @Override
    public BaseMapper<AmBasicTenureInf> getMapper() {
        return amBasicTenureInfMapper;
    }

    /**
     * 添加
     *
     */
    @TransactionalException
    public void add(AmBasicTenureInf entity){
       super.insert(entity);
    }

    /**
     *修改
     **/
    @TransactionalException
    public void updateBasic(AmBasicTenureInfParam entity) {
        List<AmBasicTenureInf> delList = entity.getDelList();
        if(!CollectionUtils.isEmpty(delList)){
            delList.forEach(e->{
                amBasicTenureInfMapper.update(e);
            });
        }
    }

    /***
     *
     * 删除
     *
     */
    @TransactionalException
    public void delOpr(AmBasicTenureInfParam condition){
        List<AmBasicTenureInf> delList = condition.getDelList();
        if (!CollectionUtils.isEmpty(delList)){
            delList.forEach(e->{
                amBasicTenureInfMapper.delete(e);
            });
        }
    }
}
