package com.kjq.kjqcommon.service;

import com.kjq.kjqcommon.model.entity.InterfaceInfo;

/**
* @author 86175
* @description 针对表【interface_info】的数据库操作Service
* @createDate 2024-03-10 17:26:34
*/
public interface InnerInterfaceInfoService {

    /**
     * 从数据库中查询接口信息（请求路径，请求方法，请求参数）
     * @param path
     * @param method
     * @return
     */
    InterfaceInfo getInterfaceInfo(String path, String method);
}
