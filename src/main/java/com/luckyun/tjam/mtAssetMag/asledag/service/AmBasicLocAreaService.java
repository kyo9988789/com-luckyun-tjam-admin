package com.luckyun.tjam.mtAssetMag.asledag.service;

import com.luckyun.core.annotation.TransactionalException;
import com.luckyun.core.data.BaseMapper;
import com.luckyun.core.data.BaseService;
import com.luckyun.tjam.mtAssetMag.asledag.mapper.AmBasicLocAreaMapper;
import com.luckyun.tjam.mtAssetMag.asledag.model.AmBasicLocArea;
import com.luckyun.tjam.mtAssetMag.asledag.param.AmBasicLocAreaParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class AmBasicLocAreaService extends BaseService<AmBasicLocArea> {

    @Autowired
    private AmBasicLocAreaMapper amBasicLocAreaMapper;

    @Override
    public BaseMapper<AmBasicLocArea> getMapper() {
        return amBasicLocAreaMapper;
    }

    /**
     * 添加
     *
     */
    @TransactionalException
    public void add(AmBasicLocArea entity){
        super.insert(entity);
    }

    /**
     *修改
     **/
    @TransactionalException
    public void updateBasic(AmBasicLocAreaParam entity) {
        List<AmBasicLocArea> delList = entity.getDelList();
        if(!CollectionUtils.isEmpty(delList)){
            delList.forEach(e->{
                amBasicLocAreaMapper.update(e);
            });
        }
    }

    /***
     *
     * 删除
     *
     */
    @TransactionalException
    public void delOpr(AmBasicLocAreaParam condition){
        List<AmBasicLocArea> delList = condition.getDelList();
        if (!CollectionUtils.isEmpty(delList)){
            delList.forEach(e->{
                amBasicLocAreaMapper.delete(e);
            });
        }
    }

}
