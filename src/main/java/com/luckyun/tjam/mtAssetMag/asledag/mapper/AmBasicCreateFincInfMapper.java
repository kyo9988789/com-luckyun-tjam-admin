package com.luckyun.tjam.mtAssetMag.asledag.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.luckyun.core.data.BaseMapper;
import com.luckyun.tjam.mtAssetMag.asledag.model.AmBasicCreateFincInf;

@Repository
public interface AmBasicCreateFincInfMapper extends BaseMapper<AmBasicCreateFincInf> {

    /**
     *  通过外键查询所有明细
     * @param ibillid
     * @return
     */
    List<AmBasicCreateFincInf> findAllByIbillid(@Param("ilinkno") Long ilinkno);
}
