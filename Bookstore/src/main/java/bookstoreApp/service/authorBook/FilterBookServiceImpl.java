package bookstoreApp.service.authorBook;

import bookstoreApp.converter.BookAuthorConverter;
import bookstoreApp.dto.BookDto;
import bookstoreApp.entity.Author;
import bookstoreApp.entity.Book;
import bookstoreApp.repository.author.AuthorRepository;
import bookstoreApp.repository.book.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class FilterBookServiceImpl implements FilterBookService {
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;
    private BookAuthorConverter bookAuthorConverter;

    @Autowired
    public FilterBookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, BookAuthorConverter bookAuthorConverter) {
        this.bookRepository = bookRepository;
        this.bookAuthorConverter = bookAuthorConverter;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<BookDto> findAll() {
        List<Book> books = bookRepository.findAll();
        List<BookDto> bookDtos = new ArrayList<>();
        for (Book book : books) {
            bookDtos.add(bookAuthorConverter.fromBookToBookDto(book));
        }
        return bookDtos;
    }

    @Override
    public List<BookDto> findByGenre(String gender) {
        List<Book> books = bookRepository.findByGenre(gender);
        List<BookDto> bookDtos = new ArrayList<>();
        for (Book book : books) {
            bookDtos.add(bookAuthorConverter.fromBookToBookDto(book));
        }
        return bookDtos;
    }

    @Override
    public List<BookDto> findByName(String name) {
        List<Book> books = bookRepository.findByName(name);
        List<BookDto> bookDtos = new ArrayList<>();
        for (Book book : books) {
            bookDtos.add(bookAuthorConverter.fromBookToBookDto(book));
        }
        return bookDtos;
    }

    @Override
    public List<BookDto> filterByAuthor(String name) {
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

}



