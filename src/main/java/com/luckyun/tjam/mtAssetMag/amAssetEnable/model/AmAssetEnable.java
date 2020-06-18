package com.luckyun.tjam.mtAssetMag.amAssetEnable.model;

import com.luckyun.annotation.Describe;
import com.luckyun.annotation.Virtual;
import com.luckyun.model.data.DocEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

//资产中期-资产启用主计划表
@Data
@EqualsAndHashCode(callSuper = false)
public class AmAssetEnable extends DocEntity {

    @Describe("主键")
    private Long indocno;

    @Describe("批次名称")
    private String sfname;

    @Describe("审批状态")
    private Integer iapprovalstate;
    @Virtual
    private String sapprovalstatenm;

    @Describe("批次编号")
    private String sfcode;

    @Describe("所属线路")
    private Integer ilineid;
    @Virtual
    private String slinenm;

    @Virtual
    private Integer iqty;

    @Describe("备注")
    private String snote;

    @Describe("创建部门")
    private Long ideptid;
    @Virtual
    private String sdeptsm;

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
        return "am_asset_enable";
    }
}
