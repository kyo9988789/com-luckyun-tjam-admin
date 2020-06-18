package com.luckyun.tjam.prophaseMag.maintenance.siatask;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.luckyun.tjam.prophaseMag.maintenance.mapper.AmAcceptanceFiassetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luckyun.core.annotation.TransactionalException;
import com.luckyun.tjam.prophaseMag.acceptance.mapper.InfL1InventoryMapper;
import com.luckyun.tjam.prophaseMag.acceptance.mapper.InfL1PlanDetailedMapper;
import com.luckyun.tjam.prophaseMag.acceptance.mapper.InfP6InventoryMapper;
import com.luckyun.tjam.prophaseMag.acceptance.model.InfL1Inventory;
import com.luckyun.tjam.prophaseMag.acceptance.model.InfL1PlanDetailed;
import com.luckyun.tjam.prophaseMag.acceptance.model.InfP6Inventory;
import com.luckyun.tjam.prophaseMag.maintenance.model.AmAcceptanceFiasset;



/**
 * Created by whs on 2019/12/17.
 */

@Service
public class InfTaskService {

   @Autowired
   private InfL1InventoryMapper infL1InventoryMapper;

   @Autowired
   private InfL1PlanDetailedMapper infL1PlanDetailedMapper;

   @Autowired
   private AmAcceptanceFiassetMapper amAcceptanceFiassetMapper;

    @Autowired
    private InfP6InventoryMapper infP6InventoryMapper;

    @TransactionalException
    public void compareTo(){
        P6compareToEquip();;
        L1compareToEquip();
    }

    public void P6compareToEquip(){
        List<InfP6Inventory> infP6InventoryList = infP6InventoryMapper.findAllByIhascopied();
        infP6InventoryList.forEach(e->{
            updateP6Ihascopied(e);
            syncP6(e);
        });
        
        // 查询已插入过备品备件台账表的但是发生了数据变更的接口数据，这批数据需要通过定时任务同步更新备品备件台账表中对应业务数据
        List<InfP6Inventory> infP6InventoryList2 = this.infP6InventoryMapper.findAllByIhascopiedAndIupdateState();
        infP6InventoryList2.forEach(e1 -> {
        	AmAcceptanceFiasset info = this.amAcceptanceFiassetMapper.findByIsourceid(e1.getIndocno(),1);
        	if(null != info) {
        		// 置空"更新状态"
        		infP6InventoryMapper.updateIupdateState(e1.getIndocno());
        		syncUpdateP6(e1,info);
        	}
        });
    }

