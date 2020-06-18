package com.luckyun.tjam.prophaseMag.verification.model;


import com.luckyun.annotation.Describe;
import com.luckyun.annotation.LogMainTableBasicAttr;
import com.luckyun.annotation.Virtual;
import com.luckyun.model.data.DocEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class AmWorthAppmtFiasset extends DocEntity {
    @Describe("主键")
    private Long indocno;

    @Describe("外键")
    @LogMainTableBasicAttr
    private Long ilinkno;

    @Describe("资产编码")
    private String sfcode;

    @Describe("资产名称")
    private String sfname;

    @Describe("资产单价")
    private Double iprice;

    @Describe("分摊金额")
    private Double iappmtmoney;

    @Describe("资产分类编号")
    private String seqclassno;
    @Describe("设备设施分类编码")
    @Virtual
    private String sclassstructurenm;
    
    @Describe("规格型号")
    private String sspecAct;

    @Describe("管理部门ID")
    private Long imanagedeptid;

    @Describe("责任人ID")
    private Long idutyid;

    @Describe("所属线路ID")
    private Integer ilineid;

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

    @Describe("名字")
    private String sname;

    @Virtual
    private String sdutynm;

    @Virtual
    private String smanagedeptnm;

    @Virtual
    private String slinenm;

    @Describe("来源数据ID(实物资产台账数据)")
    private Long isourceid;
    
    @Override
    public String __getTableName() {
        return "am_worth_appmt_fiasset";
    }

    @Override
    public boolean __isTrueDel() {
        return false;
    }
}
