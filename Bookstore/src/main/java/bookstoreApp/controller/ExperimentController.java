package bookstoreApp.controller;

import bookstoreApp.dto.AuthorDto;
import bookstoreApp.dto.BookDto;
import bookstoreApp.entity.Book;
import bookstoreApp.service.author.AuthorService;
import bookstoreApp.service.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ExperimentController {
private BookService bookService;
private AuthorService authorService;

    @Autowired
    public ExperimentController(BookService bookservice, AuthorService authorService){
        this.bookService = bookservice;
        this.authorService =authorService;

        addAuthorAndBooks();

    }

    @GetMapping("/books")
    public String findAll(Model model) {
        // returneaza fisieru html pe care il vrem in browser
        final List<Book> books = bookService.findAll();
        model.addAttribute("booksCount", books.size());
        return "bookExperiments";
    }

    public void addAuthorAndBooks(){

        AuthorDto authorDto1 = new AuthorDto();
        authorDto1.name="Mirela";

        AuthorDto authorDto2 = new AuthorDto();
        authorDto2.name="Ionut";

        BookDto bookDto1 = new BookDto();
        bookDto1.authorId= 1;
        bookDto1.isbn="12345";
        bookDto1.gender="romance";
        bookDto1.price=234.6F;
        bookDto1.quantity=100;
        bookDto1.name = "Nu imi place deloc";


        BookDto bookDto2 = new BookDto();
        bookDto2.authorId= 1;
        bookDto2.gender ="comedy";
        bookDto2.isbn="23456";
        bookDto2.price=234.6F;
        bookDto2.quantity=100;
        bookDto2.name = "la facultatea";

        BookDto bookDto3 = new BookDto();
        bookDto3.authorId= 1;
        bookDto3.gender ="comedy";
        bookDto3.isbn="34567";
        bookDto3.price=234.6F;
        bookDto3.quantity=100;
        bookDto3.name = "asta";

        authorService.create(authorDto1);
        authorService.create(authorDto2);
        authorService.addBookToAuthor(authorDto1, bookDto1);
        authorService.addBookToAuthor(authorDto2, bookDto2);
        authorService.addBookToAuthor(authorDto1, bookDto3);

        List<Book> booksGender = bookService.findByGender("romance");
        List<Book> booksName = bookService.findByName("asta");

        authorService.delete(authorDto1);


    }
}
