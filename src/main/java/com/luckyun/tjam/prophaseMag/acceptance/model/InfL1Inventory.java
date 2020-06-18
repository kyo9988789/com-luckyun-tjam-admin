package com.luckyun.tjam.prophaseMag.acceptance.model;

import java.util.Date;

import com.luckyun.annotation.Describe;
import com.luckyun.model.data.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * L1设备设施登记明细表
 * 
 * @action
 * @since JDK 1.8
 * @author zrw
 * @date: 2019年12月2日 10:54:35
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class InfL1Inventory extends BaseEntity{

	@Describe("主键")
	private Long indocno;

	@Describe("设备设施编码")
	private String sfcode;

	@Describe("设备设施名称")
	private String sfname;

	@Describe("设备设施分类编号")
	private String seqclassno;

	@Describe("线路期段")
	private String slinestage;

	@Describe("品牌")
	private String sbrand;

	@Describe("制造厂商")
	private String sproductnm;

	@Describe("供货厂商")
	private String ssuppliernm;

	@Describe("规格型号")
	private String sspec;

	@Describe("计量单位")
	private String sunit;

	@Describe("数量")
	private Integer iqty;

	@Describe("设备设施单价")
	private Double iprice;

	@Describe("分摊方式")
	private String sallocationmethod;

	@Describe("安装位置编号")
	private String sinslocationno;

	@Describe("具体位置")
	private String sconlocation;

	@Describe("使用年限")
	private Double iuseyear;

	@Describe("寿命计量单位")
	private String sdesignunit;

	@Describe("使用寿命")
	private String sestimatedlife;

	@Describe("开始使用日期")
	private Date dstartuse;

	@Describe("质保期限")
	private Integer iwarranty;

	@Describe("质保开始日期")
	private Date dwarrstart;

	@Describe("起始里程")
	private String slinestartpost;

	@Describe("终止里程")
	private String slineendpost;

	@Describe("备注一")
	private String snoteOne;

	@Describe("备注二")
	private String snoteTwo;

	@Describe("备注三")
	private String snoteThree;

	@Describe("父级资产编号")
	private String sparentno;

	@Describe("接口数据接收时间")
	private Date dinfaccept;

	@Describe("接口状态")
	private Integer iinfstate;

	@Describe("所属资产编号")
	private String sassetno;

	@Describe("外部系统主键")
	private String sexternalid;

	@Describe("唯一标识")
	private Integer itransferid;

	@Describe("所属线路")
	private Integer ilineid;

	@Describe("项目编码")
	private String sprocode;

	@Describe("使用单位")
	private String suseunit;

	@Describe("使用状态")
	private String susestate;

	@Describe("保管使用人")
	private String susernm;

	@Describe("设备类型")
	private String seqtype;

	@Describe("所属区县")
	private String sbldistrict;

	@Describe("二类分摊费用")
	private Double isharecost;

	@Describe("间[座]数")
	private String sroomsnum;

	@Describe("维修单位")
	private String srepairunitnm;

	@Describe("维修部门")
	private String srepairdeptnm;

	@Describe("购置类型")
	private String spurchasetype;

	@Describe("机动车辆牌照号")
	private String splatenum;

	@Describe("机动车核定载质量")
	private String SPASSLOAD;

	@Describe("机动车核定载客")
	private String spassengernum;

	@Describe("权属单位")
	private String sownerunit;

	@Describe("一类分摊费用")
	private Double iothercost;

	@Describe("运营单位")
	private String soperatunitnm;

	@Describe("运营类别")
	private String soperatetype;

	@Describe("设备设施别名")
	private String sfnameas;

	@Describe("电动车辆编组号")
	private String smetrono;

	@Describe("管理单位")
	private String smagunitnm;

	@Describe("管理部门")
	private String smagdeptnm;

	@Describe("重要等级")
	private String smagnitude;

	@Describe("详细位置编码")
	private String slocationcode;

	@Describe("土地面积")
	private String slandarea;

	@Describe("是否虚节点")
	private String sisfakeasset;

	@Describe("是否工程抢险车")
	private String SISENGERCAR;

	@Describe("是否抢险设备")
	private String sisemerg;

	@Describe("机动车燃料类型")
	private String sfueltype;

	@Describe("固定资产状态")
	private String sfixedassetstatus;

	@Describe("融资状态")
	private String sfinancstate;

	@Describe("机动车尾气排放标准")
	private String sexhauststandards;

	@Describe("所属产权")
	private String squity;

	@Describe("机动车发动机号码")
	private String sengineno;

	@Describe("抢险车号码")
	private String semergcarno;

	@Describe("处置状态")
	private String sdisposalstatus;

	@Describe("当前线路")
	private String scurlinenm;

	@Describe("合同类型(采购合同、协议、补充协议、结算审核、其他)")
	private String sconttype;

	@Describe("是否总价合同")
	private String scontsumflag;

	@Describe("合同编号")
	private String scontno;

	@Describe("合同总金额")
	private String sconttotalmoney;

	@Describe("合同签订乙方")
	private String scontpartb;

	@Describe("合同签订甲方")
	private String scontfirstparty;

	@Describe("合同签订日期")
	private Date dcontsign;

	@Describe("核验日期")
	private Date dtranscheck;

	@Describe("机动车辆类型")
	private String scartype;

	@Describe("房屋产权证号")
	private String sbuildingno;

	@Describe("建筑面积")
	private String sbuildingarea;

	@Describe("旧资产编码")
	private String sassetnoOld;

	@Describe("新资产编码")
	private String sassetnoNew;

	@Describe("资产原值")
	private Double iassetvalue;

	@Describe("资产入网编组号")
	private String sassetItno;

	@Describe("增加方式")
	private String saddtype;

	@Describe("核验状态")
	private String sstransnm;

	@Describe("京投受托资产核对状态")
	private String scheckstate;

	@Describe("京投状态")
	private String slmstatusnm;

	@Describe("移交来源（0.L1;1.P6）")
	private Integer isource;

	@Describe("数据是否已拷贝至业务表(空表示未拷贝,1表示已拷贝)")
	private Integer icopystate;

	@Describe("删除标识")
	private Integer idel;

	@Describe("创建时间")
	private Date dregt;

	@Describe("修改时间")
	private Date dmodt;

	@Describe("单据状态")
	private Integer istate;

	@Describe("创建人姓名")
	private String sregnm;

	@Describe("创建人ID")
	private Integer sregid;

	@Describe("最后一次修改人姓名（用于支持上线后特殊扩展需求，减少数据库调整风险）")
	private String smodnm;

	@Describe("最后一次修改人ID")
	private Integer smodid;

	@Describe("是否变更(第三方接口中数据值发生变更引起此数据变更)")
    private Integer iupdatestate;
	
	/** 重设主键：indocno -> inf_indocno */
	@Override
	public String __getkeyColumn() {
		return "indocno";
	}
	
	@Override
	public String __getTableName() {
		return "inf_l1_inventory";
	}
	
	@Override
	public boolean __isTrueDel() {
		return false;
	}

}
