package com.luckyun.tjam.mtAssetMag.assetDailyMag.model;

import com.luckyun.annotation.Describe;
import com.luckyun.annotation.LogMainTableBasicAttr;
import com.luckyun.annotation.Virtual;
import com.luckyun.model.data.DocEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

//资产中期管理-资产变更清单列表
@Data
@EqualsAndHashCode(callSuper = false)
public class AmAssetChangeDt extends DocEntity {

    @Describe("资产卡片表主键")
    private Long isourceid;

    @Describe("主键")
    private Long indocno;

    @Describe("外键")
    @LogMainTableBasicAttr
    private Long ilinkno;

    @Describe("资产名称")
    private String sfname;

    @Describe("资产编号")
    private String sfcode;

    @Describe("所属线路")
    private Integer ilineid;
    @Virtual
    private String slinenm;

    @Describe("资产分类编码")
    private String seqclassno;

    @Describe("专业")
    private String smajornm;

    @Describe("责任人")
    private Long idutyid;
    @Virtual
    private String sdutynm;

    @Describe("规格型号")
    private String sspec;

    @Describe("管理部门")
    private Long imanagedeptid;
    @Virtual
    private String smanagedeptnm;

    @Describe("使用人")
    private Long iuserid;
    @Virtual
    private String susernm;

    @Describe("新专业")
    private String smajornmNew;

    @Describe("新责任人")
    private Long idutyidNew;
    @Virtual
    private String sdutynmNew;

    @Describe("新管理部门")
    private Long imanagedeptidNew;
    @Virtual
    private String smanagedeptnmNew;

    @Describe("新规格型号")
    private String sspecNew;

    @Describe("新使用人")
    private Long iuseridNew;
    @Virtual
    private String susernmNew;

    @Describe("创建部门")
    private Long ideptid;

    @Describe("位置区域")
    private String sposarea;

    @Describe("位置建筑")
    private String sposbuild;

    @Describe("位置房间")
    private String sposroom;

    @Describe("新位置区域")
    private String sposareaNew;

    @Describe("新位置建筑")
    private String sposbuildNew;

    @Describe("新位置房间")
    private String sposroomNew;

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

    @Describe("名称")
    private String sname;



    @Override
    public boolean __isTrueDel() {
        return true;
    }

    @Override
    public String __getTableName() {
        return "am_asset_change_dt";
    }
}
