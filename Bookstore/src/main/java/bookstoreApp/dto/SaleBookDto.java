package bookstoreApp.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class SaleBookDto {
    @NotNull(message = "Book isbn is mandatory")
    public Long bookId;
    public Long authorId;

    @Min(1)
    public int saleQunatity;
}
