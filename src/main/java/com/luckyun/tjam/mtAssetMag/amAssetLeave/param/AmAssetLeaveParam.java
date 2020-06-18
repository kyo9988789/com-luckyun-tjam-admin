package com.luckyun.tjam.mtAssetMag.amAssetLeave.param;

import com.luckyun.annotation.Describe;
import com.luckyun.tjam.mtAssetMag.amAssetLeave.model.AmAssetLeave;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class AmAssetLeaveParam extends AmAssetLeave{

    @Describe("删除操作的数据集")
    private List<AmAssetLeave> delList;
}
