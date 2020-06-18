package com.luckyun.tjam.mtAssetMag.amAssetEnable.mapper;

import com.luckyun.core.data.BaseMapper;
import com.luckyun.tjam.mtAssetMag.amAssetEnable.model.AmAssetEnable;
import com.luckyun.tjam.mtAssetMag.amAssetEnable.param.AmAssetEnableParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AmAssetEnableMapper extends BaseMapper<AmAssetEnable> {

    /**
     *  查询所有
     * @param condition
     * @return
     */
    List<AmAssetEnable> findAll(@Param("condition") AmAssetEnableParam condition);

    /**
     *  查询明细
     * @param condition
     * @return
     */
    AmAssetEnable findOne(@Param("indocno") Long indocno);
}
