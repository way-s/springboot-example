package com.example.mongo.controller;

import com.example.entity.pojo.NewBookStore;
import com.example.mongo.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @Author: mxx
 * @Description:
 */
@Slf4j
@RestController
public class MgController {

    @Resource
    private BookRepository bookRepository;

    /**
     * http://localhost:8888/mgdb/1
     *
     * @param id
     * @return
     */
    @GetMapping("/mg/{id}")
    public Object getOneBookInfoByMg(@PathVariable Integer id) {
        Optional<NewBookStore> book = bookRepository.findById(id);
        log.info("book: {}", book);
        return book.orElse(null);
    }
}
