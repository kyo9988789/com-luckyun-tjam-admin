package com.luckyun.tjam.mtAssetMag.assetDailyMag.mapper;

import com.luckyun.core.data.BaseMapper;
import com.luckyun.tjam.mtAssetMag.assetDailyMag.model.AmAssetChange;
import com.luckyun.tjam.mtAssetMag.assetDailyMag.param.AmAssetChangeParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AmAssetChangeMapper extends BaseMapper<AmAssetChange> {

    /**
     *  查询所有
     * @param condition
     * @return
     */
    List<AmAssetChange> findAll(@Param("condition") AmAssetChangeParam condition);

    /**
     *  查询明细
     * @param condition
     * @return
     */
    AmAssetChange findOne(@Param("indocno") Long indocno);

    /**
     * 查询当前天最大计划编号
     *
     * @param splannm
     * @return
     */
    List<AmAssetChange> findMaxSappmtno(@Param("condition") AmAssetChangeParam condition);

    /**
     * 查询当前天最大名称
     *
     * @param splannm
     * @return
     */
    List<AmAssetChange> findMaxSname(@Param("condition") AmAssetChangeParam condition);

}
