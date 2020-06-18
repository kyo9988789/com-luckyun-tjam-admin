package com.luckyun.tjam.prophaseMag.verification.model;


import com.luckyun.annotation.Describe;
import com.luckyun.annotation.Virtual;
import com.luckyun.model.data.DocEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class AmWaitAppmt extends DocEntity {

    @Describe("待分摊项编号")
    private String sappmtno;

    @Describe("处理状态(0.待分摊,1.分摊中,2.已完成)")
    private Integer idealstatus;
    @Describe("处理状态名称(0.待分摊,1.分摊中,2.已完成)")
    @Virtual
    private String sdealstatus;

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

    @Describe("创建部门")
    private Long ideptid;

    @Describe("处理状态名称")
    private String sdealstatunm;

    @Describe("所属线路")
    private String slinenm;

    @Describe("来源")
    private String ssourcenm;

    @Describe("名字")
    private String sname;

    @Describe("创建部门名称")
    private String sdeptnm;

    @Describe("是否已被资产价值分摊选中:0.未选,1.已选")
    private Integer iquotestate;

    @Override
    public String __getTableName() {
        return "am_wait_appmt";
    }

    @Override
    public boolean __isTrueDel() {
        return false;
    }

}
