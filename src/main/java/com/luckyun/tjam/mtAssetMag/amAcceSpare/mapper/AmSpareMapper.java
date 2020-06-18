package com.luckyun.tjam.mtAssetMag.amAcceSpare.mapper;



import com.luckyun.core.data.BaseMapper;
import com.luckyun.tjam.mtAssetMag.amAcceSpare.model.AmSpare;
import com.luckyun.tjam.mtAssetMag.amAcceSpare.param.AmSpareParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AmSpareMapper extends BaseMapper<AmSpare> {

    /**
     *  查询所有
     * @param condition
     * @return
     */

    List<AmSpare> findAll(@Param("condition") AmSpareParam condition);

    /**
     *  查询明细
     * @param condition
     * @return
     */
    AmSpare findOne(@Param("indocno") Long indocno);
}
