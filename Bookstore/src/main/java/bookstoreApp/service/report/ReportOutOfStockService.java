package bookstoreApp.service.report;

import java.io.IOException;

public interface ReportOutOfStockService {
    void writeCsvReports() throws IOException;
    void writePdfReports();
}
