package com.example.springmysqlelastic.service.impl;

import com.example.springmysqlelastic.mapper.BookMapper;
import com.example.springmysqlelastic.model.Book;
import com.example.springmysqlelastic.model.dto.BookDTO;
import com.example.springmysqlelastic.repo.IBookDAO;
import com.example.springmysqlelastic.service.IBookService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService implements IBookService {

    private IBookDAO bookDAO;
    private BookMapper bookMapper;
   

    @Autowired
    public BookService(IBookDAO bookDAO, BookMapper bookMapper) {
        this.bookDAO = bookDAO;
        this.bookMapper = bookMapper;
    }

    @Override
    public BookDTO save(BookDTO bookDTO) {
        Book book = this.bookDAO.save(this.bookMapper.toBook(bookDTO));
        return this.bookMapper.toBookDTO(book);
    }

    @Override
    public BookDTO findById(Long id) {
        return this.bookMapper.toBookDTO(this.bookDAO.findById(id).orElse(null));
    }

    @Override
    public List<BookDTO> findAll() {
        return this.bookMapper.toBookDtos(this.bookDAO.findAll());
    }

	@Override
	public List<BookDTO> deleteAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteById(Long id) {
	       bookDAO.deleteById(id);
	        return "book removed !! " + id;
	    }

	@Override
	public Book updateBook(Book book) {
		// TODO Auto-generated method stub
		Book existingBook = bookDAO.findById(book.getId()).orElse(null);
		existingBook.setName(book.getName());
		existingBook.setCategory(book.getCategory());
		existingBook.setDetail(book.getDetail());
		existingBook.setThumbnail(book.getThumbnail());
        return bookDAO.save(existingBook);
		
	}

	@Override
	public Book up(BookDTO bookDTO) {
		// TODO Auto-generated method stub
		Book existingBook =bookDAO.findById(bookDTO.getId()).orElse(null);
		existingBook.setName(bookDTO.getName());
		existingBook.setCategory(bookDTO.getCategory());
		existingBook.setDetail(bookDTO.getDetail());
		existingBook.setThumbnail(bookDTO.getThumbnail());
        return bookDAO.save(existingBook);
		
	}
	
	
   
}
