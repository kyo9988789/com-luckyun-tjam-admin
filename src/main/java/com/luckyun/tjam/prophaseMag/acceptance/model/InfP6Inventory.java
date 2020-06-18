package com.luckyun.tjam.prophaseMag.acceptance.model;

import java.util.Date;

import com.luckyun.annotation.Describe;
import com.luckyun.model.data.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * P6设备设施初始清册数据
 * 
 * @action
 * @since JDK 1.8
 * @author zrw
 * @date: 2019年12月2日 11:15:36
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class InfP6Inventory extends BaseEntity{

	@Describe("主键")
	private Long indocno;

	@Describe("设备设施编码")
	private String sfcode;

	@Describe("设备设施名称")
	private String sfname;

	@Describe("设备设施别名")
	private String sfnameas;

	@Describe("所属线路")
	private Integer ilineid;

	@Describe("当前线路")
	private String scurlinenm;

	@Describe("线路期段")
	private String slinestage;

	@Describe("设备设施分类编号")
	private String seqclassno;

	@Describe("所属专业")
	private String smajornm;

	@Describe("品牌名称")
	private String sassetbrand;

	@Describe("生产厂商")
	private String sproductnm;

	@Describe("供货厂商")
	private String ssuppliernm;

	@Describe("型号规格")
	private String sspec;

	@Describe("计量单位")
	private String sunit;

	@Describe("增加方式")
	private String saddtype;

	@Describe("运营类别")
	private String soperatetype;

	@Describe("运营单位")
	private String soperatunitnm;

	@Describe("权属单位")
	private String sownerunit;

	@Describe("是否为虚节点")
	private String sisfakeasset;

	@Describe("所属区县")
	private String sbldistrict;

	@Describe("安装位置编号")
	private String sinslocationno;

	@Describe("具体位置描述")
	private String slocationdesc;

	@Describe("起始里程")
	private String slinestartpost;

	@Describe("终止里程")
	private String slineendpost;

	@Describe("设计使用年限（月）")
	private Integer sdesignlife;

	@Describe("使用寿命")
	private String sestimatedlife;

	@Describe("更改年限")
	private String ichangeyear;

	@Describe("更改寿命")
	private String ichangelife;

	@Describe("寿命计量单位")
	private String sdesignunit;

	@Describe("开始使用日期")
	private Date dstartuse;

	@Describe("质保期限(月)")
	private String iwarranty;

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

	@Describe("合同签订日期")
	private String dcontsign;

	@Describe("设备设施单价")
	private Double iprice;

	@Describe("数量")
	private Integer iqty;

	@Describe("设备设施合价")
	private Double ieqvalence;

	@Describe("币种")
	private String sassetcurrency;

	@Describe("币种单位")
	private String scurrencyunit;

	@Describe("使用单位")
	private String suseunit;

	@Describe("备注一")
	private String snote0ne;

	@Describe("备注二")
	private String snoteTwo;

	@Describe("备注三")
	private String snoteThree;

	@Describe("父级资产编号")
	private String sparentno;

	@Describe("外部系统主键")
	private String sexternalid;

	@Describe("是否删除")
	private String slmsinvaid;

	@Describe("接口数据创建人")
	private String screateusernm;

	@Describe("使用状态")
	private String susestate;

	@Describe("融资状态")
	private String sfinancstate;

	@Describe("一类分摊费用（元）")
	private Double iothercost;

	@Describe("运营维护单位")
	private String soperatmainunit;

	@Describe("运营维护协议是否签订")
	private String sisqd;

	@Describe("竣工资料是否提交")
	private String sistj;

	@Describe("过渡还是永久状态")
	private String stranspermanent;

	@Describe("使用年限（月）")
	private Integer iusefullife;

	@Describe("移交编号")
	private String stransferno;

	@Describe("数据来源(0.L1;1.P6)")
	private Integer isource;

	@Describe("接口数据接收时间")
	private Date dinfaccept;

	@Describe("数据是否已拷贝至业务表(0表示未拷贝,1表示已拷贝)")
	private Integer icopystate;

	@Describe("删除标识")
	private Integer IDEL;

	@Describe("单据状态")
	private Integer istate;

	@Describe("创建人姓名")
	private String sregnm;

	@Describe("创建人ID")
	private Integer sregid;

	@Describe("创建时间")
	private Date dregt;

	@Describe("最后一次修改人姓名（用于支持上线后特殊扩展需求，减少数据库调整风险）")
	private String smodnm;

	@Describe("最后一次修改人ID")
	private Integer smodid;

	@Describe("修改时间")
	private Date dmodt;

	@Describe("是否变更(第三方接口中数据值发生变更引起此数据变更)")
    private Integer iupdatestate;
	
	/** 重设主键：indocno -> inf_indocno */
	@Override
	public String __getkeyColumn() {
		return "indocno";
	}
	
	@Override
	public String __getTableName() {
		return "inf_p6_inventory";
	}
	
	@Override
	public boolean __isTrueDel() {
		return false;
	}

}
