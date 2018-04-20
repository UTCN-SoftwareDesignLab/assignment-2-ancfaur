package bookstoreApp.service.authorBook;

import bookstoreApp.dto.BookDto;

import java.util.List;

public interface FilteringService {
    List<BookDto> findAll();
    List<BookDto> findByGender(String gender);
    List<BookDto> findByName(String name);
    List<BookDto> filterByAuthor(String name);

}
