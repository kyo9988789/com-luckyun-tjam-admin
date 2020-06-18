package com.luckyun.tjam.mtAssetMag.amToolsag.mapper;

import com.luckyun.core.data.BaseMapper;
import com.luckyun.tjam.mtAssetMag.amToolsag.model.AmToolsAttachment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AmToolsAttachmentMapper extends BaseMapper<AmToolsAttachment> {
    /**
     *  通过外键查询所有明细
     * @param ibillid
     * @return
     */

    List<AmToolsAttachment> findAllByIlinkno(@Param("ilinkno") Long ilinkno);
}
