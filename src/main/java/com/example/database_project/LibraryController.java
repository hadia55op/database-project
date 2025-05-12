package com.example.database_project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libraries")
public class LibraryController {

    @Autowired
    private LibraryRepository libraryRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Library createLibrary(@RequestBody Library library) {
        // Link each book back to this library
        if (library.getBooks() != null) {
            library.getBooks().forEach(book -> book.setLibrary(library));
        }
        return libraryRepository.save(library);
    }

    @GetMapping
    public List<Library> getAllLibraries() {
        return libraryRepository.findAll();
    }
}


