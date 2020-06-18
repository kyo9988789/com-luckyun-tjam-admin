package com.luckyun.tjam.mtAssetMag.amTranPlan.mapper;

import com.luckyun.core.data.BaseMapper;
import com.luckyun.tjam.mtAssetMag.amTranPlan.model.AmTransferPlan;
import com.luckyun.tjam.mtAssetMag.amTranPlan.param.AmTransferPlanParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AmTransferPlanMapper extends BaseMapper<AmTransferPlan> {

    /**
     *  查询所有
     * @param condition
     * @return
     */
    List<AmTransferPlan> findAll(@Param("condition")AmTransferPlanParam condition);

    /**
     *  查询明细
     * @param condition
     * @return
     */
    AmTransferPlan findOne(@Param("indocno") Long indocno);

    /**
     *  查询当前天最大计划
     * @param splannm
     * @return
     */

    List<AmTransferPlan> findMaxSname(@Param("condition") AmTransferPlanParam condition);

    /**
     * 查询当前天最大计划编号
     *
     * @param splannm
     * @return
     */
    List<AmTransferPlan> findMaxSappmtno(@Param("condition") AmTransferPlanParam condition);



    Long findByDregt();
}
