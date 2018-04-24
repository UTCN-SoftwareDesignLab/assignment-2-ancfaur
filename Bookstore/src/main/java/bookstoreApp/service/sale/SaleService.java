package bookstoreApp.service.sale;

import bookstoreApp.dto.SaleBookDto;

public interface SaleService {
    public float sellBook(SaleBookDto saleBookDto) throws LimittedStockException;
}
