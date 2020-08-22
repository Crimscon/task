package com.test.task.service;

import com.test.task.model.Author;
import com.test.task.model.Books;
import com.test.task.repo.AuthorRepo;
import com.test.task.repo.BooksRepo;
import com.test.task.util.MyLogger;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class BooksService {

    private final BooksRepo booksRepo;
    private final AuthorRepo authorRepo;
    private final Logger logger = MyLogger.createAndGetLogger(BooksService.class);

    public BooksService(BooksRepo booksRepo, AuthorRepo authorRepo) throws IOException {
        this.booksRepo = booksRepo;
        this.authorRepo = authorRepo;
    }

    public List<Books> getAllBooks() {
        return booksRepo.findAll();
    }

    public ResponseEntity<Books> addBook(Map<String, String> books) {
        String authorName = books.get("author"), bookName = books.get("name");
        Books book = booksRepo.findByNameIgnoreCase(bookName);
        try {
            return new ResponseEntity<>(book != null ? book : getBooks(new Books(), authorName, bookName), HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Не получилось сохранить книгу");
            return new ResponseEntity<>(new Books(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Books> editBook(Books book, Map<String, String> body) {
        String authorName = body.get("author"), bookName = body.get("name");
        try {
            return new ResponseEntity<>(getBooks(book, authorName, bookName), HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Не получилось изменить книгу");
            return new ResponseEntity<>(new Books(), HttpStatus.BAD_REQUEST);
        }
    }

    private Books getBooks(Books book, String authorName, String bookName) throws Exception {
        Author author = authorRepo.findByNameIgnoreCase(authorName);
        if (author == null) {
            author = new Author();
            author.setName(authorName);
            authorRepo.save(author);

        }
        if (book.getAuthor() == null || !book.getAuthor().equals(author))
            book.setAuthor(author);

        if (book.getName() == null || !book.getName().equals(bookName))
            book.setName(bookName);

        return booksRepo.save(book);
    }

    public void deleteBook(Books books) {
        booksRepo.delete(books);
    }
}
