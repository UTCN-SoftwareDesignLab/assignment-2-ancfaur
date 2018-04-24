package bookstoreApp.service.filter;

import bookstoreApp.converter.BookAuthorConverter;
import bookstoreApp.dto.AuthorBookDto;
import bookstoreApp.entity.Book;
import bookstoreApp.repository.author.AuthorRepository;
import bookstoreApp.repository.book.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FilterBookServiceImpl implements FilterBookService {
    private BookRepository bookRepository;
    private BookAuthorConverter bookAuthorConverter;
    private AuthorRepository authorRepository;

    @Autowired
    public FilterBookServiceImpl(BookRepository bookRepository, BookAuthorConverter bookAuthorConverter, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.bookAuthorConverter = bookAuthorConverter;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<AuthorBookDto> filterByTitleAndGenreAndAuthor(String common) {
        List<Book> books = bookRepository.findByTitleGenreAuthorName(common);
        List<AuthorBookDto> authorBookDtos = new ArrayList<>();
        for(Book book:books){
            authorBookDtos.add(new AuthorBookDto(bookAuthorConverter.fromAuthorToAuthorDto(book.getAuthor()), bookAuthorConverter.fromBookToBookDto(book)));
        }
        return authorBookDtos;
    }
}



