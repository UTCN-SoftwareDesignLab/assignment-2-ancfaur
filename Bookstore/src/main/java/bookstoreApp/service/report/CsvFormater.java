package bookstoreApp.service.report;
import bookstoreApp.entity.Book;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
public class CsvFormater implements Formater {
    private static final char DEFAULT_SEPARATOR = ',';

    @Override
    public void formatBooks(List<Book> books) throws IOException {
        Date date = new Date();
        String fileName =date.toString().replace(":", " ");
        String csvFile = "D:\\Anca\\manaBionica\\an3\\sem2\\softwareDesign\\assignments\\assignment-2-ancfaur\\Bookstore\\reports\\" +fileName +".csv";
        FileWriter writer = new FileWriter(csvFile);

        //for header
        writeLine(writer, Arrays.asList("Id", "ISBN", "Title", "Price", "Quantity", "Author"));

        for (Book book : books) {
            List<String> listPerBook = new ArrayList<>();
            listPerBook.add(book.getId().toString());
            listPerBook.add(book.getIsbn());
            listPerBook.add(book.getName());
            listPerBook.add(new Float (book.getPrice()).toString());
            listPerBook.add(new Integer(book.getQuantity()).toString());
            listPerBook.add(book.getAuthor().toString());
            writeLine(writer, listPerBook);
        }

        writer.flush();
        writer.close();
    }

    private void writeLine(Writer writer, List<String> values) throws IOException {
        boolean first = true;

        StringBuilder stringBuilder = new StringBuilder();
        for (String value : values) {
            if (!first) {
                stringBuilder.append(DEFAULT_SEPARATOR);
            }
            stringBuilder.append(followCVSformat(value));
            first = false;
        }
        stringBuilder.append("\n");
        writer.append(stringBuilder.toString());
    }

    private String followCVSformat(String value) {

        String result = value;
        if (result.contains("\"")) {
            result = result.replace("\"", "\"\"");
        }
        return result;
    }

}
