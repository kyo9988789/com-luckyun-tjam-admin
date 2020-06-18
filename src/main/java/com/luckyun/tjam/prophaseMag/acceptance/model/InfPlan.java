package com.luckyun.tjam.prophaseMag.acceptance.model;

import java.util.Date;
import java.util.List;

import com.luckyun.annotation.Describe;
import com.luckyun.model.data.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * 设备设施移交计划主表
 * 
 * @action
 * @since JDK 1.8
 * @author zrw
 * @date: 2019年12月2日 10:39:01
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class InfPlan extends BaseEntity{

	@Describe("主键")
	private Long indocno;

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

	@Describe("移交描述")
	private String sdescribe;

	@Describe("移交来源（0.L1;1.P6）")
	private Integer isource;

	@Describe("删除标识")
	private Integer idel;

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

	@Describe("虚拟")
	private String slinenm;


	/*@Describe("接口表主键")
	private Long infIndocno;
	
	@Describe("移交描述")
	private String description;

	@Describe("移交编号")
	private String transferno;

	@Describe("移交启动编号")
	private String transnum;

	@Describe("计划开始日期")
	private Date planstartdate;

	@Describe("计划结束日期")
	private Date planenddate;

	@Describe("审核状态")
	private String status;

	@Describe("接口数据处理时间")
	private Date infDate;

	@Describe("删除标识")
	private Integer infIdel;

	@Describe("接口数据处理状态")
	private Integer infStatus;

	@Describe("时间戳")
	private Date infTimestamp;

	@Describe("唯一标识")
	private Long lmsTransferid;

	@Describe("外部系统主键")
	private Long pkeyValue;

	@Describe("标记")
	private Integer iflag;

	@Describe("所属线路")
	private Long iline;
	@Describe("所属线路名称")
	private String slinenm;
	
	@Describe("创建时间")
	private String createdate;

	@Describe("修改时间")
	private String changedate;

	@Describe("所属线路")
	private String slineNm;

	@Describe("移交来源(0.L1;1.P6)")
	private Integer isource;*/
	
	/** 一对多EAM移交计划子表数据 */
	@Describe("EAM移交计划子表数据")
	private List<InfL1PlanDetailed> infL1PlanDetailedList;
	
	/** L1设备设施登记明细表 */
	@Describe("L1设备设施登记明细表数据")
	private List<InfL1Inventory> infL1InvertoryList;
	
	/** P6设备设施初始清册数据 */
	@Describe("P6设备设施初始清册数据")
	private List<InfP6Inventory> infP6InventoryList;
	
	/** 重设主键：indocno -> inf_indocno */
	@Override
	public String __getkeyColumn() {
		return "indocno";
	}
	
	@Override
	public String __getTableName() {
		return "inf_plan";
	}
	
	@Override
	public boolean __isTrueDel() {
		return false;
	}

}
