package unit;

import bookstoreApp.entity.Author;
import bookstoreApp.entity.Book;
import bookstoreApp.repository.book.BookRepository;
import bookstoreApp.service.report.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
public class ReportServiceUnitTest {
    private ReportOutOfStockService reportOutOfStockService;
    @Mock
    private BookRepository bookRepository;
    @Mock
    private FormatDelegator formatDelegator;
    private static final String REQUEST_CSV="csv";
    private static final String REQUEST_PDF="pdf";

    @Before
    public void setup() {
        this.reportOutOfStockService = new ReportOutOfStockServiceImpl(bookRepository, formatDelegator);
        Author author = new Author("Vladut Mihaila");
        Book book = new Book("Ce veste minunata", author, "99999", "comedy", 12.3F, 0);
        book.setId(new Long(1));
        List<Book> books = new ArrayList<>();
        books.add(book);
        Mockito.when(bookRepository.findByQuantity(0)).thenReturn(books);
        Mockito.when(formatDelegator.selectFormat(REQUEST_CSV)).thenReturn(new CsvFormater());
        Mockito.when(formatDelegator.selectFormat(REQUEST_PDF)).thenReturn(new PdfFormater());
    }

    @Test
    public void writeReports(){
        try {
            reportOutOfStockService.writeOutOfStockReport(REQUEST_CSV);
            reportOutOfStockService.writeOutOfStockReport(REQUEST_PDF);
            assert true;
        } catch (IOException e) {
            assert false;
        }
    }
}
