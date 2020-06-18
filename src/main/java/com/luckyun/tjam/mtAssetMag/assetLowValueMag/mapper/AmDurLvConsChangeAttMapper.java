package com.luckyun.tjam.mtAssetMag.assetLowValueMag.mapper;

import com.luckyun.core.data.BaseMapper;
import com.luckyun.tjam.mtAssetMag.assetLowValueMag.model.AmDurLvConsChangeAtt;
import com.luckyun.tjam.mtAssetMag.assetLowValueMag.param.AmDurLvConsChangeAttParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AmDurLvConsChangeAttMapper extends BaseMapper<AmDurLvConsChangeAtt>{
    /**
     *  查询所有
     * @param condition
     * @return
     */
    List<AmDurLvConsChangeAtt> findAll(@Param("condition") AmDurLvConsChangeAttParam condition);

    /**
     *  查询明细
     * @param condition
     * @return
     */
    AmDurLvConsChangeAtt findOne(@Param("indocno") Long indocno);

    /**
     *  通过外键查询所有明细
     * @param ibillid
     * @return
     */

    List<AmDurLvConsChangeAtt> findAllByIlinkno(@Param("ilinkno") Long ilinkno);
}
