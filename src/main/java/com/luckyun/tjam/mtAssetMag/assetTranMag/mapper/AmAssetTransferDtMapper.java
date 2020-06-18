package com.luckyun.tjam.mtAssetMag.assetTranMag.mapper;

import com.luckyun.core.data.BaseMapper;
import com.luckyun.tjam.mtAssetMag.assetTranMag.model.AmAssetTransferDt;
import com.luckyun.tjam.mtAssetMag.assetTranMag.param.AmAssetTransferDtParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AmAssetTransferDtMapper extends BaseMapper<AmAssetTransferDt> {
    /**
     *  查询所有
     * @param condition
     * @return
     */
    List<AmAssetTransferDt> findAll(@Param("condition") AmAssetTransferDtParam condition);

    /**
     *  查询明细
     * @param condition
     * @return
     */
    AmAssetTransferDt findOne(@Param("indocno") Long indocno);

    /**
     *  通过外键查询所有明细
     * @param ibillid
     * @return
     */

    List<AmAssetTransferDt> findAllByIlinkno(@Param("ilinkno") Long ilinkno);

    int findCount();

}
