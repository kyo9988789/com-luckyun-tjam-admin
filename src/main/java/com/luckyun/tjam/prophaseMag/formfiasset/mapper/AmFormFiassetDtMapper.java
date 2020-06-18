package com.luckyun.tjam.prophaseMag.formfiasset.mapper;

import com.luckyun.core.data.BaseMapper;
import com.luckyun.tjam.prophaseMag.formfiasset.entity.AmFormFiassetDt;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2020/4/8.
 */
@Repository
public interface AmFormFiassetDtMapper extends BaseMapper<AmFormFiassetDt>{

    List<AmFormFiassetDt> findAllByIlinkno(@Param("ilinkno")Long ilinkno);
}
