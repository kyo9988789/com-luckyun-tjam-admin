package com.luckyun.tjam.mtAssetMag.asledag.mapper;


import com.luckyun.core.data.BaseMapper;
import com.luckyun.tjam.mtAssetMag.asledag.model.AmBasicAnnex;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AmBasicAnnexMapper extends BaseMapper<AmBasicAnnex> {

    /**
     *  通过外键查询所有明细
     * @param ibillid
     * @return
     */

    List<AmBasicAnnex> findAllByIbillid(@Param("ilinkno") Long ilinkno);

    /**
     *  通过外键查询所有图片
     * @param ibillid
     * @return
     */
    List<AmBasicAnnex> findAllByIclassid(@Param("ilinkno") Long ilinkno);

    /**
     *  通过外键查询所有附件
     * @param ibillid
     * @return
     */
    List<AmBasicAnnex> findAllByIclassidfuji(@Param("ilinkno") Long ilinkno);
}
