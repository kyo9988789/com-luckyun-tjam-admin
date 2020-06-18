package com.luckyun.tjam.prophaseMag.formfiasset.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.luckyun.core.data.BaseMapper;
import com.luckyun.core.data.BaseService;
import com.luckyun.tjam.prophaseMag.formfiasset.entity.AmFormFiassetDt;
import com.luckyun.tjam.prophaseMag.formfiasset.entity.AmFormFiassetDtComp;
import com.luckyun.tjam.prophaseMag.formfiasset.mapper.AmFormFiassetDtCompMapper;
import com.luckyun.tjam.prophaseMag.formfiasset.mapper.AmFormFiassetDtMapper;
import com.luckyun.tjam.prophaseMag.formfiasset.param.AmFormFiassetDtParam;
import com.luckyun.tjam.prophaseMag.maintenance.mapper.AmAcceptanceFiassetMapper;
import com.luckyun.tjam.prophaseMag.maintenance.model.AmAcceptanceFiasset;

/**
 * Created by whs on 2020/5/25.
 */
@Service
public class AmFormFiassetDtService extends BaseService<AmFormFiassetDt>{

    @Autowired
    private AmFormFiassetDtMapper amFormFiassetDtMapper;

    @Autowired
    private AmAcceptanceFiassetMapper amAcceptanceFiassetMapper;

    @Autowired
    private AmFormFiassetDtCompMapper amFormFiassetDtCompMapper;

    @Override
    public BaseMapper<AmFormFiassetDt> getMapper() {
        return this.amFormFiassetDtMapper;
    }

    @Transactional
    public void handle(AmFormFiassetDtParam entity){
        handleEntity(entity);
    }

    private void handleEntity(AmFormFiassetDtParam entity) {
        handleAddEntity(entity);
        handleDelEntity(entity);
    }


    private void handleDelEntity(AmFormFiassetDtParam entity) {
        if (!CollectionUtils.isEmpty(entity.getDelList())){
            entity.getDelList().forEach(e->{
                List<AmFormFiassetDtComp> amList = amFormFiassetDtCompMapper.findAllByIlinkno(e.getIndocno());
                amList.forEach(a->{
                    amFormFiassetDtCompMapper.delete(a);
                });
                amFormFiassetDtMapper.delete(e);
            });
        }
    }

    private void handleAddEntity(AmFormFiassetDtParam entity){
        if (!CollectionUtils.isEmpty(entity.getAddList())){
            entity.getAddList().forEach(e->{
                AmAcceptanceFiasset one = amAcceptanceFiassetMapper.findOne(e.getIndocno());
                Long ilinkno = e.getIlinkno();
                BeanUtils.copyProperties(one,e);
                e.setIndocno(null);
                e.setIlinkno(ilinkno);
                amFormFiassetDtMapper.insert(e);
                if (one.getSfcode() != null){
                    List<AmAcceptanceFiasset> sonByIparent = amAcceptanceFiassetMapper.findSonByIparent(one.getSfcode());
                    if (!CollectionUtils.isEmpty(sonByIparent)){
                        sonByIparent.forEach(a->{
                            AmFormFiassetDtComp am = new AmFormFiassetDtComp();
                            BeanUtils.copyProperties(a,am);
                            am.setIlinkno(e.getIndocno());
                            am.setIndocno(null);
                            amFormFiassetDtCompMapper.insert(am);
                        });
                    }
                }
            });
        }
    }

    public List<AmFormFiassetDt> findByIlinkno(Long ilinkno){
        List<AmFormFiassetDt> all = amFormFiassetDtMapper.findAllByIlinkno(ilinkno);
        all.forEach(e->{
            e.setIstoragestate(1);
        });
        return all;
    }
}
