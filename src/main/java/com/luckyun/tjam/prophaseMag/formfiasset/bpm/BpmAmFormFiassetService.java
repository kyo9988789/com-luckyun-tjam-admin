package com.luckyun.tjam.prophaseMag.formfiasset.bpm;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Joiner;
import com.luckyun.base.provider.feign.BaseSysUserProvider;
import com.luckyun.bpm.BpmInstanceResource;
import com.luckyun.bpm.event.BpmEventQueue;
import com.luckyun.bpm.event.BpmMessage;
import com.luckyun.core.data.filter.AuthInfo;
import com.luckyun.core.exception.CustomException;
import com.luckyun.core.tool.Base64Utils;
import com.luckyun.model.user.SysAccount;
import com.luckyun.tjam.prophaseMag.formfiasset.entity.AmFormFiasset;
import com.luckyun.tjam.prophaseMag.formfiasset.mapper.AmFormFiassetMapper;
import com.luckyun.tjam.prophaseMag.verification.bpm.BpmTaskParam;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by whs on 2020/6/15.
 */
@Service
public class BpmAmFormFiassetService extends BpmEventQueue {

    private String modulePath = "base/bpm/proInstance";

    @Autowired
    ObjectFactory<HttpSession> httpSessionFactory;

    @Autowired
    private BpmInstanceResource bpmInstanceResource;

    @Autowired
    private BaseSysUserProvider baseSysUserProvider;

    @Autowired
    AmFormFiassetMapper amFormFiassetMapper;

    @Override
    protected void onStart(BpmMessage bpmMessage) {
        AmFormFiasset one = new AmFormFiasset();
        one.setIndocno(bpmMessage.getDocid());
        one.setIapprovalstate(2);
        amFormFiassetMapper.update(one);
    }

    @Override
    protected void onProcessing(BpmMessage bpmMessage) {
        AmFormFiasset one = new AmFormFiasset();
        one.setIndocno(bpmMessage.getDocid());
        one.setIapprovalstate(2);
        amFormFiassetMapper.update(one);
    }

    @Override
    protected void onComplete(BpmMessage bpmMessage) {
        AmFormFiasset one = new AmFormFiasset();
        one.setIndocno(bpmMessage.getDocid());
        one.setIapprovalstate(3);
        amFormFiassetMapper.update(one);
    }

    @Override
    protected void onRefuse(BpmMessage bpmMessage) {
        AmFormFiasset one = new AmFormFiasset();
        one.setIndocno(bpmMessage.getDocid());
        one.setIapprovalstate(4);
        amFormFiassetMapper.update(one);
    }

    @Override
    protected void onDelete(BpmMessage bpmMessage) {
        AmFormFiasset one = new AmFormFiasset();
        one.setIndocno(bpmMessage.getDocid());
        one.setIapprovalstate(1);
        amFormFiassetMapper.update(one);
    }

    @Override
    protected void onCancel(BpmMessage bpmMessage) {
        AmFormFiasset one = new AmFormFiasset();
        one.setIndocno(bpmMessage.getDocid());
        one.setIapprovalstate(1);
        amFormFiassetMapper.update(one);
    }

    @Override
    protected void onTransfer(BpmMessage bpmMessage) {

    }

    private String basekey(){
        AuthInfo authInfo = (AuthInfo) (this.httpSessionFactory.getObject()).getAttribute("authInfo");
        List<String> skeys = this.bpmInstanceResource.getBpmInstanceVariableFeign("BpmAmWorthAppmtBatchService");
        Map<String,String> params = new HashMap<String,String>();
        if (!CollectionUtils.isEmpty(skeys)){
            for(String skey : skeys) {
                if("inituser".equals(skey)) {
                    params.put(skey,authInfo.getSloginid());
                    continue;
                }
                Map<String,Object> condition = new HashMap<String,Object>();
                condition.put("sgroupKey", skey);
                List<SysAccount> sysAccountList = this.baseSysUserProvider.findFUserByComplexCondition(condition);
                List<String> sloginList = null;
                if(!CollectionUtils.isEmpty(sysAccountList)){
                    sloginList = sysAccountList.stream().map(c -> c.getSloginid().trim()).collect(Collectors.toList());
                }
                String sloginids = null;
                if (!CollectionUtils.isEmpty(sloginList)){
                    sloginids = Joiner.on(",").join(sloginList);
                }
                params.put(skey,sloginids);
            }
            return Base64Utils.encoderInfo(JSON.toJSONString(params));
        }else {
            throw new CustomException("无法获取流程节点审批角色！");
        }
    }

    /**
     * 获取流程启动参数
     * @param indocno
     * @return
     */
    public BpmTaskParam createBpmParams(String sname, Long indocno){
        StringBuffer busKey = new StringBuffer();
        busKey.append(this.getClass().getSimpleName().toLowerCase());
        busKey.append("_");
        busKey.append(modulePath);
        busKey.append("_");
        busKey.append(indocno);
        busKey.append("_");
        busKey.append("threePart");
        String modelKey = this.getRouteKey();
        return new BpmTaskParam(sname,this.basekey(),modelKey,busKey.toString());
    }
}
