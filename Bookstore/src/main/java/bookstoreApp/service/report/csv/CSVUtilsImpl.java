package bookstoreApp.service.report.csv;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

@Component
public class CSVUtilsImpl implements CSVUtils {

    private static final char DEFAULT_SEPARATOR = ',';

    private String followCVSformat(String value) {

        String result = value;
        if (result.contains("\"")) {
            result = result.replace("\"", "\"\"");
        }
        return result;

    }

    public void writeLine(Writer writer, List<String> values) throws IOException {

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

}