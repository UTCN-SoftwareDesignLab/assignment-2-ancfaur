package bookstoreApp.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class SaleBookDto {
    @NotNull(message = "Book isbn is mandatory")
    public Long bookId;
    public Long authorId;

    @Min(1)
    public int saleQunatity;

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public int getSaleQunatity() {
        return saleQunatity;
    }

    public void setSaleQunatity(int saleQunatity) {
        this.saleQunatity = saleQunatity;
    }
}
