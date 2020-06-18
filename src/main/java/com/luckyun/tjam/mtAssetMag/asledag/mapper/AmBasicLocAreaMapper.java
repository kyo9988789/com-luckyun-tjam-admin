package com.luckyun.tjam.mtAssetMag.asledag.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.luckyun.core.data.BaseMapper;
import com.luckyun.tjam.mtAssetMag.asledag.model.AmBasicLocArea;


@Repository
public interface AmBasicLocAreaMapper extends BaseMapper<AmBasicLocArea> {

    /**
     *  通过外键查询所有明细
     * @param ibillid
     * @return
     */
    List<AmBasicLocArea> findAllByIbillid(@Param("ilinkno") Long ilinkno);
}
