package com.example.springmysqlelastic.mapper;

import com.example.springmysqlelastic.model.Book;
import com.example.springmysqlelastic.model.BookModel;
import com.example.springmysqlelastic.model.dto.BookDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-06-26T15:33:04+0100",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_241 (Oracle Corporation)"
)
@Component
public class BookMapperImpl implements BookMapper {

    @Override
    public BookDTO toBookDTO(Book book) {
        if ( book == null ) {
            return null;
        }

        BookDTO bookDTO = new BookDTO();

        bookDTO.setId( book.getId() );
        bookDTO.setName( book.getName() );
        bookDTO.setCategory( book.getCategory() );
        bookDTO.setDetail( book.getDetail() );
        bookDTO.setThumbnail( book.getThumbnail() );

        return bookDTO;
    }

    @Override
    public List<BookDTO> toBookDtos(List<Book> books) {
        if ( books == null ) {
            return null;
        }

        List<BookDTO> list = new ArrayList<BookDTO>( books.size() );
        for ( Book book : books ) {
            list.add( toBookDTO( book ) );
        }

        return list;
    }

    @Override
    public Book toBook(BookDTO bookDTO) {
        if ( bookDTO == null ) {
            return null;
        }

        Book book = new Book();

        book.setId( bookDTO.getId() );
        book.setName( bookDTO.getName() );
        book.setCategory( bookDTO.getCategory() );
        book.setDetail( bookDTO.getDetail() );
        book.setThumbnail( bookDTO.getThumbnail() );

        return book;
    }

    @Override
    public List<Book> toBooks(List<BookDTO> bookDTOS) {
        if ( bookDTOS == null ) {
            return null;
        }

        List<Book> list = new ArrayList<Book>( bookDTOS.size() );
        for ( BookDTO bookDTO : bookDTOS ) {
            list.add( toBook( bookDTO ) );
        }

        return list;
    }

    @Override
    public BookModel toBookModel(Book book) {
        if ( book == null ) {
            return null;
        }

        BookModel bookModel = new BookModel();

        bookModel.setId( book.getId() );
        bookModel.setName( book.getName() );
        bookModel.setCategory( book.getCategory() );
        bookModel.setDetail( book.getDetail() );
        bookModel.setThumbnail( book.getThumbnail() );
        bookModel.setModificationDate( book.getModificationDate() );

        return bookModel;
    }
}
