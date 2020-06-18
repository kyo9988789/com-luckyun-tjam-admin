package com.luckyun.tjam.mtAssetMag.amAssetLeave.param;

import com.luckyun.annotation.Describe;
import com.luckyun.tjam.mtAssetMag.amAssetLeave.model.AmAssetLeaveDt;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
public class AmAssetLeaveDtParam extends AmAssetLeaveDt{

    @Describe("删除操作的数据集")
    private List<AmAssetLeaveDt> delList;

    @Describe("删除操作的数据集")
    private List<AmAssetLeaveDt> addList;
}
