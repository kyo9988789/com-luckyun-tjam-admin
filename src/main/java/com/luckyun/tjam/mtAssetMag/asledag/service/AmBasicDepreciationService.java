package com.luckyun.tjam.mtAssetMag.asledag.service;


import com.luckyun.core.annotation.TransactionalException;
import com.luckyun.core.data.BaseMapper;
import com.luckyun.core.data.BaseService;
import com.luckyun.tjam.mtAssetMag.asledag.mapper.AmBasicDepreciationMapper;
import com.luckyun.tjam.mtAssetMag.asledag.model.AmBasicDepreciation;
import com.luckyun.tjam.mtAssetMag.asledag.param.AmBasicDepreciationParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class AmBasicDepreciationService extends BaseService<AmBasicDepreciation> {

    @Autowired
    private AmBasicDepreciationMapper amBasicDepreciationMapper;

    @Override
    public BaseMapper<AmBasicDepreciation> getMapper() {
        return amBasicDepreciationMapper;
    }

    /**
     * 添加
     *
     */
    @TransactionalException
    public void add(AmBasicDepreciation entity){
        super.insert(entity);
    }

    /**
     *修改
     **/
    @TransactionalException
    public void updateBasic(AmBasicDepreciationParam entity) {
        List<AmBasicDepreciation> delList = entity.getDelList();
        if(!CollectionUtils.isEmpty(delList)){
            delList.forEach(e->{
                amBasicDepreciationMapper.update(e);
            });
        }
    }

    /***
     *
     * 删除
     *
     */
    @TransactionalException
    public void delOpr(AmBasicDepreciationParam condition){
        List<AmBasicDepreciation> delList = condition.getDelList();
        if (!CollectionUtils.isEmpty(delList)){
            delList.forEach(e->{
                amBasicDepreciationMapper.delete(e);
            });
        }
    }

}
