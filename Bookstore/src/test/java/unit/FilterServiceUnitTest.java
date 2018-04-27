package unit;

import bookstoreApp.converter.BookAuthorConverterImpl;
import bookstoreApp.dto.AuthorBookDto;
import bookstoreApp.entity.Author;
import bookstoreApp.entity.Book;
import bookstoreApp.repository.book.BookRepository;
import bookstoreApp.service.filter.FilterBookService;
import bookstoreApp.service.filter.FilterBookServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")

public class FilterServiceUnitTest {
    private FilterBookService filterBookService;
    private static final String TEST_STRING="test";
    @Mock
    private BookRepository bookRepository;

    @Before
    public void setup(){
        this.filterBookService = new FilterBookServiceImpl(bookRepository, new BookAuthorConverterImpl());
        Author author1 = new Author("Vladut Mihaila");
        Author author2 = new Author("Cimpoian " + TEST_STRING);

        Book book1 = new Book(TEST_STRING, author1, "99999", "comedy", 12.3F, 100);
        Book book2 = new Book("Cer frumos", author2, "88888", "romantic", 134.5F, 100);

        List<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);
        Mockito.when(bookRepository.findByTitleGenreAuthorName(TEST_STRING)).thenReturn(books);
    }

    @Test
    public void filterByTitleAndGenreAndAuthor(){
        List<AuthorBookDto>authorBookDtos = filterBookService.filterByTitleAndGenreAndAuthor(TEST_STRING);
        Assert.assertEquals(authorBookDtos.size(), 2);
    }

}
