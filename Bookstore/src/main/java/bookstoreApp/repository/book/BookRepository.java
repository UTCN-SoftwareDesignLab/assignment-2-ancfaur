package bookstoreApp.repository.book;

import bookstoreApp.model.Book;
import org.springframework.data.repository.CrudRepository;

//entitatea repositoriului si tipul id-ului (Book, Long)
public interface BookRepository extends CrudRepository<Book, Long>{
}
