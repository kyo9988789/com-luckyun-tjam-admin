package com.luckyun.tjam.mtAssetMag.amAssetFree.mapper;

import com.luckyun.core.data.BaseMapper;
import com.luckyun.tjam.mtAssetMag.amAssetFree.model.AmAssetFreeDt;
import com.luckyun.tjam.mtAssetMag.amAssetFree.param.AmAssetFreeDtParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AmAssetFreeDtMapper extends BaseMapper<AmAssetFreeDt> {

    /**
     *  查询所有
     * @param condition
     * @return
     */
    List<AmAssetFreeDt> findAll(@Param("condition") AmAssetFreeDtParam condition);

    /**
     *  查询明细
     * @param condition
     * @return
     */
    AmAssetFreeDt findOne(@Param("indocno") Long indocno);

    /**
     *  通过外键查询所有明细
     * @param ibillid
     * @return
     */

    List<AmAssetFreeDt> findAllByIlinkno(@Param("ilinkno") Long ilinkno);

    int findCount();
}
