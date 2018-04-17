package bookstoreApp.controller;

import bookstoreApp.model.Book;
import bookstoreApp.service.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;
import java.util.List;

@Controller
public class ExperimentController {
private BookService bookService;

    @Autowired
    public ExperimentController(BookService bookservice){
        this.bookService = bookservice;
    }

    @GetMapping("/books")
    public String findAll(Model model) {
        // returneaza fisieru html pe care il vrem in browser
        final List<Book> books = bookService.findAll();
        model.addAttribute("booksCount", books.size());
        return "bookExperiments";
    }

    @GetMapping("/abook")
    public String getBook(Model model){
        Book myBook = new Book();
        myBook.setAuthor("Anca Faur");
        myBook.setPublishedDate(new Date());
        myBook.setTitle("Celalalt cer");

        model.addAttribute("book",myBook);
        return "bookview";
    }


}
