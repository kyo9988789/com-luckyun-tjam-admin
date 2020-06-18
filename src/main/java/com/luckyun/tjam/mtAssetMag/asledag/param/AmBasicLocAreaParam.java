package com.luckyun.tjam.mtAssetMag.asledag.param;


import com.luckyun.annotation.Describe;
import com.luckyun.tjam.mtAssetMag.asledag.model.AmBasicLocArea;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class AmBasicLocAreaParam extends AmBasicLocArea {

    @Describe("删除操作的数据集")
    private List<AmBasicLocArea> delList;

    @Describe("删除操作的数据集")
    private List<AmBasicLocArea> addList;

}
