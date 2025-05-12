package com.example.database_project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    // Spring Data JPA skapar automatiskt implementationer för standardmetoder som:
    // findAll(), findById(), save(), deleteById() etc.

    // Du kan också lägga till anpassade sökmetoder:
    //List<Book> findAll();

       // List<Book> findByTitle(String title);
//       List<Book> findByTitleContainingIgnoreCase(String title);
       List<Book> findByTitleContainingIgnoreCase(String title);
    List<Book> findByAuthorContainingIgnoreCase(String author);

}


