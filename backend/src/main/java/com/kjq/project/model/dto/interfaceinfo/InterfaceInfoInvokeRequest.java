package com.kjq.project.model.dto.interfaceinfo;

import lombok.Data;

import java.io.Serializable;

/**
 * 更新请求
 *
 * @author
 * @from
 */
@Data
public class InterfaceInfoInvokeRequest implements Serializable {

    /**
     * 调用接口的id
     */
    private Long id;

    /**
     * 用户发送的请参数
     * [{"name":"name","type":"string"}]
     */
    private String userRequestParams;

    private static final long serialVersionUID = 1L;
}