package com.kjq.project.common;

import lombok.Data;

import java.io.Serializable;

/**
 * id封装
 *
 * @author yupi
 */
@Data
public class IdRequest implements Serializable {
    /**
     * id
     */
    private Long id;

    private static final long serialVersionUID = 1L;
}