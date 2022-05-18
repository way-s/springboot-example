package com.example.security.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: mxx
 * @Description:
 */
@Data
public class LoginVo implements Serializable {
    private static final long serialVersionUID = 152125790795839605L;

    private String account;

    private String password;

}
