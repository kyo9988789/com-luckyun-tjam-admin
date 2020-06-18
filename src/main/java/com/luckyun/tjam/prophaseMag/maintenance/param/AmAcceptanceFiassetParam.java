package com.luckyun.tjam.prophaseMag.maintenance.param;

import java.util.List;

import com.luckyun.annotation.Describe;
import com.luckyun.tjam.prophaseMag.maintenance.model.AmAcceptanceFiasset;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class AmAcceptanceFiassetParam extends AmAcceptanceFiasset {

    @Describe("分配责任人数据集合")
    List<AmAcceptanceFiasset> distList;

    @Describe("待修改移交状态数据集合")
    List<AmAcceptanceFiasset> transferList;

    @Describe("高级检索")
    private String sname;

    @Describe("移交计划")
    private String description;

    @Describe("责任人同意或者拒绝的数据集")
    List<AmAcceptanceFiasset> oprDataList;

    @Describe("生成核验计划数据集")
    List<AmAcceptanceFiasset> generateDataList;

    @Describe("主键集合，以”,“分开")
    private String indocnos;

    @Describe("主键集合，以”,“分开")
    private List<Long> indocnoList;
}
