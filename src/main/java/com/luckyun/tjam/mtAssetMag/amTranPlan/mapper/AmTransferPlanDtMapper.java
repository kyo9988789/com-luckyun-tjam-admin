package com.luckyun.tjam.mtAssetMag.amTranPlan.mapper;

import com.luckyun.core.data.BaseMapper;
import com.luckyun.tjam.mtAssetMag.amTranPlan.model.AmTransferPlanDt;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AmTransferPlanDtMapper extends BaseMapper<AmTransferPlanDt> {
    /**
     *  通过外键查询所有明细
     * @param ibillid
     * @return
     */

    List<AmTransferPlanDt> findAllByIlinkno(@Param("ilinkno") Long ilinkno);

    /**
     *  通过主键查询所有明细
     * @param ibillid
     * @return
     */
    AmTransferPlanDt findOne(@Param("indocno") Long indocno);

    AmTransferPlanDt findOne1(@Param("sfcode") String sfcode);
}
