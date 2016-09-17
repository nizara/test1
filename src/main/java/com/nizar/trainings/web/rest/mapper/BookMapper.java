package com.nizar.trainings.web.rest.mapper;

import com.nizar.trainings.domain.*;
import com.nizar.trainings.web.rest.dto.BookDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Book and its DTO BookDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BookMapper {

    BookDTO bookToBookDTO(Book book);

    Book bookDTOToBook(BookDTO bookDTO);
}
