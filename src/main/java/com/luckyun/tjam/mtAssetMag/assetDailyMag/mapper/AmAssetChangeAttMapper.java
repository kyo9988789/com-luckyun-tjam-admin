package com.luckyun.tjam.mtAssetMag.assetDailyMag.mapper;

import com.luckyun.core.data.BaseMapper;
import com.luckyun.tjam.mtAssetMag.assetDailyMag.model.AmAssetChangeAtt;
import com.luckyun.tjam.mtAssetMag.assetDailyMag.param.AmAssetChangeAttParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AmAssetChangeAttMapper extends BaseMapper<AmAssetChangeAtt> {

    /**
     *  查询所有
     * @param condition
     * @return
     */
    List<AmAssetChangeAtt> findAll(@Param("condition") AmAssetChangeAttParam condition);

    /**
     *  查询明细
     * @param condition
     * @return
     */
    AmAssetChangeAtt findOne(@Param("indocno") Long indocno);

    /**
     *  通过外键查询所有明细
     * @param ibillid
     * @return
     */

    List<AmAssetChangeAtt> findAllByIlinkno(@Param("ilinkno") Long ilinkno);
}
