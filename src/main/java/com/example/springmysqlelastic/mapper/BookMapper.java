package com.example.springmysqlelastic.mapper;

import com.example.springmysqlelastic.model.Book;
import com.example.springmysqlelastic.model.BookModel;
import com.example.springmysqlelastic.model.dto.BookDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookDTO toBookDTO(Book book);

    List<BookDTO> toBookDtos(List<Book> books);

    Book toBook(BookDTO bookDTO);

    List<Book> toBooks(List<BookDTO> bookDTOS);

    BookModel toBookModel(Book book);
}
