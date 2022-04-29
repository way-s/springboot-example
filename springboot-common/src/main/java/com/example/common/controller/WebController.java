package com.example.common.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.service.INewBookStoreService;
import com.example.entity.pojo.NewBookStore;
import com.example.entity.vo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: mxx
 * @Title: WebController
 * @Description:
 * @Date: 2022/4/25
 */
@RestController
@RequestMapping("/common")
public class WebController {

    @Resource
    private INewBookStoreService bookStoreService;

    @GetMapping("hello")
    public Result<Object> test() {
        return Result.success();
    }

    @GetMapping
    public Result<Object> getPages() {
        IPage<NewBookStore> page = new Page<>();
        page.setPages(1).setSize(1);
        IPage<NewBookStore> pages = bookStoreService.page(page);
        return Result.success(pages);
    }

}

