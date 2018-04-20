package bookstoreApp.service.book;

import bookstoreApp.converter.BookAuthorConverter;
import bookstoreApp.converter.BookAuthorConverterImpl;
import bookstoreApp.dto.BookDto;
import bookstoreApp.dto.SaleBookDto;
import bookstoreApp.entity.Book;
import bookstoreApp.repository.book.BookRepository;
import bookstoreApp.service.author.LimittedStockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;
    private BookAuthorConverter bookAuthorConverter;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository){
        this.bookRepository = bookRepository;
        this.bookAuthorConverter = new BookAuthorConverterImpl();
    }

    @Override
    public List<BookDto> findAll() {
        List<Book> books =bookRepository.findAll();
        List<BookDto> bookDtos = new ArrayList<>();
        for(Book book:books){
            bookDtos.add(bookAuthorConverter.fromBookToBookDto(book));
        }
        return bookDtos;
    }

    @Override
    public List<BookDto> findByGender(String gender) {
        List<Book> books = bookRepository.findByGender(gender);
        List<BookDto> bookDtos = new ArrayList<>();
        for(Book book:books){
            bookDtos.add(bookAuthorConverter.fromBookToBookDto(book));
        }
        return bookDtos;
    }

    @Override
    public List<BookDto> findByName(String name) {
        List<Book> books =  bookRepository.findByName(name);
        List<BookDto> bookDtos = new ArrayList<>();
        for(Book book:books){
            bookDtos.add(bookAuthorConverter.fromBookToBookDto(book));
        }
        return bookDtos;
    }

    public void delete(String isbn){
        Book book =bookRepository.findByIsbn(isbn);
        bookRepository.delete(book);
    }

    @Override
    public void sellBook(SaleBookDto saleBookDto) throws LimittedStockException {
        Book book = bookRepository.findByIdAndAuthorId(saleBookDto.bookId, saleBookDto.authorId);
        if(book.getQuantity()<saleBookDto.saleQunatity){
            throw(new LimittedStockException("Limitted stock on book with ISBN =" + book.getIsbn()+"\n"
            + "in stock = "+ book.getQuantity()+"\n"
                    + "required ="+saleBookDto.saleQunatity+"\n"
            ));
        }else{
            book.setQuantity(book.getQuantity()-saleBookDto.saleQunatity);
            bookRepository.save(book);
        }
    }

    @Override
    public void update(BookDto bookDto) {
       Book book = bookRepository.findByIsbn(bookDto.isbn);
       book.setName(bookDto.name);
       book.setQuantity(bookDto.quantity);
       book.setGender(bookDto.gender);
       book.setPrice(bookDto.price);
       bookRepository.save(book);
    }
}
