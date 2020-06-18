package com.luckyun.tjam.mtAssetMag.amToolsag.param;

import com.luckyun.annotation.Describe;
import com.luckyun.tjam.mtAssetMag.amToolsag.model.AmTools;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
public class AmToolsParam extends AmTools {
    @Describe("删除操作的数据集")
    private List<AmTools> delList;

    @Describe("主键集合，以”,“分开")
    private String indocnos;

    @Describe("主键集合，以”,“分开")
    private List<Long> indocnoList;
}
