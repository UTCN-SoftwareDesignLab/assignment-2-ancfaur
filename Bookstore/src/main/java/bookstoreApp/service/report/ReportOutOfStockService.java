package bookstoreApp.service.report;

import bookstoreApp.entity.Book;

import java.io.IOException;
import java.util.List;

public interface ReportOutOfStockService {
    List<Book> writeOutOfStockReport(String formatType) throws IOException;
}
