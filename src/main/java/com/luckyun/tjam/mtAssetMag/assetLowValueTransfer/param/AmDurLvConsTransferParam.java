package com.luckyun.tjam.mtAssetMag.assetLowValueTransfer.param;

import com.luckyun.annotation.Describe;
import com.luckyun.tjam.mtAssetMag.assetLowValueTransfer.model.AmDurLvConsTransfer;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
public class AmDurLvConsTransferParam extends AmDurLvConsTransfer{

    @Describe("删除操作的数据集")
    private List<AmDurLvConsTransfer> delList;

}
