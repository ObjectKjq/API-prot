package com.kjq.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kjq.kjqcommon.model.entity.InterfaceInfo;


/**
* @author 86175
* @description 针对表【interface_info】的数据库操作Service
* @createDate 2024-03-10 17:26:34
*/
public interface InterfaceInfoService extends IService<InterfaceInfo> {

    /**
     * 校验
     *
     * @param interfaceInfo
     * @param add 是否为创建校验
     */
    void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add);
}
