package com.luckyun.tjam.mtAssetMag.assetLowValueTransfer.service;

import com.luckyun.base.provider.feign.BaseSysUserProvider;
import com.luckyun.core.annotation.TransactionalException;
import com.luckyun.core.data.BaseMapper;
import com.luckyun.core.data.BaseService;
import com.luckyun.core.data.filter.AuthInfo;
import com.luckyun.core.tool.OssPathHelperUtils;
import com.luckyun.model.user.SysAccount;
import com.luckyun.tjam.mtAssetMag.assetLowValueTransfer.mapper.AmDurLvConsTransferAttMapper;
import com.luckyun.tjam.mtAssetMag.assetLowValueTransfer.model.AmDurLvConsTransferAtt;
import com.luckyun.tjam.mtAssetMag.assetLowValueTransfer.param.AmDurLvConsTransferAttParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class AmDurLvConsTransferAttService extends BaseService<AmDurLvConsTransferAtt> {

    @Autowired
    private AmDurLvConsTransferAttMapper amDurLvConsTransferAttMapper;

    @Autowired
    private OssPathHelperUtils ossPathHelperUtils;

    @Autowired
    private BaseSysUserProvider baseSysUserProvider;


    @Override
    public BaseMapper<AmDurLvConsTransferAtt> getMapper() {
        return amDurLvConsTransferAttMapper;
    }

    /**
     * 条件查询所有
     * @param condition
     * @return
     */
    public List<AmDurLvConsTransferAtt> findAll(AmDurLvConsTransferAttParam condition){
        List<AmDurLvConsTransferAtt> all = amDurLvConsTransferAttMapper.findAll(condition);
        return all;
    }


    /***
     *
     * 删除
     *
     */
    @TransactionalException
    public void delOpr(AmDurLvConsTransferAttParam condition){
        List<AmDurLvConsTransferAtt> delList = condition.getDelList();
        if (!CollectionUtils.isEmpty(delList)){
            delList.forEach(e->{
                amDurLvConsTransferAttMapper.delete(e);
            });
        }
    }

    /**
     *  查询明细
     * @param condition
     * @return
     */
    public AmDurLvConsTransferAtt findOne(Long indocno){
        AmDurLvConsTransferAtt one = amDurLvConsTransferAttMapper.findOne(indocno);
        AuthInfo authInfo = getAuthInfo();
        // 图片地址
        if (null != one.getSpath()) {
            one.setNspath(ossPathHelperUtils.generateDownloadFileNoAuth(authInfo.getSloginid()
                    , "tjam", one.getSpath(), one.getSname()));
        }
        //上传人
        if(null != one.getSregid()) {
            SysAccount sysAccount = this.baseSysUserProvider.findFSysUserByIndocno(one.getSregid());
            one.setSregnm(sysAccount.getSname());
        }
        return one;
    }

    /**
     * 添加
     *
     */
    @TransactionalException
    public AmDurLvConsTransferAtt add(AmDurLvConsTransferAtt entity) {
        super.insert(entity);
        return entity;
    }
    /**
     *修改
     **/
    @TransactionalException
    public void updateBasic(AmDurLvConsTransferAtt entity) {
        amDurLvConsTransferAttMapper.update(entity);
    }

}
