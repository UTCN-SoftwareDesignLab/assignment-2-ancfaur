package bookstoreApp.service.report;

import bookstoreApp.entity.Book;
import bookstoreApp.repository.book.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ReportOutOfStockServiceImpl implements ReportOutOfStockService {
    private BookRepository bookRepository;
    private Formater formater;


    @Autowired
    public ReportOutOfStockServiceImpl(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    private List<Book> findBooksOutOfStock(){
        return bookRepository.findByQuantity(new Integer(0));
    }

    public void writeOutOfStockReport(String reportType) throws IOException {
        formater= FormatFactory.selectFormat(reportType);
        formater.formatBooks(findBooksOutOfStock());
    }

}
