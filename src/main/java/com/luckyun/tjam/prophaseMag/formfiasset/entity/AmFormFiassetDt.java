package com.luckyun.tjam.prophaseMag.formfiasset.entity;

import com.luckyun.annotation.Describe;
import com.luckyun.annotation.LogMainTableBasicAttr;
import com.luckyun.annotation.Virtual;
import com.luckyun.model.data.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class AmFormFiassetDt extends BaseEntity{

  @Describe("主键")
  private Long indocno;
  @Describe("外键")
  @LogMainTableBasicAttr
  private Long ilinkno;
  @Describe("设备设施编码")
  private String sfcode;
  @Describe("设备设施别名")
  private String sfnameas;
  @Describe("所属线路")
  private Integer ilineid;
  @Describe("设备设施名称")
  private String sfname;
  @Describe("线路期段")
  private String slinestage;
  @Describe("设备设施分类编号")
  private String seqclassno;
  @Describe("所属专业")
  private String smajornm;
  @Describe("接口数据接收时间")
  private Date dinfaccept;
  @Describe("接收单位")
  private String stranssite;
  @Describe("未接收原因")
  private String sunreceivedcause;
  @Describe("实际品牌")
  private String sbrandAct;
  @Describe("生产厂商(实际制造商)")
  private String sproductnmAct;
  @Describe("供货厂商")
  private String ssuppliernm;
  @Describe("实际规格型号")
  private String sspecAct;
  @Describe("计量单位")
  private String sunit;
  @Describe("运营类别1:直接运营资产,2:间接运营资产）")
  private String soperatetype;
  @Describe("运营单位")
  private String soperatunitnm;
  @Describe("权属单位")
  private String sownerunit;
  @Describe("所属区县")
  private String sbldistrict;
  @Describe("实际安装位置编号")
  private String sinslocationnoAct;
  @Describe("具体位置")
  private String sconlocation;
  @Describe("起始里程")
  private String slinestartpost;
  @Describe("终止里程")
  private String slineendpost;
  @Describe("使用年限")
  private Double iuseyear;
  @Describe("使用寿命")
  private String sestimatedlife;
  @Describe("寿命计量单位")
  private String sdesignunit;
  @Describe("开始使用日期")
  private Date dstartuse;
  @Describe("质保期限(月)")
  private Integer iwarranty;
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
  @Describe("分摊方式")
  private String sallocationmethod;
  @Describe("合同签订日期")
  private Date dcontsign;
  @Describe("设备设施单价")
  private Double iprice;
  @Describe("实际数量")
  private String iqty_Act;
  @Describe("设备设施合价")
  private Double ieqvalence;
  @Describe("币种")
  private String sassetcurrency;
  @Describe("币种单位")
  private String scurrencyunit;
  @Describe("使用单位")
  private String suseunit;
  @Describe("备注一")
  private String snoteOne;
  @Describe("备注二")
  private String snoteTwo;
  @Describe("备注三")
  private String snoteThree;
  @Describe("父级资产编号")
  private String sparentno;
  @Describe("外部系统主键")
  private String sexternalid;
  @Describe("使用状态")
  private String susestate;
  @Describe("保管使用人")
  private String susernm;
  @Describe("融资状态")
  private String sfinancstate;
  @Describe("二类分摊费用")
  private Double isharecost;
  @Describe("维修单位")
  private String srepairunitnm;
  @Describe("购置类型")
  private String spurchasetype;
  @Describe("固定资产状态")
  private String sfixedassetstatus;
  @Describe("旧资产编码")
  private String sassetnoOld;
  @Describe("新资产编码")
  private String sassetnoNew;
  @Describe("资产原值")
  private Double iassetvalue;
  @Describe("资产入网编组号")
  private String sassetItno;
  @Describe("核验状态")
  private String sstransnm;
  @Describe("一类分摊费用")
  private Double iothercost;
  @Describe("过渡还是永久状态")
  private String stranspermanent;
  @Describe("使用年限（月）")
  private Integer iusefullife;
  @Describe("管理部门ID")
  private Integer imanagedeptid;
  @Describe("专业ID")
  private Integer imajorid;
  @Describe("责任人ID")
  private Integer idutyid;
  @Describe("已打标签卡")
  private Integer iscard;
  @Describe("相关属性:数据来源ID(0.L1;1.P6)")
  private Integer isource;
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
  @Describe("移交条码")
  private String sbarcode;
  @Describe("是否抢险设备")
  private String sisemerg;
  @Describe("附件类型")
  private String satttype;
  @Describe("移交描述")
  private String sdescribe;
  @Describe("删除标记")
  private Integer idel;
  @Describe("状态标记")
  private Integer istate;
  @Describe("版本乐观锁")
  private Integer iversion;
  @Describe("创建人id")
  private Long sregid;
  @Describe("创建人名称")
  private String sregnm;
  @Describe("创建时间")
  private Date dregt;
  @Describe("修改人id")
  private Long smodid;
  @Describe("修改人名称")
  private String smodnm;
  @Describe("修改时间")
  private Date dmodt;
  @Virtual
  @Describe("是否入库字段")
  private Integer istoragestate;

  @Describe("资产卡片组成部分表")
  @Virtual
  private List<AmFormFiassetDtComp> amFormFiassetDtCompList;

  @Override
  public boolean __isTrueDel() {
    return true;
  }
}
