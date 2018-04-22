package bookstoreApp.service.report;

import bookstoreApp.entity.Book;

import java.io.IOException;
import java.util.List;

public interface Formater {
    void formatBooks(List<Book> book) throws IOException;
}
