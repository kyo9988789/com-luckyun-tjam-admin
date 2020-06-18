package com.luckyun.tjam.mtAssetMag.amToolsag.service;


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
import com.luckyun.tjam.mtAssetMag.amToolsag.mapper.AmToolsAttachmentMapper;
import com.luckyun.tjam.mtAssetMag.amToolsag.mapper.AmToolsMapper;
import com.luckyun.tjam.mtAssetMag.amToolsag.model.AmTools;
import com.luckyun.tjam.mtAssetMag.amToolsag.model.AmToolsAttachment;
import com.luckyun.tjam.mtAssetMag.amToolsag.param.AmToolsParam;
import com.luckyun.tjam.prophaseMag.acceptance.common.DatadicKeys;
import com.luckyun.tjam.prophaseMag.acceptance.helper.BaseServiceHelperImpl;
import com.luckyun.tjam.prophaseMag.maintenance.mapper.AmArcClassMapper;
import com.luckyun.tjam.prophaseMag.maintenance.model.AmArcClass;
import com.luckyun.tjam.prophaseMag.maintenance.service.AmArcClassService;

@Service
public class AmToolsService extends BaseService<AmTools> {

    @Autowired
    private AmToolsMapper amToolsMapper;

    @Autowired
    private BaseServiceHelperImpl baseServiceHelperImpl;

    @Autowired
    private BaseSysUserProvider baseSysUserProvider;

    @Autowired
    private BaseSysDeptProvider baseSysDeptProvider;

    @Autowired
    private AmToolsAttachmentMapper amToolsAttachmentMapper;

    @Autowired
    private AmArcClassMapper amArcClassMapper;

    @Autowired
    private AmArcClassService amArcClassService;

    @Autowired
    private OssPathHelperUtils ossPathHelperUtils;

    @Override
    public BaseMapper<AmTools> getMapper() {
        return amToolsMapper;
    }

    /**
     * 条件查询所有
     * @param condition
     * @return
     */
    public List<AmTools> findAll(AmToolsParam condition){
        if (null != condition && condition.getIndocnos() != null && condition.getIndocnos() != ""){
            String[] split = condition.getIndocnos().split(",");
            List<Long> collect = Arrays.stream(split).map(e -> Long.parseLong(e.trim())).collect(Collectors.toList());
            condition.setIndocnoList(collect);
        }
        List<AmTools> list = amToolsMapper.findAll(condition);
        if (null != list && !list.isEmpty()){
            for(AmTools e:list){
                // 分类名称
                if(null != e.getSeqclassno()) {
                    List<AmArcClass> capArcClassList = this.amArcClassMapper.findBySclassno(e.getSeqclassno());
                    if(null != capArcClassList && !capArcClassList.isEmpty()) {
                        AmArcClass capArcClass = capArcClassList.get(0);
                        e.setSamarcclassnm(capArcClass.getSclassnm());
                    }
                }

                // 所属线路
                if(null != e.getIlineid()) {
                    e.setSlinenm(baseServiceHelperImpl.getDatadicName(DatadicKeys.CAP_ACCEPTANCE_EQUIP_ILINEID,
                            e.getIlineid() != null ? e.getIlineid() : ""));
                }
                // 责任人
                if(null != e.getIdutyid()) {
                    SysAccount sysAccount1 = this.baseSysUserProvider.findFSysUserByIndocno(e.getIdutyid());
                    if(null != sysAccount1) {
                        e.setSdutynm(sysAccount1.getSname());
                    }
                }
                // 管理部门
                if(null != e.getImanagedeptid()) {
                    SysDept sysDept = this.baseSysDeptProvider.findByIndocno(e.getImanagedeptid());
                    if(null != sysDept) {
                        e.setSmanagedeptnm(sysDept.getSname());
                    }
                }
                //使用人
                if(null != e.getIuserid()) {
                    SysAccount sysAccount = this.baseSysUserProvider.findFSysUserByIndocno(e.getIuserid());
                    e.setSusernm(sysAccount.getSname());
                }
                List<AmToolsAttachment> attachmentList = amToolsAttachmentMapper.findAllByIlinkno(e.getIndocno());
                if (null != attachmentList && !attachmentList.isEmpty()){
                    for (AmToolsAttachment e1:attachmentList){
                        AuthInfo authInfo = getAuthInfo();
                        // 图片地址
                        if (null != e1.getSpath()) {
                            e1.setNspath(ossPathHelperUtils.generateDownloadFileNoAuth(authInfo.getSloginid()
                                    , "tjam", e1.getSpath(), e1.getSname()));
                        }
                    }
                }
                e.setAttachmentList(attachmentList);
            }
        }
        return list;
    }


