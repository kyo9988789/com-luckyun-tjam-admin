package com.luckyun.tjam.mtAssetMag.amAssetFree.mapper;

import com.luckyun.core.data.BaseMapper;
import com.luckyun.tjam.mtAssetMag.amAssetFree.model.AmAssetFreeAtt;
import com.luckyun.tjam.mtAssetMag.amAssetFree.param.AmAssetFreeAttParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AmAssetFreeAttMapper extends BaseMapper<AmAssetFreeAtt> {

    /**
     *  查询所有
     * @param condition
     * @return
     */
    List<AmAssetFreeAtt> findAll(@Param("condition") AmAssetFreeAttParam condition);

    /**
     *  查询明细
     * @param condition
     * @return
     */
    AmAssetFreeAtt findOne(@Param("indocno") Long indocno);

    /**
     *  通过外键查询所有明细
     * @param ibillid
     * @return
     */

    List<AmAssetFreeAtt> findAllByIlinkno(@Param("ilinkno") Long ilinkno);

}
