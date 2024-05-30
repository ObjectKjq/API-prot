package com.kjq.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kjq.kjqcommon.model.entity.UserInterfaceInfo;
import com.kjq.project.common.ErrorCode;
import com.kjq.project.exception.BusinessException;
import com.kjq.project.mapper.UserInterfaceInfoMapper;
import com.kjq.project.service.UserInterfaceInfoService;
import org.springframework.stereotype.Service;

/**
* @author 86175
* @description 针对表【user_userInterface_info】的数据库操作Service实现
* @createDate 2024-03-20 16:53:49
*/
@Service
public class UserInterfaceInfoServiceImpl extends ServiceImpl<UserInterfaceInfoMapper, UserInterfaceInfo>
    implements UserInterfaceInfoService {

    @Override
    public void validUserInterfaceInfo(UserInterfaceInfo userInterfaceInfo, boolean add) {
        if (userInterfaceInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // todo 创建时，所有参数必须非空
        if (add) {
            if (userInterfaceInfo.getInterfaceInfoId() <= 0) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR,"接口不存在");
            }
        }
    }

    /**
     * 调用接口次数加一
     */
    @Override
    public boolean invokeCount(long interfaceInfoId, long userId){
        // todo 这里需要加一个分布式锁，防止用户一下子放出万次请求
        if (interfaceInfoId <= 0 || userId <= 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        UpdateWrapper<UserInterfaceInfo> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("interfaceInfoId", interfaceInfoId);
        updateWrapper.eq("userId", userId);
        updateWrapper.gt("leftNum","0");
        updateWrapper.setSql("leftNum = leftNum - 1, totalNum = totalNum + 1");
        return this.update(updateWrapper);
    }

    @Override
    public UserInterfaceInfo isInterfaceCount(long interfaceInfoId, long userId) {
        if (interfaceInfoId <= 0 || userId <= 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        QueryWrapper<UserInterfaceInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("interfaceInfoId", interfaceInfoId);
        queryWrapper.eq("userId", userId);
        queryWrapper.eq("status", 0);
        return this.getOne(queryWrapper);
    }
}




