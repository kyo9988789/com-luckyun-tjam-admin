package com.luckyun.tjam.mtAssetMag.amTranPlan.param;

import com.luckyun.annotation.Describe;
import com.luckyun.tjam.mtAssetMag.amTranPlan.model.AmTransferPlanDt;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
public class AmTransferPlanDtParam extends AmTransferPlanDt {
    @Describe("删除操作的数据集")
    private List<AmTransferPlanDt> delList;

    @Describe("删除操作的数据集")
    private List<AmTransferPlanDt> addList;
}
