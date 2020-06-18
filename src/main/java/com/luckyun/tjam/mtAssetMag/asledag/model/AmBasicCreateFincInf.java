package com.luckyun.tjam.mtAssetMag.asledag.model;


import java.util.Date;

import com.luckyun.annotation.Describe;
import com.luckyun.annotation.LogMainTableBasicAttr;
import com.luckyun.annotation.Virtual;
import com.luckyun.model.data.DocEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
//建立财务信息表
@Data
@EqualsAndHashCode(callSuper = false)
public class AmBasicCreateFincInf extends DocEntity {

    @Describe("主键")
    private Long indocno;

    @Describe("外键")
    @LogMainTableBasicAttr
    private Long ilinkno;

    @Describe("建账日期")
    private Date daccountopen;

    @Describe("建账期间")
    private Date daccountopenperiod;

    @Virtual
    private String sdaccountopenperiod;

    @Describe("未税成本")
    private Double iuntaxcost;

    @Describe("资产原值")
    private Double iassetvalue;

    @Describe("残值类型")
    private String srevaluetype;

    @Describe("残值率")
    private Integer irevalueaount;

    @Describe("预估残值")
    private Double iperrevalue;

    @Describe("累计折旧")
    private Double iaccumdep;

    @Describe("资产净值")
    private Double inetassetvalue;

    @Describe("累计减值准备")
    private Double iaccuimpaireserve;

    @Describe("可回收市值")
    private Double irecyclmarvalue;

    @Describe("本月折旧")
    private Double imonthderip;

    @Describe("本年折旧")
    private Double iyearderip;

    @Describe("折旧方法")
    private Integer ideprecmethod;

    @Describe("使用年限")
    private Integer iuseyear;

    @Describe("使用期间数")
    private Integer iperioduse;

    @Describe("累计使用期间数")
    private Integer icountperioduse;

    @Describe("累计折旧期间数")
    private Integer icountperiodeper;

    @Describe("折旧开始日期")
    private Date dstartdeperdate;

    @Describe("对于折旧科目")
    private String sdepreaccount;

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

    //@Describe("折旧方法")
    @Virtual
    private String sdeprecmethodnm;



    @Override
    public boolean __isTrueDel() {
        return false;
    }

    @Override
    public String __getTableName() {
        return "am_basic_create_finc_inf";
    }
}
