package com.luckyun.tjam.mtAssetMag.amTranPlan.param;

import com.luckyun.annotation.Describe;
import com.luckyun.tjam.mtAssetMag.amTranPlan.model.AmTransferPlanDtComp;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
public class AmTransferPlanDtCompParam extends AmTransferPlanDtComp{
    @Describe("删除操作的数据集")
    private List<AmTransferPlanDtComp> delList;
}
