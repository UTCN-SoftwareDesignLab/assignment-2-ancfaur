package bookstoreApp.controller;

import bookstoreApp.constants.ApplicationConstants;
import bookstoreApp.dto.AuthorBookDto;
import bookstoreApp.dto.AuthorDto;
import bookstoreApp.dto.BookDto;
import bookstoreApp.service.authorBook.AuthorBookService;
import bookstoreApp.service.report.ReportOutOfStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value="/manageAuthorBook")
public class ManageAuthorBookController implements WebMvcConfigurer {
    private AuthorBookService authorBookService;
    private ReportOutOfStockService reportOutOfStockService;

    @Autowired
    public ManageAuthorBookController(AuthorBookService authorBookService, ReportOutOfStockService reportOutOfStockService) {
        this.authorBookService = authorBookService;
        this.reportOutOfStockService = reportOutOfStockService;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/manageAuthorBook").setViewName("manageAuthorBook");
    }

    @GetMapping()
    public String authorBookForm(Model model) {
        model.addAttribute(new BookDto());
        model.addAttribute(new AuthorDto());
        model.addAttribute("formats", ApplicationConstants.Formats.FORMATS);
        return "manageAuthorBook";
    }

    private void reintroduceAttributes(Model model, AuthorDto authorDto){
        model.addAttribute(new BookDto());
        model.addAttribute(authorDto);
        model.addAttribute("formats", ApplicationConstants.Formats.FORMATS);
    }

    private void reintroduceAttributes(Model model, BookDto bookDto){
        model.addAttribute(bookDto);
        model.addAttribute(new AuthorDto());
        model.addAttribute("formats", ApplicationConstants.Formats.FORMATS);
    }

    @PostMapping(params = "createAuthorBtn")
    public String createAuthor(@ModelAttribute @Valid AuthorDto authorDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            reintroduceAttributes(model, authorDto);
            return "manageAuthorBook";
        }
        authorBookService.createAuthor(authorDto);
        return "redirect:/manageAuthorBook";
    }

    @PostMapping(params = "updateAuthorBtn")
    public String updateAuthor(@ModelAttribute @Valid AuthorDto authorDto,BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            reintroduceAttributes(model, authorDto);
            return "manageAuthorBook";
        }
        authorBookService.updateAuthor(authorDto);
        return "redirect:/manageAuthorBook";
    }

    @PostMapping(params = "deleteAuthorBtn")
    public String deleteAuthor(@ModelAttribute AuthorDto authorDto) {
        authorBookService.deleteAuthor(authorDto.id);
        return "redirect:/manageAuthorBook";
    }

    @PostMapping(value = "/showAuthors", params = "showAuthorsBtn")
    public String showAll(Model model) {
        List<AuthorDto> authorDtos = authorBookService.findAllAuthors();
        model.addAttribute("authorDtos", authorDtos);
        return "authorTable";
    }

    @PostMapping(params = "createBookBtn")
    public String createBook(@ModelAttribute @Valid BookDto bookDto,BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            reintroduceAttributes(model, bookDto);
            return "manageAuthorBook";
        }
        authorBookService.addBookToAuthor(bookDto);
        return "redirect:/manageAuthorBook";
    }

    @PostMapping(params = "updateBookBtn")
    public String updateBook(@ModelAttribute @Valid BookDto bookDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            reintroduceAttributes(model, bookDto);
            return "manageAuthorBook";
        }
        authorBookService.updateBookFromAuthor(bookDto);
        return "redirect:/manageAuthorBook";
    }

    @PostMapping(params = "deleteBookBtn")
    public String deleteBook(@ModelAttribute BookDto bookDto) {
        authorBookService.removeBookFromAuthor(bookDto.id);
        return "redirect:/manageAuthorBook";
    }

    @PostMapping(value = "/showBooks", params = "showBooksBtn")
    public String showAllBooks(Model model) {
        List<AuthorBookDto> authorBookDtos = authorBookService.findAllBooksAuthors();
        model.addAttribute("authorBookDtos", authorBookDtos);
        return "bookTable";
    }

    @PostMapping(params = "generateReportsBtn")
    public String generateReports(@RequestParam String formatType) {
        try {
            reportOutOfStockService.writeOutOfStockReport(formatType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/manageAuthorBook";
    }
}
