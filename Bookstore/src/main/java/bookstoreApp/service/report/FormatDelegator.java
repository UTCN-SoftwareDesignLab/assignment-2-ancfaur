package bookstoreApp.service.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FormatDelegator {
    private PdfFormater pdfFormater;
    private CsvFormater csvFormater;

    @Autowired
    public FormatDelegator(PdfFormater pdfFormater, CsvFormater csvFormater){
        this.csvFormater = csvFormater;
        this.pdfFormater = pdfFormater;
    }

    public Formater selectFormat(String format){
        switch(format){
            case "pdf": return pdfFormater;
            case "csv": return csvFormater;
            default: return null;
        }
    }
}
