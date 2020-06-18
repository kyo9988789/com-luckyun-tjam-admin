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
public class AmWorthAppmtPro extends DocEntity {

    @Describe("主键")
    private Long indocno;

    @Describe("外键")
    @LogMainTableBasicAttr
    private Long ilinkno;

    @Describe("分摊方式(0.平均分摊；1.按比例分摊)")
    private Integer iappmttype;

    @Describe("待分摊项编号")
    private String sappmtno;

    @Describe("处理状态0.新建；1.分摊中；2.已完成")
    private Integer idealstatus;

    @Describe("分摊项")
    private String sappmtpronm;

    @Describe("项目编号")
    private String sprono;

    @Describe("项目名称")
    private String spronm;

    @Describe("合同编号")
    private String scontno;

    @Describe("合同名称")
    private String scontnm;

    @Describe("合同金额")
    private Double icontmoney;

    @Describe("分摊金额")
    private Double iappmtmoney;

    @Describe("来源")
    private Integer isource;

    @Describe("所属线路")
    private Integer ilineid;

    @Describe("备注")
    private String snote;

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

    @Virtual
    private String sappmttypenm;

    @Virtual
    private String sdealstatunm;

    @Virtual
    private String slinenm;

    @Describe("名字")
    private String sname;

    @Describe("来源数据ID(待分摊项数据)")
    private Long isourceid;
    @Describe("创建部门")
    private Long ideptid;
    
    @Override
    public String __getTableName() {
        return "am_worth_appmt_pro";
    }

    @Override
    public boolean __isTrueDel() {
        return false;
    }
}
