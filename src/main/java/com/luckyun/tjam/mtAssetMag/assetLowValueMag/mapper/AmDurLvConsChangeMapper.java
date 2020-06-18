package com.luckyun.tjam.mtAssetMag.assetLowValueMag.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.luckyun.core.data.BaseMapper;
import com.luckyun.tjam.mtAssetMag.assetLowValueMag.model.AmDurLvConsChange;
import com.luckyun.tjam.mtAssetMag.assetLowValueMag.param.AmDurLvConsChangeParam;

@Repository
public interface AmDurLvConsChangeMapper extends BaseMapper<AmDurLvConsChange> {

    /**
     *  查询所有
     * @param condition
     * @return
     */
    List<AmDurLvConsChange> findAll(@Param("condition") AmDurLvConsChangeParam condition);

    /**
     *  查询明细
     * @param condition
     * @return
     */
    AmDurLvConsChange findOne(@Param("indocno") Long indocno);

    /**
     * 查询当前天最大计划编号
     *
     * @param splannm
     * @return
     */
    List<AmDurLvConsChange> findMaxSappmtno(@Param("condition") AmDurLvConsChangeParam condition);
}
