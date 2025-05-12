package com.example.database_project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;//@pathvariabble
import java.util.List;
@RestController
@RequestMapping("/api/books")
public class BookController {
//    BookRepository bookRepository;
//
//    public BookController(BookRepository bookRepository) {
//        this.bookRepository = bookRepository;
//    }
//    @GetMapping
//    public List<Book> getAllBooks() {
//        return bookRepository.findAll();  // This retrieves all books from the database
//    }
private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
   public List<Book> getAllBooks() {
       return bookService.findAllBooks();  // This retrieves all books from the database
    }
    @GetMapping("/{id}")//http://localhost:8080/api/books/1
    public ResponseEntity<Book> getProductById(@PathVariable Long id) {
        return bookService.findProductById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public Book createBook(@RequestBody Book book) {
//        return bookService.saveBook(book);
//    }
@PostMapping //http://localhost:8080/api/books
@ResponseStatus(HttpStatus.CREATED)
public Book createBook(@RequestBody Book book) {
    if (book.getId() != null) {
        throw new IllegalArgumentException("ID must not be provided when creating a book.");
    }
    return bookService.saveBook(book);
}
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        return bookService.updateBook(id, updatedBook)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
//    @GetMapping("/search")
//    public List<Book> searchBooksByTitle(@RequestParam String title) {
//        return bookService.findBooksByTitle(title);
//    }

//    @GetMapping("/search")
//    public List<Book> searchBooksByTitle(@RequestParam String title) {
//        return bookService.findBooksByTitle(title);
//    }
//

    @GetMapping("/search")
    public List<Book> searchBooks(@RequestParam(required = false) String title,
                                  @RequestParam(required = false) String author) {

        if (title != null) {
            System.out.println("Searching for: " + title);
            return bookService.findBooksByTitle(title);
        } else if (author != null) {
            return bookService.findBooksByAuthor(author);
        }
        return bookService.findAllBooks();  // Returns all books if no filter is applied
    }

}
//CREATE TABLE books (
       // id INT PRIMARY KEY,
      //  title VARCHAR(100) NOT NULL,
//author VARCHAR(100) NOT NULL
//);