package com.luckyun.tjam.mtAssetMag.asledag.mapper;


import com.luckyun.core.data.BaseMapper;
import com.luckyun.tjam.mtAssetMag.asledag.model.AmBasicComponentInf;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AmBasicComponentInfMapper extends BaseMapper<AmBasicComponentInf> {

    /**
     *  通过外键查询所有明细
     * @param ibillid
     * @return
     */

    List<AmBasicComponentInf> findAllByIbillid(@Param("ilinkno") Long ilinkno);

    void updateIupdateState(@Param("indocno") Long indocno);
}
