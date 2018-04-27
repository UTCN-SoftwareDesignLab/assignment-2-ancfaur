package bookstoreApp.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


public class BookDto {
    public Long id;
    @Min(1)
    public Long authorId;
    @Size(min = 1,  message = "Title is mandatory")
    public String name;
    @Pattern(regexp = "^[a-zA-Z\\s]+", message="Genre is invalid, it should only contain letters")
    public String genre;
    @Pattern(regexp = "^[1-9]+$", message = "ISBN should only contain digits" )
    @Size(min = 5, max = 5, message = "ISBN must have a length of 5")
    public String isbn;

    @Min(value = 0, message = "The quantity should be a positive integer")
    public int quantity;

    @Min(value = 0, message = "The price should be a positive integer")
    public float price;

    public BookDto(){}

    public BookDto(Long authorId, String name, String genre, String isbn, int quantity, float price){
        this.authorId = authorId;
        this.name = name;
        this.genre = genre;
        this.isbn = isbn;
        this.quantity = quantity;
        this.price = price;
    }

    @Override
    public String toString() {
        return "BookDto{" +
                "name='" + name + '\'' +
                ", authorId=" + authorId +
                ", genre='" + genre + '\'' +
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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
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
