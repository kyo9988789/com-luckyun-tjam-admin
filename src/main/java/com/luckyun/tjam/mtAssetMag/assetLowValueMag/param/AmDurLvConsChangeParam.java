package com.luckyun.tjam.mtAssetMag.assetLowValueMag.param;

import com.luckyun.annotation.Describe;
import com.luckyun.tjam.mtAssetMag.assetLowValueMag.model.AmDurLvConsChange;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
public class AmDurLvConsChangeParam extends AmDurLvConsChange {

    @Describe("删除操作的数据集")
    private List<AmDurLvConsChange> delList;
}
