package bookstoreApp.service.author;
import bookstoreApp.dto.AuthorDto;
import bookstoreApp.dto.BookDto;
import bookstoreApp.entity.Author;
import java.util.List;

public interface AuthorService {
    List<Author> getAll();
    Author findById(int authorId);
    Author create(AuthorDto authorDto);
    void delete(AuthorDto authorDto);
    void addBookToAuthor(AuthorDto authorDto, BookDto bookDto);
    void removeBookFromAuthor(AuthorDto authorDto, String isbn);

}
