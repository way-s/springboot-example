package com.example.security.service.impl;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.common.service.ISysRoleService;
import com.example.common.service.IUserInfoService;
import com.example.entity.pojo.SysRole;
import com.example.entity.pojo.UserInfo;
import com.example.security.entity.RoleInfo;
import com.example.security.entity.UserDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: mxx
 * @Description:
 */
@Slf4j
@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private IUserInfoService userInfoService;

    @Resource
    private ISysRoleService sysRoleService;

    /**
     * 这传递的 username 是账号
     * 无论是从数据库中还是从缓存中查询，需要将查询出来的用户信息和权限信息组装成一个 UserDetails 返回
     * UserDetailsService：获取用户信息
     * UserDetails：代表用户信息
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("开始权限认证，用户账号为: {}", username);
        UserInfo user = this.userInfoService.getOne(new LambdaQueryWrapper<UserInfo>()
                .eq(UserInfo::getAccount, username)
        );
        if (user == null) {
            throw new UsernameNotFoundException("账号不存在");
        }

        List<SysRole> roles = this.sysRoleService.list(new LambdaQueryWrapper<SysRole>()
                .select(SysRole::getUsername, SysRole::getRole, SysRole::getStatus)
                .eq(SysRole::getRoleId, user.getId())
        );

        List<RoleInfo> roleInfos = Convert.toList(RoleInfo.class, roles);
        // 构建 userDetail 对象
        UserDetail userDetail = new UserDetail()
                .setAccount(user.getAccount())
//                .setPassword(user.getPassword()) // 注册保存的时候，使用BCryptPasswordEncoder加密之后的写法
                .setPassword(new BCryptPasswordEncoder().encode(user.getPassword()))
                .setRoles(roleInfos);
        log.info("userDetail: {}", userDetail);
        return userDetail;
    }
}
