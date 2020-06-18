package com.luckyun.tjam.mtAssetMag.amAssetLeave.mapper;

import com.luckyun.core.data.BaseMapper;
import com.luckyun.tjam.mtAssetMag.amAssetLeave.model.AmAssetLeaveDt;
import com.luckyun.tjam.mtAssetMag.amAssetLeave.param.AmAssetLeaveDtParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AmAssetLeaveDtMapper extends BaseMapper<AmAssetLeaveDt>{

    /**
     *  查询所有
     * @param condition
     * @return
     */
    List<AmAssetLeaveDt> findAll(@Param("condition") AmAssetLeaveDtParam condition);

    /**
     *  查询明细
     * @param condition
     * @return
     */
    AmAssetLeaveDt findOne(@Param("indocno") Long indocno);

    /**
     *  通过外键查询所有明细
     * @param ibillid
     * @return
     */

    List<AmAssetLeaveDt> findAllByIlinkno(@Param("ilinkno") Long ilinkno);

    int findCount();
}
