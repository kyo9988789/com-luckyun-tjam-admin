package com.luckyun.tjam.mtAssetMag.amAssetEnable.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.luckyun.core.data.BaseMapper;
import com.luckyun.tjam.mtAssetMag.amAssetEnable.model.AmAssetEnableAtt;
import com.luckyun.tjam.mtAssetMag.amAssetEnable.param.AmAssetEnableAttParam;

@Repository
public interface AmAssetEnableAttMapper extends BaseMapper<AmAssetEnableAtt> {

    /**
     *  查询所有
     * @param condition
     * @return
     */
    List<AmAssetEnableAtt> findAll(@Param("condition") AmAssetEnableAttParam condition);

    /**
     *  查询明细
     * @param condition
     * @return
     */
    AmAssetEnableAtt findOne(@Param("indocno") Long indocno);

    /**
     *  通过外键查询所有明细
     * @param ibillid
     * @return
     */

    List<AmAssetEnableAtt> findAllByIlinkno(@Param("ilinkno") Long ilinkno);
}
