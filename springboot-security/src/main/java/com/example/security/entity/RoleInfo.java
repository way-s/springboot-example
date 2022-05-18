package com.example.security.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author: mxx
 * @Description:
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class RoleInfo implements Serializable {

    private static final long serialVersionUID = 8623277193011945308L;

    /**
     * 角色权限
     */
    private String role;

    private String username;

    /**
     * 角色状态（0正常 1停用）
     */
    private String status;

}
