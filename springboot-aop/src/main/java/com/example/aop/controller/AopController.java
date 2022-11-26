package com.example.aop.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.aop.aspect.LogAnnotation;
import com.example.aop.aspect.VerifyParams;
import com.example.common.service.INewBookStoreService;
import com.example.entity.pojo.NewBookStore;
import com.example.entity.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author: mxx
 * @Title: WebController
 * @Description:
 * @Date: 2022/4/26
 */
@Slf4j
@RestController
@RequestMapping("/aop")
public class AopController {

    @Resource
    private INewBookStoreService bookStoreService;

    /**
     * aop 日志
     * http://localhost:8888/aop/
     *
     * @param current
     * @param size
     * @return
     */
    @GetMapping({"/{current}/{size}", ""})
    @LogAnnotation(module = "AOP", operaDesc = "记录日志")
    @VerifyParams
    public Object getPages(@PathVariable(value = "current", required = false) Integer current,
                           @PathVariable(value = "size", required = false) Integer size) {
        log.info("current= {}, size= {}", current, size);
        if (current == null || size == null) {
            current = 1;
            size = 1;
        }
        IPage<NewBookStore> factor = new Page<>(current, size);
        IPage<NewBookStore> pages = bookStoreService.page(factor);
        return Result.success(pages);
    }

    /**
     * aop 参数校验
     * http://localhost:8888/aop/p?cur=1&size=2
     * http://localhost:8888/aop/p?cur=1&size=-2
     *
     * @param current
     * @param size
     * @return
     */
    @GetMapping("/p")
    @VerifyParams(params = {"cur", "size"})
    public Object getPage(@RequestParam(value = "cur", defaultValue = "1", required = false) Integer current,
                          @RequestParam(value = "size", defaultValue = "2", required = false) Integer size) {
        log.info("current= {}, size= {}", current, size);
        IPage<NewBookStore> factor = new Page<>(current, size);
        IPage<NewBookStore> pages = bookStoreService.page(factor);
        return Result.success(pages);
    }

}

