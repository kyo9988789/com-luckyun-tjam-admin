package com.luckyun.tjam.mtAssetMag.amAssetFree.param;

import com.luckyun.annotation.Describe;
import com.luckyun.tjam.mtAssetMag.amAssetFree.model.AmAssetFreeAtt;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
public class AmAssetFreeAttParam extends AmAssetFreeAtt{

    @Describe("删除操作的数据集")
    private List<AmAssetFreeAtt> delList;
}
