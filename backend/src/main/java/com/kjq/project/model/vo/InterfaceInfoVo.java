package com.kjq.project.model.vo;

import com.kjq.kjqcommon.model.entity.InterfaceInfo;
import lombok.Data;

@Data
public class InterfaceInfoVo extends InterfaceInfo {
    /**
     * 调用次数
     */
    private Integer totalNum;

    private static final long serialVersionUID = 1L;
}
