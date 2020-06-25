package com.example.springmysqlelastic.service;

import com.example.springmysqlelastic.model.Book;
import com.example.springmysqlelastic.model.dto.BookDTO;

import java.util.List;

public interface IBookService {
    BookDTO save(BookDTO bookDTO);
    BookDTO findById(Long id);
    List<BookDTO> findAll();
    List<BookDTO> deleteAll();
    //String deleteById(Long id);
	String deleteById(Long id);
	Book updateBook(Book book);
	Book up(BookDTO bookDTO);
	
}
