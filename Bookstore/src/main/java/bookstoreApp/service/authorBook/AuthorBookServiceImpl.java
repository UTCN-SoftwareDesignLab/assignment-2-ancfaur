package bookstoreApp.service.authorBook;

import bookstoreApp.converter.BookAuthorConverter;
import bookstoreApp.dto.AuthorBookDto;
import bookstoreApp.dto.AuthorDto;
import bookstoreApp.dto.BookDto;
import bookstoreApp.entity.Author;
import bookstoreApp.entity.Book;
import bookstoreApp.repository.author.AuthorRepository;
import bookstoreApp.repository.book.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class AuthorBookServiceImpl implements AuthorBookService {
    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private BookAuthorConverter bookAuthorConverter;

    @Autowired
    public AuthorBookServiceImpl(AuthorRepository authorRepository, BookRepository bookRepository, BookAuthorConverter bookAuthorConverter) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.bookAuthorConverter = bookAuthorConverter;
    }

    @Override
    public List<AuthorDto> findAllAuthors() {
        List<Author> authors= authorRepository.findAll();
        List<AuthorDto> authorDtos = new ArrayList<>();
        for(Author author:authors){
            authorDtos.add(bookAuthorConverter.fromAuthorToAuthorDto(author));
        }
        return authorDtos;
    }


    @Override
    public AuthorDto findByIdAuthor(Long id){
    Author author= authorRepository.findById(id).orElse(null);
    AuthorDto authorDto = bookAuthorConverter.fromAuthorToAuthorDto(author);
    return authorDto;
    }

    @Override
    public AuthorDto createAuthor(AuthorDto authorDTO) {
        Set<Book> books = new HashSet<>();
        Author author = bookAuthorConverter.fromAuthorDtoToAuthor(authorDTO);
        author.setBooks(books);
        Author back =authorRepository.save(author);
        return bookAuthorConverter.fromAuthorToAuthorDto(author);
    }

    @Override
    public void updateAuthor(AuthorDto authorDto) {
        Author author = authorRepository.findById(authorDto.id).get();
        author.setName(authorDto.name);
        authorRepository.save(author);
    }


    @Override
    public void deleteAuthor(Long id) {
        Author author = authorRepository.findById(id).orElse(null);
        authorRepository.delete(author);
    }

    @Override
    public void addBookToAuthor(BookDto bookDto) {
        Author author = authorRepository.findById(bookDto.authorId).orElse(null);
        Book book = bookAuthorConverter.fromBookDtoToBook(bookDto, author);
        author.addBook(book);
        authorRepository.save(author);
    }

    @Override
    public void removeBookFromAuthor(Long bookId){
        Book book = bookRepository.findById(bookId).orElse(null);
        Author author = authorRepository.findById(book.getAuthor().getId()).orElse(null);
        author.removeBook(book);
        authorRepository.save(author);
    }

    @Override
    public void updateBookFromAuthor(BookDto bookDto){
        removeBookFromAuthor(bookDto.id);
        addBookToAuthor(bookDto);
    }

     @Override
     public List<AuthorBookDto> findAllBooksAuthors(){
       List<AuthorBookDto> authorBookDtos = new ArrayList<>();
       List<Book> books = bookRepository.findAll();
       for(Book book:books){
           authorBookDtos.add(new AuthorBookDto(bookAuthorConverter.fromAuthorToAuthorDto(book.getAuthor()), bookAuthorConverter.fromBookToBookDto(book)));
       }
       return authorBookDtos;
     }

    @Override
    public List<AuthorDto> findAuthorsByName(String name) {
        List<Author> authors = authorRepository.findByName(name);
        List<AuthorDto> authorDtos = new ArrayList<>();
        for(Author author:authors){
            authorDtos.add(bookAuthorConverter.fromAuthorToAuthorDto(author));
        }
        return authorDtos;
    }

    @Override
    public List<BookDto> findBooksForAuthor(Long authorId) {
        Author author = authorRepository.findById(authorId).orElse(null);
        Set<Book> books = author.getBooks();
        List<BookDto> bookDtos = new ArrayList<>();
        for(Book book:books){
            bookDtos.add(bookAuthorConverter.fromBookToBookDto(book));
        }
        return bookDtos;
    }

    @Override
    public BookDto findBookByIsbn(String isbn){
        Book book = bookRepository.findByIsbn(isbn);
        return bookAuthorConverter.fromBookToBookDto(book);
    }

    @Override
    public void deleteAll() {
        List<Author> authors = authorRepository.findAll();
        for (Author author:authors){
            deleteAuthor(author.getId());
        }
    }

}
