package com.luckyun.tjam.mtAssetMag.asledag.service;


import com.luckyun.core.annotation.TransactionalException;
import com.luckyun.core.data.BaseMapper;
import com.luckyun.core.data.BaseService;
import com.luckyun.tjam.mtAssetMag.asledag.mapper.AmBasicCreateFincInfMapper;
import com.luckyun.tjam.mtAssetMag.asledag.model.AmBasicCreateFincInf;
import com.luckyun.tjam.mtAssetMag.asledag.param.AmBasicCreateFincInfParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class AmBasicCreateFincInfService extends BaseService<AmBasicCreateFincInf> {


    @Autowired
    private AmBasicCreateFincInfMapper amBasicCreateFincInfMapper;


    @Override
    public BaseMapper<AmBasicCreateFincInf> getMapper() {
        return amBasicCreateFincInfMapper;
    }

    /**
     * 添加
     *
     */
    @TransactionalException
    public void add(AmBasicCreateFincInf entity){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
            try {
                if(null != entity.getSdaccountopenperiod() && !"".equals(entity.getSdaccountopenperiod())){
                    Date parse = sdf.parse(entity.getSdaccountopenperiod());
                    entity.setDaccountopenperiod(parse);
                }
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
            super.insert(entity);
    }

    /**
     *修改
     **/
    @TransactionalException
    public void updateBasic(AmBasicCreateFincInfParam entity) {
        List<AmBasicCreateFincInf> delList = entity.getDelList();
        if(!CollectionUtils.isEmpty(delList)){
            delList.forEach(e->{
                amBasicCreateFincInfMapper.update(e);
            });
        }
    }

    /***
     *
     * 删除
     *
     */
    @TransactionalException
    public void delOpr(AmBasicCreateFincInfParam condition){
        List<AmBasicCreateFincInf> delList = condition.getDelList();
        if (!CollectionUtils.isEmpty(delList)){
            delList.forEach(e->{
                amBasicCreateFincInfMapper.delete(e);
            });
        }
    }

}
