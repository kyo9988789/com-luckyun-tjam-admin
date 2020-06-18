package com.luckyun.tjam.mtAssetMag.amTranPlan.service;

import com.luckyun.core.annotation.TransactionalException;
import com.luckyun.core.data.BaseMapper;
import com.luckyun.core.data.BaseService;
import com.luckyun.tjam.mtAssetMag.amTranPlan.mapper.AmTransferPlanDtCompMapper;
import com.luckyun.tjam.mtAssetMag.amTranPlan.mapper.AmTransferPlanDtMapper;
import com.luckyun.tjam.mtAssetMag.amTranPlan.model.AmTransferPlanDt;
import com.luckyun.tjam.mtAssetMag.amTranPlan.model.AmTransferPlanDtComp;
import com.luckyun.tjam.mtAssetMag.amTranPlan.param.AmTransferPlanDtParam;
import com.luckyun.tjam.mtAssetMag.asledag.mapper.AmBasicComponentInfMapper;
import com.luckyun.tjam.mtAssetMag.asledag.mapper.AmBasicInfMapper;
import com.luckyun.tjam.mtAssetMag.asledag.model.AmBasicComponentInf;
import com.luckyun.tjam.mtAssetMag.asledag.model.AmBasicInf;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;


@Service
public class AmTransferPlanDtService extends BaseService<AmTransferPlanDt> {

    @Autowired
    private AmTransferPlanDtMapper amTransferPlanDtMapper;

    @Autowired
    private AmBasicInfMapper amBasicInfMapper;

    @Autowired
    private AmBasicComponentInfMapper amBasicComponentInfMapper;

    @Autowired
    private AmTransferPlanDtCompMapper amTransferPlanDtCompMapper;


    @Override
    public BaseMapper<AmTransferPlanDt> getMapper() {
        return amTransferPlanDtMapper;
    }

    /***
     *
     * 删除
     *
     */
    @TransactionalException
  public void delOpr(AmTransferPlanDtParam condition){
        List<AmTransferPlanDt> delList = condition.getDelList();
        if (!CollectionUtils.isEmpty(delList)){
            delList.forEach(e->{
                    AmBasicInf one = amBasicInfMapper.findOne(e.getIsourceid());
                    one.setIquotestate(0);
                    amBasicInfMapper.update(one);
                List<AmTransferPlanDtComp> sonByIparent = amTransferPlanDtCompMapper.findSonByIparent(e.getSfcode());
                if (!CollectionUtils.isEmpty(sonByIparent)){
                    sonByIparent.forEach(e1->{
                        amTransferPlanDtCompMapper.delete(e1);
                    });
                }
                amTransferPlanDtMapper.delete(e);
            });
        }
    }

    /**
     *  查询明细
     * @param condition
     * @return
     */
    public AmTransferPlanDt findOne(Long indocno){
        AmTransferPlanDt one = amTransferPlanDtMapper.findOne(indocno);
        if (!StringUtils.isEmpty(one)){
            List<AmTransferPlanDtComp> amTransferPlanDtCompList = amTransferPlanDtCompMapper.findSonByIparent(one.getSfcode());
            one.setAmTransferPlanDtCompList(amTransferPlanDtCompList);
        }
        return one;
    }

    /**
     * 添加
     *
     */
    @TransactionalException
    public void add(AmTransferPlanDtParam entity){
        List<AmTransferPlanDt> addList = entity.getAddList();
        if(!CollectionUtils.isEmpty(addList)){
            addList.forEach(e->{
                //AmTransferPlanDt one1 = amTransferPlanDtMapper.findOne1(e.getSfcode());
              // if (!StringUtils.isEmpty(one1)){
                    AmBasicInf one = amBasicInfMapper.findOne(e.getIsourceid());
                    Long ilinkno = e.getIlinkno();
                    BeanUtils.copyProperties(one,e);
                    e.setIndocno(null);
                    e.setIlinkno(ilinkno);
                    amTransferPlanDtMapper.insert(e);
                    one.setIquotestate(1);
                    amBasicInfMapper.update(one);
                    List<AmBasicComponentInf> componentInfs = amBasicComponentInfMapper.findAllByIbillid(one.getIndocno());
                    if(null !=componentInfs && !componentInfs.isEmpty()){
                        for (AmBasicComponentInf a:componentInfs){
                            AmTransferPlanDtComp am = new AmTransferPlanDtComp();
                            BeanUtils.copyProperties(a,am);
                            //am.setIlinkno(e.getIndocno());
                            am.setIndocno(null);
                            amTransferPlanDtCompMapper.insert(am);
                        }
                    }
                //}

            });
        }
    }

    /**
     *修改
     **/
    @TransactionalException
    public void updateBasic(AmTransferPlanDt entity) {
        amTransferPlanDtMapper.update(entity);

    }

}