    public void syncUpdateP6(InfP6Inventory infP6Inventory,AmAcceptanceFiasset amAcceptanceFiasset) {
    	amAcceptanceFiasset.setIupdatestate(1);
        amAcceptanceFiasset.setSmajornm(infP6Inventory.getSmajornm());
        amAcceptanceFiasset.setSparentno(infP6Inventory.getSparentno());
        amAcceptanceFiasset.setSbrandAct(infP6Inventory.getSassetbrand());
        amAcceptanceFiasset.setSinslocationnoAct(infP6Inventory.getSinslocationno());
        amAcceptanceFiasset.setSlineendpost(infP6Inventory.getSlineendpost());
        amAcceptanceFiasset.setSlinestartpost(infP6Inventory.getSlinestartpost());
        amAcceptanceFiasset.setSproductnmAct(infP6Inventory.getSproductnm());
        amAcceptanceFiasset.setSspecAct(infP6Inventory.getSspec());
        amAcceptanceFiasset.setSexternalid(infP6Inventory.getSexternalid());
        amAcceptanceFiasset.setSfname(infP6Inventory.getSfname());
        amAcceptanceFiasset.setSnoteOne(infP6Inventory.getSnote0ne());
        amAcceptanceFiasset.setSnoteTwo(infP6Inventory.getSnoteTwo());
        amAcceptanceFiasset.setSnoteThree(infP6Inventory.getSnoteThree());
        amAcceptanceFiasset.setSeqclassno(infP6Inventory.getSeqclassno());
        amAcceptanceFiasset.setSlinestage(infP6Inventory.getSlinestage());
        amAcceptanceFiasset.setSconlocation(infP6Inventory.getSlocationdesc());
        amAcceptanceFiasset.setSsuppliernm(infP6Inventory.getSsuppliernm());
        amAcceptanceFiasset.setSunit(infP6Inventory.getSunit());
        amAcceptanceFiasset.setSfnameas(infP6Inventory.getSfnameas());
        
        if(null != infP6Inventory.getIqty()) {
        	amAcceptanceFiasset.setIqtyAct(String.valueOf(infP6Inventory.getIqty()));
        }

        amAcceptanceFiasset.setIprice(infP6Inventory.getIprice());
        amAcceptanceFiasset.setIusefullife(infP6Inventory.getIusefullife());
        amAcceptanceFiasset.setSdesignunit(infP6Inventory.getSdesignunit());
        amAcceptanceFiasset.setSestimatedlife(infP6Inventory.getSestimatedlife());
        amAcceptanceFiasset.setDstartuse(infP6Inventory.getDstartuse());
        amAcceptanceFiasset.setIwarranty(intCast(infP6Inventory.getIwarranty()));
        amAcceptanceFiasset.setDwarrstart(infP6Inventory.getDwarrstart());
        amAcceptanceFiasset.setIdel(intCast(infP6Inventory.getSlmsinvaid()) != null ? intCast(infP6Inventory.getSlmsinvaid()):0);
        amAcceptanceFiasset.setSusestate(infP6Inventory.getSusestate());
        amAcceptanceFiasset.setSfinancstate(infP6Inventory.getSfinancstate());
        amAcceptanceFiasset.setIothercost(infP6Inventory.getIothercost());
        amAcceptanceFiasset.setStranspermanent(infP6Inventory.getStranspermanent());
        amAcceptanceFiasset.setSassetcurrency(infP6Inventory.getSassetcurrency());
        amAcceptanceFiasset.setScurrencyunit(infP6Inventory.getScurrencyunit());
        amAcceptanceFiasset.setSuseunit(infP6Inventory.getSuseunit());
        amAcceptanceFiasset.setIeqvalence(infP6Inventory.getIeqvalence());
        amAcceptanceFiasset.setSconttype(infP6Inventory.getSconttype());
        amAcceptanceFiasset.setScontsumflag(infP6Inventory.getScontsumflag());
        amAcceptanceFiasset.setScontno(infP6Inventory.getScontno());
        amAcceptanceFiasset.setScontfirstparty(infP6Inventory.getScontfirstparty());
        amAcceptanceFiasset.setScontpartb(infP6Inventory.getScontpartb());
        amAcceptanceFiasset.setSconttotalmoney(infP6Inventory.getSconttotalmoney());
        amAcceptanceFiasset.setDcontsign(infP6Inventory.getDcontsign());
        amAcceptanceFiasset.setSoperatetype(infP6Inventory.getSoperatetype());
        amAcceptanceFiasset.setSoperatunitnm(infP6Inventory.getSoperatunitnm());
        amAcceptanceFiasset.setSownerunit(infP6Inventory.getSownerunit());
        amAcceptanceFiasset.setSbldistrict(infP6Inventory.getSbldistrict());
        if(null != infP6Inventory.getSdesignlife()) {
        	amAcceptanceFiasset.setIuseyear(Double.valueOf(infP6Inventory.getSdesignlife()));
        }
        amAcceptanceFiasset.setIlineid(infP6Inventory.getIlineid());
        
        amAcceptanceFiassetMapper.update(amAcceptanceFiasset);

        // 置空"更新状态"
		infP6InventoryMapper.updateIupdateState(infP6Inventory.getIndocno());
    }
    
