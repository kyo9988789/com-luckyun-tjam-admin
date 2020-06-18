package com.luckyun.tjam.prophaseMag.formfiasset.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luckyun.core.data.BaseMapper;
import com.luckyun.core.data.BaseService;
import com.luckyun.tjam.prophaseMag.formfiasset.entity.AmFormFiassetDtComp;
import com.luckyun.tjam.prophaseMag.formfiasset.mapper.AmFormFiassetDtCompMapper;

/**
 * Created by whs on 2020/5/25.
 */
@Service
public class AmFormFiassetDtCompService extends BaseService<AmFormFiassetDtComp>{

    @Autowired
    private AmFormFiassetDtCompMapper amFormFiassetDtCompMapper;
    @Override
    public BaseMapper<AmFormFiassetDtComp> getMapper() {
        return amFormFiassetDtCompMapper;
    }

    @Transactional
    public List<AmFormFiassetDtComp> findAllByIlinkno(Long ilinkno){
        List<AmFormFiassetDtComp> allByIlinkno = amFormFiassetDtCompMapper.findAllByIlinkno(ilinkno);
        return allByIlinkno;
    }
}
