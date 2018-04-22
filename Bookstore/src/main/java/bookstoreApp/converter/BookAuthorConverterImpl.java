package bookstoreApp.converter;

import bookstoreApp.dto.AuthorDto;
import bookstoreApp.dto.BookDto;
import bookstoreApp.entity.Author;
import bookstoreApp.entity.Book;
import org.springframework.stereotype.Component;

@Component("bookAuthorConverter")
public class BookAuthorConverterImpl implements BookAuthorConverter {

    public Book fromBookDtoToBook(BookDto bookDto, Author author){
        Book book = new Book(bookDto.name, author, bookDto.isbn, bookDto.genre, new Float(bookDto.price), new Integer(bookDto.quantity));
        book.setId(bookDto.id);
        return book;
    }

    public BookDto fromBookToBookDto(Book book){
        BookDto bookDto = new BookDto();
        bookDto.id = book.getId();
        bookDto.genre = book.getGenre();
        bookDto.quantity = book.getQuantity();
        bookDto.name = book.getName();
        bookDto.price = book.getPrice();
        bookDto.isbn = book.getIsbn();
        bookDto.authorId = bookDto.authorId;
        return bookDto;
    }

    public Author fromAuthorDtoToAuthor(AuthorDto authorDto){
        Author author = new Author(authorDto.name);
        author.setId(author.getId());
        return author;
    }

    public AuthorDto fromAuthorToAuthorDto(Author author){
        AuthorDto authorDto = new AuthorDto();
        authorDto.name = author.getName();
        authorDto.id = author.getId();
        return authorDto;
    }
}
