package com.luckyun.tjam.mtAssetMag.asledag.model;


import com.luckyun.annotation.Describe;
import com.luckyun.annotation.LogMainTableBasicAttr;
import com.luckyun.annotation.Virtual;
import com.luckyun.model.data.DocEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

//价值信息表
@Data
@EqualsAndHashCode(callSuper = false)
public class AmBasicValueInf extends DocEntity {

    @Describe("主键")
    private Long indocno;

    @Describe("外键")
    @LogMainTableBasicAttr
    private Long ilinkno;

    @Describe("数量")
    private Integer iqty;

    @Describe("单价")
    private Double iprice;

    @Describe("金额")
    private Double imoney;

    @Describe("计量单位")
    private String sunit;

    @Describe("分摊方式(0.平均分摊；1.按比例分摊)")
    private Integer iappmttype;

    @Virtual
    private String sappmttypenm;

    @Describe("费用来源")
    private String scostsource;

    @Describe("成本中心")
    private String scostcenter;

    @Describe("使用年限")
    private Integer iusefullife;

    @Describe("寿命计量单位")
    private Integer idesignunit;

    @Describe("使用寿命")
    private String sestimatedlife;

    @Describe("质保开始日期")
    private Date dwarrstart;

    @Describe("质保期限")
    private Integer iwarranty;

    @Describe("融资状态:0是，1否")
    private Integer ifinancstate;

    @Describe("处置状态")
    private String sdisposalstate;

    @Describe("币种")
    private String sassetcurrency;

    @Describe("币种单位")
    private String scurrencyunit;

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

    //("寿命计量单位名称")
    @Virtual
    private String sdesignunitnm;

    //("融资状态:0是，1否")
    @Virtual
    private String sfinancstatenm;


    @Override
    public boolean __isTrueDel() {
        return false;
    }

    @Override
    public String __getTableName() {
        return "am_basic_value_inf";
    }
}
