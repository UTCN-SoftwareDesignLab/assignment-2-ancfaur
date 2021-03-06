package bookstoreApp.entity;
import javax.persistence.*;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String isbn;
    private String name;
    private Float price;
    private Integer quantity;
    private String genre;


    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "author_id")
    private Author author;

    public Book(String name, Author author, String isbn, String genre, Float price, Integer quantity) {
        this.name = name;
        this.author = author;
        this.isbn = isbn;
        this.price = price;
        this.quantity = quantity;
        this.genre = genre;
    }

    public Book() {}

    public String getIsbn() {
        return isbn;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Book" +
                "id=" + id +
                "isbn='" + isbn + '\'' +
                "title='" + name + '\'' +
                "price=" + price +
                "quantity=" + quantity +
                "genre=" + genre + '\'' +
                author.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof User)) {
            return false;
        }
        Book book = (Book) o;
        return book.getIsbn().equals(this.isbn);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + isbn.hashCode();
        return result;
    }
}
