package com.luckyun.tjam.prophaseMag.formfiasset.entity;


import java.util.List;

import com.luckyun.annotation.Describe;
import com.luckyun.annotation.Virtual;
import com.luckyun.model.data.DocEntity;

import com.luckyun.tjam.prophaseMag.verification.bpm.BpmTaskParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AmFormFiasset extends DocEntity{

  @Describe("批次编号")
  private String sfcode;
  @Describe("批次名称")
  private String sfname;
  @Describe("审批状态(0.未下发;1.已下发)")
  private Integer iplanstate;
  @Describe("所属线路")
  private Integer ilineid;
  @Virtual
  private String slinenm;
  @Describe("增加方式")
  private Integer iaddtype;
  @Virtual
  private String saddtype;
  @Virtual
  @Describe("清单数量")
  private Integer iqty;
  @Describe("项目编号")
  private String sprono;
  @Describe("合同编号")
  private String scontno;
  @Describe("工程名称")
  private String sengineeringnm;
  @Describe("工程内容")
  private String sengineeringcon;
  @Describe("质量及评价")
  private String squalityevaluation;
  @Describe("验收结论")
  private String sacceptancecon;
  @Describe("部门")
  private Long ideptid;
  @Virtual
  private String sdeptnm;
  @Describe("形成固定资产附件")
  @Virtual
  private List<AmFormFiassetAttachment> amFormFiassetAttachmentList;
  @Describe("形成固定资产清单列表")
  @Virtual
  private List<AmFormFiassetDt> amFormFiassetDtList;

  @Describe("用户形成固定资产readOne初始化时所有组成部分")
  @Virtual
  private List<AmFormFiassetDtComp> amFormFiassetDtCompList;

  @Describe("审批流程状态")
  private Integer iapprovalstate;
  @Virtual
  private String sapprovalstate;

  @Describe("流程对象")
  @Virtual
  private BpmTaskParam bpmTaskParam;

  @Override
  public boolean __isTrueDel() {
    return false;
  }
}
