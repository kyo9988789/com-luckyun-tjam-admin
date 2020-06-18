package com.luckyun.tjam.mtAssetMag.amTranPlan.model;

//资产中期管理 - 资产下发计划组成部分表

import com.luckyun.annotation.Describe;
import com.luckyun.model.data.DocEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class AmTransferPlanDtComp extends DocEntity {

    @Describe("资产卡片表主键")
    private Long isourceid;

    @Describe("主键")
    private Long indocno;

    @Describe("资产名称")
    private String sfname;

    @Describe("资产编码")
    private String sfcode;

    @Describe("所属线路")
    private Integer ilineid;

    @Describe("设备设施分类编码")
    private String seqclassno;

    @Describe("所属专业")
    private String smajornm;

    @Describe("品牌名称")
    private String sbrandnm;

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

    @Describe("名称")
    private String sname;

    @Override
    public boolean __isTrueDel() {
        return false;
    }

    @Override
    public String __getTableName() {
        return "am_transfer_plan_dt_comp";
    }
}
