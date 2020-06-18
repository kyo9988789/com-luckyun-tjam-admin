package com.luckyun.tjam.mtAssetMag.asledag.param;


import com.luckyun.annotation.Describe;
import com.luckyun.tjam.mtAssetMag.asledag.model.AmBasicContractInf;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class AmBasicContractInfParam extends AmBasicContractInf {

    @Describe("删除操作的数据集")
    private List<AmBasicContractInf> delList;

    @Describe("删除操作的数据集")
    private List<AmBasicContractInf> addList;
}
