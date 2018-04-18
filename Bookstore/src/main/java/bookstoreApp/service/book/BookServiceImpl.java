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
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> findByGender(String gender) {
        return bookRepository.findByGender(gender);
    }

    @Override
    public List<Book> findByName(String name) {
        return bookRepository.findByName(name);
    }


    public void delete(String isbn){
        Book book =bookRepository.findByIsbn(isbn);
        bookRepository.delete(book);
    }

}
