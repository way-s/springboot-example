package com.example.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.pojo.UserInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * user info 表 Mapper 接口
 * </p>
 *
 * @author mxx
 * @since 2022-04-25
 */
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {

}
