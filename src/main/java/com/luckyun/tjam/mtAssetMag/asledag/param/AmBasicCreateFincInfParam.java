package com.luckyun.tjam.mtAssetMag.asledag.param;


import com.luckyun.annotation.Describe;
import com.luckyun.tjam.mtAssetMag.asledag.model.AmBasicCreateFincInf;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class AmBasicCreateFincInfParam extends AmBasicCreateFincInf {

    @Describe("删除操作的数据集")
    private List<AmBasicCreateFincInf> delList;

    @Describe("删除操作的数据集")
    private List<AmBasicCreateFincInf> addList;
}
