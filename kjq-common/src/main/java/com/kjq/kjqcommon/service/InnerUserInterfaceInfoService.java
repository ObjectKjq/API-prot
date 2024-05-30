package com.kjq.kjqcommon.service;

/**
* @author 86175
* @description 针对表【user_interface_info】的数据库操作Service
* @createDate 2024-03-20 16:53:49
*/
public interface InnerUserInterfaceInfoService{

    /**
     * 修改调用次数
     * @param interfaceInfoId
     * @param userId
     * @return
     */
    boolean invokeCount(long interfaceInfoId, long userId);

    /**
     *
     */
    boolean isInterfaceCount(long interfaceInfoId, long userId);

}
