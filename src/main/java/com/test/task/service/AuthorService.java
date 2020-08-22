package com.test.task.service;

import com.test.task.model.Author;
import com.test.task.repo.AuthorRepo;
import com.test.task.util.MyLogger;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class AuthorService {

    private final Logger logger = MyLogger.createAndGetLogger(AuthorService.class);
    private final AuthorRepo authorRepo;

    public AuthorService(AuthorRepo authorRepo) throws IOException {
        this.authorRepo = authorRepo;
    }

    public List<Author> getAllAuthors() {
        return authorRepo.findAll();
    }

    public ResponseEntity<Author> addAuthor(Author author) {
        try {
            return new ResponseEntity<>(authorRepo.save(author), HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Не получилось сохранить автора");
            return new ResponseEntity<>(new Author(), HttpStatus.BAD_REQUEST);
        }
    }

    public void deleteAuthor(Author author) {
        authorRepo.delete(author);
    }
}
