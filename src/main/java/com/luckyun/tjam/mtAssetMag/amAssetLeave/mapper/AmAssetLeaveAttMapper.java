package com.luckyun.tjam.mtAssetMag.amAssetLeave.mapper;

import com.luckyun.core.data.BaseMapper;
import com.luckyun.tjam.mtAssetMag.amAssetLeave.model.AmAssetLeaveAtt;
import com.luckyun.tjam.mtAssetMag.amAssetLeave.param.AmAssetLeaveAttParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AmAssetLeaveAttMapper extends BaseMapper<AmAssetLeaveAtt> {

    /**
     *  查询所有
     * @param condition
     * @return
     */
    List<AmAssetLeaveAtt> findAll(@Param("condition") AmAssetLeaveAttParam condition);

    /**
     *  查询明细
     * @param condition
     * @return
     */
    AmAssetLeaveAtt findOne(@Param("indocno") Long indocno);

    /**
     *  通过外键查询所有明细
     * @param ibillid
     * @return
     */

    List<AmAssetLeaveAtt> findAllByIlinkno(@Param("ilinkno") Long ilinkno);
}
