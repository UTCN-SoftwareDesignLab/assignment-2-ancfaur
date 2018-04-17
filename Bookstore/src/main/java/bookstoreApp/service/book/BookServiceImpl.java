package bookstoreApp.service.book;

import bookstoreApp.model.Book;
import bookstoreApp.repository.book.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> findAll() {
        final Iterable<Book> books = bookRepository.findAll();
        List<Book> result = new ArrayList<>();
        books.forEach(result::add);
        return result;
    }


}
