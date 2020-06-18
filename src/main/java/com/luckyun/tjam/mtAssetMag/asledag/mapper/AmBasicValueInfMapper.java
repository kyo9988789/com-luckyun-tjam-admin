package com.luckyun.tjam.mtAssetMag.asledag.mapper;

import com.luckyun.core.data.BaseMapper;
import com.luckyun.tjam.mtAssetMag.asledag.model.AmBasicValueInf;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AmBasicValueInfMapper extends BaseMapper<AmBasicValueInf> {

    /**
     *  通过外键查询所有明细
     * @param ibillid
     * @return
     */

    List<AmBasicValueInf> findAllByIbillid(@Param("ilinkno") Long ilinkno);
}
