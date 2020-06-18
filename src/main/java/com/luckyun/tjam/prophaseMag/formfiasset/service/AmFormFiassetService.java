package com.luckyun.tjam.prophaseMag.formfiasset.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.luckyun.tjam.prophaseMag.formfiasset.bpm.BpmAmFormFiassetService;
import com.luckyun.tjam.prophaseMag.verification.bpm.BpmAmWorthAppmtBatchService;
import com.luckyun.tjam.prophaseMag.verification.bpm.BpmTaskParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.luckyun.base.provider.feign.BaseSysDeptProvider;
import com.luckyun.base.provider.feign.BaseSysUserProvider;
import com.luckyun.core.data.BaseMapper;
import com.luckyun.core.data.BaseService;
import com.luckyun.model.user.SysAccount;
import com.luckyun.tjam.prophaseMag.acceptance.common.DatadicKeys;
import com.luckyun.tjam.prophaseMag.acceptance.helper.BaseServiceHelperImpl;
import com.luckyun.tjam.prophaseMag.formfiasset.entity.AmFormFiasset;
import com.luckyun.tjam.prophaseMag.formfiasset.entity.AmFormFiassetDt;
import com.luckyun.tjam.prophaseMag.formfiasset.entity.AmFormFiassetDtComp;
import com.luckyun.tjam.prophaseMag.formfiasset.mapper.AmFormFiassetDtCompMapper;
import com.luckyun.tjam.prophaseMag.formfiasset.mapper.AmFormFiassetDtMapper;
import com.luckyun.tjam.prophaseMag.formfiasset.mapper.AmFormFiassetMapper;
import com.luckyun.tjam.prophaseMag.formfiasset.param.AmFormFiassetParam;
import com.luckyun.tjam.prophaseMag.maintenance.mapper.AmAcceptanceFiassetMapper;
import com.luckyun.tjam.prophaseMag.maintenance.model.AmAcceptanceFiasset;

/**
 * Created by Administrator on 2020/4/8.
 */
@Service
public class AmFormFiassetService extends BaseService<AmFormFiasset>{

    @Autowired
    private AmFormFiassetMapper amFormFiassetMapper;

    @Autowired
    private AmFormFiassetDtMapper amFormFiassetDtMapper;

    @Autowired
    private AmFormFiassetDtCompMapper amFormFiassetDtCompMapper;

    @Autowired
    private BaseServiceHelperImpl baseServiceHelper;

    @Autowired
    private BaseSysUserProvider baseSysUserProvider;

    @Autowired
    private BaseSysDeptProvider baseSysDeptProvider;

    @Autowired
    private AmAcceptanceFiassetMapper amAcceptanceFiassetMapper;

    @Autowired
    private BpmAmFormFiassetService bpmAmFormFiassetService;

    @Autowired
    private BpmAmWorthAppmtBatchService bpmAmWorthAppmtBatchService;

    @Override
    public BaseMapper<AmFormFiasset> getMapper() {
        return amFormFiassetMapper;
    }

    @Transactional
    public AmFormFiasset add(AmFormFiasset amFormFiasset){
        getCode(amFormFiasset);
        SysAccount account = baseSysUserProvider.findFSysUserByIndocno(getAuthInfo().getIndocno());
        amFormFiasset.setIdeptid(account != null?account.getSysUserInfo().getIdeptid():null);
        super.insert(amFormFiasset);
        BpmTaskParam bpmParams = bpmAmFormFiassetService.createBpmParams(new SimpleDateFormat("yyyy-MM-dd").format(new Date())
                + "形成固定资产申请-" + baseSysUserProvider.findFSysUserByIndocno(getAuthInfo().getIndocno()).getSname(), amFormFiasset.getIndocno());
        amFormFiasset.setBpmTaskParam(bpmParams);
        return amFormFiasset;
    }

    @Transactional
    public AmFormFiassetParam delete(AmFormFiassetParam amFormFiassetParam){
        List<AmFormFiasset> delList = amFormFiassetParam.getDelList();
        if (!CollectionUtils.isEmpty(delList)){
            delList.forEach(e->{
                amFormFiassetMapper.delete(e);
            });
        }
        return amFormFiassetParam;
    }

    @Transactional
    public AmFormFiasset updatet(AmFormFiasset amFormFiasset){
        amFormFiassetMapper.update(amFormFiasset);
        BpmTaskParam bpmParams = bpmAmFormFiassetService.createBpmParams(new SimpleDateFormat("yyyy-MM-dd").format(new Date())
                + "形成固定资产申请-" + baseSysUserProvider.findFSysUserByIndocno(getAuthInfo().getIndocno()).getSname(), amFormFiasset.getIndocno());
        amFormFiasset.setBpmTaskParam(bpmParams);
        return amFormFiasset;
    }

