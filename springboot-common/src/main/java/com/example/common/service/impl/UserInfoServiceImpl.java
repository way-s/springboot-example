package com.example.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.mapper.UserInfoMapper;
import com.example.common.service.IUserInfoService;
import com.example.entity.pojo.UserInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * user info 表 服务实现类
 * </p>
 *
 * @author mxx
 * @since 2022-04-25
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

}
