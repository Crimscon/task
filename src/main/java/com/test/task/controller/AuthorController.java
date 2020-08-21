package com.test.task.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.test.task.model.Author;
import com.test.task.model.Views;
import com.test.task.service.AuthorService;
import org.springframework.beans.BeanUtils;
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
    public List<Author> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @GetMapping("{id}")
    @JsonView(Views.Books.class)
    public Author getAuthor(@PathVariable("id") Author author) {
        return author;
    }

    @PostMapping
    public Author addAuthor(@RequestBody Author author) {
        return authorService.addAuthor(author);
    }

    @PutMapping("/{id}")
    public Author editAuthor(@PathVariable("id") Author authorFromDb, @RequestBody Author author) {
        BeanUtils.copyProperties(author, authorFromDb, "id");

        return authorService.addAuthor(authorFromDb);
    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable("id") Author author) {
        authorService.deleteAuthor(author);
    }

}
