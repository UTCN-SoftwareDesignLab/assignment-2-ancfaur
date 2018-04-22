package bookstoreApp.service.report;

import org.springframework.stereotype.Component;

@Component
public class FormatFactory {

    public static Formater selectFormat(String format){
        switch(format){
            case "pdf": return new PdfFormater();
            case "csv": return new CsvFormater();
            default: return null;
        }
    }
}
