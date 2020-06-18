package com.luckyun.tjam.prophaseMag.formfiasset.mapper;

import com.luckyun.core.data.BaseMapper;
import com.luckyun.tjam.prophaseMag.formfiasset.entity.AmFormFiassetDtComp;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2020/4/8.
 */
@Repository
public interface AmFormFiassetDtCompMapper extends BaseMapper<AmFormFiassetDtComp>{

    List<AmFormFiassetDtComp> findAllByIlinkno(@Param("ilinkno")Long ilinkno);
}
