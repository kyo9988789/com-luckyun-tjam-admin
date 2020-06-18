package com.luckyun.tjam.mtAssetMag.assetLowValueMag.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.luckyun.base.provider.feign.BaseSysUserProvider;
import com.luckyun.core.annotation.TransactionalException;
import com.luckyun.core.data.BaseMapper;
import com.luckyun.core.data.BaseService;
import com.luckyun.core.data.filter.AuthInfo;
import com.luckyun.core.tool.OssPathHelperUtils;
import com.luckyun.model.user.SysAccount;
import com.luckyun.tjam.mtAssetMag.assetLowValueMag.mapper.AmDurLvConsChangeAttMapper;
import com.luckyun.tjam.mtAssetMag.assetLowValueMag.model.AmDurLvConsChangeAtt;
import com.luckyun.tjam.mtAssetMag.assetLowValueMag.param.AmDurLvConsChangeAttParam;

@Service
public class AmDurLvConsChangeAttService extends BaseService<AmDurLvConsChangeAtt> {

    @Autowired
    private AmDurLvConsChangeAttMapper amDurLvConsChangeAttMapper;

    @Autowired
    private OssPathHelperUtils ossPathHelperUtils;

    @Autowired
    private BaseSysUserProvider baseSysUserProvider;

    @Override
    public BaseMapper<AmDurLvConsChangeAtt> getMapper() {
        return amDurLvConsChangeAttMapper;
    }
    /**
     * 条件查询所有
     * @param condition
     * @return
     */
    public List<AmDurLvConsChangeAtt> findAll(AmDurLvConsChangeAttParam condition){
        List<AmDurLvConsChangeAtt> all = amDurLvConsChangeAttMapper.findAll(condition);
        return all;
    }


    /***
     *
     * 删除
     *
     */
    @TransactionalException
    public void delOpr(AmDurLvConsChangeAttParam condition){
        List<AmDurLvConsChangeAtt> delList = condition.getDelList();
        if (!CollectionUtils.isEmpty(delList)){
            delList.forEach(e->{
                amDurLvConsChangeAttMapper.delete(e);
            });
        }
    }

    /**
     *  查询明细
     * @param condition
     * @return
     */
    public AmDurLvConsChangeAtt findOne(Long indocno){
        AmDurLvConsChangeAtt one = amDurLvConsChangeAttMapper.findOne(indocno);
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
    public AmDurLvConsChangeAtt add(AmDurLvConsChangeAtt entity) {
        super.insert(entity);
        return entity;
    }
    /**
     *修改
     **/
    @TransactionalException
    public void updateBasic(AmDurLvConsChangeAtt entity) {
        amDurLvConsChangeAttMapper.update(entity);
    }

}
