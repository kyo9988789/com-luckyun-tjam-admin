package com.luckyun.tjam.mtAssetMag.assetLowValueMag.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.luckyun.core.data.BaseMapper;
import com.luckyun.tjam.mtAssetMag.assetLowValueMag.model.AmDurLvConsChangeDt;
import com.luckyun.tjam.mtAssetMag.assetLowValueMag.param.AmDurLvConsChangeDtParam;

@Repository
public interface AmDurLvConsChangeDtMapper extends BaseMapper<AmDurLvConsChangeDt> {

    /**
     *  查询所有
     * @param condition
     * @return
     */
    List<AmDurLvConsChangeDt> findAll(@Param("condition") AmDurLvConsChangeDtParam condition);

    /**
     *  查询明细
     * @param condition
     * @return
     */
    AmDurLvConsChangeDt findOne(@Param("indocno") Long indocno);

    /**
     *  通过外键查询所有明细
     * @param ibillid
     * @return
     */
    List<AmDurLvConsChangeDt> findAllByIlinkno(@Param("ilinkno") Long ilinkno);

    int findCount();

}
