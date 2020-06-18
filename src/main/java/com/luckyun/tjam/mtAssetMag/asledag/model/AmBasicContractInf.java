package com.luckyun.tjam.mtAssetMag.asledag.model;


import java.util.Date;

import com.luckyun.annotation.Describe;
import com.luckyun.annotation.LogMainTableBasicAttr;
import com.luckyun.annotation.Virtual;
import com.luckyun.model.data.DocEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
//合同信息表
@Data
@EqualsAndHashCode(callSuper = false)
public class AmBasicContractInf extends DocEntity {

    @Describe("主键")
    private Long indocno;

    @Describe("外键")
    @LogMainTableBasicAttr
    private Long ilinkno;

    @Describe("合同类型")
    private String sconttype;

    @Describe("是否总价合同0：否，1：是")
    private Integer icontsumflag;

    @Describe("合同编号")
    private String scontno;

    @Describe("合同签订甲方")
    private String scontfirstparty;

    @Describe("合同签订乙方")
    private String scontpartb;

    @Describe("合同总金额")
    private String sconttotalmony;

    @Describe("合同签订日期")
    private String dcontsign;

    @Virtual
    private String ddcontsign;

    @Describe("项目编码")
    private String sprono;

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

    //@Describe("是否总价合同0：否，1：是")
    @Virtual
    private String scontsumflagnm;



    @Override
    public boolean __isTrueDel() {
        return false;
    }

    @Override
    public String __getTableName() {
        return "am_basic_contract_inf";
    }
}
