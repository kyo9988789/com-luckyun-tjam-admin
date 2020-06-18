package com.luckyun.tjam.mtAssetMag.asledag.param;

import com.luckyun.annotation.Describe;
import com.luckyun.tjam.mtAssetMag.asledag.model.AmBasicAnnex;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class AmBasicAnnexParam extends AmBasicAnnex {

    @Describe("删除操作的数据集")
    private List<AmBasicAnnex> delList;
}
