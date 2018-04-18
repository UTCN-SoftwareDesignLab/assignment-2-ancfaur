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

        BookDto bookDto1 = new BookDto();
        bookDto1.authorId= 1;
        bookDto1.isbn="89562";
        bookDto1.price=234.6F;
        bookDto1.quantity=100;
        bookDto1.name = "Nu imi place deloc";


        BookDto bookDto2 = new BookDto();
        bookDto2.authorId= 1;
        bookDto2.isbn="89562";
        bookDto2.price=234.6F;
        bookDto2.quantity=100;
        bookDto2.name = "Nu imi place deloc";


        authorService.create(authorDto1);
        authorService.addBookToAuthor(authorDto1, bookDto1);

    }
}
