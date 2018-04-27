package integretation;

import bookstoreApp.dto.AuthorBookDto;
import bookstoreApp.dto.AuthorDto;
import bookstoreApp.dto.BookDto;
import bookstoreApp.service.authorBook.AuthorBookService;
import bookstoreApp.service.filter.FilterBookService;
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

import java.util.List;

@RunWith(SpringRunner.class)
@Configuration
@PropertySource("classpath:testing.properties")
@EnableJpaRepositories(basePackages = "bookstoreApp.repository")
@ComponentScan({"bookstoreApp.launcher", "bookstoreApp.entity", "bookstoreApp.dto", "bookstoreApp.repository", "bookstoreApp.service", "bookstoreApp.controller", "bookstoreApp.converter", "bookstoreApp.config"})
@EntityScan(basePackages ={"bookstoreApp.entity"})

public class FilterServiceIntegTest {
    @Autowired
    private FilterBookService filterBookService;
    @Autowired
    private AuthorBookService authorBookService;
    private static final String TEST_STRING= "Minune";

    @Before
    public void setup(){
        authorBookService.deleteAll();
    }

    @Test
    public void filterWhenNone(){
        List<AuthorBookDto> authorBookDtos= filterBookService.filterByTitleAndGenreAndAuthor(TEST_STRING);
        Assert.assertTrue(authorBookDtos.size()==0);
    }

    @Test
    public void filterWhenThereAre(){
        AuthorDto authorDto1= new AuthorDto("Domnica Simedru");
        AuthorDto backAuthorDto1 =authorBookService.createAuthor(authorDto1);

        AuthorDto authorDto2= new AuthorDto("Simina Faur"+ TEST_STRING);
        AuthorDto backAuthorDto2 =authorBookService.createAuthor(authorDto2);

        BookDto bookDto1 = new BookDto(backAuthorDto1.id, "Multumim Domnului pentru" + TEST_STRING, "religious", "10101", 3000, 10);
        BookDto bookDto2 = new BookDto(backAuthorDto1.id, "Ce lume frumoasa", "religious", "15155", 300, 120);
        BookDto bookDto3 = new BookDto(backAuthorDto2.id, "Iubim pentru ca traim", "religious", "16155", 3200, 150);
        authorBookService.addBookToAuthor(bookDto1);
        authorBookService.addBookToAuthor(bookDto2);
        authorBookService.addBookToAuthor(bookDto3);

        List<AuthorBookDto> authorBookDtos= filterBookService.filterByTitleAndGenreAndAuthor(TEST_STRING);
        Assert.assertEquals(2, authorBookDtos.size());
    }



}
