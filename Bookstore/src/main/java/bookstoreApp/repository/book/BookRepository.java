package bookstoreApp.repository.book;

import bookstoreApp.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByName(String name);
    List<Book> findByGenre(String gender);
    List<Book> findByNameAndGenreAndAuthorId(String name, String genre, Long authorId);
    List<Book> findByAuthorId(Long authorid);
    Book findByIsbn(String isbn);
    List<Book> findByQuantity(Integer quantity);

}