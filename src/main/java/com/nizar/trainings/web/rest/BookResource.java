package com.nizar.trainings.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.nizar.trainings.domain.Book;
import com.nizar.trainings.service.BookService;
import com.nizar.trainings.web.rest.util.HeaderUtil;
import com.nizar.trainings.web.rest.dto.BookDTO;
import com.nizar.trainings.web.rest.mapper.BookMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * REST controller for managing Book.
 */
@RestController
@RequestMapping("/api")
public class BookResource {

    private final Logger log = LoggerFactory.getLogger(BookResource.class);
        
    @Inject
    private BookService bookService;
    
    @Inject
    private BookMapper bookMapper;
    
    /**
     * POST  /books -> Create a new book.
     */
    @RequestMapping(value = "/books",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO bookDTO) throws URISyntaxException {
        log.debug("REST request to save Book : {}", bookDTO);
        if (bookDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("book", "idexists", "A new book cannot already have an ID")).body(null);
        }
        BookDTO result = bookService.save(bookDTO);
        return ResponseEntity.created(new URI("/api/books/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("book", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /books -> Updates an existing book.
     */
    @RequestMapping(value = "/books",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<BookDTO> updateBook(@RequestBody BookDTO bookDTO) throws URISyntaxException {
        log.debug("REST request to update Book : {}", bookDTO);
        if (bookDTO.getId() == null) {
            return createBook(bookDTO);
        }
        BookDTO result = bookService.save(bookDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("book", bookDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /books -> get all the books.
     */
    @RequestMapping(value = "/books",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional(readOnly = true)
    public List<BookDTO> getAllBooks() {
        log.debug("REST request to get all Books");
        return bookService.findAll();
            }

    /**
     * GET  /books/:id -> get the "id" book.
     */
    @RequestMapping(value = "/books/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<BookDTO> getBook(@PathVariable Long id) {
        log.debug("REST request to get Book : {}", id);
        BookDTO bookDTO = bookService.findOne(id);
        return Optional.ofNullable(bookDTO)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /books/:id -> delete the "id" book.
     */
    @RequestMapping(value = "/books/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        log.debug("REST request to delete Book : {}", id);
        bookService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("book", id.toString())).build();
    }
}
