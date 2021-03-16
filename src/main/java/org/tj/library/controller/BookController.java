package org.tj.library.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tj.library.model.Book;
import org.tj.library.service.BookService;

@RestController
@RequestMapping("/book")
public class BookController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookController.class);
    private BookService bookService;

    @PostMapping
    public ResponseEntity addBook(@RequestBody Book book) {
        LOGGER.info("post /book is called");
        bookService.addBook(book);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity getAllBooks() {
        LOGGER.info("get /book is called");

        return ResponseEntity.ok()
                .body(bookService.getAllBooks());
    }

    @GetMapping("/{bookId}")
    public ResponseEntity getBook(@PathVariable Integer bookId) {
        LOGGER.info("get /book/{} is called", bookId);

        return ResponseEntity.ok()
                .body(bookService.getBook(bookId));
    }

    @PutMapping
    public ResponseEntity updateBook(@RequestBody Book book) {
        LOGGER.info("put /book/{} is called", book.getId());
        bookService.updateBook(book);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping
    public ResponseEntity updateBookLocation(@RequestParam Integer id, @RequestParam String nl) {
        LOGGER.info("patch /book?id={}&nl={}", id, nl);
        bookService.patchBookLocation(id, nl);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity deleteBook(@PathVariable Integer bookId) {
        LOGGER.info("delete /book/{} is called", bookId);
        bookService.deleteBook(bookId);

        return ResponseEntity.noContent().build();
    }

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
}
