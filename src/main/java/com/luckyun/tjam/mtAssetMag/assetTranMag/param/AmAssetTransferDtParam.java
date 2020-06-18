package com.luckyun.tjam.mtAssetMag.assetTranMag.param;

import com.luckyun.annotation.Describe;
import com.luckyun.tjam.mtAssetMag.assetTranMag.model.AmAssetTransferDt;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
public class AmAssetTransferDtParam extends AmAssetTransferDt {

    @Describe("删除操作的数据集")
    private List<AmAssetTransferDt> delList;

    @Describe("删除操作的数据集")
    private List<AmAssetTransferDt> addList;
}
