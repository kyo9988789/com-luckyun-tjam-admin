package com.luckyun.tjam.mtAssetMag.asledag.model;


import com.luckyun.annotation.Describe;
import com.luckyun.annotation.LogMainTableBasicAttr;
import com.luckyun.annotation.Virtual;
import com.luckyun.model.data.DocEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

//管理信息表
@Data
@EqualsAndHashCode(callSuper = false)
public class AmBasicManagementInf extends DocEntity {

    @Describe("主键")
    private Long indocno;

    @Describe("外键")
    @LogMainTableBasicAttr
    private Long ilinkno;

    @Describe("专业")
    private String smajornm;

    @Describe("管理部门")
    private Long imanagedeptid;

    @Describe("责任人")
    private Long idutyid;

    @Describe("核算部门")
    private Long iaccountdeptid;

    @Describe("使用人")
    private Long iuserid;

    @Describe("使用部门")
    private Long iuserdeptid;

    /*@Describe("创建日期")
    private Date dregt;*/

    @Describe("使用状态:0是正常，1是损坏，2是报废")
    private Integer iusestate;

    @Describe("维修单位")
    private String srepairunitnm;

    @Describe("维修部门")
    private String srepairdeptnm;

    @Describe("删除（0 未删 1 虚拟删除）")
    private Integer idel;

    @Describe("创建人ID")
    private Long sregid;

    @Describe("创建时间")
    private Date dregt;

    @Describe("状态（0无效1有效）")
    private Integer istate;

    @Virtual
    private String sregnm;

    @Describe("修改人id")
    private Long smodid;

    @Virtual
    private String smodnm;

    @Describe("修改时间")
    private Date dmodt;

    //("使用状态:0是正常，1是损坏，2是报废")
    @Virtual
    private String susestatenm;

    //@Describe("管理部门")
    @Virtual
    private String smanagedeptnm;

    //@Describe("责任人")
    @Virtual
    private String sdutynm;

    //@Describe("核算部门")
    @Virtual
    private String saccountdeptnm;

    //@Describe("使用人")
    @Virtual
    private String susernm;

    //@Describe("使用部门")
    @Virtual
    private String suserdeptnm;


    @Override
    public boolean __isTrueDel() {
        return false;
    }

    @Override
    public String __getTableName() {
        return "am_basic_management_inf";
    }
}
