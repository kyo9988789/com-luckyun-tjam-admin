package com.luckyun.tjam.prophaseMag.verification.param;

import com.luckyun.annotation.Describe;
import com.luckyun.tjam.prophaseMag.verification.model.AmWorthAppmtPro;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


@Data
@EqualsAndHashCode(callSuper = false)
public class AmWorthAppmtProParam extends AmWorthAppmtPro {
    @Describe("待删除数据")
    private List<AmWorthAppmtPro> delList;
    @Describe("待修改数据")
    private List<AmWorthAppmtPro> updList;
    @Describe("待添加数据")
    private List<AmWorthAppmtPro> addList;
}
