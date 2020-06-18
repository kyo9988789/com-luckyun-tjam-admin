package com.luckyun.tjam.prophaseMag.formfiasset.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.luckyun.core.data.BaseMapper;
import com.luckyun.core.data.BaseService;
import com.luckyun.core.tool.OssPathHelperUtils;
import com.luckyun.tjam.prophaseMag.formfiasset.entity.AmFormFiassetAttachment;
import com.luckyun.tjam.prophaseMag.formfiasset.mapper.AmFormFiassetAttachmentMapper;
import com.luckyun.tjam.prophaseMag.formfiasset.param.AmFormFiassetAttachmentParam;

/**
 * Created by whs on 2020/5/25.
 */
@Service
public class AmFormFiassetAttachmentService extends BaseService<AmFormFiassetAttachment>{

    @Autowired
    private AmFormFiassetAttachmentMapper amFormFiassetAttachmentMapper;

    @Autowired
    OssPathHelperUtils ossPathHelperUtils;

    @Override
    public BaseMapper<AmFormFiassetAttachment> getMapper() {
        return amFormFiassetAttachmentMapper;
    }

    @Transactional
    public void handle(AmFormFiassetAttachmentParam entity){
        handleEntity(entity);
    }

    private void handleEntity(AmFormFiassetAttachmentParam entity) {
        handleAddEntity(entity);
        handleDelEntity(entity);
    }

    private void handleDelEntity(AmFormFiassetAttachmentParam entity) {
        if (!CollectionUtils.isEmpty(entity.getDelList())){
            entity.getDelList().forEach(e->{
                amFormFiassetAttachmentMapper.delete(e);
            });
        }
    }

    private void handleAddEntity(AmFormFiassetAttachmentParam entity) {
        if (!CollectionUtils.isEmpty(entity.getAddList())){
            entity.getAddList().forEach(e->{
                amFormFiassetAttachmentMapper.insert(e);
            });
        }
    }

    public List<AmFormFiassetAttachment> findAllByIlinkno(Long ilinkno) {
        List<AmFormFiassetAttachment> all = amFormFiassetAttachmentMapper.findAllByIlinkno(ilinkno);
        all.forEach(e->{
            e.setNspath(ossPathHelperUtils.generateDownloadFileNoAuth(getAuthInfo().getSloginid(),"tjam",e.getSpath(),e.getSname()));
        });
        return all;
    }
}
