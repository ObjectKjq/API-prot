package com.kjq.project.model.dto.userinterfaceinfo;

import com.kjq.project.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 查询请求
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserInterfaceInfoQueryRequest extends PageRequest implements Serializable {

    /**
     * 主键
     */
    private Long id;

    /**
     * 调用用户
     */
    private Long userId;

    /**
     * 接口
     */
    private Long interfaceInfoId;

    /**
     * 调用次数
     */
    private Integer totalNum;

    /**
     * 剩余次数
     */
    private Integer leftNum;

    /**
     * 状态int0-正常，1-禁用
     */
    private Integer status;
    private static final long serialVersionUID = 1L;
}