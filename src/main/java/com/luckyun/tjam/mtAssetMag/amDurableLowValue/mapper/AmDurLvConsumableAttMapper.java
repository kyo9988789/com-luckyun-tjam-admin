package com.luckyun.tjam.mtAssetMag.amDurableLowValue.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.luckyun.core.data.BaseMapper;
import com.luckyun.tjam.mtAssetMag.amDurableLowValue.model.AmDurLvConsumableAtt;

@Repository
public interface AmDurLvConsumableAttMapper extends BaseMapper<AmDurLvConsumableAtt> {

    /**
     *  通过外键查询所有明细
     * @param ibillid
     * @return
     */
    List<AmDurLvConsumableAtt> findAllByIlinkno(@Param("ilinkno") Long ilinkno);
}
