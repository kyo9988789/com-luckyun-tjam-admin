package com.luckyun.tjam.mtAssetMag.asledag.param;

import com.luckyun.annotation.Describe;
import com.luckyun.tjam.mtAssetMag.asledag.model.AmBasicDepreciation;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class AmBasicDepreciationParam extends AmBasicDepreciation {

    @Describe("删除操作的数据集")
    private List<AmBasicDepreciation> delList;

    @Describe("删除操作的数据集")
    private List<AmBasicDepreciation> addList;

}
