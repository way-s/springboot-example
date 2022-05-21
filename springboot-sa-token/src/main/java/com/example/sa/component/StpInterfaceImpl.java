package com.example.sa.component;

import cn.dev33.satoken.stp.StpInterface;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.common.service.ISysRoleService;
import com.example.common.service.IUserInfoService;
import com.example.entity.pojo.SysRole;
import com.example.entity.pojo.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: mxx
 * @Description:
 */
@Slf4j
@Component
public class StpInterfaceImpl implements StpInterface {

    @Resource
    private IUserInfoService userInfoService;

    @Resource
    private ISysRoleService sysRoleService;

    /**
     * 返回一个账号所拥有的权限码集合
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        return getUserRoles(loginId);
    }

    /**
     * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        return getUserRoles(loginId);
    }

    private List<String> getUserRoles(Object loginId) {
        log.info("开始权限认证，用户账号为: {}", loginId);
        UserInfo user = this.userInfoService.getOne(new LambdaQueryWrapper<UserInfo>()
                .select(UserInfo::getId, UserInfo::getAccount, UserInfo::getPassword)
                .eq(UserInfo::getAccount, loginId)
        );
        if (user == null) {
            return null;
        }

        List<SysRole> roles = this.sysRoleService.list(new LambdaQueryWrapper<SysRole>()
                .select(SysRole::getUsername, SysRole::getRole, SysRole::getStatus)
                .eq(SysRole::getRoleId, user.getId())
        );

        List<String> list = new ArrayList<>();
//        list.add("user");

        roles.forEach(userRole -> {
            list.add(userRole.getRole());
        });
        return list;
    }
}
