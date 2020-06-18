package com.luckyun.tjam.mtAssetMag.assetLowValueTransfer.param;

import com.luckyun.annotation.Describe;
import com.luckyun.tjam.mtAssetMag.assetLowValueTransfer.model.AmDurLvConsTransferDt;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
public class AmDurLvConsTransferDtParam extends AmDurLvConsTransferDt{

    @Describe("删除操作的数据集")
    private List<AmDurLvConsTransferDt> delList;

    @Describe("删除操作的数据集")
    private List<AmDurLvConsTransferDt> addList;
}
