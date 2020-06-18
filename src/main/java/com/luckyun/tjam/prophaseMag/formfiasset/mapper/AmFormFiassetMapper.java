package com.luckyun.tjam.prophaseMag.formfiasset.mapper;

import com.luckyun.core.data.BaseMapper;
import com.luckyun.tjam.prophaseMag.formfiasset.entity.AmFormFiasset;
import com.luckyun.tjam.prophaseMag.formfiasset.param.AmFormFiassetParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2020/4/8.
 */
@Repository
public interface AmFormFiassetMapper extends BaseMapper<AmFormFiasset> {

    List<AmFormFiasset> findAll(@Param("condition")AmFormFiassetParam condition);

    AmFormFiasset findOne(@Param("indocno")Long indocno);

    Long findByDregt();
}
