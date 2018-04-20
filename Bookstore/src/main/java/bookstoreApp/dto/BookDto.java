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
}
