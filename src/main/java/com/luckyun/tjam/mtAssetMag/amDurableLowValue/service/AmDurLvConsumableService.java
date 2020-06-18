package com.luckyun.tjam.mtAssetMag.amDurableLowValue.service;

import com.luckyun.base.provider.feign.BaseSysDeptProvider;
import com.luckyun.base.provider.feign.BaseSysUserProvider;
import com.luckyun.core.annotation.TransactionalException;
import com.luckyun.core.data.BaseMapper;
import com.luckyun.core.data.BaseService;
import com.luckyun.core.data.ExcelBatchReceiver;
import com.luckyun.core.data.ExcelBatchSender;
import com.luckyun.core.data.filter.AuthInfo;
import com.luckyun.core.tool.OssPathHelperUtils;
import com.luckyun.model.dept.SysDept;
import com.luckyun.model.user.SysAccount;
import com.luckyun.tjam.mtAssetMag.amDurableLowValue.mapper.AmDurLvConsumableAttMapper;
import com.luckyun.tjam.mtAssetMag.amDurableLowValue.mapper.AmDurLvConsumableMapper;
import com.luckyun.tjam.mtAssetMag.amDurableLowValue.model.AmDurLvConsumable;
import com.luckyun.tjam.mtAssetMag.amDurableLowValue.model.AmDurLvConsumableAtt;
import com.luckyun.tjam.mtAssetMag.amDurableLowValue.param.AmDurLvConsumableParam;
import com.luckyun.tjam.prophaseMag.acceptance.common.DatadicKeys;
import com.luckyun.tjam.prophaseMag.acceptance.helper.BaseServiceHelperImpl;
import com.luckyun.tjam.prophaseMag.maintenance.mapper.AmArcClassMapper;
import com.luckyun.tjam.prophaseMag.maintenance.model.AmArcClass;
import com.luckyun.tjam.prophaseMag.maintenance.service.AmArcClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AmDurLvConsumableService extends BaseService<AmDurLvConsumable> {
    @Autowired
    private AmDurLvConsumableMapper amDurLvConsumableMapper;

    @Autowired
    private AmArcClassMapper amArcClassMapper;

    @Autowired
    private BaseServiceHelperImpl baseServiceHelperImpl;

    @Autowired
    private BaseSysUserProvider baseSysUserProvider;

    @Autowired
    private BaseSysDeptProvider baseSysDeptProvider;

    @Autowired
    private AmDurLvConsumableAttMapper amDurLvConsumableAttMapper;

    @Autowired
    private OssPathHelperUtils ossPathHelperUtils;

    @Autowired
    private AmArcClassService amArcClassService;




    @Override
    public BaseMapper<AmDurLvConsumable> getMapper() {
        return amDurLvConsumableMapper;
    }

    /**
     * 条件查询所有
     * @param condition
     * @return
     */
    public List<AmDurLvConsumable> findAll(AmDurLvConsumableParam condition){
        if (null != condition && condition.getIndocnos() != null && condition.getIndocnos() != ""){
            String[] split = condition.getIndocnos().split(",");
            List<Long> collect = Arrays.stream(split).map(e -> Long.parseLong(e.trim())).collect(Collectors.toList());
            condition.setIndocnoList(collect);
        }
        List<AmDurLvConsumable> list = amDurLvConsumableMapper.findAll(condition);
        if (null != list && !list.isEmpty()){
            for (AmDurLvConsumable amDurLvConsumable:list){
               // 分类名称
                if(null != amDurLvConsumable.getSeqclassno()) {
                    List<AmArcClass> capArcClassList = this.amArcClassMapper.findBySclassno(amDurLvConsumable.getSeqclassno());
                    if(null != capArcClassList && !capArcClassList.isEmpty()) {
                        AmArcClass capArcClass = capArcClassList.get(0);
                        amDurLvConsumable.setSamarcclassnm(capArcClass.getSclassnm());
                    }
                }

                // 所属线路
                if(null != amDurLvConsumable.getIlineid()) {
                    amDurLvConsumable.setSlinenm(baseServiceHelperImpl.getDatadicName(DatadicKeys.CAP_ACCEPTANCE_EQUIP_ILINEID,
                            amDurLvConsumable.getIlineid() != null ? amDurLvConsumable.getIlineid() : ""));
                }
                // 责任人
                if(null != amDurLvConsumable.getIdutyid()) {
                    SysAccount sysAccount1 = this.baseSysUserProvider.findFSysUserByIndocno(amDurLvConsumable.getIdutyid());
                    if(null != sysAccount1) {
                        amDurLvConsumable.setSdutynm(sysAccount1.getSname());
                    }
                }
                // 核算部门
                if(null != amDurLvConsumable.getIaccountdeptid()) {
                    SysDept sysDept = this.baseSysDeptProvider.findByIndocno(amDurLvConsumable.getIaccountdeptid());
                    if(null != sysDept) {
                        amDurLvConsumable.setSaccountdeptnm(sysDept.getSname());
                    }
                }
                //使用人
                if(null != amDurLvConsumable.getIuserid()) {
                    SysAccount sysAccount = this.baseSysUserProvider.findFSysUserByIndocno(amDurLvConsumable.getIuserid());
                    amDurLvConsumable.setSusernm(sysAccount.getSname());
                }
                List<AmDurLvConsumableAtt> consumableAttList = amDurLvConsumableAttMapper.findAllByIlinkno(amDurLvConsumable.getIndocno());
                if (null !=consumableAttList && !consumableAttList.isEmpty()){
                    for (AmDurLvConsumableAtt e1:consumableAttList){
                        AuthInfo authInfo = getAuthInfo();
                        // 图片地址
                        if (null != e1.getSpath()) {
                            e1.setNspath(ossPathHelperUtils.generateDownloadFileNoAuth(authInfo.getSloginid()
                                    , "tjam", e1.getSpath(), e1.getSname()));
                        }
                    }
                    amDurLvConsumable.setConsumableAttList(consumableAttList);
                }
            }
        }
        return list;
    }

    /**
     *  查询明细
     * @param condition
     * @return
     */
    public AmDurLvConsumable findOne(Long indocno){
        AmDurLvConsumable amDurLvConsumable = amDurLvConsumableMapper.findOne(indocno);
        if (null != amDurLvConsumable) {
            // 分类名称
            if(null != amDurLvConsumable.getSeqclassno()) {
                List<AmArcClass> capArcClassList = this.amArcClassMapper.findBySclassno(amDurLvConsumable.getSeqclassno());
                if(null != capArcClassList && !capArcClassList.isEmpty()) {
                    AmArcClass capArcClass = capArcClassList.get(0);
                    amDurLvConsumable.setSamarcclassnm(capArcClass.getSclassnm());
                }
            }

            // 所属线路
            if(null != amDurLvConsumable.getIlineid()) {
                amDurLvConsumable.setSlinenm(baseServiceHelperImpl.getDatadicName(DatadicKeys.CAP_ACCEPTANCE_EQUIP_ILINEID,
                        amDurLvConsumable.getIlineid() != null ? amDurLvConsumable.getIlineid() : ""));
            }
            // 责任人
            if(null != amDurLvConsumable.getIdutyid()) {
                SysAccount sysAccount1 = this.baseSysUserProvider.findFSysUserByIndocno(amDurLvConsumable.getIdutyid());
                if(null != sysAccount1) {
                    amDurLvConsumable.setSdutynm(sysAccount1.getSname());
                }
            }
            // 核算部门
            if(null != amDurLvConsumable.getIaccountdeptid()) {
                SysDept sysDept = this.baseSysDeptProvider.findByIndocno(amDurLvConsumable.getIaccountdeptid());
                if(null != sysDept) {
                    amDurLvConsumable.setSaccountdeptnm(sysDept.getSname());
                }
            }
            //使用人
            if(null != amDurLvConsumable.getIuserid()) {
                SysAccount sysAccount = this.baseSysUserProvider.findFSysUserByIndocno(amDurLvConsumable.getIuserid());
                amDurLvConsumable.setSusernm(sysAccount.getSname());
            }
            List<AmDurLvConsumableAtt> consumableAttList = amDurLvConsumableAttMapper.findAllByIlinkno(amDurLvConsumable.getIndocno());
            if (null !=consumableAttList && !consumableAttList.isEmpty()){
                for (AmDurLvConsumableAtt e1:consumableAttList){
                    AuthInfo authInfo = getAuthInfo();
                    // 图片地址
                    if (null != e1.getSpath()) {
                        e1.setNspath(ossPathHelperUtils.generateDownloadFileNoAuth(authInfo.getSloginid()
                                , "tjam", e1.getSpath(), e1.getSname()));
                    }
                }
                amDurLvConsumable.setConsumableAttList(consumableAttList);
            }
        }
        return amDurLvConsumable;
    }

    /**
     *  修改
     * @param condition
     * @return
     */
    @TransactionalException
    public void updateBasic(AmDurLvConsumable entity){
        if(null != entity.getIamarcclassid()) {
            String byIn = amArcClassService.findByIn(entity.getIamarcclassid());
            entity.setSidcc(byIn);
            String sclassno = amArcClassMapper.findSclassno(entity.getIamarcclassid());
            entity.setSeqclassno(sclassno);
        }
        super.update(entity);
        //修改图片
        handleupdateAttchment(entity);
    }

    public void handleupdateAttchment(AmDurLvConsumable entity){
        //先删除
        List<AmDurLvConsumableAtt> attachmentList = amDurLvConsumableAttMapper.findAllByIlinkno(entity.getIndocno());
        if (null !=attachmentList && !attachmentList.isEmpty()){
            for (AmDurLvConsumableAtt e:attachmentList){
                amDurLvConsumableAttMapper.delete(e);
            }
        }
        //再新增
        List<AmDurLvConsumableAtt> attachmentList1 = entity.getConsumableAttList();
        if (null !=attachmentList1 && !attachmentList1.isEmpty()){
            for (AmDurLvConsumableAtt e:attachmentList1){
                e.setIlinkno(entity.getIndocno());
                amDurLvConsumableAttMapper.insert(e);
            }
        }
    }

    //删除
    @TransactionalException
    public void delOpr(AmDurLvConsumableParam condition){
        List<AmDurLvConsumable> delList = condition.getDelList();
        for (AmDurLvConsumable e:delList){
            super.delete(e);
        }
    }

    /**
     *  导入耐用北京
     *
     * @param batchReceiver
     */
    @TransactionalException
    public ExcelBatchSender importSendCheck(ExcelBatchReceiver<AmDurLvConsumable> batchReceiver) {
        String[] slinenms = {"大兴机场线","公司本部"};
        ExcelBatchSender batchSender = new ExcelBatchSender(1);
        List<AmDurLvConsumable> content = batchReceiver.getContent();
        List<ExcelBatchSender.ExcelBatchSenderError> batchSenderErrorIndexs = new LinkedList<>();
        for (int i = 0; i < content.size(); i++) {
            AmDurLvConsumable amDurLvConsumable = content.get(i);
            if (null == amDurLvConsumable
                    || amDurLvConsumable.getSfname() == null || amDurLvConsumable.getSfname() == ""
                    || amDurLvConsumable.getSlinenm() == null || amDurLvConsumable.getSlinenm() == "" || !Arrays.asList(slinenms).contains(amDurLvConsumable.getSlinenm())
                    || amDurLvConsumable.getSbrandnm() == null || amDurLvConsumable.getSbrandnm() == ""
                    || amDurLvConsumable.getSaccountdeptnm() == null || amDurLvConsumable.getSaccountdeptnm() == ""
                    || amDurLvConsumable.getSspec() == null || amDurLvConsumable.getSspec() == ""
                    || amDurLvConsumable.getSinsarea() == null || amDurLvConsumable.getSinsarea() == ""
                    || amDurLvConsumable.getSinspos() == null || amDurLvConsumable.getSinspos() == ""
                    || amDurLvConsumable.getIprice() == null
            ){
                addErrorIndex(batchSender, batchSenderErrorIndexs, i);
            }else {
                try {
                    switch (amDurLvConsumable.getSlinenm()){
                        case "大兴机场线":
                            amDurLvConsumable.setIlineid(88);
                            amDurLvConsumable.setSlinenm(null);
                            break;
                        case "公司本部":
                            amDurLvConsumable.setIlineid(0);
                            amDurLvConsumable.setSlinenm(null);
                            break;
                    }
                    amDurLvConsumableMapper.insert(amDurLvConsumable);
                } catch (Exception e) {
                    addErrorIndex(batchSender, batchSenderErrorIndexs, i);
                }
            }
        }
        batchSender.setBatchErrorIndex(batchSenderErrorIndexs);
        return batchSender;
    }
    /**
     *  获取导入失败数据
     * @param batchSender
     * @param batchSenderErrorIndexs
     * @param i
     * @return
     */
    private List<ExcelBatchSender.ExcelBatchSenderError> addErrorIndex(ExcelBatchSender batchSender,
                                                                       List<ExcelBatchSender.ExcelBatchSenderError> batchSenderErrorIndexs, Integer i) {
        ExcelBatchSender.ExcelBatchSenderError batchSenderError = batchSender.new ExcelBatchSenderError();
        batchSenderError.setIndex(i);
        batchSenderErrorIndexs.add(batchSenderError);
        return batchSenderErrorIndexs;
    }
}
