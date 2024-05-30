package com.kjq.kjqcommon.service;

import com.kjq.kjqcommon.model.entity.User;

/**
 * 用户服务
 *
 * @author yupi
 */
public interface InnerUserService {

    /**
     * 获取是那用户调用的
     * @param accessKey
     * @return
     */
    User getInvokeUser(String accessKey);
}
