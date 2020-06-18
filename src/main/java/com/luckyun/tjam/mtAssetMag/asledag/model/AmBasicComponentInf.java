package com.luckyun.tjam.mtAssetMag.asledag.model;

import com.luckyun.annotation.Describe;
import com.luckyun.annotation.LogMainTableBasicAttr;
import com.luckyun.annotation.Virtual;
import com.luckyun.model.data.DocEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;


//资产卡片组成部分表

@Data
@EqualsAndHashCode(callSuper = false)
public class AmBasicComponentInf extends DocEntity {

    @Describe("是否已生成移交计划(0.未生成，1.已生成)")
    private Integer igenetpstateid;

    @Describe("主键")
    private Long indocno;

    @Describe("外键")
    @LogMainTableBasicAttr
    private Long ilinkno;

    @Describe("资产名称")
    private String sfname;

    @Describe("资产编码")
    private String sfcode;

    @Describe("所属线路")
    private Integer ilineid;

    @Describe("设备设施分类编码")
    private String seqclassno;

    @Describe("所所属专业")
    private String smajornm;

    @Describe("品牌名称")
    private String sbrandnm ;

    @Describe("安装位置")
    private String sinspos;

    @Describe("生产厂商")
    private String sproductnm;

    @Describe("供货厂商")
    private String ssuppliernm;

    @Describe("型号规格")
    private String sspec;

    @Describe("计量单位")
    private String sunit;

    @Describe("使用寿命")
    private String sestimatedlife;

    @Describe("寿命计量单位")
    private String sdesignunit;

    @Describe("单价")
    private Double iprice;

    @Describe("数量")
    private Integer iqty;

    @Describe("总价")
    private Double ieqvalence;

    @Describe("父级资产编码")
    private String sparentno;

    @Describe("数据来源(0:L1,1:P6)")
    private Integer isource;

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

    //数据来源名称
    @Virtual
    private String ssource;



    @Override
    public boolean __isTrueDel() {
        return false;
    }

    @Override
    public String __getTableName() {
        return "am_basic_component_inf";
    }
}
