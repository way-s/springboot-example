package com.example.security.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: mxx
 * @Description:
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1415202339860723509L;

    private Long id;

    private String username;

    private String account;

    private long exp;

    private long iat;
}
