package bookstoreApp.service.report;

import java.io.IOException;

public interface ReportOutOfStockService {
    void writeOutOfStockReport(String formatType) throws IOException;
}
