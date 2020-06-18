package com.luckyun.tjam.mtAssetMag.amAssetLeave.mapper;

import com.luckyun.core.data.BaseMapper;
import com.luckyun.tjam.mtAssetMag.amAssetLeave.model.AmAssetLeave;
import com.luckyun.tjam.mtAssetMag.amAssetLeave.param.AmAssetLeaveParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AmAssetLeaveMapper extends BaseMapper<AmAssetLeave> {

    /**
     *  查询所有
     * @param condition
     * @return
     */
    List<AmAssetLeave> findAll(@Param("condition") AmAssetLeaveParam condition);

    /**
     *  查询明细
     * @param condition
     * @return
     */
    AmAssetLeave findOne(@Param("indocno") Long indocno);

    /**
     * 查询当前天最大计划编号
     *
     * @param splannm
     * @return
     */
    List<AmAssetLeave> findMaxSappmtno(@Param("condition") AmAssetLeaveParam condition);

    /**
     * 查询当前天最大名称
     *
     * @param splannm
     * @return
     */
    List<AmAssetLeave> findMaxSname(@Param("condition") AmAssetLeaveParam condition);
}
