package com.luckyun.tjam.prophaseMag.acceptance.model;

import java.util.Date;

import com.luckyun.annotation.Describe;
import com.luckyun.model.data.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * EAM移交计划子表数据
 * 
 * @action
 * @since JDK 1.8
 * @author zrw
 * @date: 2019年12月2日 10:43:56
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class InfL1PlanDetailed extends BaseEntity{

	@Describe("主键")
	private Long indocno;

	@Describe("设备设施编码")
	private String sfcode;

	@Describe("移交专业")
	private String sassetmajornm;

	@Describe("移交条码")
	private String sbarcode;

	@Describe("差异信息")
	private String sdiff;

	@Describe("差异处理")
	private String sdonediff;

	@Describe("接口数据接收时间")
	private Date dinfaccept;

	@Describe("接口数据处理状态")
	private String iinfstate;

	@Describe("核验状态")
	private String sstransnm;

	@Describe("父资产")
	private String sparentno;

	@Describe("实际品牌")
	private String sbrandAct;

	@Describe("实际安装位置编号")
	private String sinslocationnoAct;

	@Describe("起始里程")
	private String slinestartpost;

	@Describe("实际制造厂商")
	private String sproductnmAct;

	@Describe("实际数量")
	private String iqtyAct;

	@Describe("移交编号")
	private String stransferno;

	@Describe("接收单位")
	private String stranssite;

	@Describe("终止里程")
	private String slineendpost;

	@Describe("唯一标识")
	private Integer itransferid;

	@Describe("外部系统主键")
	private String sexternalid;

	@Describe("实际规格型号")
	private String sspecAct;

	@Describe("所属线路")
	private Integer ilineid;

	@Describe("资产描述")
	private String sdescribe;

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
		return "inf_l1_plan_detailed";
	}
	
	@Override
	public boolean __isTrueDel() {
		return false;
	}

}
