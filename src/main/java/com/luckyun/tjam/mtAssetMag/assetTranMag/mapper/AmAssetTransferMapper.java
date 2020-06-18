package com.luckyun.tjam.mtAssetMag.assetTranMag.mapper;

import com.luckyun.core.data.BaseMapper;
import com.luckyun.tjam.mtAssetMag.assetTranMag.model.AmAssetTransfer;
import com.luckyun.tjam.mtAssetMag.assetTranMag.param.AmAssetTransferParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AmAssetTransferMapper extends BaseMapper<AmAssetTransfer> {

    /**
     *  查询所有
     * @param condition
     * @return
     */
    List<AmAssetTransfer> findAll(@Param("condition") AmAssetTransferParam condition);

    /**
     *  查询明细
     * @param condition
     * @return
     */
    AmAssetTransfer findOne(@Param("indocno") Long indocno);

    int findCount();

    /**
     * 查询当前天最大计划编号
     *
     * @param splannm
     * @return
     */
    List<AmAssetTransfer> findMaxSappmtno(@Param("condition") AmAssetTransferParam condition);
}
