package com.luckyun.tjam.mtAssetMag.assetLowValueTransfer.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.luckyun.core.data.BaseMapper;
import com.luckyun.tjam.mtAssetMag.assetLowValueTransfer.model.AmDurLvConsTransferDt;
import com.luckyun.tjam.mtAssetMag.assetLowValueTransfer.param.AmDurLvConsTransferDtParam;

@Repository
public interface AmDurLvConsTransferDtMapper extends BaseMapper<AmDurLvConsTransferDt> {

    /**
     *  查询所有
     * @param condition
     * @return
     */
    List<AmDurLvConsTransferDt> findAll(@Param("condition") AmDurLvConsTransferDtParam condition);

    /**
     *  查询明细
     * @param condition
     * @return
     */
    AmDurLvConsTransferDt findOne(@Param("indocno") Long indocno);

    /**
     *  通过外键查询所有明细
     * @param ibillid
     * @return
     */
    List<AmDurLvConsTransferDt> findAllByIlinkno(@Param("ilinkno") Long ilinkno);

    int findCount();
}
