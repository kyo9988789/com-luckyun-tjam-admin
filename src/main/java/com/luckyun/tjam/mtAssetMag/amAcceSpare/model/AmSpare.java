package com.luckyun.tjam.mtAssetMag.amAcceSpare.model;

import com.luckyun.annotation.Describe;
import com.luckyun.annotation.Virtual;
import com.luckyun.model.data.DocEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

//资产中期管理 - 备品备件台账表
@Data
@EqualsAndHashCode(callSuper = false)
public class AmSpare extends DocEntity {

    @Describe("主键")
    private Long indocno;

    @Describe("物品名称")
    private String sfname;

    @Describe("存货编号")
    private String sfcode;

    @Describe("专业")
    private String smajornm;

    @Describe("所属线路")
    private Integer ilineid;
    @Virtual
    private String slinenm;

    @Describe("供应商")
    private String ssuppliernm;

    @Describe("规格型号")
    private String sspec;

    @Describe("计量单位")
    private String sunit;

    @Describe("单价")
    private Double iprice;

    @Describe("数量")
    private String iqty;

    @Describe("金额")
    private Double imoney;

    @Describe("责任人")
    private Long idutyid;
    @Virtual
    private String sdutynm;

    @Describe("管理部门ID")
    private Long imanagedeptid;
    @Virtual
    private String smanagedeptnm;

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

    @Describe("所属资产")
    private String sassetno;

    @Describe("资产分类节点ID")
    private Long iamarcclassid;
    @Virtual
    private String samarcclassnm;

    @Describe("分类编号")
    private String seqclassno;

    @Describe("删除（0 未删 1 虚拟删除）")
    private Integer idel;

    @Describe("创建人ID")
    private Long sregid;

    @Describe("创建时间")
    private Date dregt;

    @Describe("资产所属资产节点位置")
    private String sidcc;

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

    @Describe("工器具台账图片表")
    private List<AmSpareAttachment> sparementList;

    @Override
    public boolean __isTrueDel() {
        return false;
    }

    @Override
    public String __getTableName() {
        return "am_spare";
    }
}
