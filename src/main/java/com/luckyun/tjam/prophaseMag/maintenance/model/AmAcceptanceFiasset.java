package com.luckyun.tjam.prophaseMag.maintenance.model;

import java.util.Date;
import java.util.List;

import com.luckyun.annotation.Describe;
import com.luckyun.annotation.Virtual;
import com.luckyun.model.data.DocEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

 /*
 * 设备设施计划详情表
 * 
 * @action
 * @since JDK 1.8
 * @author zrw
 * @date: 2019年12月3日 16:09:18
*/


@Data
@EqualsAndHashCode(callSuper = false)
public class AmAcceptanceFiasset extends DocEntity{

    @Describe("主键")
    private Long indocno;

    @Describe("外键")
    private Long ilinkno;

    @Describe("设备设施编码")
    private String sfcode;

    @Describe("设备设施别名")
    private String sfnameas;

    @Describe("所属线路")
    private Integer ilineid;

    @Describe("设备设施名称")
    private String sfname;

    @Describe("线路期段")
    private String slinestage;

    @Describe("设备设施分类编号")
    private String seqclassno;

    @Describe("所属专业")
    private String smajornm;

    @Describe("接口数据接收时间")
    private Date dinfaccept;

    @Describe("接收单位")
    private String stranssite;

    @Describe("未接收原因")
    private String sunreceivedcause;

    @Describe("实际品牌")
    private String sbrandAct;

    @Describe("生产厂商(实际制造商)")
    private String sproductnmAct;

    @Describe("供货厂商")
    private String ssuppliernm;

    @Describe("实际规格型号")
    private String sspecAct;

    @Describe("计量单位")
    private String sunit;

    @Describe("运营类别1:直接运营资产,2:间接运营资产）")
    private String soperatetype;

    @Describe("运营单位")
    private String soperatunitnm;

    @Describe("权属单位")
    private String sownerunit;

    @Describe("所属区县")
    private String sbldistrict;

    @Describe("实际安装位置编号")
    private String sinslocationnoAct;

    @Describe("具体位置")
    private String sconlocation;

    @Describe("起始里程")
    private String slinestartpost;

    @Describe("终止里程")
    private String slineendpost;

    @Describe("使用年限")
    private Double iuseyear;

    @Describe("使用寿命")
    private String sestimatedlife;

    @Describe("寿命计量单位")
    private String sdesignunit;

    @Describe("开始使用日期")
    private Date dstartuse;

    @Describe("质保期限(月)")
    private Integer iwarranty;

    @Describe("质保开始日期")
    private Date dwarrstart;

    @Describe("合同类型(采购合同、协议、补充协议、结算审核、其他)")
    private String sconttype;

    @Describe("是否总价合同")
    private String scontsumflag;

    @Describe("合同编号")
    private String scontno;

    @Describe("合同签订甲方")
    private String scontfirstparty;

    @Describe("合同签订乙方")
    private String scontpartb;

    @Describe("合同总金额")
    private String sconttotalmoney;

    @Describe("分摊方式")
    private String sallocationmethod;

    @Describe("合同签订日期")
    private String dcontsign;

    @Describe("设备设施单价")
    private Double iprice;

    @Describe("实际数量")
    private String iqtyAct;

    @Describe("设备设施合价")
    private Double ieqvalence;

    @Describe("币种")
    private String sassetcurrency;

    @Describe("币种单位")
    private String scurrencyunit;

    @Describe("使用单位")
    private String suseunit;

    @Describe("备注一")
    private String snoteOne;

    @Describe("备注二")
    private String snoteTwo;

    @Describe("备注三")
    private String snoteThree;

    @Describe("父级资产编号")
    private String sparentno;

    @Describe("外部系统主键")
    private String sexternalid;
    @Describe("使用状态")
    private String susestate;

    @Describe("保管使用人")
    private String susernm;

    @Describe("融资状态")
    private String sfinancstate;

    @Describe("二类分摊费用")
    private Double isharecost;

    @Describe("维修单位")
    private String srepairunitnm;

    @Describe("购置类型")
    private String spurchasetype;

    @Describe("固定资产状态")
    private String sfixedassetstatus;

    @Describe("旧资产编码")
    private String sassetnoOld;

    @Describe("新资产编码")
    private String sassetnoNew;

    @Describe("资产原值")
    private Double iassetvalue;

    @Describe("资产入网编组号")
    private String sassetItno;

    @Describe("核验状态")
    private Integer istrans;

    @Describe("一类分摊费用")
    private Double iothercost;

    @Describe("过渡还是永久状态")
    private String stranspermanent;

