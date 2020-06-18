
package com.luckyun.tjam.prophaseMag.verification.model;

import java.util.Date;
import java.util.List;

import com.luckyun.annotation.Describe;
import com.luckyun.annotation.LogMainTableBasicAttr;
import com.luckyun.model.data.DocEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;


/*
 * 
  *  备品备件核验计划明细表
 * 
 * @action
 * @since JDK 1.8
 * @author zrw
 * @date: 2019年12月10日 14:39:39
 */


@Data
@EqualsAndHashCode(callSuper = false)
public class AmVrfPlanDtSpare extends DocEntity{

    @Describe("主键")
    private Long indocno;

    @Describe("外键")
    @LogMainTableBasicAttr
    private Long ilinkno;

    @Describe("备品备件编号")
    private String sspareno;

    @Describe("存货编号名称")
    private String ssparenm;

    @Describe("移交编号")
    private String stransferno;

    @Describe("核验状态")
    private Integer istrans;

    @Describe("未接收原因")
    private String sunreceivedcause;

    @Describe("厂家/供应商")
    private String ssuppliernm;

    @Describe("单价")
    private Double iprice;

    @Describe("数量")
    private Integer iqty;

    @Describe("金额")
    private Double imoney;

    @Describe("金额单位")
    private String scurrencyunit;

    @Describe("专业")
    private String smajornm;

    @Describe("所属资产")
    private String sassetno;

    @Describe("外部系统主键")
    private String sexternalid;

    @Describe("线路")
    private Integer ilineid;

    @Describe("权属单位")
    private String sownerunit;

    @Describe("运营单位")
    private String soperatunitnm;

    @Describe("合同描述")
    private String scontdesc;

    @Describe("合同编号")
    private String scontno;

    @Describe("结算审计单价（元）")
    private Double ijscost;

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

    @Describe("规格型号")
    private String sspec;

    @Describe("备品备件台账表主键")
    private Long iamaccspareid;

    @Describe("资产组成部分")
    List<AmVrfPlanDtSpare> sonList;

    @Describe("核验状态名称")
    private String sstrans;

	@Override
	public boolean __isTrueDel() {
		return false;
	}
	
	public String __getTableName() {
		return "am_vrf_plan_dt_spare";
	}
}

