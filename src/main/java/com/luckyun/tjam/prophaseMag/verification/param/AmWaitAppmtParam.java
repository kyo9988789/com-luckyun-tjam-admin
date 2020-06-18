package com.luckyun.tjam.prophaseMag.verification.param;

import com.luckyun.annotation.Describe;
import com.luckyun.tjam.prophaseMag.verification.model.AmWaitAppmt;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


@Data
@EqualsAndHashCode(callSuper=false)
public class AmWaitAppmtParam extends AmWaitAppmt {

    @Describe("待删除数据")
    private List<AmWaitAppmt> delList;

}
