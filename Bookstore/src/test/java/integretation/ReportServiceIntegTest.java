package integretation;

import bookstoreApp.dto.AuthorDto;
import bookstoreApp.dto.BookDto;
import bookstoreApp.service.authorBook.AuthorBookService;
import bookstoreApp.service.report.ReportOutOfStockService;
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

import java.io.IOException;

@RunWith(SpringRunner.class)
@Configuration
@PropertySource("classpath:testing.properties")
@EnableJpaRepositories(basePackages = "bookstoreApp.repository")
@ComponentScan({"bookstoreApp.launcher", "bookstoreApp.entity", "bookstoreApp.dto", "bookstoreApp.repository", "bookstoreApp.service", "bookstoreApp.controller", "bookstoreApp.converter", "bookstoreApp.config"})
@EntityScan(basePackages ={"bookstoreApp.entity"})
public class ReportServiceIntegTest {
    @Autowired
    private ReportOutOfStockService reportOutOfStockService;
    @Autowired
    private AuthorBookService authorBookService;
    private static final String TEST_CSV = "csv";
    private static final String TEST_PDF = "pdf";

    @Before
    public void setup(){
        authorBookService.deleteAll();
    }

    @Test
    public void reportWhenNone() throws IOException {
        int no1 =reportOutOfStockService.writeOutOfStockReport(TEST_CSV).size();
        int no2 =reportOutOfStockService.writeOutOfStockReport(TEST_PDF).size();
        Assert.assertEquals(0, no1);
        Assert.assertTrue(no1 == no2);
    }

    @Test
    public void reportThereIs() throws IOException {
        AuthorDto authorDto= new AuthorDto("Domnica Simedru");
        AuthorDto backAuthorDto =authorBookService.createAuthor(authorDto);
        BookDto bookDto1 = new BookDto(backAuthorDto.id, "Multumim Domnului ", "religious", "16261", 3000, 10);
        BookDto bookDto2 = new BookDto(backAuthorDto.id, "Ce lume frumoasa", "religious", "15355", 0, 120);
        authorBookService.addBookToAuthor(bookDto1);
        authorBookService.addBookToAuthor(bookDto2);

        int no1 =reportOutOfStockService.writeOutOfStockReport(TEST_CSV).size();
        int no2 =reportOutOfStockService.writeOutOfStockReport(TEST_PDF).size();
        Assert.assertEquals(1, no1);
        Assert.assertTrue(no1== no2);

    }

}
