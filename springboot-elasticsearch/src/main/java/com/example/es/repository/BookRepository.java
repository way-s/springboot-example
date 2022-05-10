package com.example.es.repository;

import com.example.es.entity.Book;
import org.springframework.data.elasticsearch.annotations.Highlight;
import org.springframework.data.elasticsearch.annotations.HighlightField;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: mxx
 * @Description:
 */
@Repository
public interface BookRepository extends ElasticsearchRepository<Book, Object> {

    /**
     * 通过作者或姓名或发布获取
     *
     * @param name
     * @param publish
     * @param author
     * @return
     */
    @Highlight(fields = {
            @HighlightField(name = "name") // 使用@Highlight定义关键字高亮
    })
    List<Book> getByAuthorOrNameOrPublish(String name, String publish, String author);
}
