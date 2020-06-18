
package com.luckyun.tjam.prophaseMag.verification.model;

import java.util.Date;
import java.util.List;

import com.luckyun.annotation.Describe;
import com.luckyun.annotation.LogMainTableBasicAttr;
import com.luckyun.annotation.Virtual;
import com.luckyun.model.data.DocEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;


/*
 * 
  *  设备设施核验计划明细表
 * 
 * @action
 * @since JDK 1.8
 * @author zrw
 * @date: 2019年12月10日 14:41:59
 */


@Data
@EqualsAndHashCode(callSuper = false)
public class AmVrfPlanDtFiasset extends DocEntity{

    @Describe("主键")
    private Long indocno;

    @Describe("外键")
    @LogMainTableBasicAttr
    private Long ilinkno;

    @Describe("实物资产编号")
    private String sfcode;

    @Describe("实物资产名称")
    private String sfname;

    @Describe("设备设施分类编号")
    private String seqclassno;
    
    @Describe("设备设施分类编码")
    @Virtual
    private String sclassstructurenm;
    
    @Describe("移交编号")
    private String stransferno;

    @Describe("核验状态")
    private Integer istrans;

    @Describe("未接收原因")
    private String sunreceivedcause;

    @Describe("移交描述")
    private String sdescribe;

    @Describe("移交专业")
    private String smajornm;

    @Describe("线路期段")
    private String slinestage;

    @Describe("供货厂商")
    private String ssuppliernm;

    @Describe("生产厂商")
    private String sproductnmAct;

    @Describe("实际品牌")
    private String sbrandAct;

    @Describe("实际安装位置编号")
    private String sinslocationnoAct;

    @Describe("实际数量")
    private String iqtyAct;

    @Describe("实际规格型号")
    private String sspecAct;

    @Describe("起始里程")
    private String slinestartpost;

    @Describe("终止里程")
    private String slineendpost;

    @Describe("设备设施单价")
    private Double iprice;

    @Describe("使用寿命")
    private String sestimatedlife;

    @Describe("父级资产编号")
    private String sparentno;

    @Describe("权属单位")
    private String sownerunit;

    @Describe("数据来源")
    private Integer isource;

    @Describe("外部系统主键")
    private String sexternalid;

    @Describe("备注一(数量及单位)")
    private String snoteOne;

    @Describe("备注二(位置信息")
    private String snoteTwo;

    @Describe("备注三(入网编组、调度号等)")
    private String snoteThree;

    @Describe("核验日期")
    private Date dvrf;

    @Describe("核验人ID")
    private Integer icheckuserid;

    @Describe("核验人名称")
    private String svrfusernm;

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

    @Describe("计划状态:0.未下发;1.已下发;2.完结")
    private Integer iplanstate;

    @Describe("核验计划明细生成方式:0.实物资产模块生成;1.自增")
    private Integer igeneratetype;

    @Describe("实物资产台账表主键")
    private Long iamaccfiassetid;

    @Describe("资产组成部分")
    List<AmVrfPlanDtFiasset> sonList;

    @Describe("核验状态名称")
    private String sstrans;
    
    @Describe("入库状态")
    @Virtual
    private Integer istoragestate;

	@Override
	public boolean __isTrueDel() {
		return false;
	}

	@Override
	public String __getTableName() {
		return "am_vrf_plan_dt_fiasset";
	}
}

