package integretation;

import bookstoreApp.dto.AuthorDto;
import bookstoreApp.dto.BookDto;
import bookstoreApp.service.authorBook.AuthorBookService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@Configuration
@EnableJpaRepositories(basePackages = "bookstoreApp.repository")
@PropertySource("classpath:testing.properties")
@ComponentScan({"bookstoreApp.launcher", "bookstoreApp.entity", "bookstoreApp.dto", "bookstoreApp.repository", "bookstoreApp.service", "bookstoreApp.controller", "bookstoreApp.converter", "bookstoreApp.config"})
@EntityScan(basePackages ={"bookstoreApp.entity"})
public class AuthorBookServiceIntegTest {
    @Autowired
    private AuthorBookService authorBookService;

    @Before
    public void setup(){
        authorBookService.deleteAll();
    }

    @Test
    public void createAuthor(){
        AuthorDto authorDto= new AuthorDto("Radu Faur");
        AuthorDto backAuthorDto =authorBookService.createAuthor(authorDto);
        AuthorDto tryAuthorDto =authorBookService.findByIdAuthor(backAuthorDto.id);
        Assert.assertNotNull(tryAuthorDto);
    }

    @Test
    public void findAllAuthors(){
        int initialSize = authorBookService.findAllAuthors().size();
        AuthorDto authorDto1= new AuthorDto("Liliana Faur");
        AuthorDto authorDto2= new AuthorDto("Andrei Faur");
        authorBookService.createAuthor(authorDto1);
        authorBookService.createAuthor(authorDto2);
        Assert.assertTrue(authorBookService.findAllAuthors().size()==initialSize+2);
    }

    @Test
    public void updateAuthor(){
        AuthorDto authorDto= new AuthorDto("Anca Faur");
        AuthorDto backAuthorDto =authorBookService.createAuthor(authorDto);
        backAuthorDto.setName("Anca Maria Faur");
        authorBookService.updateAuthor(backAuthorDto);
        Assert.assertTrue(authorBookService.findByIdAuthor(backAuthorDto.id).name.equals("Anca Maria Faur"));
    }

    @Test
    public void deleteAuthor(){
        AuthorDto authorDto= new AuthorDto("Andrada Faur");
        AuthorDto backAuthorDto =authorBookService.createAuthor(authorDto);
        authorBookService.deleteAuthor(backAuthorDto.id);
        Assert.assertTrue(authorBookService.findAuthorsByName("Andrada Faur").size()==0);
    }

    @Test
    public void addBookToAuthor(){
        AuthorDto authorDto= new AuthorDto("Domnica Simedru");
        AuthorDto backAuthorDto =authorBookService.createAuthor(authorDto);
        Long authorId= backAuthorDto.id;
        int booksBefore = authorBookService.findBooksForAuthor(authorId).size();
        BookDto bookDto = new BookDto(authorId, "Multumim Domnului", "religious", "10101", 3000, 10);
        authorBookService.addBookToAuthor(bookDto);
        int booksAfter = authorBookService.findBooksForAuthor(authorId).size();
        Assert.assertTrue(booksBefore+1 == booksAfter);
    }

    @Test
    public void removeBookFromAuthor(){
        AuthorDto authorDto= new AuthorDto("Vlad Simedru");
        AuthorDto backAuthorDto =authorBookService.createAuthor(authorDto);
        Long authorId= backAuthorDto.id;
        int booksBefore = authorBookService.findBooksForAuthor(authorId).size();
        BookDto bookDto = new BookDto(authorId, "Viata buna", "biography", "12555", 1000, 102);
        authorBookService.addBookToAuthor(bookDto);
        BookDto bookBack = authorBookService.findBookByIsbn("12555");
        authorBookService.removeBookFromAuthor(bookBack.id);
        int booksAfter = authorBookService.findBooksForAuthor(authorId).size();
        Assert.assertTrue(booksBefore == booksAfter);
    }

    @Test
    public void updateBookFromAuthor(){
        AuthorDto authorDto= new AuthorDto("Irina Simedru");
        AuthorDto backAuthorDto =authorBookService.createAuthor(authorDto);

        BookDto bookDto = new BookDto(backAuthorDto.id, "Nunta in rai", "biography", "13626", 3000, 1202F);
        authorBookService.addBookToAuthor(bookDto);

        BookDto bookBack = authorBookService.findBookByIsbn("13626");

        bookBack.setGenre("thriller");
        authorBookService.updateBookFromAuthor(bookBack);

        Assert.assertTrue(authorBookService.findBookByIsbn("13626").genre.equals("thriller"));
    }

}
