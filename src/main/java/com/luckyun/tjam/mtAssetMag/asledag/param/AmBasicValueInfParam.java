package com.luckyun.tjam.mtAssetMag.asledag.param;


import com.luckyun.annotation.Describe;
import com.luckyun.tjam.mtAssetMag.asledag.model.AmBasicValueInf;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class AmBasicValueInfParam extends AmBasicValueInf {

    @Describe("删除操作的数据集")
    private List<AmBasicValueInf> delList;

    @Describe("删除操作的数据集")
    private List<AmBasicValueInf> addList;
}
