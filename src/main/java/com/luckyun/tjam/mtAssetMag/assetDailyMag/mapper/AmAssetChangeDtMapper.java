package com.luckyun.tjam.mtAssetMag.assetDailyMag.mapper;

import com.luckyun.core.data.BaseMapper;
import com.luckyun.tjam.mtAssetMag.assetDailyMag.model.AmAssetChangeDt;
import com.luckyun.tjam.mtAssetMag.assetDailyMag.param.AmAssetChangeDtParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AmAssetChangeDtMapper extends BaseMapper<AmAssetChangeDt> {
    /**
     *  查询所有
     * @param condition
     * @return
     */
    List<AmAssetChangeDt> findAll(@Param("condition") AmAssetChangeDtParam condition);

    /**
     *  查询明细
     * @param condition
     * @return
     */
    AmAssetChangeDt findOne(@Param("indocno") Long indocno);

    /**
     *  通过外键查询所有明细
     * @param ibillid
     * @return
     */

    List<AmAssetChangeDt> findAllByIlinkno(@Param("ilinkno") Long ilinkno);

    int findCount();

    int updateShu(AmAssetChangeDt entity);


}