    public void L1compareToEquip(){
    	// 查询未插入过实物资产台账表的接口数据
        List<InfL1PlanDetailed> infL1PlanDetailedList = infL1PlanDetailedMapper.findAllByIhascopied();
        for (InfL1PlanDetailed e:infL1PlanDetailedList){
        	if(null != e.getSfcode() && !"".equals(e.getSfcode())) {
        		InfL1Inventory infL1Inventory = infL1InventoryMapper.findByAssetnum(e.getSfcode());
                if (infL1Inventory != null){
                    updateL1Ihascopied(e,infL1Inventory);
                    syncL1(infL1Inventory,e);
                }else {
                	e.setIcopystate(1);
            		this.infL1PlanDetailedMapper.updateIhascopied(e);
            		syncPlanDetailedL1(e);
                }
        	}else {
        		e.setIcopystate(1);
        		this.infL1PlanDetailedMapper.updateIhascopied(e);
        		syncPlanDetailedL1(e);
        	}
        };
        
        // 查询已插入过实物资产台账表的但是发生了数据变更的接口数据，这批数据需要通过定时任务同步更新实物资产表中对应业务数据
        List<InfL1PlanDetailed> infL1PlanDetailedList2 = this.infL1PlanDetailedMapper.findAllByIhascopiedAndIupdateState();
        infL1PlanDetailedList2.forEach(e1 -> {
        	AmAcceptanceFiasset info = this.amAcceptanceFiassetMapper.findByIsourceid(e1.getIndocno(),0);
        	if(null != info) {
        		infL1PlanDetailedMapper.updateIupdateState(e1.getIndocno());
        		if(null != e1.getSfcode() && !"".equals(e1.getSfcode())) {
        			InfL1Inventory e2 = this.infL1InventoryMapper.findByAssetnum(e1.getSfcode());
            		if(null != e2) {
            			// 置空"更新状态"
            			infL1InventoryMapper.updateIupdateState(e2.getIndocno());
            		}
            		syncUpdateL1(e1,info,e2);
        		}else {
        			syncUpdateL1(e1,info,null);
        		}
        	}
        });
    }

    public void syncPlanDetailedL1(InfL1PlanDetailed infL1PlanDetailed) {
    	AmAcceptanceFiasset capAcceptanceEquip = new AmAcceptanceFiasset();
        capAcceptanceEquip.setIsourceid(infL1PlanDetailed.getIndocno());
        capAcceptanceEquip.setSfcode(infL1PlanDetailed.getSfcode());
        capAcceptanceEquip.setSmajornm(infL1PlanDetailed.getSassetmajornm());
        
        if(null != infL1PlanDetailed.getSstransnm() && !"".equals(infL1PlanDetailed.getSstransnm())) {
        	if("未核验".equals(infL1PlanDetailed.getSstransnm())) {
        		capAcceptanceEquip.setIstrans(0);
        	}else if("同意接收".equals(infL1PlanDetailed.getSstransnm())) {
        		capAcceptanceEquip.setIstrans(1);
        	}else if("拒绝接收".equals(infL1PlanDetailed.getSstransnm())) {
        		capAcceptanceEquip.setIstrans(2);
        	}
        }
        
        capAcceptanceEquip.setSparentno(infL1PlanDetailed.getSparentno());
        capAcceptanceEquip.setSbrandAct(infL1PlanDetailed.getSbrandAct());
        capAcceptanceEquip.setSinslocationnoAct(infL1PlanDetailed.getSinslocationnoAct());
        capAcceptanceEquip.setSlineendpost(infL1PlanDetailed.getSlineendpost());
        capAcceptanceEquip.setSlinestartpost(infL1PlanDetailed.getSlinestartpost());
        capAcceptanceEquip.setSproductnmAct(infL1PlanDetailed.getSproductnmAct());
        capAcceptanceEquip.setSspecAct(infL1PlanDetailed.getSspecAct());
        capAcceptanceEquip.setStranssite(infL1PlanDetailed.getStranssite());
        capAcceptanceEquip.setSbarcode(infL1PlanDetailed.getSbarcode());
        capAcceptanceEquip.setSexternalid(String.valueOf(infL1PlanDetailed.getSexternalid() != null ? infL1PlanDetailed.getSexternalid() :""));
        capAcceptanceEquip.setSdescribe(infL1PlanDetailed.getSdescribe());
        capAcceptanceEquip.setIdel(0);
        capAcceptanceEquip.setStransferno(infL1PlanDetailed.getStransferno());
        capAcceptanceEquip.setItransferid(infL1PlanDetailed.getItransferid());
        
        //来自于L1的数据
        capAcceptanceEquip.setIsource(0);
        amAcceptanceFiassetMapper.insert(capAcceptanceEquip);
    }
    
