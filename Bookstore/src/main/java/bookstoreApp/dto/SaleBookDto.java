package bookstoreApp.dto;

import javax.validation.constraints.Min;

public class SaleBookDto {
    public Long bookId;
    @Min(value = 1, message = "The quantity should be a strictly positive integer")
    public int saleQuantity;

    public float price;

    public int getSaleQuantity() {
        return saleQuantity;
    }

    public void setSaleQuantity(int saleQuantity) {
        this.saleQuantity = saleQuantity;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
