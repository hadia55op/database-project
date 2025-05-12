package com.example.database_project;
import com.example.database_project.Book;
import com.example.database_project.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;
    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }
    public Optional<Book> findProductById(Long id) {
        return bookRepository.findById(id);
    }
//    public Book saveBook(Book book) {
//        return bookRepository.save(book);
//    }
public Book saveBook(Book book) {
    // Set reverse side of the relationship
    if (book.getPublisher() != null) {
        book.getPublisher().setBook(book);
    }

    // Save the book (and publisher if CascadeType.ALL is enabled)
    return bookRepository.save(book);
}

    public Optional<Book> updateBook(Long id, Book updatedBook) {
        return bookRepository.findById(id).map(existingBook -> {
            existingBook.setTitle(updatedBook.getTitle());
            existingBook.setAuthor(updatedBook.getAuthor());

            Publisher updatedPublisher = updatedBook.getPublisher();
            if (updatedPublisher != null) {
                Publisher existingPublisher = existingBook.getPublisher();

                if (existingPublisher == null) {
                    // Assign new publisher
                    updatedPublisher.setBook(existingBook); // set reverse relation
                    existingBook.setPublisher(updatedPublisher);
                } else {
                    // Update fields of existing publisher
                    existingPublisher.setName(updatedPublisher.getName());
                }
            }

            return bookRepository.save(existingBook);
        });
    }


//    public Optional<Book> updateBook(Long id, Book updatedBook) {
//        return bookRepository.findById(id).map(existingBook -> {
//            existingBook.setTitle(updatedBook.getTitle());
//            existingBook.setAuthor(updatedBook.getAuthor());
//            return bookRepository.save(existingBook);
//        });
//    }

//    public List<Book> findBooksByTitle(String title) {
//        return bookRepository.findByTitle(title);
//    }
//public List<Book> findBooksByTitle(String title) {
//    return bookRepository.findByTitleContainingIgnoreCase(title);
//}
public List<Book> findBooksByTitle(String title) {
    return bookRepository.findByTitleContainingIgnoreCase(title);
}

    public List<Book> findBooksByAuthor(String author) {
        return bookRepository.findByAuthorContainingIgnoreCase(author);
    }


}
