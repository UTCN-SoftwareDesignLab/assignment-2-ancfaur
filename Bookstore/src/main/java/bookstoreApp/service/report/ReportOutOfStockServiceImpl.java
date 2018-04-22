package bookstoreApp.service.report;

import bookstoreApp.entity.Book;
import bookstoreApp.repository.book.BookRepository;
import bookstoreApp.service.report.csv.CSVUtilsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class ReportOutOfStockServiceImpl implements ReportOutOfStockService {
    private BookRepository bookRepository;
    private CSVUtilsImpl csvUtils;


    @Autowired
    public ReportOutOfStockServiceImpl(BookRepository bookRepository, CSVUtilsImpl csvUtils){
        this.csvUtils = csvUtils;
        this.bookRepository = bookRepository;
    }

    private List<Book> findBooksOutOfStock(){
        return bookRepository.findByQuantity(new Integer(0));
    }

    @Override
    public void writeCsvReports() throws IOException{
        List<Book> books = findBooksOutOfStock();
        Date date = new Date();
        String fileName =date.toString().replace(":", " ");
        String csvFile = "D:\\Anca\\manaBionica\\an3\\sem2\\softwareDesign\\assignments\\assignment-2-ancfaur\\Bookstore\\reports\\" +fileName +".csv";
        FileWriter writer = new FileWriter(csvFile);

        //for header
        csvUtils.writeLine(writer, Arrays.asList("Id", "ISBN", "Title", "Price", "Quantity", "Author"));

        for (Book book : books) {
            List<String> listPerBook = new ArrayList<>();
            listPerBook.add(book.getId().toString());
            listPerBook.add(book.getIsbn());
            listPerBook.add(book.getName());
            listPerBook.add(new Float (book.getPrice()).toString());
            listPerBook.add(new Integer(book.getQuantity()).toString());
            listPerBook.add(book.getAuthor().toString());
            csvUtils.writeLine(writer, listPerBook);
            }

            writer.flush();
            writer.close();
    }

    @Override
    public void writePdfReports() {

    }
}
