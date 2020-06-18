package com.luckyun.tjam.mtAssetMag.assetLowValueTransfer.mapper;

import com.luckyun.core.data.BaseMapper;
import com.luckyun.tjam.mtAssetMag.assetLowValueTransfer.model.AmDurLvConsTransferAtt;
import com.luckyun.tjam.mtAssetMag.assetLowValueTransfer.param.AmDurLvConsTransferAttParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AmDurLvConsTransferAttMapper extends BaseMapper<AmDurLvConsTransferAtt> {

    /**
     *  查询所有
     * @param condition
     * @return
     */
    List<AmDurLvConsTransferAtt> findAll(@Param("condition") AmDurLvConsTransferAttParam condition);

    /**
     *  查询明细
     * @param condition
     * @return
     */
    AmDurLvConsTransferAtt findOne(@Param("indocno") Long indocno);

    /**
     *  通过外键查询所有明细
     * @param ibillid
     * @return
     */

    List<AmDurLvConsTransferAtt> findAllByIlinkno(@Param("ilinkno") Long ilinkno);

}
