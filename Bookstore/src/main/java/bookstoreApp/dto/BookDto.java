package bookstoreApp.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


public class BookDto {
    @Size(min = 1)
    public Long id;
    public Long authorId;
    public String name;
    public String gender;
    @Pattern(regexp = "^[1-9]+$")
    @Size(min = 5, max = 5, message = "ISBN is the wrong size")
    public String isbn;

    @Min(0)
    public int quantity;

    @Min(0)
    public float price;

    @Override
    public String toString() {
        return "BookDto{" +
                "name='" + name + '\'' +
                ", authorId=" + authorId +
                ", gender='" + gender + '\'' +
                ", isbn='" + isbn + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
