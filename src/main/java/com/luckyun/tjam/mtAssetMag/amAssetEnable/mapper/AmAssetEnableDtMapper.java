package com.luckyun.tjam.mtAssetMag.amAssetEnable.mapper;

import com.luckyun.core.data.BaseMapper;
import com.luckyun.tjam.mtAssetMag.amAssetEnable.model.AmAssetEnableDt;
import com.luckyun.tjam.mtAssetMag.amAssetEnable.param.AmAssetEnableDtParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AmAssetEnableDtMapper extends BaseMapper<AmAssetEnableDt> {

    /**
     *  查询所有
     * @param condition
     * @return
     */
    List<AmAssetEnableDt> findAll(@Param("condition") AmAssetEnableDtParam condition);

    /**
     *  查询明细
     * @param condition
     * @return
     */
    AmAssetEnableDt findOne(@Param("indocno") Long indocno);

    /**
     *  通过外键查询所有明细
     * @param ibillid
     * @return
     */

    List<AmAssetEnableDt> findAllByIlinkno(@Param("ilinkno") Long ilinkno);

    int findCount();
}
