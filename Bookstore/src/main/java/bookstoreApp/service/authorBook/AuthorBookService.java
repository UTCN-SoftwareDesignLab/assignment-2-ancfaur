package bookstoreApp.service.authorBook;
import bookstoreApp.dto.AuthorDto;
import bookstoreApp.dto.BookDto;
import bookstoreApp.dto.SaleBookDto;

import java.util.List;

public interface AuthorBookService {
    List<AuthorDto> findAll();
    AuthorDto findByIdAuthor(Long id);
    void createAuthor(AuthorDto authorDto);
    void deleteAuthor(Long id);
    void updateAuthor(AuthorDto authorDto);
    void addBookToAuthor(Long authorId, BookDto bookDto);
    void removeBookFromAuthor(Long authorId, Long bookId);
    void updateBookFromAuthor(BookDto bookDto, Long oldAuthorId);


    void sellBookFromAuthor(SaleBookDto saleBookDto) throws LimittedStockException;

}