    /**
         * 如果接口数据已推送实物资产台账表，但是发生变更，则需要将更新的数据同步更新实物资产台账表中对应的业务数据
     */
    public void syncUpdateL1(InfL1PlanDetailed infL1PlanDetailed,AmAcceptanceFiasset amAcceptanceFiasset,InfL1Inventory infL1Inventory) {
    	amAcceptanceFiasset.setSmajornm(infL1PlanDetailed.getSassetmajornm());
    	amAcceptanceFiasset.setIupdatestate(1);
        if(null != infL1PlanDetailed.getSstransnm() && !"".equals(infL1PlanDetailed.getSstransnm())) {
        	if("未核验".equals(infL1PlanDetailed.getSstransnm())) {
        		amAcceptanceFiasset.setIstrans(0);
        	}else if("同意接收".equals(infL1PlanDetailed.getSstransnm())) {
        		amAcceptanceFiasset.setIstrans(1);
        	}else if("拒绝接收".equals(infL1PlanDetailed.getSstransnm())) {
        		amAcceptanceFiasset.setIstrans(2);
        	}
        }
        
        if(null != amAcceptanceFiasset.getSfcode() && !"".equals(amAcceptanceFiasset.getSfcode())) {
        	List<AmAcceptanceFiasset> sonList = this.amAcceptanceFiassetMapper.findSonByIparent(amAcceptanceFiasset.getSfcode());
        	sonList.forEach(e -> {
        		e.setIstrans(amAcceptanceFiasset.getIstrans());
        		this.amAcceptanceFiassetMapper.update(e);
        	});
        }
        
        amAcceptanceFiasset.setSparentno(infL1PlanDetailed.getSparentno());
        amAcceptanceFiasset.setSbrandAct(infL1PlanDetailed.getSbrandAct());
        amAcceptanceFiasset.setSinslocationnoAct(infL1PlanDetailed.getSinslocationnoAct());
        amAcceptanceFiasset.setSlineendpost(infL1PlanDetailed.getSlineendpost());
        amAcceptanceFiasset.setSlinestartpost(infL1PlanDetailed.getSlinestartpost());
        amAcceptanceFiasset.setSproductnmAct(infL1PlanDetailed.getSproductnmAct());
        amAcceptanceFiasset.setSspecAct(infL1PlanDetailed.getSspecAct());
        amAcceptanceFiasset.setStranssite(infL1PlanDetailed.getStranssite());
        amAcceptanceFiasset.setSbarcode(infL1PlanDetailed.getSbarcode());
        amAcceptanceFiasset.setSexternalid(String.valueOf(infL1PlanDetailed.getSexternalid() != null ? infL1PlanDetailed.getSexternalid() :""));
        amAcceptanceFiasset.setSdescribe(infL1PlanDetailed.getSdescribe());
        
        if(null != infL1Inventory) {
        	amAcceptanceFiasset.setSrepairdeptnm(infL1Inventory.getSrepairdeptnm());
        	amAcceptanceFiasset.setSfnameas(infL1Inventory.getSfnameas());
        	amAcceptanceFiasset.setSnoteOne(infL1Inventory.getSnoteOne());
        	amAcceptanceFiasset.setSnoteTwo(infL1Inventory.getSnoteTwo());
        	amAcceptanceFiasset.setSnoteThree(infL1Inventory.getSnoteThree());
        	amAcceptanceFiasset.setSeqclassno(infL1Inventory.getSeqclassno());
        	amAcceptanceFiasset.setSlinestage(infL1Inventory.getSlinestage());
        	amAcceptanceFiasset.setSconlocation(infL1Inventory.getSconlocation());
        	amAcceptanceFiasset.setSsuppliernm(infL1Inventory.getSsuppliernm());
        	amAcceptanceFiasset.setSunit(infL1Inventory.getSunit());
        	
        	if(null != infL1Inventory.getIqty()) {
        		amAcceptanceFiasset.setIqtyAct(String.valueOf(infL1Inventory.getIqty()));
        	}
        	
        	amAcceptanceFiasset.setIprice(infL1Inventory.getIprice());
        	amAcceptanceFiasset.setSallocationmethod(infL1Inventory.getSallocationmethod());
        	amAcceptanceFiasset.setIuseyear(infL1Inventory.getIuseyear());
        	amAcceptanceFiasset.setSdesignunit(infL1Inventory.getSdesignunit());
        	amAcceptanceFiasset.setSestimatedlife(infL1Inventory.getSestimatedlife());
        	amAcceptanceFiasset.setDstartuse(null == infL1Inventory.getDstartuse() ? null : infL1Inventory.getDstartuse());
        	amAcceptanceFiasset.setIwarranty(infL1Inventory.getIwarranty());
        	amAcceptanceFiasset.setDwarrstart(infL1Inventory.getDwarrstart());
        	amAcceptanceFiasset.setIothercost(infL1Inventory.getIothercost());
        	amAcceptanceFiasset.setSuseunit(infL1Inventory.getSuseunit());
        	amAcceptanceFiasset.setSconttype(infL1Inventory.getSconttype());
        	amAcceptanceFiasset.setScontsumflag(infL1Inventory.getScontsumflag());
        	amAcceptanceFiasset.setScontno(infL1Inventory.getScontno());
        	amAcceptanceFiasset.setScontfirstparty(infL1Inventory.getScontpartb());
        	amAcceptanceFiasset.setScontpartb(infL1Inventory.getScontpartb());
        	amAcceptanceFiasset.setSconttotalmoney(infL1Inventory.getSconttotalmoney());
        	amAcceptanceFiasset.setDcontsign(null == infL1Inventory.getDcontsign() ? null : infL1Inventory.getDcontsign().toString());
        	amAcceptanceFiasset.setSoperatetype(infL1Inventory.getSoperatetype());
        	amAcceptanceFiasset.setSoperatunitnm(infL1Inventory.getSoperatunitnm());
        	amAcceptanceFiasset.setSownerunit(infL1Inventory.getSownerunit());
        	amAcceptanceFiasset.setSfnameas(infL1Inventory.getSfnameas());
        	amAcceptanceFiasset.setIsharecost(infL1Inventory.getIsharecost());
        	amAcceptanceFiasset.setSpurchasetype(infL1Inventory.getSpurchasetype());
        	amAcceptanceFiasset.setSfixedassetstatus(infL1Inventory.getSfixedassetstatus());
        	amAcceptanceFiasset.setSassetnoOld(infL1Inventory.getSassetnoOld());
        	amAcceptanceFiasset.setSassetnoNew(infL1Inventory.getSassetnoNew());
        	amAcceptanceFiasset.setIassetvalue(infL1Inventory.getIassetvalue());
        	amAcceptanceFiasset.setSassetItno(infL1Inventory.getSassetItno());
        	amAcceptanceFiasset.setSfname(infL1Inventory.getSfname());
        	amAcceptanceFiasset.setIlineid(infL1Inventory.getIlineid());
        	amAcceptanceFiasset.setSusestate(infL1Inventory.getSusestate());
        	amAcceptanceFiasset.setSfinancstate(infL1Inventory.getSfinancstate());
        	amAcceptanceFiasset.setSbldistrict(infL1Inventory.getSbldistrict());
        	amAcceptanceFiasset.setIuseyear(infL1Inventory.getIuseyear());
        }
        
        amAcceptanceFiasset.setIdel(0);
        amAcceptanceFiasset.setStransferno(infL1PlanDetailed.getStransferno());
        amAcceptanceFiasset.setItransferid(infL1PlanDetailed.getItransferid());
        
        this.amAcceptanceFiassetMapper.update(amAcceptanceFiasset);
        
        // 置空"更新状态"
        this.infL1InventoryMapper.updateIupdateState(infL1Inventory.getIndocno());
        this.infL1PlanDetailedMapper.updateIupdateState(infL1PlanDetailed.getIndocno());
    }
    
