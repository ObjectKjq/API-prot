package com.kjq.project.service.impl.inner;

import com.kjq.kjqcommon.model.entity.InterfaceInfo;
import com.kjq.kjqcommon.model.entity.UserInterfaceInfo;
import com.kjq.kjqcommon.service.InnerUserInterfaceInfoService;
import com.kjq.project.common.ErrorCode;
import com.kjq.project.exception.BusinessException;
import com.kjq.project.service.UserInterfaceInfoService;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

@DubboService
public class InnerUserInterfaceInfoServiceImpl implements InnerUserInterfaceInfoService {

    @Resource
    private UserInterfaceInfoService userInterfaceInfoService;

    /**
     * 修改调用次数
     * @param interfaceInfoId
     * @param userId
     * @return
     */
    @Override
    public boolean invokeCount(long interfaceInfoId, long userId) {

        return userInterfaceInfoService.invokeCount(interfaceInfoId, userId);
    }

    @Override
    public boolean isInterfaceCount(long interfaceInfoId, long userId) {
        //判断是否还有调用次数
        UserInterfaceInfo userInterfaceInfo = userInterfaceInfoService.isInterfaceCount(interfaceInfoId, userId);
        if (userInterfaceInfo == null){
            return true;
        }
        return (userInterfaceInfo.getLeftNum() <= 0);
    }
}
