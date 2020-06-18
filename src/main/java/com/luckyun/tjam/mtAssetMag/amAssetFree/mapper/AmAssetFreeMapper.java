package com.luckyun.tjam.mtAssetMag.amAssetFree.mapper;

import com.luckyun.core.data.BaseMapper;
import com.luckyun.tjam.mtAssetMag.amAssetFree.model.AmAssetFree;
import com.luckyun.tjam.mtAssetMag.amAssetFree.param.AmAssetFreeParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AmAssetFreeMapper extends BaseMapper<AmAssetFree>{

    /**
     *  查询所有
     * @param condition
     * @return
     */
    List<AmAssetFree> findAll(@Param("condition") AmAssetFreeParam condition);

    /**
     *  查询明细
     * @param condition
     * @return
     */
    AmAssetFree findOne(@Param("indocno") Long indocno);

    /**
     * 查询当前天最大计划编号
     *
     * @param splannm
     * @return
     */
    List<AmAssetFree> findMaxSappmtno(@Param("condition") AmAssetFreeParam condition);

    /**
     * 查询当前天最大名称
     *
     * @param splannm
     * @return
     */
    List<AmAssetFree> findMaxSname(@Param("condition") AmAssetFreeParam condition);
}
