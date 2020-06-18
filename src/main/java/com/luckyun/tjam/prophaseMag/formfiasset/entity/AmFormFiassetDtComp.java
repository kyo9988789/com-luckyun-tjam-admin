package com.luckyun.tjam.prophaseMag.formfiasset.entity;

import com.luckyun.annotation.Describe;
import com.luckyun.annotation.LogMainTableBasicAttr;
import com.luckyun.model.data.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public class AmFormFiassetDtComp extends BaseEntity{

  @Describe("主键")
  private Long indocno;
  @Describe("外键")
  @LogMainTableBasicAttr
  private Long ilinkno;
  @Describe("资产编码")
  private String sfcode;
  @Describe("所属线路")
  private Integer ilineid;
  @Describe("设备设施分类编码")
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
  @Describe("使用寿命")
  private String sestimatedlife;
  @Describe("寿命计量单位")
  private String sdesignunit;
  @Describe("单价")
  private Double iprice;
  @Describe("数量")
  private Integer iqty;
  @Describe("总价")
  private Double ieqvalence;
  @Describe("父级资产编码")
  private String sparentno;
  @Describe("数据来源(0:L1,1:P6)")
  private Integer isource;
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

  @Override
  public boolean __isTrueDel() {
    return true;
  }
}
