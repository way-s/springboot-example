package com.example.entity.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: mxx
 * @Title: NewBookStore
 * @Description:
 * @Date: 2022/4/26
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@TableName("new_book_store")
public class NewBookStore implements Serializable {

    private static final long serialVersionUID = 8248464625012208487L;

    @TableId(value = "id", type = IdType.AUTO)
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
