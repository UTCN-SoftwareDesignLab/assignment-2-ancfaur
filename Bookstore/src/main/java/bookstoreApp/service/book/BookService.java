package bookstoreApp.service.book;

import bookstoreApp.dto.BookDto;
import bookstoreApp.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> findAll();
    //void delete(BookDto bookDto);
}
