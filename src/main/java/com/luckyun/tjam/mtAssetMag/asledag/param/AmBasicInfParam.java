package com.luckyun.tjam.mtAssetMag.asledag.param;


import com.luckyun.annotation.Describe;
import com.luckyun.tjam.mtAssetMag.asledag.model.AmBasicInf;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
public class AmBasicInfParam extends AmBasicInf {

    @Describe("删除操作的数据集")
    private List<AmBasicInf> delList;

    @Describe("权属单位")
    private String sownernuit;

    @Describe("专业")
    private String smajornm;

    @Describe("安装位置")
    private String sinspos;

    @Describe("责任人")
    private Long idutyid;

    @Describe("主键集合，以”,“分开")
    private String indocnos;

    @Describe("主键集合，以”,“分开")
    private List<Long> indocnoList;

    @Describe("变更数据集")
    List<AmBasicInf> generateDataList;


}
