package com.luckyun.tjam.prophaseMag.acceptance.model;

import java.util.Date;

import com.luckyun.annotation.Describe;
import com.luckyun.annotation.Virtual;
import com.luckyun.model.data.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * EAM和P6备品备件台账信息
 * 
 * @action
 * @since JDK 1.8
 * @author zrw
 * @date: 2019年12月2日 11:28:40
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class InfSparesInventory extends BaseEntity{

	@Describe("主键")
	private Long indocno;

	@Describe("外键")
	private Long ilinkno;

	@Describe("存货编号")
	private String sspareno;

	@Describe("存货名称")
	private String ssparenm;

	@Describe("规格型号")
	private String sspec;

	@Describe("计量单位")
	private String sunit;

	@Describe("厂家/供应商")
	private String ssuppliernm;

	@Describe("单价")
	private Double iprice;

	@Describe("数量")
	private Integer iqty;

	@Describe("金额")
	private Double imoney;

	@Describe("专业")
	private String smajornm;

	@Describe("所属资产")
	private String sassetno;

	@Describe("金额单位")
	private String scurrencyunit;

	@Describe("外部系统主键")
	private String sexternalid;

	@Describe("是否删除")
	private String slmsinvaid;

	@Describe("核验状态")
	private String sstransnm;

	@Describe("接口数据处理状态")
	private Integer iinfstate;

	@Describe("接口数据接收时间")
	private Date dinfaccept;

	@Describe("所属线路")
	private Integer ilineid;
	@Describe("所属线路名称")
	@Virtual
	private String slinenm;
	
	@Describe("权属单位")
	private String sownerunit;

	@Describe("运营单位(默认北京地铁)")
	private String soperatunitnm;

	@Describe("核验描述")
	private String sdescribe;

	@Describe("数据来源(0.L1;1.P6)")
	private Integer isource;

	@Describe("合同描述")
	private String scontdesc;

	@Describe("合同编号")
	private String scontno;

	@Describe("结算审计单价（元）")
	private Double ijscost;

	@Describe("数据是否已拷贝至业务表(0表示未拷贝,1表示已拷贝)")
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
		return "inf_spares_inventory";
	}
	
	@Override
	public boolean __isTrueDel() {
		return false;
	}

}
