package bookstoreApp.service.author;

import bookstoreApp.dto.BookDto;
import bookstoreApp.entity.Author;
import bookstoreApp.dto.AuthorDto;
import bookstoreApp.entity.Book;
import bookstoreApp.repository.author.AuthorRepository;
import bookstoreApp.repository.book.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AuthorServiceImpl implements AuthorService{
    AuthorRepository authorRepository;
    BookRepository bookRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author findById(int authorId){
    Author author= authorRepository.findById(new Long(authorId)).get();
    return author;
    }

    @Override
    public Author create(AuthorDto authorDTO) {
        Set<Book> books = new HashSet<>();
        Author author = new Author(authorDTO.name);
        author.setBooks(books);
        return authorRepository.save(author);
    }

    @Override
    public void delete(AuthorDto authorDTO) {
        Author author = authorRepository.findByName(authorDTO.name);
        authorRepository.delete(author);
    }

    @Override
    public void addBookToAuthor(AuthorDto authorDto, BookDto bookDto) {
        Author author = authorRepository.findByName(authorDto.name);
        Book book = new Book(bookDto.name, author, bookDto.isbn, bookDto.gender, new Float(bookDto.price), new Integer(bookDto.quantity));
        author.getBooks().add(book);
        authorRepository.save(author);
    }

    public void removeBookFromAuthor(AuthorDto authorDto, String isbn){
        Author author = authorRepository.findByName(authorDto.name);
        Book book = bookRepository.findByIsbn(isbn);
        author.getBooks().remove(book);
    }


}
