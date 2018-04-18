package bookstoreApp.service.book;

import bookstoreApp.dto.BookDto;
import bookstoreApp.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> findAll();
    List<Book> findByGender(String gender);
    List<Book> findByName(String name);
    void delete(String isbn);
}
