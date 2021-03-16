package org.tj.library.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.tj.library.model.Book;
import org.tj.library.repository.BookRepository;
import org.tj.library.validation.ClientException;

import java.util.List;

@Service
public class BookService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookService.class);
    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void addBook(Book book) {
        LOGGER.info("add book {}", book.getTitle());
        bookRepository.insertIntoBook(book);
    }

    public List<Book> getAllBooks() {
        LOGGER.info("get all books");

        return bookRepository.selectAllBooks();
    }

    public Book getBook(Integer bookId) {
        LOGGER.info("get book {}", bookId);

        return bookRepository.selectBook(bookId);
    }

    public void updateBook(Book book) {
        LOGGER.info("update book {}", book.getId());
        int updatedRow = bookRepository.updateBook(book);
        if (updatedRow == 0) {
            LOGGER.info("No book found by id: {}.  Throwing ClientException", book.getId());
            throw new ClientException("No book found!", HttpStatus.NOT_FOUND);
        }
    }

    public void patchBookLocation(Integer bookId, String newLocation) {
        LOGGER.info("patch book {} location to {}", bookId, newLocation);
        int updatedRow = bookRepository.updateLocationOfBook(bookId, newLocation);
        if (updatedRow == 0) {
            LOGGER.info("No book found by id: {}.  Throwing ClientException", bookId);
            throw new ClientException("No book found!", HttpStatus.NOT_FOUND);
        }
    }

    public void deleteBook(Integer bookId) {
        LOGGER.info("delete book {}", bookId);
        int updatedRow = bookRepository.deleteBook(bookId);
        if (updatedRow == 0) {
            LOGGER.info("No book found by id: {}.  Throwing ClientException", bookId);
            throw new ClientException("No book found!", HttpStatus.NOT_FOUND);
        }
    }
}
