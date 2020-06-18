package com.luckyun.tjam.mtAssetMag.asledag.param;

import com.luckyun.annotation.Describe;
import com.luckyun.tjam.mtAssetMag.asledag.model.AmBasicComponentInf;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class AmBasicComponentInfParam extends AmBasicComponentInf{

    @Describe("删除操作的数据集")
    private List<AmBasicComponentInf> delList;

    @Describe("删除操作的数据集")
    private List<AmBasicComponentInf> addList;
}
