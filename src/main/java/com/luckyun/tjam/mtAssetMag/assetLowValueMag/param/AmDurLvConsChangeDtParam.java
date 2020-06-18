package com.luckyun.tjam.mtAssetMag.assetLowValueMag.param;

import com.luckyun.annotation.Describe;
import com.luckyun.tjam.mtAssetMag.assetLowValueMag.model.AmDurLvConsChangeDt;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
public class AmDurLvConsChangeDtParam extends AmDurLvConsChangeDt {

    @Describe("删除操作的数据集")
    private List<AmDurLvConsChangeDt> delList;

    @Describe("删除操作的数据集")
    private List<AmDurLvConsChangeDt> addList;

    @Describe("修改")
    private List<AmDurLvConsChangeDt> updList;
}
