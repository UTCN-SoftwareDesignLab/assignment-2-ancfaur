package bookstoreApp.service.authorBook;

import bookstoreApp.dto.BookDto;

import java.util.List;

public interface FilterBookService {
    List<BookDto> findAll();
    List<BookDto> findByGenre(String gender);
    List<BookDto> findByName(String name);
    List<BookDto> filterByAuthor(String name);


}
