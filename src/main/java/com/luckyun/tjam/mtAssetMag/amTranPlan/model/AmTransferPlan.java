package com.luckyun.tjam.mtAssetMag.amTranPlan.model;


import com.luckyun.annotation.Describe;
import com.luckyun.annotation.Virtual;
import com.luckyun.model.data.DocEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

//资产中期管理 - 资产下发计划(运营公司)表
@Data
@EqualsAndHashCode(callSuper = false)
public class AmTransferPlan extends DocEntity {

    @Describe("主键")
    private Long indocno;

    @Describe("计划名称")
    private String sfname;

    @Describe("设备编号")
    private String sfcode;

    @Describe("创建部门ID")
    private Long icreatedeptid;
    @Virtual
    private String screatedeptnm;

    @Describe("下发状态(0.未下发,1.已下发)")
    private Integer itransferstate;
    @Virtual
    private String stransferstatenm;

    @Virtual
    @Describe("数量")
    private Integer iqty;

    @Describe("下发至")
    private Integer itransferto;
    @Virtual
    private String stransfertonm;

    @Describe("所属线路")
    private Integer ilineid;
    @Virtual
    private String slinenm;

    @Describe("下发日期")
    private Date dtransfer;

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

    @Describe("名称")
    private String sname;

    @Describe("明细列表")
    @Virtual
    private List<AmTransferPlanDt> amTransferPlanDtList;

    @Describe("所有组成部分")
    @Virtual
    private List<AmTransferPlanDtComp> amTransferPlanDtCompList;

    @Override
    public boolean __isTrueDel() {
        return false;
    }

    @Override
    public String __getTableName() {
        return "am_transfer_plan";
    }
}
