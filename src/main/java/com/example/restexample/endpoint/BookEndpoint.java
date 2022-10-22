package com.example.restexample.endpoint;

import com.example.restexample.model.Book;
import com.example.restexample.model.BookLanguage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookEndpoint {

    List<Book> books = new ArrayList<>(List.of(
            new Book(1, "girq 1", "poxos", 34.5, BookLanguage.EN),
            new Book(2, "girq 2", "petros", 34.5, BookLanguage.ARM),
            new Book(3, "girq 3", "martiros", 34.5, BookLanguage.RU)

    ));

    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return books;
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                return ResponseEntity.ok(book);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/books")
    public ResponseEntity<?> createBook(@RequestBody Book book) {
        for (Book bookFromDB : books) {
            if (bookFromDB.getTitle().equals(book.getTitle())
                    && bookFromDB.getAuthorName().equals(book.getAuthorName())) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .build();
            }
        }
        books.add(book);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/books")
    public ResponseEntity<Book> updateBook(@RequestBody Book book) {
        if (book.getId() > 0) {
            for (Book bookFromDB : books) {
                if (bookFromDB.getId() == book.getId()) {
                    bookFromDB.setLanguage(book.getLanguage());
                    bookFromDB.setTitle(book.getTitle());
                    bookFromDB.setPrice(book.getPrice());
                    bookFromDB.setAuthorName(book.getAuthorName());
                    return ResponseEntity.ok(bookFromDB);
                }
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<?> deleteBookById(@PathVariable("id") int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                books.remove(book);
                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.notFound().build();
    }
}
