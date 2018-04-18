package bookstoreApp.service.book;

import bookstoreApp.dto.BookDto;
import bookstoreApp.entity.Author;
import bookstoreApp.entity.Book;
import bookstoreApp.repository.book.BookRepository;
import bookstoreApp.service.author.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;
   // private AuthorService authorService;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository){
        this.bookRepository = bookRepository;
       // this.authorService = authorService;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    /*
    public void delete(BookDto bookDto){
        Author author = authorService.findById(bookDto.authorId);
        Book book = new Book(bookDto.name, author, bookDto.isbn, new Float(bookDto.price), new Integer(bookDto.quantity));
        bookRepository.delete(book);
    }
*/
}
