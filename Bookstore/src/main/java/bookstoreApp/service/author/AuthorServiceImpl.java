package bookstoreApp.service.author;

import bookstoreApp.converter.BookAuthorConverter;
import bookstoreApp.converter.BookAuthorConverterImpl;
import bookstoreApp.dto.AuthorDto;
import bookstoreApp.dto.BookDto;
import bookstoreApp.dto.SaleBookDto;
import bookstoreApp.entity.Author;
import bookstoreApp.entity.Book;
import bookstoreApp.repository.author.AuthorRepository;
import bookstoreApp.repository.book.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class AuthorServiceImpl implements AuthorService{
    AuthorRepository authorRepository;
    BookRepository bookRepository;
    BookAuthorConverter bookAuthorConverter;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.bookAuthorConverter = new BookAuthorConverterImpl();
    }

    @Override
    public List<AuthorDto> findAll() {
        List<Author> authors= authorRepository.findAll();
        List<AuthorDto> authorDtos = new ArrayList<>();
        for(Author author:authors){
            authorDtos.add(bookAuthorConverter.fromAuthorToAuthorDto(author));
        }
        return authorDtos;
    }

    @Override
    public AuthorDto findById(Long id){
    Author author= authorRepository.findById(id).orElse(null);
    AuthorDto authorDto = bookAuthorConverter.fromAuthorToAuthorDto(author);
    return authorDto;
    }

    @Override
    public void create(AuthorDto authorDTO) {
        Set<Book> books = new HashSet<>();
        Author author = bookAuthorConverter.fromAuthorDtoToAuthor(authorDTO);
        author.setBooks(books);
        authorRepository.save(author);
    }

    @Override
    public void delete(Long id) {
        Author author = authorRepository.findById(id).orElse(null);
        authorRepository.delete(author);
    }

    @Override
    public void addBookToAuthor(Long authorId, BookDto bookDto) {
        Author author = authorRepository.findById(authorId).orElse(null);
        Book book = bookAuthorConverter.fromBookDtoToBook(bookDto, author);
        author.addBook(book);
        authorRepository.save(author);
    }

    public void removeBookFromAuthor(Long authorId, Long bookId){
        Author author = authorRepository.findById(authorId).orElse(null);
        Book book = bookRepository.findByIdAndAuthorId(authorId, bookId);
        author.removeBook(book);
        authorRepository.save(author);
    }

    @Override
    public void update(AuthorDto authorDto) {
        Author author = authorRepository.findById(authorDto.id).get();
        author.setName(authorDto.name);
        authorRepository.save(author);
    }

    @Override
    public void sellBookFromAuthor(SaleBookDto saleBookDto) throws LimittedStockException {
       Book book = bookRepository.findById(saleBookDto.bookId).orElse(null);
       if (book.getQuantity()<saleBookDto.saleQunatity){
           throw(new LimittedStockException("Limitted stock on book with ISBN =" + book.getIsbn()+"\n"
                   + "in stock = "+ book.getQuantity()+"\n"
                   + "required ="+saleBookDto.saleQunatity+"\n"
           ));
       }else{
           Author author = authorRepository.findById(saleBookDto.authorId).orElse(null);
           removeBookFromAuthor(saleBookDto.bookId, saleBookDto.authorId);
           book.setQuantity(book.getQuantity() - saleBookDto.saleQunatity);
           author.addBook(book);
           authorRepository.save(author);
       }
    }

    @Override
    public List<BookDto> listBooksByAuthor(String name) {
        List<Author> authors = authorRepository.findByName(name);
        List<BookDto> allBooks = new ArrayList<>();
        Set<Book> booksForAuthor;
        for(Author author:authors){
            booksForAuthor = author.getBooks();
            for(Book book:booksForAuthor){
                allBooks.add(bookAuthorConverter.fromBookToBookDto(book));
            }
        }
        return allBooks;
    }

    public void updateBookFromAuthor(BookDto bookDto, Long oldAuthorId){
        // for also updating the author
        removeBookFromAuthor(bookDto.id, oldAuthorId);
        addBookToAuthor(bookDto.authorId, bookDto);
    }

}