    public List<AmFormFiasset> findAll(AmFormFiassetParam condition){
        List<AmFormFiasset> amFormFiassetList = amFormFiassetMapper.findAll(condition);
        if (!CollectionUtils.isEmpty(amFormFiassetList)){
            amFormFiassetList.forEach(e->{
                this.AmformFiassetUtil(e);
            });
        }
        return amFormFiassetList;
    }

    public AmFormFiasset findOne(Long indocno){
        AmFormFiasset amFormFiasset = amFormFiassetMapper.findOne(indocno);
        this.AmformFiassetUtil(amFormFiasset);
        BpmTaskParam bpmParams = bpmAmWorthAppmtBatchService.createBpmParams(new SimpleDateFormat("yyyy-MM-dd").format(new Date())
                + "资产价值分摊申请-" + baseSysUserProvider.findFSysUserByIndocno(getAuthInfo().getIndocno()).getSname(), amFormFiasset.getIndocno());
        amFormFiasset.setBpmTaskParam(bpmParams);
        return amFormFiasset;
    }

    //AmFormFiasset属性翻译
    private void AmformFiassetUtil(AmFormFiasset amFormFiasset){
        amFormFiasset.setSaddtype(baseServiceHelper.getDatadicName(DatadicKeys.CAP_VERIFICATION_PLAN_IADDTYPE,
                amFormFiasset.getIaddtype() != null?amFormFiasset.getIaddtype():""));
        amFormFiasset.setSlinenm(baseServiceHelper.getDatadicName(DatadicKeys.CAP_ACCEPTANCE_EQUIP_ILINEID,
                amFormFiasset.getIlineid() != null ?amFormFiasset.getIlineid():""));
        if (amFormFiasset.getIdeptid() != null && baseSysDeptProvider.findByIndocno(amFormFiasset.getIdeptid()) != null){
            amFormFiasset.setSdeptnm(baseSysDeptProvider.findByIndocno(amFormFiasset.getIdeptid()).getSname());
        }
        if (amFormFiasset.getSregid() != null && baseSysUserProvider.findFSysUserByIndocno(amFormFiasset.getSregid()) != null){
            amFormFiasset.setSregnm(baseSysUserProvider.findFSysUserByIndocno(amFormFiasset.getSregid()).getSname());
        }
        amFormFiasset.setSapprovalstate(baseServiceHelper.getDatadicName(DatadicKeys.BPM_IAPPROVALSTATE,
                amFormFiasset.getIapprovalstate() != null ? amFormFiasset.getIapprovalstate():""));
    }

    //形成固定资产清单列表添加
    public void AddAmFormFiassetDt(AmFormFiasset amFormFiasset){
        List<AmFormFiassetDt> amFormFiassetDtList = amFormFiasset.getAmFormFiassetDtList();
        if (!CollectionUtils.isEmpty(amFormFiassetDtList)){
            amFormFiassetDtList.forEach(e->{
                AmAcceptanceFiasset one = amAcceptanceFiassetMapper.findOne(e.getIndocno());
                BeanUtils.copyProperties(one,e);
                e.setIlinkno(amFormFiasset.getIndocno());
                e.setIndocno(null);
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


    /**
     * 获取当天申请单号:如果该数据为当天第一条数据，则最后三位流水号初始化；否则找到最大主键所在的编号，最后三位流水号+1
     * @param entity
     * @return
     */
    private String getCode(AmFormFiasset entity) {
        String field = new SimpleDateFormat("yyyyMMdd").format(new Date());
            Long maxIndocno = this.amFormFiassetMapper.findByDregt();
            if(maxIndocno == null) {
                entity.setSfcode("XCGZ-" + field  + "-001");
            }else {
                AmFormFiasset ua = this.amFormFiassetMapper.findOne(maxIndocno);
                String scode = (ua.getSfcode()).substring((ua.getSfcode()).length()-3);
                Long code = (Long.valueOf(scode)+1);
                if(code>=1 && code<10) {
                    entity.setSfcode("XCGZ-" + field + "-00" + code.toString());
                }
                if(code>=10 && code<100) {
                    entity.setSfcode("XCGZ-" + field + "-0" + code.toString());
                }
                if(code>=100 && code<=999) {
                    entity.setSfcode("XCGZ-" + field + "-" + code.toString());
                }
                if(code>999) {
                    entity.setSfcode("XCGZ-" + field + "-001");
                }
            }
        return null;
    }
}
