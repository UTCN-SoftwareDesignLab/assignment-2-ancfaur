package bookstoreApp.service.authorBook;
import bookstoreApp.dto.AuthorBookDto;
import bookstoreApp.dto.AuthorDto;
import bookstoreApp.dto.BookDto;
import bookstoreApp.dto.SaleBookDto;

import java.util.List;

public interface AuthorBookService {
    List<AuthorDto> findAllAuthors();

    AuthorDto findByIdAuthor(Long id);

    void createAuthor(AuthorDto authorDto);

    void deleteAuthor(Long id);

    void updateAuthor(AuthorDto authorDto);

    void addBookToAuthor(BookDto bookDto);

    void removeBookFromAuthor(Long bookId);

    void updateBookFromAuthor(BookDto bookDto);

    void sellBookFromAuthor(SaleBookDto saleBookDto) throws LimittedStockException;

    List<AuthorBookDto> findAllBooksAuthors();
}