    public void updateL1Ihascopied(InfL1PlanDetailed infL1PlanDetailed,InfL1Inventory infL1Inventory){
        infL1Inventory.setIcopystate(1);
        infL1PlanDetailed.setIcopystate(1);
        infL1InventoryMapper.updateIhascopied(infL1Inventory);
        infL1PlanDetailedMapper.updateIhascopied(infL1PlanDetailed);
    }

    //修改接口表数据同步状态ihascopied=1
    public void updateP6Ihascopied(InfP6Inventory infP6Inventory){
        infP6Inventory.setIcopystate(1);
        infP6Inventory.setStransferno("LW1026");
        infP6InventoryMapper.updateIhascopied(infP6Inventory);
    }

    //接口表同步到业务表
    public void syncP6(InfP6Inventory infP6Inventory){
        AmAcceptanceFiasset capAcceptanceEquip = new AmAcceptanceFiasset();
        capAcceptanceEquip.setIsourceid(infP6Inventory.getIndocno());
        capAcceptanceEquip.setSfcode(infP6Inventory.getSfcode());
        capAcceptanceEquip.setSfnameas(infP6Inventory.getSfnameas());
        capAcceptanceEquip.setSmajornm(infP6Inventory.getSmajornm());
        capAcceptanceEquip.setSparentno(infP6Inventory.getSparentno());
        capAcceptanceEquip.setSbrandAct(infP6Inventory.getSassetbrand());
        capAcceptanceEquip.setSinslocationnoAct(infP6Inventory.getSinslocationno());
        capAcceptanceEquip.setSlineendpost(infP6Inventory.getSlineendpost());
        capAcceptanceEquip.setSlinestartpost(infP6Inventory.getSlinestartpost());
        capAcceptanceEquip.setSproductnmAct(infP6Inventory.getSproductnm());
        capAcceptanceEquip.setSspecAct(infP6Inventory.getSspec());
        capAcceptanceEquip.setSexternalid(infP6Inventory.getSexternalid());
        capAcceptanceEquip.setSfname(infP6Inventory.getSfname());
        capAcceptanceEquip.setSnoteOne(infP6Inventory.getSnote0ne());
        capAcceptanceEquip.setSnoteTwo(infP6Inventory.getSnoteTwo());
        capAcceptanceEquip.setSnoteThree(infP6Inventory.getSnoteThree());
        capAcceptanceEquip.setSeqclassno(infP6Inventory.getSeqclassno());
        capAcceptanceEquip.setSlinestage(infP6Inventory.getSlinestage());
        capAcceptanceEquip.setSconlocation(infP6Inventory.getSlocationdesc());
        capAcceptanceEquip.setSsuppliernm(infP6Inventory.getSsuppliernm());
        capAcceptanceEquip.setSunit(infP6Inventory.getSunit());
        capAcceptanceEquip.setIqtyAct(String.valueOf(infP6Inventory.getIqty()));

        capAcceptanceEquip.setIprice(infP6Inventory.getIprice());
        capAcceptanceEquip.setIusefullife(infP6Inventory.getIusefullife());
        capAcceptanceEquip.setSdesignunit(infP6Inventory.getSdesignunit());
        capAcceptanceEquip.setSestimatedlife(infP6Inventory.getSestimatedlife());
        capAcceptanceEquip.setDstartuse(infP6Inventory.getDstartuse());
        capAcceptanceEquip.setIwarranty(intCast(infP6Inventory.getIwarranty()));
        capAcceptanceEquip.setDwarrstart(infP6Inventory.getDwarrstart());
        capAcceptanceEquip.setIdel(intCast(infP6Inventory.getSlmsinvaid()) != null ? intCast(infP6Inventory.getSlmsinvaid()):0);
        capAcceptanceEquip.setSusestate(infP6Inventory.getSusestate());
        capAcceptanceEquip.setSfinancstate(infP6Inventory.getSfinancstate());
        capAcceptanceEquip.setIothercost(infP6Inventory.getIothercost());
        capAcceptanceEquip.setStranspermanent(infP6Inventory.getStranspermanent());
        capAcceptanceEquip.setSassetcurrency(infP6Inventory.getSassetcurrency());
        capAcceptanceEquip.setScurrencyunit(infP6Inventory.getScurrencyunit());
        capAcceptanceEquip.setSuseunit(infP6Inventory.getSuseunit());
        capAcceptanceEquip.setIeqvalence(infP6Inventory.getIeqvalence());
        capAcceptanceEquip.setSconttype(infP6Inventory.getSconttype());
        capAcceptanceEquip.setScontsumflag(infP6Inventory.getScontsumflag());
        capAcceptanceEquip.setScontno(infP6Inventory.getScontno());
        capAcceptanceEquip.setScontfirstparty(infP6Inventory.getScontfirstparty());
        capAcceptanceEquip.setScontpartb(infP6Inventory.getScontpartb());
        capAcceptanceEquip.setSconttotalmoney(infP6Inventory.getSconttotalmoney());
        capAcceptanceEquip.setDcontsign(infP6Inventory.getDcontsign());
        capAcceptanceEquip.setSoperatetype(infP6Inventory.getSoperatetype());
        capAcceptanceEquip.setSoperatunitnm(infP6Inventory.getSoperatunitnm());
        capAcceptanceEquip.setSownerunit(infP6Inventory.getSownerunit());
        capAcceptanceEquip.setSbldistrict(infP6Inventory.getSbldistrict());
        if(null != infP6Inventory.getSdesignlife()) {
        	capAcceptanceEquip.setIuseyear(Double.valueOf(infP6Inventory.getSdesignlife()));
        }
        capAcceptanceEquip.setIlineid(infP6Inventory.getIlineid());
        //来资源P6的数据
        capAcceptanceEquip.setIsource(1);
        amAcceptanceFiassetMapper.insert(capAcceptanceEquip);

    }

