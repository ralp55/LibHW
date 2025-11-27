package org.example.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.entity.author;
import org.example.entity.book;
import org.example.entity.publisher;
import org.example.service.LibServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/library")
@CrossOrigin(origins = "*")
public class LibController implements LibControllerInterface {
    private static final Logger logger = LogManager.getLogger(LibController.class);

    @Autowired
    private LibServiceInterface libraryService;

    @Override
    @PostMapping("/books")
    public ResponseEntity<Map<String, Object>> createBook(@RequestBody book book) {
        logger.info("POST /api/library/books - Creating new book");
        try {
            book createdBook = libraryService.createBook(book);
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Book created successfully");
            response.put("data", createdBook);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            logger.error("Error creating book", e);
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", "error");
            errorResponse.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @Override
    @GetMapping("/books/{id}")
    public ResponseEntity<Map<String, Object>> getBook(@PathVariable Long id) {
        logger.info("GET /api/library/books/" + id);
        try {
            book book = libraryService.getBookById(id);
            if (book == null) {
                Map<String, Object> errorResponse = new HashMap<>();
                errorResponse.put("status", "error");
                errorResponse.put("message", "Book not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            }
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("data", book);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error getting book", e);
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", "error");
            errorResponse.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @Override
    @GetMapping("/books")
    public ResponseEntity<Map<String, Object>> getAllBooks() {
        logger.info("GET /api/library/books");
        try {
            List<book> books = libraryService.getAllBooks();
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("data", books);
            response.put("count", books.size());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error getting all books", e);
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", "error");
            errorResponse.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @Override
    @PutMapping("/books/{id}")
    public ResponseEntity<Map<String, Object>> updateBook(@PathVariable Long id, @RequestBody book book) {
        logger.info("PUT /api/library/books/" + id);
        try {
            book.setId(id);
            book updatedBook = libraryService.updateBook(book);
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Book updated successfully");
            response.put("data", updatedBook);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error updating book", e);
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", "error");
            errorResponse.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @Override
    @DeleteMapping("/books/{id}")
    public ResponseEntity<Map<String, Object>> deleteBook(@PathVariable Long id) {
        logger.info("DELETE /api/library/books/" + id);
        try {
            boolean deleted = libraryService.deleteBook(id);
            Map<String, Object> response = new HashMap<>();
            if (deleted) {
                response.put("status", "success");
                response.put("message", "Book deleted successfully");
                return ResponseEntity.ok(response);
            } else {
                response.put("status", "error");
                response.put("message", "Book not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            logger.error("Error deleting book", e);
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", "error");
            errorResponse.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @Override
    @GetMapping("/books/author/{authorId}")
    public ResponseEntity<Map<String, Object>> getBooksByAuthor(@PathVariable Long authorId) {
        logger.info("GET /api/library/books/author/" + authorId);
        try {
            List<book> books = libraryService.getBooksByAuthorId(authorId);
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("data", books);
            response.put("count", books.size());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error getting books by author", e);
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", "error");
            errorResponse.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @Override
    @GetMapping("/books/publisher/{publisherId}")
    public ResponseEntity<Map<String, Object>> getBooksByPublisher(@PathVariable Long publisherId) {
        logger.info("GET /api/library/books/publisher/" + publisherId);
        try {
            List<book> books = libraryService.getBooksByPublisherId(publisherId);
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("data", books);
            response.put("count", books.size());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error getting books by publisher", e);
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", "error");
            errorResponse.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @Override
    @PostMapping("/authors")
    public ResponseEntity<Map<String, Object>> createAuthor(@RequestBody author author) {
        logger.info("POST /api/library/authors - Creating new author");
        try {
            author createdAuthor = libraryService.createAuthor(author);
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Author created successfully");
            response.put("data", createdAuthor);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            logger.error("Error creating author", e);
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", "error");
            errorResponse.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @Override
    @GetMapping("/authors/{id}")
    public ResponseEntity<Map<String, Object>> getAuthor(@PathVariable Long id) {
        logger.info("GET /api/library/authors/" + id);
        try {
            author author = libraryService.getAuthorById(id);
            if (author == null) {
                Map<String, Object> errorResponse = new HashMap<>();
                errorResponse.put("status", "error");
                errorResponse.put("message", "Author not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            }
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("data", author);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error getting author", e);
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", "error");
            errorResponse.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @Override
    @GetMapping("/authors")
    public ResponseEntity<Map<String, Object>> getAllAuthors() {
        logger.info("GET /api/library/authors");
        try {
            List<author> authors = libraryService.getAllAuthors();
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("data", authors);
            response.put("count", authors.size());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error getting all authors", e);
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", "error");
            errorResponse.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @Override
    @PutMapping("/authors/{id}")
    public ResponseEntity<Map<String, Object>> updateAuthor(@PathVariable Long id, @RequestBody author author) {
        logger.info("PUT /api/library/authors/" + id);
        try {
            author.setId(id);
            author updatedAuthor = libraryService.updateAuthor(author);
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Author updated successfully");
            response.put("data", updatedAuthor);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error updating author", e);
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", "error");
            errorResponse.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @Override
    @DeleteMapping("/authors/{id}")
    public ResponseEntity<Map<String, Object>> deleteAuthor(@PathVariable Long id) {
        logger.info("DELETE /api/library/authors/" + id);
        try {
            boolean deleted = libraryService.deleteAuthor(id);
            Map<String, Object> response = new HashMap<>();
            if (deleted) {
                response.put("status", "success");
                response.put("message", "Author deleted successfully");
                return ResponseEntity.ok(response);
            } else {
                response.put("status", "error");
                response.put("message", "Author not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            logger.error("Error deleting author", e);
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", "error");
            errorResponse.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @Override
    @PostMapping("/publishers")
    public ResponseEntity<Map<String, Object>> createPublisher(@RequestBody publisher publisher) {
        logger.info("POST /api/library/publishers - Creating new publisher");
        try {
            publisher createdPublisher = libraryService.createPublisher(publisher);
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Publisher created successfully");
            response.put("data", createdPublisher);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            logger.error("Error creating publisher", e);
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", "error");
            errorResponse.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @Override
    @GetMapping("/publishers/{id}")
    public ResponseEntity<Map<String, Object>> getPublisher(@PathVariable Long id) {
        logger.info("GET /api/library/publishers/" + id);
        try {
            publisher publisher = libraryService.getPublisherById(id);
            if (publisher == null) {
                Map<String, Object> errorResponse = new HashMap<>();
                errorResponse.put("status", "error");
                errorResponse.put("message", "Publisher not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            }
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("data", publisher);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error getting publisher", e);
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", "error");
            errorResponse.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @Override
    @GetMapping("/publishers")
    public ResponseEntity<Map<String, Object>> getAllPublishers() {
        logger.info("GET /api/library/publishers");
        try {
            List<publisher> publishers = libraryService.getAllPublishers();
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("data", publishers);
            response.put("count", publishers.size());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error getting all publishers", e);
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", "error");
            errorResponse.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @Override
    @PutMapping("/publishers/{id}")
    public ResponseEntity<Map<String, Object>> updatePublisher(@PathVariable Long id, @RequestBody publisher publisher) {
        logger.info("PUT /api/library/publishers/" + id);
        try {
            publisher.setId(id);
            publisher updatedPublisher = libraryService.updatePublisher(publisher);
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Publisher updated successfully");
            response.put("data", updatedPublisher);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error updating publisher", e);
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", "error");
            errorResponse.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @Override
    @DeleteMapping("/publishers/{id}")
    public ResponseEntity<Map<String, Object>> deletePublisher(@PathVariable Long id) {
        logger.info("DELETE /api/library/publishers/" + id);
        try {
            boolean deleted = libraryService.deletePublisher(id);
            Map<String, Object> response = new HashMap<>();
            if (deleted) {
                response.put("status", "success");
                response.put("message", "Publisher deleted successfully");
                return ResponseEntity.ok(response);
            } else {
                response.put("status", "error");
                response.put("message", "Publisher not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            logger.error("Error deleting publisher", e);
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", "error");
            errorResponse.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }
}