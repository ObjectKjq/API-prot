package com.kjq.kjqinterface.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户
 *
 * @TableName user
 */
@Data
public class User implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 用户昵称
     */
    private String name;

    private static final long serialVersionUID = 1L;
}