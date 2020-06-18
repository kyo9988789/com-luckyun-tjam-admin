package com.luckyun.tjam.mtAssetMag.assetTranMag.param;

import com.luckyun.annotation.Describe;
import com.luckyun.tjam.mtAssetMag.assetTranMag.model.AmAssetTransferAtt;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
public class AmAssetTransferAttParam extends AmAssetTransferAtt {

    @Describe("删除操作的数据集")
    private List<AmAssetTransferAtt> delList;
}
