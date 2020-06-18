package com.luckyun.tjam.mtAssetMag.amDurableLowValue.param;


import com.luckyun.annotation.Describe;
import com.luckyun.tjam.mtAssetMag.amDurableLowValue.model.AmDurLvConsumable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
public class AmDurLvConsumableParam extends AmDurLvConsumable {
    @Describe("待删除数据集合")
    private List<AmDurLvConsumable> delList;

    @Describe("主键集合，以”,“分开")
    private String indocnos;

    @Describe("主键集合，以”,“分开")
    private List<Long> indocnoList;

}
