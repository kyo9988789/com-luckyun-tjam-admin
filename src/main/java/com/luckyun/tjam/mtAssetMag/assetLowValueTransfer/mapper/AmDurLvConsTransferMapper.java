package com.luckyun.tjam.mtAssetMag.assetLowValueTransfer.mapper;

import com.luckyun.core.data.BaseMapper;
import com.luckyun.tjam.mtAssetMag.assetLowValueTransfer.model.AmDurLvConsTransfer;
import com.luckyun.tjam.mtAssetMag.assetLowValueTransfer.param.AmDurLvConsTransferParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AmDurLvConsTransferMapper extends BaseMapper<AmDurLvConsTransfer> {

    /**
     *  查询所有
     * @param condition
     * @return
     */
    List<AmDurLvConsTransfer> findAll(@Param("condition") AmDurLvConsTransferParam condition);

    /**
     *  查询明细
     * @param condition
     * @return
     */
    AmDurLvConsTransfer findOne(@Param("indocno") Long indocno);

    /**
     * 查询当前天最大计划编号
     *
     * @param splannm
     * @return
     */
    List<AmDurLvConsTransfer> findMaxSappmtno(@Param("condition") AmDurLvConsTransferParam condition);
}
