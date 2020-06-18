package com.luckyun.tjam.prophaseMag.verification.param;

import com.luckyun.annotation.Describe;
import com.luckyun.tjam.prophaseMag.verification.model.AmWorthAppmtBatch;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


@Data
@EqualsAndHashCode(callSuper=false)
public class AmWorthAppmtBatchParam extends AmWorthAppmtBatch {

    @Describe("待删除数据")
    private List<AmWorthAppmtBatch> delList;
}
