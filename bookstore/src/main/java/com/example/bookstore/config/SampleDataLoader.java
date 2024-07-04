package com.example.bookstore.config;

import com.example.bookstore.model.Book;
import com.example.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class SampleDataLoader implements CommandLineRunner {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public void run(String... args) throws Exception {
        bookRepository.deleteAll();

      
        List<Book> books = Arrays.asList(
            new Book("The Great Gatsby", "F. Scott Fitzgerald", "9780743273565"),
            new Book("To Kill a Mockingbird", "Harper Lee", "9780061120084"),
            new Book("1984", "George Orwell", "9780451524935"),
            new Book("The Catcher in the Rye", "J.D. Salinger", "9780316769488"),
            new Book("Moby-Dick", "Herman Melville", "9780142437247"),
            new Book("Pride and Prejudice", "Jane Austen", "9780141439518"),
            new Book("The Hobbit", "J.R.R. Tolkien", "9780547928227"),
            new Book("Harry Potter and the Philosopher's Stone", "J.K. Rowling", "9781408855652"),
            new Book("The Lord of the Rings", "J.R.R. Tolkien", "9780618640157"),
            new Book("Alice's Adventures in Wonderland", "Lewis Carroll", "9781503275923"),
            new Book("The Chronicles of Narnia", "C.S. Lewis", "9780064471190"),
            new Book("Don Quixote", "Miguel de Cervantes", "9780060934347"),
            new Book("War and Peace", "Leo Tolstoy", "9780143039990"),
            new Book("The Odyssey", "Homer", "9780140268867"),
            new Book("Anna Karenina", "Leo Tolstoy", "9780143035008"),
            new Book("The Grapes of Wrath", "John Steinbeck", "9780143039433"),
            new Book("One Hundred Years of Solitude", "Gabriel Garcia Marquez", "9780060883287"),
            new Book("The Picture of Dorian Gray", "Oscar Wilde", "9780141439570"),
            new Book("Frankenstein", "Mary Shelley", "9780486282114"),
            new Book("Wuthering Heights", "Emily Bronte", "9780141439556"),
            new Book("The Divine Comedy", "Dante Alighieri", "9780141197494")
        );

       
        bookRepository.saveAll(books);
    }
}
