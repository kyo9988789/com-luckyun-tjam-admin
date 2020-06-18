package com.luckyun.tjam.prophaseMag.acceptance.mapper;

import com.luckyun.core.data.BaseMapper;
import com.luckyun.tjam.prophaseMag.acceptance.model.InfTransferState;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by whs on 2019/12/31.
 */
@Repository
public interface InfTransferStateMapper extends BaseMapper<InfTransferState>{

    /**
     * 查询同意或拒绝的实物资产(可能需修改)
     *
     * */
    List<InfTransferState> findAll();
}
