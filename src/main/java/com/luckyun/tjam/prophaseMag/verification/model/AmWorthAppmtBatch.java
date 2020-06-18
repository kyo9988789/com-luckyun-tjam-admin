package com.luckyun.tjam.prophaseMag.verification.model;


import com.luckyun.annotation.Describe;
import com.luckyun.annotation.Virtual;
import com.luckyun.model.data.DocEntity;
import com.luckyun.tjam.prophaseMag.verification.bpm.BpmTaskParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

//资产价值分摊批次表

@Data
@EqualsAndHashCode(callSuper = false)
public class AmWorthAppmtBatch extends DocEntity {

    @Describe("主键")
    private Long indocno;

    @Describe("编号")
    private String sfcode;

    @Describe("名称")
    private String sfname;

    @Describe("备注")
    private String snote;

    @Describe("创建部门")
    private Long ideptid;

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

    @Describe("资产价值分摊项信息")
    private List<AmWorthAppmtPro>  proList;

    @Describe("资产价值分摊项信息")
    private List<AmWorthAppmtFiasset>  fiassetList;

    @Describe("新增资产价值分摊项信息")
    private List<AmWorthAppmtPro> addProList;

    @Describe("删除资产价值分摊项信息")
    private List<AmWorthAppmtPro> delProList;

    @Describe("新增资产价值分摊项信息")
    private List<AmWorthAppmtFiasset> addFiassetList;

    @Describe("删除资产价值分摊项信息")
    private List<AmWorthAppmtFiasset> delFiassetList;

    @Describe("传的所有主键")
    private List<AmWorthAppmtBatch> indocnoList;

    @Describe("创建部门名称")
    private String sdeptnm;

    @Describe("审批流程状态")
    private Integer iapprovalstate;
    @Virtual
    private String sapprovalstate;

    @Describe("流程对象")
    @Virtual
    private BpmTaskParam bpmTaskParam;

    @Override
    public String __getTableName() {
        return "am_worth_appmt_batch";
    }

    @Override
    public boolean __isTrueDel() {
        return false;
    }
}
