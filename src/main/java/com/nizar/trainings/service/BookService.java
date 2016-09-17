package com.nizar.trainings.service;

import com.nizar.trainings.domain.Book;
import com.nizar.trainings.repository.BookRepository;
import com.nizar.trainings.web.rest.dto.BookDTO;
import com.nizar.trainings.web.rest.mapper.BookMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Book.
 */
@Service
@Transactional
public class BookService {

    private final Logger log = LoggerFactory.getLogger(BookService.class);
    
    @Inject
    private BookRepository bookRepository;
    
    @Inject
    private BookMapper bookMapper;
    
    /**
     * Save a book.
     * @return the persisted entity
     */
    public BookDTO save(BookDTO bookDTO) {
        log.debug("Request to save Book : {}", bookDTO);
        Book book = bookMapper.bookDTOToBook(bookDTO);
        book = bookRepository.save(book);
        BookDTO result = bookMapper.bookToBookDTO(book);
        return result;
    }

    /**
     *  get all the books.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<BookDTO> findAll() {
        log.debug("Request to get all Books");
        List<BookDTO> result = bookRepository.findAll().stream()
            .map(bookMapper::bookToBookDTO)
            .collect(Collectors.toCollection(LinkedList::new));
        return result;
    }

    /**
     *  get one book by id.
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public BookDTO findOne(Long id) {
        log.debug("Request to get Book : {}", id);
        Book book = bookRepository.findOne(id);
        BookDTO bookDTO = bookMapper.bookToBookDTO(book);
        return bookDTO;
    }

    /**
     *  delete the  book by id.
     */
    public void delete(Long id) {
        log.debug("Request to delete Book : {}", id);
        bookRepository.delete(id);
    }
}
