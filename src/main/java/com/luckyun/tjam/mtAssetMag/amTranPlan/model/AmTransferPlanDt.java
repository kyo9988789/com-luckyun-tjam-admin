package com.luckyun.tjam.mtAssetMag.amTranPlan.model;

//资产中期管理 - 移交计划明细表

import com.luckyun.annotation.Describe;
import com.luckyun.annotation.LogMainTableBasicAttr;
import com.luckyun.annotation.Virtual;
import com.luckyun.model.data.DocEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class AmTransferPlanDt extends DocEntity {

    @Describe("资产卡片表主键")
    private Long isourceid;

    @Describe("主键")
    private Long indocno;

    @Describe("外键")
    @LogMainTableBasicAttr
    private Long ilinkno;

    @Describe("资产编码")
    private String sfcode;

    @Describe("资产名称")
    private String sfname;

    @Describe("资产别名")
    private String sfnameas;

    @Describe("资产分类编码")
    private String seqclassno;

    @Describe("所属线路")
    private Integer ilineid;

    @Describe("线路期段")
    private String slinestage;

    @Describe("品牌名称")
    private String sbrandnm;

    @Describe("生产厂商")
    private String sproductnm;

    @Describe("供货厂商")
    private String ssuppliernm;

    @Describe("规格型号")
    private String sspec;

    @Describe("计量单位")
    private String sunit;

    @Describe("数量")
    private Integer iqty;

    @Describe("安装位置")
    private String sinspos;

    @Describe("具体位置")
    private String sconlocation;

    @Describe("开始使用日期")
    private Date dstartuse;

    @Describe("质保期限(月)")
    private Integer iwarranty;

    @Describe("质保开始日期")
    private Date dwarrstart;

    @Describe("起始里程")
    private String slinestartpost;

    @Describe("终止里程")
    private String slineendpost;

    @Describe("权属单位")
    private String sownerunit;

    @Describe("资产原值")
    private Double iassetvalue;

    @Describe("融资状态")
    private String sfinancstate;

    @Describe("运营单位")
    private String soperatunitnm;

    @Describe("所属区县")
    private String sbldistrict;

    @Describe("固定资产状态")
    private String sfixedassetstatus;

    @Describe("二类分摊费用")
    private Double isharecost;

    @Describe("一类分摊费用")
    private Double iothercost;

    @Describe("币种")
    private String sassetcurrency;

    @Describe("币种单位")
    private String scurrencyunit;

    @Describe("所属专业")
    private String smajornm;

    @Describe("备注一")
    private String snoteOne;

    @Describe("备注二")
    private String snoteTwo;

    @Describe("备注三")
    private String snoteThree;

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

    @Virtual
    @Describe("是否入库字段")
    private Integer istoragestate;

    @Describe("所有组成部分")
    @Virtual
    private List<AmTransferPlanDtComp> amTransferPlanDtCompList;


    @Override
    public boolean __isTrueDel() {
        return false;
    }

    @Override
    public String __getTableName() {
        return "am_transfer_plan_dt";
    }

}
