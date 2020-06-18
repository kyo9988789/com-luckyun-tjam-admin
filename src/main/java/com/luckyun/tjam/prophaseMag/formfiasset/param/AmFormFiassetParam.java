package com.luckyun.tjam.prophaseMag.formfiasset.param;

import com.luckyun.tjam.prophaseMag.formfiasset.entity.AmFormFiasset;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2020/4/8.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AmFormFiassetParam extends AmFormFiasset {

    private List<AmFormFiasset> delList;

    private Long start;

    private Long end;

    private Date startDate;

    private Date endDate;
}
