package com.luckyun.tjam.mtAssetMag.asledag.mapper;

import com.luckyun.core.data.BaseMapper;
import com.luckyun.tjam.mtAssetMag.asledag.model.AmBasicManagementInf;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AmBasicManagementInfMapper extends BaseMapper<AmBasicManagementInf> {

    /**
     *  通过外键查询所有明细
     * @param ibillid
     * @return
     */

    List<AmBasicManagementInf> findAllByIbillid(@Param("ilinkno") Long ilinkno);
}
