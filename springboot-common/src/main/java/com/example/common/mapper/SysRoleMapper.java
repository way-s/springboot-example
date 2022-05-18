package com.example.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.pojo.SysRole;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * role 权限表 Mapper 接口
 * </p>
 *
 * @author mxx
 * @since 2022-04-25
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {

}
