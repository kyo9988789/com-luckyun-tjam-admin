package com.luckyun.tjam.prophaseMag.verification.bpm;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by chengrui on 2019/8/28.
 */
@Data
public class BpmTaskParam implements Serializable {
    /** 申请单号*/
    private String sapplyno;
    /** 申请时间*/
    private Date dapplytime;

    /** 代办名称 */
    private String sname;
    /** */
    private String basekey;

    private String modelkey;

    private String buskey;

    public BpmTaskParam() {
    }

    public BpmTaskParam(String sname, String basekey, String modelkey, String buskey) {
        this.sname = sname;
        this.basekey = basekey;
        this.modelkey = modelkey;
        this.buskey = buskey;
    }
}