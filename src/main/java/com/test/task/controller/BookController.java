package com.test.task.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.test.task.model.Books;
import com.test.task.model.Views;
import com.test.task.service.BooksService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("book")
public class BookController {

    private final BooksService booksService;

    public BookController(BooksService booksService) {
        this.booksService = booksService;
    }

    @GetMapping
    @JsonView(Views.IdName.class)
    public ResponseEntity<List<Books>> getAllBooks() {
        return new ResponseEntity<>(booksService.getAllBooks(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    @JsonView(Views.Full.class)
    public ResponseEntity<Books> getBook(@PathVariable("id") Books books) {
        return new ResponseEntity<>(books, books == null ? HttpStatus.NO_CONTENT : HttpStatus.OK);
    }

    @PostMapping
    @JsonView(Views.Full.class)
    public ResponseEntity<Books> addBook(@RequestBody Map<String, String> body) {
        return booksService.addBook(body);
    }

    @PutMapping("/{id}")
    @JsonView(Views.Full.class)
    public ResponseEntity<Books> editBook(@PathVariable("id") Books booksFromDb, @RequestBody Map<String, String> body) {
        return booksService.editBook(booksFromDb, body);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteBook(@PathVariable("id") Books Books) {
        booksService.deleteBook(Books);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
