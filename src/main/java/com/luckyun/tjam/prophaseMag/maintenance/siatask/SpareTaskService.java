package com.luckyun.tjam.prophaseMag.maintenance.siatask;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.luckyun.core.annotation.TransactionalException;
import com.luckyun.tjam.prophaseMag.acceptance.mapper.InfSparesInventoryMapper;
import com.luckyun.tjam.prophaseMag.acceptance.mapper.InfSparesPlanMapper;
import com.luckyun.tjam.prophaseMag.acceptance.model.InfSparesInventory;
import com.luckyun.tjam.prophaseMag.acceptance.model.InfSparesPlan;
import com.luckyun.tjam.prophaseMag.maintenance.mapper.AmAcceptanceSpareMapper;
import com.luckyun.tjam.prophaseMag.maintenance.model.AmAcceptanceSpare;


/**
 * Created by whs on 2019/12/18.
 */

@Service
public class SpareTaskService {

    @Autowired
    private AmAcceptanceSpareMapper amAcceptanceSpareMapper;

    @Autowired
    private InfSparesInventoryMapper infSparesInventoryMapper;

    @Autowired
    private InfSparesPlanMapper infSparesPlanMapper;

    @TransactionalException
    public void SparesCompareTo(){
        List<InfSparesInventory> infSparesInventoryList = infSparesInventoryMapper.findAllByIhascopied();
        infSparesInventoryList.forEach(e->{
            updateSparesIhascopied(e);
            syncSpares(e);
        });
        
        List<InfSparesInventory> infSparesInventoryList2 = this.infSparesInventoryMapper.findAllByIhascopiedAndIupdateState();
        infSparesInventoryList2.forEach(e1 -> {
        	AmAcceptanceSpare info = this.amAcceptanceSpareMapper.findByIsourceid(e1.getIndocno());
        	if(null != info) {

        		// 变更状态 置空
        		infSparesInventoryMapper.updateIupdateState(e1.getIndocno());
        		
        		syncUpdateSpares(e1,info);
        	}
        });
    }

    public void syncUpdateSpares(InfSparesInventory infSparesInventory,AmAcceptanceSpare amAcceptanceSpare) {
    	amAcceptanceSpare.setIupdatestate(1);
        amAcceptanceSpare.setSspareno(infSparesInventory.getSspareno());
        amAcceptanceSpare.setSsparenm(infSparesInventory.getSsparenm());
        amAcceptanceSpare.setSspec(infSparesInventory.getSspec());
        amAcceptanceSpare.setSunit(infSparesInventory.getSunit());
        amAcceptanceSpare.setSsuppliernm(infSparesInventory.getSsuppliernm());
        amAcceptanceSpare.setIprice(infSparesInventory.getIprice());
        amAcceptanceSpare.setIqty(infSparesInventory.getIqty());
        amAcceptanceSpare.setImoney(infSparesInventory.getImoney());
        amAcceptanceSpare.setSmajornm(infSparesInventory.getSmajornm());
        amAcceptanceSpare.setSassetno(infSparesInventory.getSassetno());
        amAcceptanceSpare.setScurrencyunit(infSparesInventory.getScurrencyunit());
        amAcceptanceSpare.setSexternalid(infSparesInventory.getSexternalid());
        
        if(null != infSparesInventory.getSstransnm() && !"".equals(infSparesInventory.getSstransnm())) {
        	if("未核验".equals(infSparesInventory.getSstransnm())) {
        		amAcceptanceSpare.setIstrans(0);
        	}else if("同意接收".equals(infSparesInventory.getSstransnm())) {
        		amAcceptanceSpare.setIstrans(1);
        	}else if("拒绝接收".equals(infSparesInventory.getSstransnm())) {
        		amAcceptanceSpare.setIstrans(2);
        	}
        }
        
        amAcceptanceSpare.setDinfaccept(infSparesInventory.getDinfaccept());
        amAcceptanceSpare.setIlineid(infSparesInventory.getIlineid());
        amAcceptanceSpare.setIdel(infSparesInventory.getIdel() != null ? infSparesInventory.getIdel() : 0);
        amAcceptanceSpare.setDregt(infSparesInventory.getDregt());
        amAcceptanceSpare.setDmodt(infSparesInventory.getDmodt());
        amAcceptanceSpare.setIlineid(infSparesInventory.getIlineid());
        amAcceptanceSpare.setSownerunit(infSparesInventory.getSownerunit());
        amAcceptanceSpare.setSoperatunitnm(infSparesInventory.getSoperatunitnm());
        amAcceptanceSpare.setScontdesc(infSparesInventory.getScontdesc());
        amAcceptanceSpare.setScontno(infSparesInventory.getScontno());
        amAcceptanceSpare.setIjscost(infSparesInventory.getIjscost());
        amAcceptanceSpare.setSdescribe(infSparesInventory.getSdescribe());
        amAcceptanceSpare.setIsource(infSparesInventory.getIsource());
        amAcceptanceSpareMapper.update(amAcceptanceSpare);
    }
    
