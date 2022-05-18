package com.example.security.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: mxx
 * @Description: 改实体提供用户信息，SpringSecurity用户信息详情，当UserDetails不满足要求时，可以自定义字段
 */
@Data
@Accessors(chain = true)
public class UserDetail implements UserDetails, Serializable {

    private static final long serialVersionUID = 3481171701620828980L;

    /**
     * 自定需要 SecurityContextHolder保存的数据
     */

    private String account;

    private String password;

    private List<RoleInfo> roles;

    /**
     * 返回当前用户信息的权限
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(list -> new SimpleGrantedAuthority(list.getRole()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.account;
    }

    /**
     * 账户是否没过期
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 账户是否没被锁定
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 账户凭据是否没过期
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 账户是否启用
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
