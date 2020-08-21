package com.test.task.service;

import com.test.task.model.Author;
import com.test.task.repo.AuthorRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepo authorRepo;

    public AuthorService(AuthorRepo authorRepo) {
        this.authorRepo = authorRepo;
    }

    public List<Author> getAllAuthors() {
        return authorRepo.findAll();
    }

    public Author addAuthor(Author author) {
        return authorRepo.save(author);
    }

    public void deleteAuthor(Author author) {
        authorRepo.delete(author);
    }
}
