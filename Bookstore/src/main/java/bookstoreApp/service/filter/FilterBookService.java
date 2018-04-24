package bookstoreApp.service.filter;

import bookstoreApp.dto.AuthorBookDto;

import java.util.List;

public interface FilterBookService {
    public List<AuthorBookDto> filterByTitleAndGenreAndAuthor(String common);
}
