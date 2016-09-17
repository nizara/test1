package com.nizar.trainings.web.rest.dto;

import java.io.Serializable;
import java.util.Objects;


/**
 * A DTO for the Book entity.
 */
public class BookDTO implements Serializable {

    private Long id;

    private String book_name;


    private String book_author;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }
    public String getBook_author() {
        return book_author;
    }

    public void setBook_author(String book_author) {
        this.book_author = book_author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BookDTO bookDTO = (BookDTO) o;

        if ( ! Objects.equals(id, bookDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "BookDTO{" +
            "id=" + id +
            ", book_name='" + book_name + "'" +
            ", book_author='" + book_author + "'" +
            '}';
    }
}