    @Describe("使用年限（月）")
    private Integer iusefullife;

    @Describe("态变更人ID")
    private Long ichangeuserid;

    @Describe("状态变更时间")
    private Date dchangetime;

    @Describe("变更说明")
    private String scon;

    @Describe("管理部门ID")
    private Long imanagedeptid;

    @Describe("专业ID")
    private Long imajorid;

    @Describe("责任人ID")
    private Long idutyid;

    @Describe("分配人ID")
    private Long idistruserid;

    @Describe("分配时间")
    private Date ddisttime;

    @Describe("分配状态ID")
    private Integer idiststatus;

    @Describe("移交验收人ID")
    private Long icheckuserid;

    @Virtual
    private String scheckusernm;

    @Describe("已打标签卡")
    private Integer iscard;

    @Describe("相关属性:数据来源ID(0.L1;1.P6)")
    private Integer isource;

    @Describe("数据接入ID(0:接口,1:导入)")
    private Integer iaccess;

    @Describe("附件名称")
    private String sname;

    @Describe("附件路径")
    private String spath;

    @Describe("附件大小")
    private Integer isize;

    @Describe("附件描述")
    private String sattdescribe;

    @Describe("删除标识")
    private Integer idel;

    @Describe("单据状态")
    private Integer istate;

    @Describe("创建人姓名")
    private String sregnm;

    @Describe("创建人ID")
    private Long sregid;

    @Describe("创建时间")
    private Date dregt;

    @Describe("最后一次修改人姓名（用于支持上线后特殊扩展需求，减少数据库调整风险）")
    private String smodnm;

    @Describe("最后一次修改人ID")
    private Long smodid;

    @Describe("修改时间")
    private Date dmodt;

    @Describe("移交条码")
    private String sbarcode;

    @Describe("是否已生成核验计划:0.未生成,1.已生成")
    private Integer idatastate;

    @Describe("消息主键")
    private Long imessageid;

    @Describe("是否抢险设备")
    private String sisemerg;

    @Describe("附件类型")
    private String satttype;

    @Describe("移交描述")
    private String sdescribe;

    @Describe("是否已上传")
    private Integer iuploadstate;

    @Describe("组成清单")
    private List<AmAcceptanceFiasset> inventoryList;

    @Describe("是否含有重要组成")
    private Integer ihasSonInventory;

    @Describe("当前用户主键,用作责任人版过滤数据")
    private Long icuruserid;

    @Describe("查看的当前模块是管理员版还是责任人版，0 = 责任人版，未传表示是管理员版不用过滤数据，展示所有即可")
    private Integer iiscapadmin;

    @Describe("是否含有重要组成名称")
    private String shasSonInventory;

    @Describe("管理部门名称")
    private String smanagedeptnm;

    @Describe("责任人名称")
    private String sdutynm;

    @Describe("分配状态名称")
    private String sdiststatusnm;

    @Virtual
    private String slinenm;

    @Describe("是否外部系统")
    private String isOutSys;

    @Describe("状态变更人名称")
    private String schangeusernm;

    @Describe("分配人ID")
    private String sdistrusernm;

    @Describe("相关属性:已打标签卡名称")
    private String sscard;

    @Describe("相关属性:数据来源名称")
    private String ssource;

    @Virtual
    private String sstrans;

    @Describe("相关属性:数据接入名称")
    private String saccess;

    @Describe("设备设施分类编码")
    private String sclassstructurenm;

    @Describe("附件oss路径")
    private String nspath;

    @Virtual
    private String sstransnm;

    @Describe("是否变更(第三方接口中数据值发生变更引起此数据变更)")
    private Integer iupdatestate;
    
    @Describe("数据来源ID(接口数据ID)")
    private Long isourceid;
    
    @Describe("是否可生成核验计划")
    @Virtual
    private Integer igenevrfstateid;
    
    @Describe("是否可生成核验计划")
    @Virtual
    private String sgenevrfstatenm;

    @Describe("是否已被其他模块引用:0.未引用,1.已引用")
    private Integer iquotestate;

    @Describe("类别(0.设备设施；1.办公用品；2.工器具)")
    private Integer icategoryid;
    
    @Describe("费用来源")
    private String scostsource;
    
    @Describe("移交编号")
	private String stransferno;
    
    @Describe("唯一标识")
	private Integer itransferid;
    
    @Describe("维修部门")
	private String srepairdeptnm;
    
	@Override
	public boolean __isTrueDel() {
		return false;
	}
	
	@Override
	public String __getTableName() {
		return "am_acceptance_fiasset";
	}
	
}
