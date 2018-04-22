package bookstoreApp.service.report.csv;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

public interface CSVUtils {
    void writeLine(Writer writer, List<String> values) throws IOException;
}
