package com.luckyun.tjam.mtAssetMag.amDurableLowValue.mapper;


import com.luckyun.core.data.BaseMapper;
import com.luckyun.tjam.mtAssetMag.amDurableLowValue.model.AmDurLvConsumable;
import com.luckyun.tjam.mtAssetMag.amDurableLowValue.param.AmDurLvConsumableParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AmDurLvConsumableMapper extends BaseMapper<AmDurLvConsumable> {

    /**
     *  查询所有
     * @param condition
     * @return
     */

    List<AmDurLvConsumable> findAll(@Param("condition") AmDurLvConsumableParam condition);

    /**
     *  查询明细
     * @param condition
     * @return
     */
    AmDurLvConsumable findOne(@Param("indocno") Long indocno);
}
