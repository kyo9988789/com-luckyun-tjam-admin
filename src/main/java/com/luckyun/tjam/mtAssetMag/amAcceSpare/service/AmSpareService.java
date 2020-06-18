package com.luckyun.tjam.mtAssetMag.amAcceSpare.service;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.luckyun.tjam.mtAssetMag.amAcceSpare.mapper.AmSpareAttachmentMapper;
import com.luckyun.tjam.mtAssetMag.amAcceSpare.mapper.AmSpareMapper;
import com.luckyun.tjam.mtAssetMag.amAcceSpare.model.AmSpare;
import com.luckyun.tjam.mtAssetMag.amAcceSpare.model.AmSpareAttachment;
import com.luckyun.tjam.mtAssetMag.amAcceSpare.param.AmSpareParam;
import com.luckyun.tjam.prophaseMag.acceptance.common.DatadicKeys;
import com.luckyun.tjam.prophaseMag.acceptance.helper.BaseServiceHelperImpl;
import com.luckyun.tjam.prophaseMag.maintenance.mapper.AmArcClassMapper;
import com.luckyun.tjam.prophaseMag.maintenance.model.AmArcClass;
import com.luckyun.tjam.prophaseMag.maintenance.service.AmArcClassService;

@Service
public class AmSpareService extends BaseService<AmSpare> {

    @Autowired
    private AmSpareMapper amSpareMapper;

    @Autowired
    private BaseServiceHelperImpl baseServiceHelperImpl;

    @Autowired
    private BaseSysUserProvider baseSysUserProvider;

    @Autowired
    private BaseSysDeptProvider baseSysDeptProvider;

    @Autowired
    private AmSpareAttachmentMapper amSpareAttachmentMapper;

    @Autowired
    private AmArcClassMapper amArcClassMapper;

    @Autowired
    private AmArcClassService amArcClassService;

    @Autowired
    private OssPathHelperUtils ossPathHelperUtils;

    @Override
    public BaseMapper<AmSpare> getMapper() {
        return amSpareMapper;
    }

    /**
     * 条件查询所有
     * @param condition
     * @return
     */
    public List<AmSpare> findAll(AmSpareParam condition){
        if (null != condition && condition.getIndocnos() != null && condition.getIndocnos() != ""){
            String[] split = condition.getIndocnos().split(",");
            List<Long> collect = Arrays.stream(split).map(e -> Long.parseLong(e.trim())).collect(Collectors.toList());
            condition.setIndocnoList(collect);
        }
        List<AmSpare> list = amSpareMapper.findAll(condition);
        if (null != list && !list.isEmpty()){
            for (AmSpare amSpare:list){
                // 分类名称
                if(null != amSpare.getSeqclassno()) {
                    List<AmArcClass> capArcClassList = this.amArcClassMapper.findBySclassno(amSpare.getSeqclassno());
                    if(null != capArcClassList && !capArcClassList.isEmpty()) {
                        AmArcClass capArcClass = capArcClassList.get(0);
                        amSpare.setSamarcclassnm(capArcClass.getSclassnm());
                    }
                }

                // 所属线路
                if(null != amSpare.getIlineid()) {
                    amSpare.setSlinenm(baseServiceHelperImpl.getDatadicName(DatadicKeys.CAP_ACCEPTANCE_EQUIP_ILINEID,
                            amSpare.getIlineid() != null ? amSpare.getIlineid() : ""));
                }
                // 责任人
                if(null != amSpare.getIdutyid()) {
                    SysAccount sysAccount1 = this.baseSysUserProvider.findFSysUserByIndocno(amSpare.getIdutyid());
                    if(null != sysAccount1) {
                        amSpare.setSdutynm(sysAccount1.getSname());
                    }
                }
                // 管理部门
                if(null != amSpare.getImanagedeptid()) {
                    SysDept sysDept = this.baseSysDeptProvider.findByIndocno(amSpare.getImanagedeptid());
                    if(null != sysDept) {
                        amSpare.setSmanagedeptnm(sysDept.getSname());
                    }
                }
                //使用人
                if(null != amSpare.getIuserid()) {
                    SysAccount sysAccount = this.baseSysUserProvider.findFSysUserByIndocno(amSpare.getIuserid());
                    amSpare.setSusernm(sysAccount.getSname());
                }
                List<AmSpareAttachment> sparementList = amSpareAttachmentMapper.findAllByIlinkno(amSpare.getIndocno());
                if (null != sparementList && !sparementList.isEmpty()){
                    for (AmSpareAttachment e1:sparementList){
                        AuthInfo authInfo = getAuthInfo();
                        // 图片地址
                        if (null != e1.getSpath()) {
                            e1.setNspath(ossPathHelperUtils.generateDownloadFileNoAuth(authInfo.getSloginid()
                                    , "tjam", e1.getSpath(), e1.getSname()));
                        }
                    }
                }
                amSpare.setSparementList(sparementList);
            }
        }
        return list;
    }