    public void syncL1(InfL1Inventory infL1Inventory,InfL1PlanDetailed infL1PlanDetailed){
        AmAcceptanceFiasset capAcceptanceEquip = new AmAcceptanceFiasset();
        capAcceptanceEquip.setIsourceid(infL1PlanDetailed.getIndocno());
        capAcceptanceEquip.setSfcode(infL1PlanDetailed.getSfcode());
        capAcceptanceEquip.setSmajornm(infL1PlanDetailed.getSassetmajornm());
        
        if(null != infL1PlanDetailed.getSstransnm() && !"".equals(infL1PlanDetailed.getSstransnm())) {
        	if("未核验".equals(infL1PlanDetailed.getSstransnm())) {
        		capAcceptanceEquip.setIstrans(0);
        	}else if("同意接收".equals(infL1PlanDetailed.getSstransnm())) {
        		capAcceptanceEquip.setIstrans(1);
        	}else if("拒绝接收".equals(infL1PlanDetailed.getSstransnm())) {
        		capAcceptanceEquip.setIstrans(2);
        	}
        }
        
        capAcceptanceEquip.setSparentno(infL1PlanDetailed.getSparentno());
        capAcceptanceEquip.setSbrandAct(infL1PlanDetailed.getSbrandAct());
        capAcceptanceEquip.setSinslocationnoAct(infL1PlanDetailed.getSinslocationnoAct());
        capAcceptanceEquip.setSlineendpost(infL1PlanDetailed.getSlineendpost());
        capAcceptanceEquip.setSlinestartpost(infL1PlanDetailed.getSlinestartpost());
        capAcceptanceEquip.setSproductnmAct(infL1PlanDetailed.getSproductnmAct());
        capAcceptanceEquip.setSspecAct(infL1PlanDetailed.getSspecAct());
        capAcceptanceEquip.setStranssite(infL1PlanDetailed.getStranssite());
        capAcceptanceEquip.setSbarcode(infL1PlanDetailed.getSbarcode());
        capAcceptanceEquip.setSexternalid(String.valueOf(infL1PlanDetailed.getSexternalid() != null ? infL1PlanDetailed.getSexternalid() :""));
        capAcceptanceEquip.setSdescribe(infL1PlanDetailed.getSdescribe());
        capAcceptanceEquip.setSfnameas(infL1Inventory.getSfnameas());
        capAcceptanceEquip.setSnoteOne(infL1Inventory.getSnoteOne());
        capAcceptanceEquip.setSnoteTwo(infL1Inventory.getSnoteTwo());
        capAcceptanceEquip.setSnoteThree(infL1Inventory.getSnoteThree());
        capAcceptanceEquip.setSeqclassno(infL1Inventory.getSeqclassno());
        capAcceptanceEquip.setSlinestage(infL1Inventory.getSlinestage());
        capAcceptanceEquip.setSconlocation(infL1Inventory.getSconlocation());
        capAcceptanceEquip.setSsuppliernm(infL1Inventory.getSsuppliernm());
        capAcceptanceEquip.setSunit(infL1Inventory.getSunit());
        
        if(null != infL1Inventory.getIqty()) {
        	capAcceptanceEquip.setIqtyAct(String.valueOf(infL1Inventory.getIqty()));
        }
        
        capAcceptanceEquip.setIprice(infL1Inventory.getIprice());
        capAcceptanceEquip.setSallocationmethod(infL1Inventory.getSallocationmethod());
        capAcceptanceEquip.setIuseyear(infL1Inventory.getIuseyear());
        capAcceptanceEquip.setSdesignunit(infL1Inventory.getSdesignunit());
        capAcceptanceEquip.setSestimatedlife(infL1Inventory.getSestimatedlife());
        capAcceptanceEquip.setDstartuse(null == infL1Inventory.getDstartuse() ? null : infL1Inventory.getDstartuse());
        capAcceptanceEquip.setIwarranty(infL1Inventory.getIwarranty());
        capAcceptanceEquip.setDwarrstart(infL1Inventory.getDwarrstart());
        capAcceptanceEquip.setIdel(0);
        capAcceptanceEquip.setSusestate(infL1Inventory.getSusestate());
        capAcceptanceEquip.setSfinancstate(infL1Inventory.getSfinancstate());
        capAcceptanceEquip.setIothercost(infL1Inventory.getIothercost());
        capAcceptanceEquip.setSuseunit(infL1Inventory.getSuseunit());
        capAcceptanceEquip.setSconttype(infL1Inventory.getSconttype());
        capAcceptanceEquip.setScontsumflag(infL1Inventory.getScontsumflag());
        capAcceptanceEquip.setScontno(infL1Inventory.getScontno());
        capAcceptanceEquip.setScontfirstparty(infL1Inventory.getScontpartb());
        capAcceptanceEquip.setScontpartb(infL1Inventory.getScontpartb());
        capAcceptanceEquip.setSconttotalmoney(infL1Inventory.getSconttotalmoney());
        capAcceptanceEquip.setDcontsign(null == infL1Inventory.getDcontsign() ? null : infL1Inventory.getDcontsign().toString());
        capAcceptanceEquip.setSoperatetype(infL1Inventory.getSoperatetype());
        
        capAcceptanceEquip.setSoperatunitnm(infL1Inventory.getSoperatunitnm());
        capAcceptanceEquip.setSownerunit(infL1Inventory.getSownerunit());
        capAcceptanceEquip.setSbldistrict(infL1Inventory.getSbldistrict());
        capAcceptanceEquip.setSfnameas(infL1Inventory.getSfnameas());
        capAcceptanceEquip.setIsharecost(infL1Inventory.getIsharecost());
        capAcceptanceEquip.setSpurchasetype(infL1Inventory.getSpurchasetype());
        capAcceptanceEquip.setSfixedassetstatus(infL1Inventory.getSfixedassetstatus());
        capAcceptanceEquip.setSassetnoOld(infL1Inventory.getSassetnoOld());
        capAcceptanceEquip.setSassetnoNew(infL1Inventory.getSassetnoNew());
        capAcceptanceEquip.setIassetvalue(infL1Inventory.getIassetvalue());
        capAcceptanceEquip.setSassetItno(infL1Inventory.getSassetItno());
        capAcceptanceEquip.setSfname(infL1Inventory.getSfname());
        capAcceptanceEquip.setIlineid(infL1Inventory.getIlineid());
        capAcceptanceEquip.setStransferno(infL1PlanDetailed.getStransferno());
        capAcceptanceEquip.setItransferid(infL1PlanDetailed.getItransferid());
        capAcceptanceEquip.setSrepairdeptnm(infL1Inventory.getSrepairdeptnm());
        //来自于L1的数据
        capAcceptanceEquip.setIsource(0);
        amAcceptanceFiassetMapper.insert(capAcceptanceEquip);
    }

    public Date simpledate(String date){
        if (date == null){
            return null;
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Double doubleCast(String str){
        if (null != str && "" != str){
            try {
                Double dou = Double.valueOf(str);
                return dou;
            } catch (NumberFormatException e) {
                e.printStackTrace();
                return null;
            }
        }else {
            return null;
        }
    }

    private Integer intCast(String str){
        if (null != str && "" != str){
            try {
            	if(str.contains(".")) {
            		str = str.substring(0, str.indexOf("."));
            	}
            	System.out.println("----------------------str：值为------------------" + str);
                Integer integer = Integer.valueOf(str);
                return integer;
            } catch (NumberFormatException e) {
                e.printStackTrace();
                return null;
            }
        }else {
            return null;
        }
    }
}
