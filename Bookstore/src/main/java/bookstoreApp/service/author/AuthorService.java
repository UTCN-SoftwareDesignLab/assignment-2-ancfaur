package bookstoreApp.service.author;
import bookstoreApp.dto.AuthorDto;
import bookstoreApp.dto.BookDto;
import bookstoreApp.dto.SaleBookDto;

import java.util.List;

public interface AuthorService {
    List<AuthorDto> findAll();
    AuthorDto findById(Long id);
    void create(AuthorDto authorDto);
    void delete(Long id);
    void addBookToAuthor(Long authorId, BookDto bookDto);
    void removeBookFromAuthor(Long authorId, Long bookId);
    void update(AuthorDto authorDto);
    void sellBookFromAuthor(SaleBookDto saleBookDto) throws LimittedStockException;
    List<BookDto> listBooksByAuthor(String name);
    void updateBookFromAuthor(BookDto bookDto, Long oldAuthorId);
}
