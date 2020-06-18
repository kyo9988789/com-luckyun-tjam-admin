package com.luckyun.tjam.mtAssetMag.assetLowValueTransfer.param;

import java.util.List;

import com.luckyun.annotation.Describe;
import com.luckyun.tjam.mtAssetMag.assetLowValueTransfer.model.AmDurLvConsTransferAtt;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class AmDurLvConsTransferAttParam extends AmDurLvConsTransferAtt{

    @Describe("删除操作的数据集")
    private List<AmDurLvConsTransferAtt> delList;
}