    public void updateSparesIhascopied(InfSparesInventory infSparesInventory){
        infSparesInventory.setIcopystate(1);
        if(null != infSparesInventory.getIsource() && infSparesInventory.getIsource() == 1){
            List<InfSparesPlan> infSparesPlans = infSparesPlanMapper.findByIsource(1);
            if (!CollectionUtils.isEmpty(infSparesPlans)){
                infSparesInventory.setIlinkno(infSparesPlans.get(0).getIndocno());
            }
        }else if(null != infSparesInventory.getIsource() && infSparesInventory.getIsource() == 0){
            List<InfSparesPlan> infSparesPlans = infSparesPlanMapper.findByIsource(0);
            if (!CollectionUtils.isEmpty(infSparesPlans)){
                infSparesInventory.setIlinkno(infSparesPlans.get(0).getIndocno());
            }
        }
        infSparesInventoryMapper.updateIhascopied(infSparesInventory);
    }

    public void syncSpares(InfSparesInventory infSparesInventory){
        AmAcceptanceSpare capAcceptanceSpare = new AmAcceptanceSpare();
        capAcceptanceSpare.setIsourceid(infSparesInventory.getIndocno());
        capAcceptanceSpare.setSspareno(infSparesInventory.getSspareno());
        capAcceptanceSpare.setSsparenm(infSparesInventory.getSsparenm());
        capAcceptanceSpare.setSspec(infSparesInventory.getSspec());
        capAcceptanceSpare.setSunit(infSparesInventory.getSunit());
        capAcceptanceSpare.setSsuppliernm(infSparesInventory.getSsuppliernm());
        capAcceptanceSpare.setIprice(infSparesInventory.getIprice());
        capAcceptanceSpare.setIqty(infSparesInventory.getIqty());
        capAcceptanceSpare.setImoney(infSparesInventory.getImoney());
        capAcceptanceSpare.setSmajornm(infSparesInventory.getSmajornm());
        capAcceptanceSpare.setSassetno(infSparesInventory.getSassetno());
        capAcceptanceSpare.setScurrencyunit(infSparesInventory.getScurrencyunit());
        capAcceptanceSpare.setSexternalid(infSparesInventory.getSexternalid());
        
        if(null != infSparesInventory.getSstransnm() && !"".equals(infSparesInventory.getSstransnm())) {
        	if("未核验".equals(infSparesInventory.getSstransnm())) {
        		capAcceptanceSpare.setIstrans(0);
        	}else if("同意接收".equals(infSparesInventory.getSstransnm())) {
        		capAcceptanceSpare.setIstrans(1);
        	}else if("拒绝接收".equals(infSparesInventory.getSstransnm())) {
        		capAcceptanceSpare.setIstrans(2);
        	}
        }
        
        capAcceptanceSpare.setDinfaccept(infSparesInventory.getDinfaccept());
        capAcceptanceSpare.setIlineid(infSparesInventory.getIlineid());
        capAcceptanceSpare.setIdel(infSparesInventory.getIdel() != null ? infSparesInventory.getIdel() : 0);
        capAcceptanceSpare.setDregt(infSparesInventory.getDregt());
        capAcceptanceSpare.setDmodt(infSparesInventory.getDmodt());
        capAcceptanceSpare.setIlineid(infSparesInventory.getIlineid());
        capAcceptanceSpare.setSownerunit(infSparesInventory.getSownerunit());
        capAcceptanceSpare.setSoperatunitnm(infSparesInventory.getSoperatunitnm());
        capAcceptanceSpare.setScontdesc(infSparesInventory.getScontdesc());
        capAcceptanceSpare.setScontno(infSparesInventory.getScontno());
        capAcceptanceSpare.setIjscost(infSparesInventory.getIjscost());
        capAcceptanceSpare.setSdescribe(infSparesInventory.getSdescribe());
        capAcceptanceSpare.setIsource(infSparesInventory.getIsource());
        amAcceptanceSpareMapper.insert(capAcceptanceSpare);
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

    public Integer intCast(String str){
        if (null != str && "" != str){
            try {
            	if(str.contains(".")) {
            		str = str.substring(0, str.indexOf("."));
            	}
            	if(str.contains(",")) {
            		str = str.replace(",", "");
            	}
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

