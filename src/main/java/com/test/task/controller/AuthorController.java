package com.test.task.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.test.task.model.Author;
import com.test.task.model.Views;
import com.test.task.service.AuthorService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("author")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    @JsonView(Views.IdName.class)
    public ResponseEntity<List<Author>> getAllAuthors() {
        return new ResponseEntity<>(authorService.getAllAuthors(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    @JsonView(Views.FullWithBooks.class)
    public ResponseEntity<Author> getAuthor(@PathVariable("id") Author author) {
        return new ResponseEntity<>(author, HttpStatus.OK);
    }

    @PostMapping
    @JsonView(Views.Full.class)
    public ResponseEntity<Author> addAuthor(@RequestBody Author author) {
        return authorService.addAuthor(author);
    }

    @PutMapping("/{id}")
    @JsonView(Views.Full.class)
    public ResponseEntity<Author> editAuthor(@PathVariable("id") Author authorFromDb, @RequestBody Author author) {
        BeanUtils.copyProperties(author, authorFromDb, "id");

        return authorService.addAuthor(authorFromDb);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteAuthor(@PathVariable("id") Author author) {
        authorService.deleteAuthor(author);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
