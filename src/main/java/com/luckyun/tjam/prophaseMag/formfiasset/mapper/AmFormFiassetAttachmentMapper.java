package com.luckyun.tjam.prophaseMag.formfiasset.mapper;

import com.luckyun.core.data.BaseMapper;
import com.luckyun.tjam.prophaseMag.formfiasset.entity.AmFormFiassetAttachment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2020/4/8.
 */
@Repository
public interface AmFormFiassetAttachmentMapper extends BaseMapper<AmFormFiassetAttachment>{

    List<AmFormFiassetAttachment> findAllByIlinkno(@Param("ilinkno")Long ilinkno);
}
