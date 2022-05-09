package com.example.mongo.repository;

import com.example.entity.pojo.NewBookStore;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: mxx
 * @Description:
 */
@Repository
public interface BookRepository extends MongoRepository<NewBookStore, Object> {
    /**
     * 自定义 根据id获取，无需对方法进行实现
     *
     * @param id
     * @return
     */
    NewBookStore getBookInfoById(Integer id);
}
