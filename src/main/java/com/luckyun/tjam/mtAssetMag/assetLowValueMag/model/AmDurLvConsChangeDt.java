package com.luckyun.tjam.mtAssetMag.assetLowValueMag.model;

import com.luckyun.annotation.Describe;
import com.luckyun.annotation.LogMainTableBasicAttr;
import com.luckyun.annotation.Virtual;
import com.luckyun.model.data.DocEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

//资产中期管理-耐用低值易耗品变更清单列表
@Data
@EqualsAndHashCode(callSuper = false)
public class AmDurLvConsChangeDt extends DocEntity {

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

    @Describe("管理部门")
    private Long imanagedeptid;
    @Virtual
    private String smanagedeptnm;

    @Describe("位置区域")
    private String sinsarea;

    @Describe("位置建筑")
    private String sinspos;

    @Describe("品牌名称")
    private String sbrandnm;

    @Describe("规格型号")
    private String sspec;

    @Describe("使用人")
    private Long iuserid;
    @Virtual
    private String susernm;

    @Describe("位置区域")
    private String sposarea;

    @Describe("位置建筑")
    private String sposbuild;

    @Describe("位置房间")
    private String sposroom;

    @Describe("新责任人")
    private Long idutyidNew;
    @Virtual
    private String sdutynmNew;

    @Describe("新使用人")
    private Long iuseridNew;
    @Virtual
    private String susernmNew;

    @Describe("新管理部门")
    private Long imanagedeptidNew;
    @Virtual
    private String smanagedeptnmNew;

    @Describe("新位置建筑")
    private String sposbuildNew;

    @Describe("新位置房间")
    private String sposroomNew;

    @Describe("创建部门")
    private Long ideptid;

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

    @Describe("新存放位置")
    private String sinsposNew;

    @Describe("新存放区域")
    private String sinsareaNew;

    @Override
    public boolean __isTrueDel() {
        return true;
    }

    @Override
    public String __getTableName() {
        return "am_dur_lv_cons_change_dt";
    }
}
