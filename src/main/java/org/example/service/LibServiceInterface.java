package org.example.service;

import org.example.entity.author;
import org.example.entity.book;
import org.example.entity.publisher;

import java.util.List;

public interface LibServiceInterface {

    book createBook(book book);
    book getBookById(Long id);
    List<book> getAllBooks();
    book updateBook(book book);
    boolean deleteBook(Long id);
    List<book> getBooksByAuthorId(Long authorId);
    List<book> getBooksByPublisherId(Long publisherId);
    author createAuthor(author author);
    author getAuthorById(Long id);
    List<author> getAllAuthors();
    author updateAuthor(author author);
    boolean deleteAuthor(Long id);
    author getAuthorByFullName(String firstName, String lastName);
    publisher createPublisher(publisher publisher);
    publisher getPublisherById(Long id);
    List<publisher> getAllPublishers();
    publisher updatePublisher(publisher publisher);
    boolean deletePublisher(Long id);
    publisher getPublisherByName(String name);
}
