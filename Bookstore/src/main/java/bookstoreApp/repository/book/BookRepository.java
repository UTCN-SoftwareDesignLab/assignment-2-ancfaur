package bookstoreApp.repository.book;

import bookstoreApp.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;



public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByName(String name);
}