package com.luckyun.tjam.prophaseMag.formfiasset.param;

import com.luckyun.tjam.prophaseMag.formfiasset.entity.AmFormFiassetDtComp;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Created by whs on 2020/5/25.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AmFormFiassetDtCompParam extends AmFormFiassetDtComp{
    private List<AmFormFiassetDtComp> delList;

    private List<AmFormFiassetDtComp> addList;

    private List<AmFormFiassetDtComp> updList;
}
