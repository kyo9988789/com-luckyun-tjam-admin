package com.luckyun.tjam.mtAssetMag.assetLowValueMag.param;

import com.luckyun.annotation.Describe;
import com.luckyun.tjam.mtAssetMag.assetLowValueMag.model.AmDurLvConsChangeAtt;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
public class AmDurLvConsChangeAttParam extends AmDurLvConsChangeAtt {
    @Describe("删除操作的数据集")
    private List<AmDurLvConsChangeAtt> delList;
}
