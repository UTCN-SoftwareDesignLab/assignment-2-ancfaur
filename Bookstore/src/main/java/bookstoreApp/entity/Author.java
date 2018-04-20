package bookstoreApp.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Catalysts on 9/9/2015.
 */
@Entity
@Table(name = "authorBook")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "author",  orphanRemoval=true)
    private Set<Book> books = new HashSet<>();

    private Author() {}

    public Author(String name) { this.name = name; }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public void removeBook(Book book){
        book.setAuthor(null);
        books.remove(book);
    }

    public void addBook(Book book){
        book.setAuthor(this);
        books.add(book);
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}