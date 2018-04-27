package unit;

import bookstoreApp.converter.BookAuthorConverterImpl;
import bookstoreApp.dto.AuthorBookDto;
import bookstoreApp.dto.AuthorDto;
import bookstoreApp.entity.Author;
import bookstoreApp.entity.Book;
import bookstoreApp.repository.author.AuthorRepository;
import bookstoreApp.repository.book.BookRepository;
import bookstoreApp.service.authorBook.AuthorBookService;
import bookstoreApp.service.authorBook.AuthorBookServiceImpl;
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
public class AuthorBookServiceUnitTest {
    private AuthorBookService authorBookService;
    @Mock
    private AuthorRepository authorRepository;
    @Mock
    private BookRepository bookRepository;


    @Before
    public void setup() {
        this.authorBookService = new AuthorBookServiceImpl(authorRepository, bookRepository, new BookAuthorConverterImpl());
        Author author1 = new Author("Vladut Mihaila");
        Author author2 = new Author("Cimpoian Timonescu");
        List<Author> authors = new ArrayList<>();
        authors.add(author1);
        authors.add(author2);
        Mockito.when(authorRepository.findAll()).thenReturn(authors);

        Book book1 = new Book("Ce veste minunata", author1, "99999", "comedy", 12.3F, 100);
        Book book2 = new Book("Cer frumos", author2, "88888", "romantic", 134.5F, 100);

        List<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);

        Mockito.when(bookRepository.findAll()).thenReturn(books);
    }

    @Test
    public void findAllAuthors(){
        List<AuthorDto>authorDtos =authorBookService.findAllAuthors();
        Assert.assertEquals(authorDtos.size(), 2);
    }

    @Test
    public void findAllBooksAuthors(){
        List<AuthorBookDto>authorBookDtos =authorBookService.findAllBooksAuthors();
        Assert.assertEquals(authorBookDtos.size(), 2);
    }

}
