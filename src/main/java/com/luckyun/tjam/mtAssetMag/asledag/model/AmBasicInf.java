package com.luckyun.tjam.mtAssetMag.asledag.model;


import com.luckyun.annotation.Describe;
import com.luckyun.annotation.Virtual;
import com.luckyun.model.data.DocEntity;
import com.luckyun.tjam.prophaseMag.verification.bpm.BpmTaskParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

//资产卡片基本信息表
@Data
@EqualsAndHashCode(callSuper = false)
public class AmBasicInf extends DocEntity {

    @Describe("是否已生成移交计划(0.未生成，1.已生成)")
    private Integer igenetpstateid;

    @Describe("开始闲置时间")
    private Date dstartfreetime;

    @Describe("主键")
    private Long indocno;

    @Describe("资产分类节点ID")
    private Long iamarcclassid;

    @Virtual
    private String samarcclassnm;

    @Describe("编号")
    private String sfcode;

    @Describe("父子节点关系")
    private String sidcc;

    @Describe("资产分类")
    private String  seqclassno;

    @Virtual
    private String sclassstructurenm;

    @Describe("审批状态(1.未发起；2.审批中；3.审批完成；4.退回)")
    private Integer iapprovalstate;
    @Virtual
    private String sapprovalstate;

    @Describe("所属线路")
    private Integer ilineid;

    @Describe("线路期段")
    private String slinestage;

    @Describe("类别(0.设备设施；1.办公用品；2.工器具)")
    private Integer icategoryid;

    @Describe("规格型号")
    private String sspec;

    @Describe("开始使用日期")
    private Date dstart;

    @Describe("品牌名称")
    private String sbrandnm;

    @Describe("生产厂商")
    private String sproductnm;

    @Virtual
    private Long imanagedeptid;
    @Virtual
    private String smanagedeptnm;

    @Virtual
    private Long iuserdeptid;
    @Virtual
    private String suserdeptnm;

    @Describe("供货厂商")
    private String ssuppliernm;

    @Describe("卡片类型,数据字典")
    private Integer icardtype;

    @Describe("运营类别0.设备设施；1.办公用品；2.工器具")
    private Integer iopergoryid;

    @Describe("资产状态:0.正常(默认值)；1.损坏；2.报废；3.闲置")
    private Integer iamstate;

    @Describe("资产类别0.直接运营资产(默认值)；1.间接运营资产")
    private Integer iamclassid;

    @Describe("重要等级:0.A；1.B；2.C；3.D")
    private Integer iimportlevelid;

    @Describe("增加方式")
    private String saddway;

    @Describe("备注一")
    private String snoteone;

    @Describe("备注二")
    private String snotetwo;

    @Describe("备注三")
    private String snotethree;

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
    private String sfname;

    @Describe("名称")
    private String sname;

    @Describe("是否已被其他模块引用:0.未引用,1.已引用")
    private Integer iquotestate;

    //卡片类型名称
    @Virtual
    private String scardtypenm;

    //@Describe("所属线路")
    @Virtual
    private String slinenm;


    //@Describe("类别名字(0.设备设施；1.办公用品；2.工器具)")
    @Virtual
    private String scategorynm;

    //@Describe("运营类别名称0.设备设施；1.办公用品；2.工器具")
    @Virtual
    private String sopergorynm;

    //@Describe("资产状态:0.正常(默认值)；1.损坏；2.报废；")
    @Virtual
    private String samstatenm;

    //@Describe("资产类别0.直接运营资产(默认值)；1.间接运营资产")
    @Virtual
    private String samclassnm;

    //@Describe("重要等级:0.A；1.B；2.C；3.D")
    @Virtual
    private String simportlevelnm;

    //@Describe("资产卡片信息")
    @Virtual
    private AmBasicAnnex annex;

    //@Describe("合同信息表")
    @Virtual
    private AmBasicContractInf contractInfs;

   // @Describe("建立财务信息表")
   @Virtual
    private  AmBasicCreateFincInf createFincInfs;

   /* @Describe("ll")
    private List<AmBasicCurFincInf> curFincInfs;*/

    //@Describe("折旧流水表")
    @Virtual
    private AmBasicDepreciation depreciations;

    //@Describe("当前折旧信息表")
    @Virtual
    private AmBasicDepreciationInf depreciationInfs;

    //@Describe("位置区域")
    @Virtual
    private AmBasicLocArea locAreas;

   // @Describe("管理信息")
   @Virtual
    private AmBasicManagementInf managementInfs;

    //@Describe("权属信息")
    @Virtual
    private AmBasicTenureInf tenureInfs;

    //@Describe("价值信息表")
    @Virtual
    private AmBasicValueInf valueInfs;

    //@Describe("资产卡片组成部分")
    @Virtual
    private AmBasicComponentInf componentInf;

    //@Describe("资产卡片信息")
    @Virtual
    private List<AmBasicAnnex> annexs;

    //@Describe("合同信息表")
    @Virtual
    private List<AmBasicContractInf> contractInfss;

    //@Describe("建立财务信息表")
    @Virtual
    private  List<AmBasicCreateFincInf> createFincInfss;

   /* @Describe("ll")
    private List<AmBasicCurFincInf> curFincInfs;*/

    //@Describe("折旧流水表")
    @Virtual
    private List<AmBasicDepreciation> depreciationss;

    //@Describe("当前折旧信息表")
    @Virtual
    private List<AmBasicDepreciationInf> depreciationInfss;

    //@Describe("位置区域")
    @Virtual
    private List<AmBasicLocArea> locAreass;

    //@Describe("管理信息")
    @Virtual
    private List<AmBasicManagementInf> managementInfss;

    //@Describe("权属信息")
    @Virtual
    private List<AmBasicTenureInf> tenureInfss;

    //@Describe("价值信息表")
    @Virtual
    private List<AmBasicValueInf> valueInfss;

    //@Describe("资产卡片组成部分")
    @Virtual
    private List<AmBasicComponentInf> componentInfs;

    //@Describe("新增图片集合")
    @Virtual
    private List<AmBasicAnnex> addImagesList;

    //@Describe("新增附件集合")
    @Virtual
    private List<AmBasicAnnex> addFujiList;

    //@Describe("待删除图片集合")
    @Virtual
    private List<AmBasicAnnex> delImagesList;

    //@Describe("待删除附件集合")
    @Virtual
    private List<AmBasicAnnex> delFujiList;

    @Virtual
    private String smajornm;

    @Virtual
    private Long idutyid;
    @Virtual
    private String sdutynm;

    @Virtual
    private Long iuserid;
    @Virtual
    private String susernm;

    @Virtual
    private String sinspos;

    @Virtual
    private String sownernuit;

    @Virtual
    private Integer iqty;

    @Describe("流程对象")
    @Virtual
    private BpmTaskParam bpmTaskParam;


    @Override
    public boolean __isTrueDel() {
        return false;
    }

    @Override
    public String __getTableName() {
        return "am_basic_inf";
    }

}
