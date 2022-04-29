package com.example.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.mapper.NewBookStoreMapper;
import com.example.common.service.INewBookStoreService;
import com.example.entity.pojo.NewBookStore;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mxx
 * @since 2022-04-25
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class NewBookStoreServiceImpl extends ServiceImpl<NewBookStoreMapper, NewBookStore> implements INewBookStoreService {

}
