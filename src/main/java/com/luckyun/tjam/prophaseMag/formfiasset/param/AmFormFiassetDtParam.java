package com.luckyun.tjam.prophaseMag.formfiasset.param;

import com.luckyun.tjam.prophaseMag.formfiasset.entity.AmFormFiassetDt;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Created by whs on 2020/5/25.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AmFormFiassetDtParam extends AmFormFiassetDt{

    private List<AmFormFiassetDt> delList;

    private List<AmFormFiassetDt> addList;

    private List<AmFormFiassetDt> updList;
}
