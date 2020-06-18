package com.luckyun.tjam.mtAssetMag.amToolsag.model;

import com.luckyun.annotation.Describe;
import com.luckyun.annotation.Virtual;
import com.luckyun.model.data.DocEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;


//资产中期管理 - 工器具台账表
@Data
@EqualsAndHashCode(callSuper = false)
public class AmTools extends DocEntity {

    @Describe("主键")
    private Long indocno;

    @Describe("物品名称")
    private String sfname;

    @Describe("物品编号")
    private String sfcode;

    @Describe("分类编号")
    private String seqclassno;

    @Describe("所属线路")
    private Integer ilineid;
    @Virtual
    private String slinenm;

    @Describe("品牌名称")
    private String sbrand;

    @Describe("规格型号")
    private String sspec;

    @Describe("计量单位")
    private String sunit;

    @Describe("数量")
    private Integer iqty;

    @Describe("单价")
    private Double iprice;

    @Describe("管理部门ID")
    private Long imanagedeptid;
    @Virtual
    private String smanagedeptnm;

    @Describe("责任人")
    private Long idutyid;
    @Virtual
    private String sdutynm;

    @Describe("专业")
    private String smajornm;

    @Describe("存放位置")
    private String sinspos;

    @Describe("存放房间")
    private String sinsroom;

    @Describe("存放区域")
    private String sinsarea;

    @Describe("备注一")
    private String snoteone;

    @Describe("备注二")
    private String snotetwo;

    @Describe("权属单位")
    private String sownerunit;

    @Describe("管理单位")
    private String smagunitnm;

    @Describe("使用人ID")
    private Long iuserid;
    @Virtual
    private String susernm;

    @Describe("资产分类节点ID")
    private Long iamarcclassid;
    @Virtual
    private String samarcclassnm;

    @Describe("资产所属资产节点位置")
    private String sidcc;

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

    @Describe("工器具台账图片表")
    private List<AmToolsAttachment> attachmentList;

    @Override
    public boolean __isTrueDel() {
        return false;
    }

    @Override
    public String __getTableName() {
        return "am_tools";
    }
}
