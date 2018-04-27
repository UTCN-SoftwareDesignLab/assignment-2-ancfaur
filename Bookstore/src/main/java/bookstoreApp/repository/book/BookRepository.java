package bookstoreApp.repository.book;

import bookstoreApp.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByIsbn(String isbn);
    List<Book> findByQuantity(Integer quantity);
    @Query("select b from Book b WHERE b.author.name LIKE %:common% OR b.genre LIKE %:common% OR b.name LIKE %:common%")
    List<Book> findByTitleGenreAuthorName(@Param("common")String common);

}