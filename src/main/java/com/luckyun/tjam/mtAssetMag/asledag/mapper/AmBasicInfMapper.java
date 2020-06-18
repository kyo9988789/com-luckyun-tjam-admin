package com.luckyun.tjam.mtAssetMag.asledag.mapper;



import com.luckyun.core.data.BaseMapper;
import com.luckyun.tjam.mtAssetMag.asledag.model.AmBasicInf;
import com.luckyun.tjam.mtAssetMag.asledag.param.AmBasicInfParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AmBasicInfMapper extends BaseMapper<AmBasicInf> {

    /**
     * 查询所有设备设施资产
     * @param condition
     * @return
     */
    List<AmBasicInf> findAll(@Param("condition") AmBasicInfParam condition);

    /**
     * 查询未被引用的主资产数据
     * @param condition
     * @return
     */
    List<AmBasicInf> readAllHasNotQuoted();

    /**
     *  查询明细
     * @param indocno
     * @return
     */

    AmBasicInf findOne(@Param("indocno") Long indocno);

    /**
     *  查询闲置时间
     * @param indocno
     * @return
     */
    AmBasicInf findByIamstate();


    /**
     *  查询时间
     * @param indocno
     * @return
     */
    List<AmBasicInf> findBydstart();

    /**
     * 查询当前天最大计划编号
     *
     * @param splannm
     * @return
     */
    List<AmBasicInf> findMaxSappmtno(@Param("condition") AmBasicInfParam condition);

    void updateIupdateState(@Param("indocno") Long indocno);

}
