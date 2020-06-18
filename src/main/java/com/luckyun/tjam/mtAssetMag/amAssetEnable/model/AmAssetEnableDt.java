package com.luckyun.tjam.mtAssetMag.amAssetEnable.model;

import com.luckyun.annotation.Describe;
import com.luckyun.annotation.LogMainTableBasicAttr;
import com.luckyun.annotation.Virtual;
import com.luckyun.model.data.DocEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

//
@Data
@EqualsAndHashCode(callSuper = false)
public class AmAssetEnableDt extends DocEntity {

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

    @Describe("开始使用日期")
    private Date dstart;

    @Describe("已使用年限")
    private String servicelife;

    @Describe("资产状态:0.正常(默认值);1.损坏;2.报废;3.闲置。")
    private Integer iamstate;

    @Describe("位置区域")
    private String sposarea;

    @Describe("位置建筑")
    private String sposbuild;

    @Describe("位置房间")
    private String sposroom;

    @Describe("开始启用时间")
    private Date dstartfenabletime;

    @Describe("启用原因")
    private String senablereason;

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

    @Describe("闲置时长")
    private String sfreetime;


    @Override
    public boolean __isTrueDel() {
        return false;
    }

    @Override
    public String __getTableName() {
        return "am_asset_enable_dt";
    }
}
