
package com.luckyun.tjam.prophaseMag.maintenance.model;

import java.util.Date;

import com.luckyun.annotation.Describe;
import com.luckyun.annotation.Virtual;
import com.luckyun.model.data.DocEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;


/*
 * 
 * EAM和P6备品备件业务台账信息
 * 
 * @action
 * @since JDK 1.8
 * @author zrw
 * @date: 2019年12月3日 16:09:45
 */


@Data
@EqualsAndHashCode(callSuper = false)
public class AmAcceptanceSpare extends DocEntity{


    @Describe("主键")
    private Long indocno;

    @Describe("备品备件编号/存货编号")
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

    @Describe("专业")
    private String smajornm;

    @Describe("所属资产")
    private String sassetno;

    @Describe("金额单位")
    private String scurrencyunit;

    @Describe("外部系统主键")
    private String sexternalid;

    @Describe("核验状态")
    private Integer istrans;

    @Describe("接口数据处理时间")
    private Date dinfaccept;

    @Describe("线路")
    private Integer ilineid;

    @Describe("权属单位")
    private String sownerunit;

    @Describe("运营单位(默认北京地铁)")
    private String soperatunitnm;

    @Describe("核验描述")
    private String sdescribe;

    @Describe("数据来源ID(0.L1;1.P6)")
    private Integer isource;

    @Describe("合同描述")
    private String scontdesc;

    @Describe("合同编号")
    private String scontno;

    @Describe("结算审计单价（元）")
    private Double ijscost;

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

    @Describe("已打标签卡")
    private Integer iscard;

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

    @Describe("是否已生成核验计划:0.未生成,1.已生成")
    private Integer idatastate;

    @Describe("消息主键")
    private Long imessageid;

    @Describe("金额")
    private Double imoney;

    @Describe("当前用户主键,用作责任人版过滤数据")
    @Virtual
    private Long icuruserid;

    @Describe("查看的当前模块是管理员版还是责任人版，0 = 责任人版，未传表示是管理员版不用过滤数据，展示所有即可")
    @Virtual
    private Integer iiscapadmin;

    @Describe("核验状态名称")
    private String sstrans;

    @Describe("所属线路")
    private String slinenm;

    @Describe("管理部门名称")
    private String smanagedeptnm;

    @Describe("责任人名称")
    private String sdutynm;

    @Describe("分配状态名称")
    private String sdiststatusnm;

    @Describe("分配人ID")
    private String sdistrusernm;

    @Describe("移交验收人名称")
    private String scheckusernm;

    @Describe("相关属性:已打标签卡名称")
    private String sscard;

    @Describe("相关属性:数据来源名称")
    private String ssource;

    @Describe("相关属性:数据接入名称")
    private String saccess;

    @Describe("附件oss路径")
    private String nspath;

    @Describe("是否外部系统")
    private String isOutSys;

    @Describe("状态变更人名称")
    private String schangeusernm;

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
    
	@Override
	public String __getTableName() {
		return "am_acceptance_spare";
	}
	
	@Override
	public boolean __isTrueDel() {
		return false;
	}

}

