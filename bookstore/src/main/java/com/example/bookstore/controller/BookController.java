package com.example.bookstore.controller;

import com.example.bookstore.model.Book;
import com.example.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable String id) {
        Optional<Book> book = bookService.getBookById(id);
        return book.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookService.saveBook(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable String id, @RequestBody Book bookDetails) {
        Optional<Book> book = bookService.getBookById(id);
        if (book.isPresent()) {
            Book bookToUpdate = book.get();
            bookToUpdate.setTitle(bookDetails.getTitle());
            bookToUpdate.setAuthor(bookDetails.getAuthor());
            bookToUpdate.setIsbn(bookDetails.getIsbn());
            return ResponseEntity.ok(bookService.saveBook(bookToUpdate));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable String id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public List<Book> searchBooks(@RequestParam String title) {
        return bookService.searchBooksByTitle(title);
    }

    @GetMapping("/paged")
    public Page<Book> getBooksPaged(@RequestParam int page, @RequestParam int size) {
        return bookService.getBooksPaged(PageRequest.of(page, size));
    }

    @GetMapping("/sorted")
    public List<Book> getBooksSorted(@RequestParam String sortBy) {
        return bookService.getBooksSorted(Sort.by(sortBy));
    }

    @PostMapping("/import")
    public ResponseEntity<Void> importBooks(@RequestBody List<Book> books) {
        bookService.importBooks(books);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/export")
    public List<Book> exportBooks() {
        return bookService.getAllBooks();
    }
}
