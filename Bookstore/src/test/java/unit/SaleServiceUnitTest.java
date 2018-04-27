package unit;

import bookstoreApp.dto.SaleBookDto;
import bookstoreApp.entity.Author;
import bookstoreApp.entity.Book;
import bookstoreApp.repository.author.AuthorRepository;
import bookstoreApp.repository.book.BookRepository;
import bookstoreApp.service.sale.LimittedStockException;
import bookstoreApp.service.sale.SaleService;
import bookstoreApp.service.sale.SaleServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
public class SaleServiceUnitTest {
    private SaleService saleService;
    private static final Long BOOK_ID = new Long(1);
    private static final float BOOK_PRICE = 100.0F;
    @Mock
    private BookRepository bookRepository;
    @Mock
    private AuthorRepository authorRepository;

    @Before
    public void setup(){
        saleService = new SaleServiceImpl(bookRepository, authorRepository);
        Author author = new Author("Dumitrel");
        Book book = new Book("Ce veste minunata", author, "99999", "comedy", BOOK_PRICE, 100);
        book.setId(BOOK_ID);
        when(bookRepository.findById(BOOK_ID)).thenReturn(Optional.of(book));
        when(authorRepository.save(any(Author.class))).thenReturn(author);
    }

    @Test
    public void saleWithoutLimittedStockException(){
        SaleBookDto saleBookDtoOK= new SaleBookDto();
        saleBookDtoOK.bookId = BOOK_ID;
        saleBookDtoOK.saleQuantity =30;

        try {
            float salePrice = saleService.sellBook(saleBookDtoOK);
            Assert.assertEquals((saleBookDtoOK.saleQuantity* BOOK_PRICE), salePrice, 0.0002);
            assert true;
        } catch (LimittedStockException e) {
            assert false;
        }
    }

    @Test
    public void saleWithLimittedStockException(){
        SaleBookDto saleBookDtoBAD= new SaleBookDto();
        saleBookDtoBAD.bookId = BOOK_ID;
        saleBookDtoBAD.saleQuantity =200;
        try {
            saleService.sellBook(saleBookDtoBAD);
            assert false;
        } catch (LimittedStockException e) {
            assert true;
        }
    }


}
