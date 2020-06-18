package com.luckyun.tjam.mtAssetMag.amToolsag.mapper;

import com.luckyun.core.data.BaseMapper;
import com.luckyun.tjam.mtAssetMag.amToolsag.model.AmTools;
import com.luckyun.tjam.mtAssetMag.amToolsag.param.AmToolsParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AmToolsMapper extends BaseMapper<AmTools> {

    /**
     *  查询所有
     * @param condition
     * @return
     */

    List<AmTools> findAll(@Param("condition")AmToolsParam condition);

    /**
     *  查询明细
     * @param condition
     * @return
     */
    AmTools findOne(@Param("indocno") Long indocno);
}
