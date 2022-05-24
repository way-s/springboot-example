package com.example.filter.controller;

import com.example.entity.vo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: mxx
 * @Description:
 */
@RestController
@RequestMapping("/filter")
public class WebController {

    /**
     * http://localhost:8888/filter/hello
     *
     * @return
     */
    @GetMapping("/hello")
    public Object hello() {
        return Result.success();
    }

}