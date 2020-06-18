package com.luckyun.tjam.mtAssetMag.amAssetLeave.service;

import com.luckyun.base.provider.feign.BaseSysUserProvider;
import com.luckyun.core.annotation.TransactionalException;
import com.luckyun.core.data.BaseMapper;
import com.luckyun.core.data.BaseService;
import com.luckyun.core.data.filter.AuthInfo;
import com.luckyun.core.tool.OssPathHelperUtils;
import com.luckyun.model.user.SysAccount;
import com.luckyun.tjam.mtAssetMag.amAssetLeave.mapper.AmAssetLeaveAttMapper;
import com.luckyun.tjam.mtAssetMag.amAssetLeave.model.AmAssetLeaveAtt;
import com.luckyun.tjam.mtAssetMag.amAssetLeave.param.AmAssetLeaveAttParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class AmAssetLeaveAttService extends BaseService<AmAssetLeaveAtt> {

    @Autowired
    private AmAssetLeaveAttMapper amAssetLeaveAttMapper;

    private OssPathHelperUtils ossPathHelperUtils;

    @Autowired
    private BaseSysUserProvider baseSysUserProvider;

    @Override
    public BaseMapper<AmAssetLeaveAtt> getMapper() {
        return amAssetLeaveAttMapper;
    }

    /***
     *
     * 删除
     *
     */
    @TransactionalException
    public void delOpr(AmAssetLeaveAttParam condition){
        List<AmAssetLeaveAtt> delList = condition.getDelList();
        if (!CollectionUtils.isEmpty(delList)){
            delList.forEach(e->{
                amAssetLeaveAttMapper.delete(e);
            });
        }
    }

    /**
     *  查询明细
     * @param condition
     * @return
     */
    public AmAssetLeaveAtt findOne(Long indocno){
        AmAssetLeaveAtt one = amAssetLeaveAttMapper.findOne(indocno);
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
    public AmAssetLeaveAtt add(AmAssetLeaveAtt entity) {
        super.insert(entity);
        return entity;
    }

    /**
     *修改
     **/
    @TransactionalException
    public void updateBasic(AmAssetLeaveAtt entity) {
        amAssetLeaveAttMapper.update(entity);
    }

}
