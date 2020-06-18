package com.luckyun.tjam.mtAssetMag.asledag.model;


import com.luckyun.annotation.Describe;
import com.luckyun.annotation.Virtual;
import com.luckyun.model.data.DocEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

//资产卡片信息表
@Data
@EqualsAndHashCode(callSuper = false)
public class AmBasicAnnex extends DocEntity {

    @Describe("主键")
    private Long indocno;

    @Describe("外键")
    private Long ilinkno;

    @Describe("类型，0是图片，1是附件")
    private Integer iclassid;

    @Describe("附件名称")
    private String sname;

    @Describe("路径")
    private String spath;

    @Describe("大小")
    private String isize;

    @Describe("图片类似")
    private String stype;

    @Describe("图片描述")
    private String sdescribe;

    @Describe("上传人")
    private String suploadname;

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

    @Describe("附件oss路径")
    private String nspath;

    //@Describe("新增图片集合")
    @Virtual
    private List<AmBasicAnnex> addImagesList;

    //@Describe("新增附件集合")
    @Virtual
    private List<AmBasicAnnex> addFujiList;

    @Override
    public boolean __isTrueDel() {
        return true;
    }

    @Override
    public String __getTableName() {
        return "am_basic_annex";
    }
}
