package org.example.controller;


import org.example.entity.author;
import org.example.entity.book;
import org.example.entity.publisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

public interface LibControllerInterface {

    @PostMapping("/books")
    ResponseEntity<Map<String, Object>> createBook(@RequestBody book book);

    @GetMapping("/books/{id}")
    ResponseEntity<Map<String, Object>> getBook(@PathVariable Long id);

    @GetMapping("/books")
    ResponseEntity<Map<String, Object>> getAllBooks();

    @PutMapping("/books/{id}")
    ResponseEntity<Map<String, Object>> updateBook(@PathVariable Long id, @RequestBody book book);

    @DeleteMapping("/books/{id}")
    ResponseEntity<Map<String, Object>> deleteBook(@PathVariable Long id);

    @GetMapping("/books/author/{authorId}")
    ResponseEntity<Map<String, Object>> getBooksByAuthor(@PathVariable Long authorId);

    @GetMapping("/books/publisher/{publisherId}")
    ResponseEntity<Map<String, Object>> getBooksByPublisher(@PathVariable Long publisherId);

    @PostMapping("/authors")
    ResponseEntity<Map<String, Object>> createAuthor(@RequestBody author author);

    @GetMapping("/authors/{id}")
    ResponseEntity<Map<String, Object>> getAuthor(@PathVariable Long id);

    @GetMapping("/authors")
    ResponseEntity<Map<String, Object>> getAllAuthors();

    @PutMapping("/authors/{id}")
    ResponseEntity<Map<String, Object>> updateAuthor(@PathVariable Long id, @RequestBody author author);

    @DeleteMapping("/authors/{id}")
    ResponseEntity<Map<String, Object>> deleteAuthor(@PathVariable Long id);

    @PostMapping("/publishers")
    ResponseEntity<Map<String, Object>> createPublisher(@RequestBody publisher publisher);

    @GetMapping("/publishers/{id}")
    ResponseEntity<Map<String, Object>> getPublisher(@PathVariable Long id);

    @GetMapping("/publishers")
    ResponseEntity<Map<String, Object>> getAllPublishers();

    @PutMapping("/publishers/{id}")
    ResponseEntity<Map<String, Object>> updatePublisher(@PathVariable Long id, @RequestBody publisher publisher);

    @DeleteMapping("/publishers/{id}")
    ResponseEntity<Map<String, Object>> deletePublisher(@PathVariable Long id);
}