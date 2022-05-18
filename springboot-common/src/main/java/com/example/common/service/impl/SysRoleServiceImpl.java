package com.example.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.mapper.SysRoleMapper;
import com.example.common.service.ISysRoleService;
import com.example.entity.pojo.SysRole;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * role 权限表 服务实现类
 * </p>
 *
 * @author mxx
 * @since 2022-04-25
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

}
