package com.example.es.entity;


import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: mxx
 * @Description:
 */
@Data
@Document(indexName = "book")//定义在Elasticsearch中索引信息
public class Book implements Serializable {

    private static final long serialVersionUID = -8608111377094973871L;

    private Integer id;

    /**
     * 图书作者
     */
    private String author;

    /**
     * 图书名称
     */
    private String name;

    /**
     * 图书页数
     */
    private Integer pages;

    /**
     * 单价
     */
    private Double price;

    /**
     * 出版社
     */
    private String publish;

    /**
     * 出版时间
     */
    private Date publishTime;

    /**
     * 库存
     */
    private Integer size;

    /**
     * 翻译
     */
    private String translate;

    /**
     * 类型
     */
    private String type;

}
