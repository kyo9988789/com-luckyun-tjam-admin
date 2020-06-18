package com.luckyun.tjam.mtAssetMag.amDurableLowValue.model;


import com.luckyun.annotation.Describe;
import com.luckyun.annotation.Virtual;
import com.luckyun.model.data.DocEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

//资产中期管理 - 耐用低值易耗品台账表
@Data
@EqualsAndHashCode(callSuper = false)
public class AmDurLvConsumable extends DocEntity {

    @Describe("主键")
    private Long indocno;

    @Describe("物品名称")
    private String sfname;

    @Describe("物品编号")
    private String sfcode;

    @Describe("所属线路")
    private Integer ilineid;
    @Virtual
    private String slinenm;

    @Describe("品牌名称")
    private String sbrandnm;

    @Describe("规格型号")
    private String sspec;

    @Describe("资产分类节点ID")
    private Long iamarcclassid;
    @Virtual
    private String samarcclassnm;

    @Describe("分类编号")
    private String seqclassno;

    @Describe("资产所属资产节点位置")
    private String sidcc;

    @Describe("单价")
    private Double iprice;

    @Describe("核算部门")
    private Long iaccountdeptid;
    @Virtual
    private String saccountdeptnm;

    @Describe("存放区域")
    private String sinsarea;

    @Describe("存放位置")
    private String sinspos;

    @Describe("存放房间")
    private String sposroom;

    @Describe("购置日期")
    private Date dpurchase;

    @Describe("责任人")
    private Long idutyid;
    @Virtual
    private String sdutynm;

    @Describe("费用来源")
    private String scostsource;

    @Describe("管理单位")
    private String smanagenuit;

    @Describe("权属单位")
    private String sownernuit;

    @Describe("使用单位")
    private String susenuit;

    @Describe("使用人ID")
    private Long iuserid;
    @Virtual
    private String susernm;

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

    @Describe("图片表")
    private List<AmDurLvConsumableAtt> consumableAttList;


    @Override
    public boolean __isTrueDel() {
        return false;
    }

    @Override
    public String __getTableName() {
        return "am_dur_lv_consumable";
    }
}
