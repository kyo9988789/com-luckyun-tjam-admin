package com.luckyun.tjam.mtAssetMag.amAssetEnable.param;

import java.util.List;

import com.luckyun.annotation.Describe;
import com.luckyun.tjam.mtAssetMag.amAssetEnable.model.AmAssetEnableDt;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class AmAssetEnableDtParam extends AmAssetEnableDt {

    @Describe("删除操作的数据集")
    private List<AmAssetEnableDt> delList;

    @Describe("删除操作的数据集")
    private List<AmAssetEnableDt> addList;

    @Describe("修改")
    private List<AmAssetEnableDt> updList;
}
