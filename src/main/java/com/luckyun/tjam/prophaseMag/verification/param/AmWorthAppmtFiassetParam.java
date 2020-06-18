package com.luckyun.tjam.prophaseMag.verification.param;

import com.luckyun.annotation.Describe;
import com.luckyun.tjam.prophaseMag.verification.model.AmWorthAppmtFiasset;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


@Data
@EqualsAndHashCode(callSuper = false)
public class AmWorthAppmtFiassetParam extends AmWorthAppmtFiasset {

    @Describe("待删除集合")
    private List<AmWorthAppmtFiasset> delList;

    @Describe("待修改集合")
    private List<AmWorthAppmtFiasset> updList;

    @Describe("待新增集合")
    private List<AmWorthAppmtFiasset> addList;




}
