package com.kjq.project.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.kjq.kjqcommon.model.entity.InterfaceInfo;
import com.kjq.kjqcommon.model.entity.UserInterfaceInfo;

/**
* @author 86175
* @description 针对表【user_interface_info】的数据库操作Service
* @createDate 2024-03-20 16:53:49
*/
public interface UserInterfaceInfoService extends IService<UserInterfaceInfo> {

    void validUserInterfaceInfo(UserInterfaceInfo userInterfaceInfo, boolean add);

    boolean invokeCount(long interfaceInfoId, long userId);

    UserInterfaceInfo isInterfaceCount(long interfaceInfoId, long userId);
}
