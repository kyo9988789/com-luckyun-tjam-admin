package com.luckyun.tjam.mtAssetMag.amDurableLowValue.model;


import com.luckyun.annotation.Describe;
import com.luckyun.model.data.DocEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class AmDurLvConsumableAtt extends DocEntity {

    @Describe("主键")
    private Long indocno;

    @Describe("外键")
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

    @Describe("附件oss路径")
    private String nspath;

    @Override
    public boolean __isTrueDel() {
        return true;
    }

    @Override
    public String __getTableName() {
        return "am_dur_lv_consumable_att";
    }
}
