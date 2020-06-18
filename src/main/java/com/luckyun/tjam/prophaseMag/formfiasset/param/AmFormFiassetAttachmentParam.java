package com.luckyun.tjam.prophaseMag.formfiasset.param;

import com.luckyun.tjam.prophaseMag.formfiasset.entity.AmFormFiassetAttachment;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Created by whs on 2020/5/25.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AmFormFiassetAttachmentParam extends AmFormFiassetAttachment{

    private List<AmFormFiassetAttachment> delList;

    private List<AmFormFiassetAttachment> addList;

    private List<AmFormFiassetAttachment> updList;
}
