package com.luckyun.tjam.prophaseMag.acceptance.model;

import java.util.Date;
import java.util.List;

import com.luckyun.annotation.Describe;
import com.luckyun.annotation.Virtual;
import com.luckyun.model.data.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * 备品备件移交计划主表
 * 
 * @action
 * @since JDK 1.8
 * @author zrw
 * @date: 2019年12月2日 11:15:47
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class InfSparesPlan extends BaseEntity{

	@Describe("主键")
	private Long indocno;

	@Describe("移交描述")
	private String sdescribe;

	@Describe("移交编号")
	private String stransferno;

	@Describe("移交启动编号")
	private String strstartno;

	@Describe("计划开始日期")
	private Date dstart;

	@Describe("计划结束日期")
	private Date dend;

	@Describe("审核状态")
	private String sauditnm;

	@Describe("接口数据接收时间")
	private Date dinfaccept;

	@Describe("接口数据处理状态")
	private Integer iinfstate;

	@Describe("唯一标识")
	private Integer itransferid;

	@Describe("外部系统主键")
	private String sexternalid;

	@Describe("所属线路")
	private Integer ilineid;
	@Describe("所属线路名称")
	@Virtual
	private String slinenm;

	@Describe("移交来源（0.L1;1.P6）")
	private Integer isource;

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

	/** EAM和P6备品备件台账信息 */
	@Describe("EAM和P6备品备件台账信息")
	List<InfSparesInventory> infSparesInventoryList;
	
	/** 重设主键：indocno -> inf_indocno */
	@Override
	public String __getkeyColumn() {
		return "indocno";
	}
	
	@Override
	public String __getTableName() {
		return "inf_spares_plan";
	}
	
	@Override
	public boolean __isTrueDel() {
		return false;
	}
	
}
