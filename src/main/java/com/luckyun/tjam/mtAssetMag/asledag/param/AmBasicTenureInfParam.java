package com.luckyun.tjam.mtAssetMag.asledag.param;


import com.luckyun.annotation.Describe;
import com.luckyun.tjam.mtAssetMag.asledag.model.AmBasicTenureInf;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class AmBasicTenureInfParam extends AmBasicTenureInf {

    @Describe("删除操作的数据集")
    private List<AmBasicTenureInf> delList;

    @Describe("删除操作的数据集")
    private List<AmBasicTenureInf> addList;

}
