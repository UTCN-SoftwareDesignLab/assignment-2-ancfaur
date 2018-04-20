package bookstoreApp.service.book;

import bookstoreApp.dto.BookDto;
import bookstoreApp.dto.SaleBookDto;
import bookstoreApp.service.author.LimittedStockException;

import java.util.List;

public interface BookService {
    List<BookDto> findAll();
    List<BookDto> findByGender(String gender);
    List<BookDto> findByName(String name);
    void delete(String isbn);
    void sellBook(SaleBookDto saleBookDto) throws LimittedStockException;
    void update(BookDto bookDto);
}
