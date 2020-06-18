package com.luckyun.tjam.mtAssetMag.assetDailyMag.param;

import com.luckyun.annotation.Describe;
import com.luckyun.tjam.mtAssetMag.assetDailyMag.model.AmAssetChangeAtt;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
public class AmAssetChangeAttParam extends AmAssetChangeAtt {
    @Describe("删除操作的数据集")
    private List<AmAssetChangeAtt> delList;
}
