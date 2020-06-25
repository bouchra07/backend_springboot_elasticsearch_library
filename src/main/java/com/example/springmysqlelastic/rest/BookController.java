package com.example.springmysqlelastic.rest;

import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import com.example.springmysqlelastic.model.dto.BookDTO;
import com.example.springmysqlelastic.service.IBookService;
import com.example.springmysqlelastic.utils.PathResources;

import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.springmysqlelastic.repo.IBookDAO;
import com.example.springmysqlelastic.model.Book;
import com.example.springmysqlelastic.model.BookModel;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(PathResources.BOOK)
public class BookController {
	
	@Autowired
    private IBookDAO repository;
    private final IBookService bookService;

    @Autowired
    public BookController(IBookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping(PathResources.SAVE)
    public ResponseEntity<BookDTO> saveBook(@RequestBody BookDTO bookDTO) {
        return new ResponseEntity<>(this.bookService.save(bookDTO), HttpStatus.OK);
    }

    @GetMapping(PathResources.FIND_ONE + "/{" + PathResources.ID + "}")
    public ResponseEntity<BookDTO> findById(@PathVariable Long id) {
        return new ResponseEntity<>(this.bookService.findById(id), HttpStatus.OK);
    }

    @GetMapping(PathResources.FIND_ALL)
    public ResponseEntity<List<BookDTO>> findById() {
        return new ResponseEntity<>(this.bookService.findAll(), HttpStatus.OK);
    }
    @DeleteMapping(PathResources.delete_ALL)
    public void whenDeleteAllFromRepository_thenRepositoryShouldBeEmpty() {
        repository.deleteAll();
       // assertThat(repository.count()).isEqualTo(0);
    }
    
    
    /*@DeleteMapping(PathResources.DELETE_ONE + "/{" + PathResources.ID + "}")
    public String deleteById(@PathVariable int id) {
        return bookService.deleteById(id);
    }*/
    @DeleteMapping(PathResources.DELETE_ONE + "/{" + PathResources.ID + "}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        return new ResponseEntity<>(this.bookService.deleteById(id), HttpStatus.OK);
    }
    
    @PutMapping(PathResources.UPDATE)
    public Book updateBook(@RequestBody Book book) {
        return bookService.updateBook(book);
    }
    @Autowired
	ElasticsearchTemplate template;
   
    
    @PutMapping(PathResources.UP)
    public ResponseEntity<Book> up(@RequestBody BookDTO bookDTO) {
        return new ResponseEntity<>(this.bookService.up(bookDTO), HttpStatus.OK);
    }
    
   
   
    @GetMapping("/search/{name}")
    public List<BookModel> findbyName(@PathVariable String name){
        String search = ".*"+name+".*";
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withFilter(QueryBuilders.regexpQuery("name",search)).build();
        List<BookModel> books = template.queryForList(searchQuery,BookModel.class);
        return books;
    }
    
    
    
}
  
    
   
