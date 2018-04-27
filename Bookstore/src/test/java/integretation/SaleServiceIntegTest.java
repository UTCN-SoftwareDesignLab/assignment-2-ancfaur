package integretation;

import bookstoreApp.dto.AuthorDto;
import bookstoreApp.dto.BookDto;
import bookstoreApp.dto.SaleBookDto;
import bookstoreApp.service.authorBook.AuthorBookService;
import bookstoreApp.service.sale.LimittedStockException;
import bookstoreApp.service.sale.SaleService;
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
@PropertySource("classpath:testing.properties")
@EnableJpaRepositories(basePackages = "bookstoreApp.repository")
@ComponentScan({"bookstoreApp.launcher", "bookstoreApp.entity", "bookstoreApp.dto", "bookstoreApp.repository", "bookstoreApp.service", "bookstoreApp.controller", "bookstoreApp.converter", "bookstoreApp.config"})
@EntityScan(basePackages ={"bookstoreApp.entity"})
public class SaleServiceIntegTest {
    @Autowired
    private SaleService saleService;
    @Autowired
    private AuthorBookService authorBookService;
    private SaleBookDto saleBookDto;
    private static final int INITIAL_QUANTITY=100;
    private static final float BOOK_PRICE = 200.5F;
    private static final String BOOK_ISBN = "12345";

    @Before
    public void setup(){
        authorBookService.deleteAll();

        AuthorDto authorDto= new AuthorDto("Domnica Simedru");
        AuthorDto backAuthorDto =authorBookService.createAuthor(authorDto);
        BookDto bookDto1 = new BookDto(backAuthorDto.id, "Multumim Domnului ", "religious", BOOK_ISBN, INITIAL_QUANTITY, BOOK_PRICE);
        authorBookService.addBookToAuthor(bookDto1);
        BookDto bookBack = authorBookService.findBookByIsbn(BOOK_ISBN);

        saleBookDto = new SaleBookDto();
        saleBookDto.bookId = bookBack.id;
    }

    @Test
    public void saleWithLimittedStockException(){
        saleBookDto.saleQuantity = 300;
        try {
            saleService.sellBook(saleBookDto);
            assert false;
        } catch (LimittedStockException e) {
            Assert.assertEquals(INITIAL_QUANTITY,authorBookService.findBookByIsbn(BOOK_ISBN).quantity);
            assert true;
        }
    }


    @Test
    public void saleWithoutLimittedStockException(){
        saleBookDto.saleQuantity = 30;

        try {
            float salePrice = saleService.sellBook(saleBookDto);
            Assert.assertEquals((saleBookDto.saleQuantity* BOOK_PRICE), salePrice, 0.0002);
            Assert.assertEquals(INITIAL_QUANTITY - saleBookDto.saleQuantity,authorBookService.findBookByIsbn(BOOK_ISBN).quantity);
        } catch (LimittedStockException e) {
            assert false;
        }
    }

}
