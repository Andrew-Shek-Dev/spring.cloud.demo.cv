package io.tecky.demo;

import io.tecky.demo.persistence.model.Book;
import io.tecky.demo.persistence.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    @GetMapping
    public Iterable<Book> findAll(){
        return bookRepository.findAll();
    }

    @GetMapping("/title/{bookTitle}")
    public List<Book> findByTitle(@PathVariable String bookTitle ){
        return bookRepository.findByTitle(bookTitle);
    }
}
