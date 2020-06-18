package com.luckyun.tjam.mtAssetMag.amAcceSpare.param;


import com.luckyun.annotation.Describe;
import com.luckyun.tjam.mtAssetMag.amAcceSpare.model.AmSpare;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
public class AmSpareParam extends AmSpare {

    @Describe("删除操作的数据集")
    private List<AmSpare> delList;

    @Describe("主键集合，以”,“分开")
    private String indocnos;
    
    @Describe("主键集合，long类型")
    private List<Long> indocnoList;
}
