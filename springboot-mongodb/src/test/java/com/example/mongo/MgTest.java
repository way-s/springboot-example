package com.example.mongo;

import com.example.common.service.INewBookStoreService;
import com.example.entity.pojo.NewBookStore;
import com.example.mongo.repository.BookRepository;
import com.mongodb.client.result.DeleteResult;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * @Author: mxx
 * @Description:
 */
@Slf4j
@SpringBootTest
public class MgTest {

    @Resource
    private INewBookStoreService bookStoreService;

    @Resource
    private BookRepository bookRepository;

    /**
     * 使用MongoTemplate模板类实现数据库操作
     */
    @Resource
    private MongoTemplate mongoTemplate;

    /**
     * get
     */
    @Test
    public void testGet() {
//     自定义 根据id获取
        Integer id = 1;
        NewBookStore oneBook = this.bookRepository.getBookInfoById(id);
        log.info("oneBook: {}", oneBook);
        Optional<NewBookStore> byId = this.bookRepository.findById(Long.valueOf(id));
        log.info("byId: {}", byId);


        long count = this.bookRepository.count();
        log.info("count：{}", count);
        List<NewBookStore> all = this.bookRepository.findAll();
        log.info("all size:{} ,all: {}", all.size(), all);
    }

    /**
     * save
     */
    @Test
    public void testSave() {
//        NewBookStore book = this.bookStoreService.getById(2L);
//        log.info("book：{}", book);
//        NewBookStore save = this.bookRepository.save(book);
//        log.info("save：{}", save);

        List<NewBookStore> list = this.bookStoreService.list();
        List<NewBookStore> all = this.bookRepository.saveAll(list);
        log.info("all size:{} ,all: {}", all.size(), all);
    }

    /**
     * delete
     */
    @Test
    public void testDelete() {
//        Integer id = 2;
//        this.bookRepository.deleteById(id);

        Optional<NewBookStore> id1 = this.bookRepository.findById(2L);
        log.info("id1: {} ", id1);
        boolean present = id1.isPresent();
        if (present) {
            log.info("id.get(): {}", id1.get());
            this.bookRepository.delete(id1.get());
            log.info("book id : {} 删除成功", id1.get().getId());
        }

//        删除全部
        this.bookRepository.deleteAll();
        log.info("全部删除成功");
    }

    /**
     * MongoTemplate
     */
    @Test
    public void testMongoTemplate() {
//        根据id查询
        Integer id = 2;
        NewBookStore store = mongoTemplate.findById(id, NewBookStore.class);
        log.info("MgTemplate By Id: {}, store：{}", id, store);

//        删除
        assert store != null;
        DeleteResult remove = mongoTemplate.remove(store);
        log.info("remove: {}", remove);

//        保存或修改
        NewBookStore store1 = bookStoreService.getById(id);
        NewBookStore save = mongoTemplate.save(store1);
        log.info("保存或修改 save: {}", save);
    }

}
