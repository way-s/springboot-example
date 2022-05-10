package com.example.es;

import cn.hutool.core.convert.Convert;
import com.example.common.service.INewBookStoreService;
import com.example.entity.pojo.NewBookStore;
import com.example.es.entity.Book;
import com.example.es.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @Author: mxx
 * @Description:
 */
@Slf4j
@SpringBootTest
public class EsTest {

    @Resource
    private INewBookStoreService newBookStoreService;

    @Resource
    private BookRepository bookRepository;

    /**
     * save
     */
    @Test
    public void testSave() {
//        NewBookStore byId = newBookStoreService.getById(1);
//        log.info("book: {}", byId);
//        Book book = Convert.convert(Book.class, byId);
//        Book save = bookRepository.save(book);
//        log.info("save: {}", save);

        List<NewBookStore> list = newBookStoreService.list();
        log.info("list size: {}", list.size());

        List<Book> books = Convert.toList(Book.class, list);
        Iterable<Book> saveAll = bookRepository.saveAll(books);
        log.info("saveAll size: {}", saveAll.spliterator().getExactSizeIfKnown());
    }

    /**
     * get
     */
    @Test
    public void testGet() {
        Iterable<Book> all = bookRepository.findAll();
        List<Book> list = copyIterator(all);
        log.info("size: {},all: {}", list.size(), list);

        Optional<Book> book = bookRepository.findById(1);
        // orElse(T other) 不论容器是否为空,只要调用该方法, 则对象other一定存在
        // orElseGet(Supplier<? extends T> supplier) 只有当容器为空时,才调用supplier.get()方法产生对象
        log.info("get book.orElseGet: {}", book.orElseGet(Book::new));
        log.info("get book: {}", book.orElse(new Book()));
    }

    /**
     * delete
     */
    @Test
    public void testDelete() {
        Integer id = 2;
        bookRepository.deleteById(id);
        log.info("删除id为 {}的数据", id);
        bookRepository.deleteAll();
        log.info("删除全部完毕");
    }

    /**
     * Iterable 转 List
     */
    public static <T> List<T> copyIterator(Iterable<T> iter) {
        List<T> list = new ArrayList<>();
        iter.forEach(list::add);
        return list;
    }

}
