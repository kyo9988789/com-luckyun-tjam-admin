package com.luckyun.tjam.mtAssetMag.amAssetEnable.param;

import com.luckyun.annotation.Describe;
import com.luckyun.tjam.mtAssetMag.amAssetEnable.model.AmAssetEnableAtt;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class AmAssetEnableAttParam extends AmAssetEnableAtt {

    @Describe("删除操作的数据集")
    private List<AmAssetEnableAtt> delList;
}
