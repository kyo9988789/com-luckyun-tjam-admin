package com.luckyun.tjam.mtAssetMag.assetDailyMag.param;

import com.luckyun.annotation.Describe;
import com.luckyun.tjam.mtAssetMag.assetDailyMag.model.AmAssetChange;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
public class AmAssetChangeParam extends AmAssetChange {

    @Describe("删除操作的数据集")
    private List<AmAssetChange> delList;
}
