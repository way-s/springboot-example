package com.example.es.service;

import com.example.es.entity.Book;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: mxx
 * @Description:
 */
@Slf4j
@Service
public class EsService {

    @Resource
    private ElasticsearchRestTemplate esTemplate;

    public Object getBooksByPage(Integer current, Integer size, String keyword) {

        Pageable pageable = PageRequest.of(current - 1, size);
        // 查询字段
        BoolQueryBuilder query = QueryBuilders.boolQuery()
                .should(QueryBuilders.matchQuery("author", keyword))
                .should(QueryBuilders.matchQuery("name", keyword))
                .should(QueryBuilders.matchQuery("publish", keyword));

        // 创建搜索 DSL 查询
        NativeSearchQuery builder = new NativeSearchQueryBuilder()
                .withQuery(query)
                .withPageable(pageable)
                .build();
        // 查询
        SearchHits<Book> search = esTemplate.search(builder, Book.class);
        List<SearchHit<Book>> searchHits = search.getSearchHits();
        log.info("search size: {}, search: {}", search.getTotalHits(), search);

        // 要返回的集合
        List<Book> list = new ArrayList<>();
        // 遍历
        searchHits.forEach(obj -> {
            list.add(obj.getContent());
        });
        return list;
    }
}
