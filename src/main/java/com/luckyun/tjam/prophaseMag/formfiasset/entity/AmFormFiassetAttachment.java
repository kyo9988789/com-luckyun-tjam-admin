package com.luckyun.tjam.prophaseMag.formfiasset.entity;

import com.luckyun.annotation.Describe;
import com.luckyun.annotation.LogMainTableBasicAttr;
import com.luckyun.annotation.Virtual;
import com.luckyun.model.data.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class AmFormFiassetAttachment extends BaseEntity {

  @Describe("主键")
  private Long indocno;
  @Describe("工器具表主键")
  @LogMainTableBasicAttr
  private Long ilinkno;
  @Describe("附件名称")
  private String sname;
  @Describe("附件路径")
  private String spath;
  @Describe("附件类型")
  private String stype;
  @Describe("附件大小")
  private Integer isize;
  @Describe("附件描述")
  private String sdescribe;
  @Describe("创建人")
  private Long sregid;
  @Describe("创建时间")
  private Date dregt;

  @Virtual
  private String nspath;
  @Virtual
  private String susernm;

  @Override
  public boolean __isTrueDel() {
    return true;
  }
}
