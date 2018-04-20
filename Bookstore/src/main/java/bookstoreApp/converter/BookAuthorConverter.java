package bookstoreApp.converter;

import bookstoreApp.dto.AuthorDto;
import bookstoreApp.dto.BookDto;
import bookstoreApp.entity.Author;
import bookstoreApp.entity.Book;

public interface BookAuthorConverter {
    public Book fromBookDtoToBook(BookDto bookDto, Author author);
    public BookDto fromBookToBookDto(Book book);
    public Author fromAuthorDtoToAuthor(AuthorDto authorDto);
    public AuthorDto fromAuthorToAuthorDto(Author author);

}
