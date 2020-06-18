package com.luckyun.tjam.mtAssetMag.amTranPlan.param;

import com.luckyun.annotation.Describe;
import com.luckyun.tjam.mtAssetMag.amTranPlan.model.AmTransferPlan;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
public class AmTransferPlanParam extends AmTransferPlan {

    @Describe("删除操作的数据集")
    private List<AmTransferPlan> delList;

    @Describe("主键集合，以”,“分开")
    private String indocnos;

    @Describe("主键集合，以”,“分开")
    private List<Long> indocnoList;


    @Describe("下发开始时间")
    private Date dreleasestart;

    public void setDreleasestart(String  sFrom) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if(sFrom != null && sFrom.length() == 13) {
            try {
                dreleasestart = sdf.parse(sdf.format(Long.parseLong(sFrom)));
            } catch (Exception e) {
                dreleasestart = new Date();
            }
        }
    }

    @Describe("下发结束日期")
    private Date dreleaseend;
    public void setDreleaseend(String sTo) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if(sTo != null && sTo.length() == 13) {
            try {
                dreleaseend = sdf.parse(sdf.format(Long.parseLong(sTo)));
            } catch (Exception e) {
                dreleaseend = new Date();
            }
        }
    }

}
