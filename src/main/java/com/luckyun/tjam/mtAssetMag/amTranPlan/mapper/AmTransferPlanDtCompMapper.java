package com.luckyun.tjam.mtAssetMag.amTranPlan.mapper;


import com.luckyun.core.data.BaseMapper;
import com.luckyun.tjam.mtAssetMag.amTranPlan.model.AmTransferPlanDtComp;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface AmTransferPlanDtCompMapper extends BaseMapper<AmTransferPlanDtComp> {
    /**
     *  通过外键查询所有明细
     * @param ibillid
     * @return
     */

    List<AmTransferPlanDtComp> findAllByIlinkno(@Param("ilinkno") Long ilinkno);


    List<AmTransferPlanDtComp> findSonByIparent(@Param("sfcode") String sfcode);

    /**
     *  通过主键查询所有明细
     * @param ibillid
     * @return
     */
    AmTransferPlanDtComp findOne(@Param("indocno") Long indocno);
}
