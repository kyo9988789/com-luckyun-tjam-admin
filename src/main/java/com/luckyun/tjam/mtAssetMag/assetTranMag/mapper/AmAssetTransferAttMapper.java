package com.luckyun.tjam.mtAssetMag.assetTranMag.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.luckyun.core.data.BaseMapper;
import com.luckyun.tjam.mtAssetMag.assetTranMag.model.AmAssetTransferAtt;
import com.luckyun.tjam.mtAssetMag.assetTranMag.param.AmAssetTransferAttParam;

@Repository
public interface AmAssetTransferAttMapper extends BaseMapper<AmAssetTransferAtt> {

    /**
     *  查询所有
     * @param condition
     * @return
     */
    List<AmAssetTransferAtt> findAll(@Param("condition") AmAssetTransferAttParam condition);

    /**
     *  查询明细
     * @param condition
     * @return
     */
    AmAssetTransferAtt findOne(@Param("indocno") Long indocno);

    /**
     *  通过外键查询所有明细
     * @param ibillid
     * @return
     */
    List<AmAssetTransferAtt> findAllByIlinkno(@Param("ilinkno") Long ilinkno);
}
