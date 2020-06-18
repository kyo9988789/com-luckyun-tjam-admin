package com.luckyun.tjam.prophaseMag.acceptance.model;


import com.luckyun.annotation.Describe;
import com.luckyun.model.data.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public class InfTransferState extends BaseEntity {

	@Describe("主键")
	private Long indocno;

	@Describe("实物资产台账表主键")
	private Long ilinkno;

	@Describe("设备设施名称")
	private String sfname;

	@Describe("设备设施编码")
	private String sfcode;

	@Describe("外部系统主键")
	private String sexternalid;

	@Describe("核验状态")
	private String sstransnm;

	@Describe("核验日期")
	private Date dvrf;

	@Describe("接口数据处理时间")
	private Date dinfsend;

	@Describe("接口数据处理状态")
	private Integer iinfstate;

	@Describe("移交编号")
	private String stransferno;

	@Describe("唯一标识")
	private Integer itransferid;

	@Describe("实际品牌")
	private String sbrandAct;

	@Describe("实际安装位置")
	private String sinslocationnoAct;

	@Describe("终止里程")
	private String slineendpost;

	@Describe("起始里程")
	private String slinestartpost;

	@Describe("实际制造厂商")
	private String sproductnmAct;

	@Describe("实际规格型号")
	private String sspecAct;

	@Describe("实际数量")
	private String iqty;

	@Describe("维修单位")
	private String srepairunitnm;

	@Describe("维修部门")
	private String srepairdeptnm;

	@Describe("对方主键(和唯一键一样)")
	private String seamid;

	@Describe("所属线路")
	private String slinenm;

	@Describe("未接收原因")
	private String unreceivedcause;

	@Describe("数据来源")
	private Integer isource;

	@Describe("线路期段")
	private String slinestage;

	@Describe("删除（0 未删 1 虚拟删除）")
	private Integer idel;

	@Describe("创建人ID")
	private Integer sregid;

	@Describe("创建时间")
	private Date dregt;

	@Describe("状态（0无效1有效）")
	private Integer istate;

	@Describe("创建人名称")
	private  String sregnm;

	@Describe("修改人id")
	private Integer smodid;

	@Describe("修改人名称")
	private String smodnm;

	@Describe("修改时间")
	private  Date dmodt;

	@Describe("移交描述")
	private String sdescribe;

	@Override
	public String __getkeyColumn() {
		return "indocno";
	}

	@Override
	public String __getLogicDelColumn() {
		return "idel";
	}

	@Override
	public boolean __isTrueDel() {
		return false;
	}

	@Override
	public String __getTableName() {
		return "inf_transfer_state";
	}
}
