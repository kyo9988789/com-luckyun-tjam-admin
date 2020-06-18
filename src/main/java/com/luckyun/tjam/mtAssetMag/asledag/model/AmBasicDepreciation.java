package com.luckyun.tjam.mtAssetMag.asledag.model;


import com.luckyun.annotation.Describe;
import com.luckyun.annotation.LogMainTableBasicAttr;
import com.luckyun.model.data.DocEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

//折旧流水表
@Data
@EqualsAndHashCode(callSuper = false)
public class AmBasicDepreciation extends DocEntity {

    @Describe("主键")
    private Long indocno;

    @Describe("外键")
    @LogMainTableBasicAttr
    private Long ilinkno;

    @Describe("计提时间")
    private Date daccrualtime;

    @Describe("折旧年度")
    private Integer idepreciationyear;

    @Describe("折旧期间")
    private Integer idepreciationperiod;

    @Describe("折旧金额")
    private Double idepreciatiomoney;

    @Describe("删除（0 未删 1 虚拟删除）")
    private Integer idel;

    @Describe("创建人ID")
    private Long sregid;

    @Describe("创建时间")
    private Date dregt;

    @Describe("状态（0无效1有效）")
    private Integer istate;

    @Describe("创建人名称")
    private String sregnm;

    @Describe("修改人id")
    private Long smodid;

    @Describe("修改人名称")
    private String smodnm;

    @Describe("修改时间")
    private Date dmodt;


    @Override
    public boolean __isTrueDel() {
        return false;
    }

    @Override
    public String __getTableName() {
        return "am_basic_depreciation";
    }
}
