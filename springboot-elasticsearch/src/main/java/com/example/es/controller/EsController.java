package com.example.es.controller;

import com.example.entity.vo.Result;
import com.example.es.entity.Book;
import com.example.es.repository.BookRepository;
import com.example.es.service.EsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * @Author: mxx
 * @Description:
 */
@Slf4j
@RestController
public class EsController {

    @Resource
    private BookRepository bookRepository;

    @Resource
    private EsService esService;

    /**
     * http://localhost:8888/es/1
     *
     * @param id
     * @return
     */
    @GetMapping("/es/{id}")
    public Object getEsById(@PathVariable Integer id) {
        Optional<Book> byId = bookRepository.findById(id);
        return byId.orElse(new Book());
    }

    /**
     * http://localhost:8888/like?k=%22%E5%85%89%22
     *
     * @param keyword
     * @return
     */
    @GetMapping("/like")
    public Object getEsByBookNameLike(@RequestParam("k") String keyword) {
        log.info("query book keyword:{}", keyword);
        List<Book> list = bookRepository.getByAuthorOrNameOrPublish(keyword, keyword, keyword);
        return Result.success(list);
    }

    /**
     * http://localhost:8888/like/1/5?k=%22%E9%87%91%22
     * 分页
     *
     * @param current
     * @param size
     * @param keyword
     * @return
     */
    @GetMapping("/like/{current}/{size}")
    public Object getPage(@PathVariable Integer current, @PathVariable Integer size,
                          @RequestParam("k") String keyword
    ) {
        log.info("getPage current:{}, size: {}, keyword:{}", current, size, keyword);
        Object list = esService.getBooksByPage(current, size, keyword);
        return Result.success(list);
    }
}
