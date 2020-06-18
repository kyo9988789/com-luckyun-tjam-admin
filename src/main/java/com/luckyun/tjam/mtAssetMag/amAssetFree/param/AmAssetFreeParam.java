package com.luckyun.tjam.mtAssetMag.amAssetFree.param;

import com.luckyun.annotation.Describe;
import com.luckyun.tjam.mtAssetMag.amAssetFree.model.AmAssetFree;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class AmAssetFreeParam extends AmAssetFree{

    @Describe("删除操作的数据集")
    private List<AmAssetFree> delList;
}
