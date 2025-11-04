package org.example.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.dto.authorDTO;
import org.example.dto.bookDTO;
import org.example.dto.publisherDTO;
import org.example.entity.author;
import org.example.entity.book;
import org.example.entity.publisher;

import java.util.List;

public class LibService implements LibServiceInterface {
    private static final Logger logger = LogManager.getLogger(LibService.class);

    private bookDTO bookDTO;
    private authorDTO authorDTO;
    private publisherDTO publisherDTO;

    public LibService(bookDTO bookDTO, authorDTO authorDTO, publisherDTO publisherDTO) {
        this.bookDTO = bookDTO;
        this.authorDTO = authorDTO;
        this.publisherDTO = publisherDTO;
    }

    @Override
    public book createBook(book book) {
        logger.info("Creating new book: " + book.getTitle());

        if (book.getAuthor() == null || book.getAuthor().getId() == null) {
            logger.error("Author is required for book creation");
            throw new IllegalArgumentException("Author is required");
        }

        if (book.getPublisher() == null || book.getPublisher().getId() == null) {
            logger.error("Publisher is required for book creation");
            throw new IllegalArgumentException("Publisher is required");
        }

        return bookDTO.save(book);
    }

    @Override
    public book getBookById(Long id) {
        logger.info("Getting book by id: " + id);
        return bookDTO.findById(id);
    }

    @Override
    public List<book> getAllBooks() {
        logger.info("Getting all books");
        return bookDTO.findAll();
    }

    @Override
    public book updateBook(book book) {
        logger.info("Updating book: " + book.getId());

        if (book.getId() == null) {
            logger.error("Book id is required for update");
            throw new IllegalArgumentException("Book id is required");
        }

        book existingBook = bookDTO.findById(book.getId());
        if (existingBook == null) {
            logger.error("Book not found: " + book.getId());
            throw new IllegalArgumentException("Book not found");
        }

        return bookDTO.update(book);
    }

    @Override
    public boolean deleteBook(Long id) {
        logger.info("Deleting book: " + id);
        return bookDTO.delete(id);
    }

    @Override
    public List<book> getBooksByAuthorId(Long authorId) {
        logger.info("Getting books by author id: " + authorId);
        return bookDTO.findByAuthorId(authorId);
    }

    @Override
    public List<book> getBooksByPublisherId(Long publisherId) {
        logger.info("Getting books by publisher id: " + publisherId);
        return bookDTO.findByPublisherId(publisherId);
    }

    @Override
    public author createAuthor(author author) {
        logger.info("Creating new author: " + author.getFirstName() + " " + author.getLastName());

        if (author.getFirstName() == null || author.getFirstName().isEmpty()) {
            logger.error("First name is required");
            throw new IllegalArgumentException("First name is required");
        }

        if (author.getLastName() == null || author.getLastName().isEmpty()) {
            logger.error("Last name is required");
            throw new IllegalArgumentException("Last name is required");
        }

        return authorDTO.save(author);
    }

    @Override
    public author getAuthorById(Long id) {
        logger.info("Getting author by id: " + id);
        return authorDTO.findById(id);
    }

    @Override
    public List<author> getAllAuthors() {
        logger.info("Getting all authors");
        return authorDTO.findAll();
    }

    @Override
    public author updateAuthor(author author) {
        logger.info("Updating author: " + author.getId());

        if (author.getId() == null) {
            logger.error("Author id is required for update");
            throw new IllegalArgumentException("Author id is required");
        }

        author existingAuthor = authorDTO.findById(author.getId());
        if (existingAuthor == null) {
            logger.error("Author not found: " + author.getId());
            throw new IllegalArgumentException("Author not found");
        }

        return authorDTO.update(author);
    }

    @Override
    public boolean deleteAuthor(Long id) {
        logger.info("Deleting author: " + id);
        return authorDTO.delete(id);
    }

    @Override
    public author getAuthorByFullName(String firstName, String lastName) {
        logger.info("Getting author by full name: " + firstName + " " + lastName);
        return authorDTO.findByFullName(firstName, lastName);
    }

    @Override
    public publisher createPublisher(publisher publisher) {
        logger.info("Creating new publisher: " + publisher.getName());

        if (publisher.getName() == null || publisher.getName().isEmpty()) {
            logger.error("Publisher name is required");
            throw new IllegalArgumentException("Publisher name is required");
        }

        if (publisher.getHeadOfficeCity() == null || publisher.getHeadOfficeCity().isEmpty()) {
            logger.error("Head office city is required");
            throw new IllegalArgumentException("Head office city is required");
        }

        if (publisher.getFoundationYear() == null) {
            logger.error("Foundation year is required");
            throw new IllegalArgumentException("Foundation year is required");
        }

        return publisherDTO.save(publisher);
    }

    @Override
    public publisher getPublisherById(Long id) {
        logger.info("Getting publisher by id: " + id);
        return publisherDTO.findById(id);
    }

    @Override
    public List<publisher> getAllPublishers() {
        logger.info("Getting all publishers");
        return publisherDTO.findAll();
    }

    @Override
    public publisher updatePublisher(publisher publisher) {
        logger.info("Updating publisher: " + publisher.getId());

        if (publisher.getId() == null) {
            logger.error("Publisher id is required for update");
            throw new IllegalArgumentException("Publisher id is required");
        }

        publisher existingPublisher = publisherDTO.findById(publisher.getId());
        if (existingPublisher == null) {
            logger.error("Publisher not found: " + publisher.getId());
            throw new IllegalArgumentException("Publisher not found");
        }

        return publisherDTO.update(publisher);
    }

    @Override
    public boolean deletePublisher(Long id) {
        logger.info("Deleting publisher: " + id);
        return publisherDTO.delete(id);
    }

    @Override
    public publisher getPublisherByName(String name) {
        logger.info("Getting publisher by name: " + name);
        return publisherDTO.findByName(name);
    }
}