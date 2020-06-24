package com.example.springmysqlelastic.repo.elastic;

import com.example.springmysqlelastic.model.BookModel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface IBookESRepo extends ElasticsearchRepository<BookModel, Long> {
}
