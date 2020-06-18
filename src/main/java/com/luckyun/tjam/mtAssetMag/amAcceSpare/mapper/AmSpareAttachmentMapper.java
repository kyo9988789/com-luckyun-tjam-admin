package com.luckyun.tjam.mtAssetMag.amAcceSpare.mapper;

import com.luckyun.core.data.BaseMapper;
import com.luckyun.tjam.mtAssetMag.amAcceSpare.model.AmSpareAttachment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AmSpareAttachmentMapper extends BaseMapper<AmSpareAttachment> {
    /**
     *  通过外键查询所有明细
     * @param ibillid
     * @return
     */

    List<AmSpareAttachment> findAllByIlinkno(@Param("ilinkno") Long ilinkno);
}
