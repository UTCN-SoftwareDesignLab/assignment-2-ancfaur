package bookstoreApp.service.authorBook;

import bookstoreApp.dto.AuthorBookDto;
import bookstoreApp.dto.AuthorDto;
import bookstoreApp.dto.BookDto;

import java.util.List;

public interface AuthorBookService {
    List<AuthorDto> findAllAuthors();

    AuthorDto findByIdAuthor(Long id);

    AuthorDto createAuthor(AuthorDto authorDto);

    void deleteAuthor(Long id);

    void updateAuthor(AuthorDto authorDto);

    void addBookToAuthor(BookDto bookDto);

    void removeBookFromAuthor(Long bookId);

    void updateBookFromAuthor(BookDto bookDto);

    List<AuthorBookDto> findAllBooksAuthors();

    List<AuthorDto> findAuthorsByName(String name);

    List<BookDto> findBooksForAuthor(Long authorId);

    void deleteAll();

    BookDto findBookByIsbn(String isbn);
}