    /**
     *  查询明细
     * @param condition
     * @return
     */
    public AmSpare findOne(Long indocno) {
        AmSpare amSpare = amSpareMapper.findOne(indocno);
        if (null != amSpare) {
            // 分类名称
            if(null != amSpare.getSeqclassno()) {
                List<AmArcClass> capArcClassList = this.amArcClassMapper.findBySclassno(amSpare.getSeqclassno());
                if(null != capArcClassList && !capArcClassList.isEmpty()) {
                    AmArcClass capArcClass = capArcClassList.get(0);
                    amSpare.setSamarcclassnm(capArcClass.getSclassnm());
                }
            }
            // 所属线路
            if (null != amSpare.getIlineid()) {
                amSpare.setSlinenm(baseServiceHelperImpl.getDatadicName(DatadicKeys.CAP_ACCEPTANCE_EQUIP_ILINEID,
                        amSpare.getIlineid() != null ? amSpare.getIlineid() : ""));
            }
            // 责任人
            if (null != amSpare.getIdutyid()) {
                SysAccount sysAccount1 = this.baseSysUserProvider.findFSysUserByIndocno(amSpare.getIdutyid());
                if (null != sysAccount1) {
                    amSpare.setSdutynm(sysAccount1.getSname());
                }
            }
            // 管理部门
            if (null != amSpare.getImanagedeptid()) {
                SysDept sysDept = this.baseSysDeptProvider.findByIndocno(amSpare.getImanagedeptid());
                if (null != sysDept) {
                    amSpare.setSmanagedeptnm(sysDept.getSname());
                }
            }
            //使用人
            if (null != amSpare.getIuserid()) {
                SysAccount sysAccount = this.baseSysUserProvider.findFSysUserByIndocno(amSpare.getIuserid());
                amSpare.setSusernm(sysAccount.getSname());
            }
            List<AmSpareAttachment> sparementList = amSpareAttachmentMapper.findAllByIlinkno(amSpare.getIndocno());
            if (null != sparementList && !sparementList.isEmpty()) {
                for (AmSpareAttachment e1 : sparementList) {
                    AuthInfo authInfo = getAuthInfo();
                    // 图片地址
                    if (null != e1.getSpath()) {
                        e1.setNspath(ossPathHelperUtils.generateDownloadFileNoAuth(authInfo.getSloginid()
                                , "tjam", e1.getSpath(), e1.getSname()));
                    }
                }
            }
            amSpare.setSparementList(sparementList);
        }
        return amSpare;
    }

    /**
     *  修改
     * @param condition
     * @return
     */
    @TransactionalException
    public void updateBasic(AmSpare entity){
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
    public void handleupdateAttchment(AmSpare entity){
        //先删除
        List<AmSpareAttachment> attachmentList = amSpareAttachmentMapper.findAllByIlinkno(entity.getIndocno());
        if (null !=attachmentList && !attachmentList.isEmpty()){
            for (AmSpareAttachment e:attachmentList){
                amSpareAttachmentMapper.delete(e);
            }
        }
        //再新增
        List<AmSpareAttachment> attachmentList1 = entity.getSparementList();
        if (null !=attachmentList1 && !attachmentList1.isEmpty()){
            for (AmSpareAttachment e:attachmentList1){
                e.setIlinkno(entity.getIndocno());
                amSpareAttachmentMapper.insert(e);
            }
        }

    }

    //删除
    @TransactionalException
    public void delOpr(AmSpareParam condition){
        List<AmSpare> delList = condition.getDelList();
        if (null != delList && !delList.isEmpty()){
            for (AmSpare e:delList){
                super.delete(e);
            }
        }
    }

    /**
     *  导入备品北京
     *
     * @param batchReceiver
     */
    @TransactionalException
    public ExcelBatchSender importSendCheck(ExcelBatchReceiver<AmSpare> batchReceiver) {
        String[] slinenms = {"大兴机场线","公司本部"};
        ExcelBatchSender batchSender = new ExcelBatchSender(1);
        List<AmSpare> content = batchReceiver.getContent();
        List<ExcelBatchSender.ExcelBatchSenderError> batchSenderErrorIndexs = new LinkedList<>();
        for (int i = 0; i < content.size(); i++) {
            AmSpare amSpare = content.get(i);
            if (null == amSpare
                    || amSpare.getSfname() == null || amSpare.getSfname() == ""
                    || amSpare.getSlinenm() == null || amSpare.getSlinenm() == "" || !Arrays.asList(slinenms).contains(amSpare.getSlinenm())
                    || amSpare.getSmajornm() == null || amSpare.getSmajornm() == ""
                    || amSpare.getSspec() == null || amSpare.getSspec() == ""
                    || amSpare.getIprice() == null
            ){
                addErrorIndex(batchSender, batchSenderErrorIndexs, i);
            }else {
                try {
                    switch (amSpare.getSlinenm()){
                        case "大兴机场线":
                            amSpare.setIlineid(88);
                            amSpare.setSlinenm(null);
                            break;
                        case "公司本部":
                            amSpare.setIlineid(0);
                            amSpare.setSlinenm(null);
                            break;
                    }
                    amSpareMapper.insert(amSpare);
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
