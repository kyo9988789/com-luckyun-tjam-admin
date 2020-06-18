package com.luckyun.tjam.mtAssetMag.assetDailyMag.param;

import com.luckyun.annotation.Describe;
import com.luckyun.tjam.mtAssetMag.assetDailyMag.model.AmAssetChangeDt;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
public class AmAssetChangeDtParam extends AmAssetChangeDt {
    @Describe("删除操作的数据集")
    private List<AmAssetChangeDt> delList;

    @Describe("删除操作的数据集")
    private List<AmAssetChangeDt> addList;
}