    /**
     *  查询明细
     * @param condition
     * @return
     */
    public AmTools findOne(Long indocno){
        AmTools e = amToolsMapper.findOne(indocno);
        if (null != e) {
            // 分类名称
            if(null != e.getSeqclassno()) {
                List<AmArcClass> capArcClassList = this.amArcClassMapper.findBySclassno(e.getSeqclassno());
                if(null != capArcClassList && !capArcClassList.isEmpty()) {
                    AmArcClass capArcClass = capArcClassList.get(0);
                    e.setSamarcclassnm(capArcClass.getSclassnm());
                }
            }
            // 所属线路
            if(null != e.getIlineid()) {
                e.setSlinenm(baseServiceHelperImpl.getDatadicName(DatadicKeys.CAP_ACCEPTANCE_EQUIP_ILINEID,
                        e.getIlineid() != null ? e.getIlineid() : ""));
            }
            // 责任人
            if(null != e.getIdutyid()) {
                SysAccount sysAccount1 = this.baseSysUserProvider.findFSysUserByIndocno(e.getIdutyid());
                if(null != sysAccount1) {
                    e.setSdutynm(sysAccount1.getSname());
                }
            }
            // 管理部门
            if(null != e.getImanagedeptid()) {
                SysDept sysDept = this.baseSysDeptProvider.findByIndocno(e.getImanagedeptid());
                if(null != sysDept) {
                    e.setSmanagedeptnm(sysDept.getSname());
                }
            }
            //使用人
            if(null != e.getIuserid()) {
                SysAccount sysAccount = this.baseSysUserProvider.findFSysUserByIndocno(e.getIuserid());
                e.setSusernm(sysAccount.getSname());
            }

            List<AmToolsAttachment> attachmentList = amToolsAttachmentMapper.findAllByIlinkno(e.getIndocno());
            if (null != attachmentList && !attachmentList.isEmpty()){
                for (AmToolsAttachment e1:attachmentList){
                    AuthInfo authInfo = getAuthInfo();
                    // 图片地址
                    if (null != e1.getSpath()) {
                        e1.setNspath(ossPathHelperUtils.generateDownloadFileNoAuth(authInfo.getSloginid()
                                , "tjam", e1.getSpath(), e1.getSname()));
                    }
                }
            }
            e.setAttachmentList(attachmentList);
        }
        return e;
    }

    /**
     *  修改
     * @param condition
     * @return
     */
    @TransactionalException
    public void updateBasic(AmTools entity){
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
    public void handleupdateAttchment(AmTools entity){
        //先删除
        List<AmToolsAttachment> attachmentList = amToolsAttachmentMapper.findAllByIlinkno(entity.getIndocno());
        if (null !=attachmentList && !attachmentList.isEmpty()){
            for (AmToolsAttachment e:attachmentList){
                amToolsAttachmentMapper.delete(e);
            }
        }
        //再新增
        List<AmToolsAttachment> attachmentList1 = entity.getAttachmentList();
        if (null !=attachmentList1 && !attachmentList1.isEmpty()){
            for (AmToolsAttachment e:attachmentList1){
                e.setIlinkno(entity.getIndocno());
                amToolsAttachmentMapper.insert(e);
            }
        }

    }

    //删除
    @TransactionalException
    public void delOpr(AmToolsParam condition){
        List<AmTools> delList = condition.getDelList();
        if (null != delList && !delList.isEmpty()){
            for (AmTools e:delList){
                List<AmToolsAttachment> attachment = amToolsAttachmentMapper.findAllByIlinkno(e.getIndocno());
                if (null !=attachment && !attachment.isEmpty()){
                    for (AmToolsAttachment e1:attachment){
                        amToolsAttachmentMapper.delete(e1);
                    }
                }
                super.delete(e);
            }
        }
    }

    /**
     *  导入检验报告(施工监测单位)
     *
     * @param batchReceiver
     */
    @SuppressWarnings("unused")
    @TransactionalException
    public ExcelBatchSender importSendCheck(ExcelBatchReceiver<AmTools> batchReceiver) {
        String[] sstrans = {"未核验","同意接收","拒绝接收"};
        String[] slinenms = {"大兴机场线","公司本部"};
        ExcelBatchSender batchSender = new ExcelBatchSender(1);
        List<AmTools> content = batchReceiver.getContent();
        List<ExcelBatchSender.ExcelBatchSenderError> batchSenderErrorIndexs = new LinkedList<>();
        for (int i = 0; i < content.size(); i++) {
            AmTools amTools = content.get(i);
            if (null == amTools
                    || amTools.getSfname() == null || amTools.getSfname() == ""
                    || amTools.getSlinenm() == null || amTools.getSlinenm() == "" || !Arrays.asList(slinenms).contains(amTools.getSlinenm())
                    || amTools.getSspec() == null || amTools.getSspec() == ""
                    || amTools.getSmajornm() == null
            ){
                addErrorIndex(batchSender, batchSenderErrorIndexs, i);
            }else {
                try {
                    switch (amTools.getSlinenm()){
                        case "大兴机场线":
                            amTools.setIlineid(88);
                            amTools.setSlinenm(null);
                            break;
                        case "公司本部":
                            amTools.setIlineid(0);
                            amTools.setSlinenm(null);
                            break;
                    }
                    amToolsMapper.insert(amTools);
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
