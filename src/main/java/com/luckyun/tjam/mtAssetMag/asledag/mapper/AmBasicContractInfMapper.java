package com.luckyun.tjam.mtAssetMag.asledag.mapper;


import com.luckyun.core.data.BaseMapper;
import com.luckyun.tjam.mtAssetMag.asledag.model.AmBasicContractInf;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AmBasicContractInfMapper extends BaseMapper<AmBasicContractInf> {

    /**
     *  通过外键查询所有明细
     * @param ibillid
     * @return
     */

    List<AmBasicContractInf> findAllByIbillid(@Param("ilinkno") Long ilinkno);
}
