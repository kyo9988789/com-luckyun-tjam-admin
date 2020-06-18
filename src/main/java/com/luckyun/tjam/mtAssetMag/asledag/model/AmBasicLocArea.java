package com.luckyun.tjam.mtAssetMag.asledag.model;


import com.luckyun.annotation.Describe;
import com.luckyun.annotation.LogMainTableBasicAttr;
import com.luckyun.model.data.DocEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
//位置区域表
@Data
@EqualsAndHashCode(callSuper = false)
public class AmBasicLocArea extends DocEntity {

    @Describe("主键")
    private Long indocno;

    @Describe("外键")
    @LogMainTableBasicAttr
    private Long ilinkno;

    @Describe("安装位置")
    private String sinspos;

    @Describe("安装区域")
    private String sinsarea;

    @Describe("具体位置")
    private String sspecificpos;

    @Describe("具体位置描述")
    private String specificdescribe;

    @Describe("终止里程")
    private String slineendpost;

    @Describe("起始里程")
    private String slinestartpost;

    @Describe("位置区域")
    private String sposarea;

    @Describe("位置建筑")
    private String sposbuild;

    @Describe("位置房间")
    private String sposroom;

    @Describe("所属区县")
    private String sbldistrict;

    @Describe("起点里程标")
    private String startpoint;

    @Describe("终点里程标")
    private String sendpoint;

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



    @Override
    public boolean __isTrueDel() {
        return false;
    }

    @Override
    public String __getTableName() {
        return "am_basic_loc_area";
    }
}
