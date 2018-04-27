package bookstoreApp.service.report;

import bookstoreApp.entity.Book;
import bookstoreApp.repository.book.BookRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ReportOutOfStockServiceImpl implements ReportOutOfStockService {
    private BookRepository bookRepository;
    private Formater formater;
    private FormatDelegator formatDelegator;



    public ReportOutOfStockServiceImpl(BookRepository bookRepository, FormatDelegator formatDelegator){
        this.bookRepository = bookRepository;
        this.formatDelegator = formatDelegator;
    }

    private List<Book> findBooksOutOfStock(){
        return bookRepository.findByQuantity(new Integer(0));
    }

    public List<Book> writeOutOfStockReport(String reportType) throws IOException {
        formater= formatDelegator.selectFormat(reportType);
        List<Book> books = findBooksOutOfStock();
        formater.formatBooks(books);
        return books;
    }

}
