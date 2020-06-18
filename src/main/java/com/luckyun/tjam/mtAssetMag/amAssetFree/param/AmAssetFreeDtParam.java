package com.luckyun.tjam.mtAssetMag.amAssetFree.param;

import com.luckyun.annotation.Describe;
import com.luckyun.tjam.mtAssetMag.amAssetFree.model.AmAssetFreeDt;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
public class AmAssetFreeDtParam extends AmAssetFreeDt{

    @Describe("删除操作的数据集")
    private List<AmAssetFreeDt> delList;

    @Describe("删除操作的数据集")
    private List<AmAssetFreeDt> addList;
}
