package bookstoreApp.controller;

import bookstoreApp.constants.ApplicationConstants;
import bookstoreApp.dto.AuthorBookDto;
import bookstoreApp.dto.AuthorDto;
import bookstoreApp.service.authorBook.AuthorBookService;
import bookstoreApp.service.report.ReportOutOfStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value="/authorBookManagement")
public class AuthorBookManagementController {
    private AuthorBookService authorBookService;
    private ReportOutOfStockService reportOutOfStockService;

    @Autowired
    public AuthorBookManagementController(AuthorBookService authorBookService, ReportOutOfStockService reportOutOfStockService) {
        this.authorBookService = authorBookService;
        this.reportOutOfStockService = reportOutOfStockService;
    }

    @GetMapping()
    public String authorBookForm(Model model) {
        model.addAttribute(new AuthorBookDto());
        model.addAttribute("formats", ApplicationConstants.Formats.FORMATS);
        return "manageAuthorBook";
    }

    @PostMapping(params = "createAuthorBtn")
    public String createAuthor(@ModelAttribute AuthorBookDto authorBookDto) {
        authorBookService.createAuthor(authorBookDto.authorDto);
        return "manageAuthorBook";
    }

    @PostMapping(params = "updateAuthorBtn")
    public String updateAuthor(@ModelAttribute AuthorBookDto authorBookDto) {
        authorBookService.updateAuthor(authorBookDto.authorDto);
        return "manageAuthorBook";
    }

    @PostMapping(params = "deleteAuthorBtn")
    public String deleteAuthor(@ModelAttribute AuthorBookDto authorBookDto) {
        authorBookService.deleteAuthor(authorBookDto.authorDto.id);
        return "manageAuthorBook";
    }

    @PostMapping(value = "/showAuthors", params = "showAuthorsBtn")
    public String showAll(Model model) {
        List<AuthorDto> authorDtos = authorBookService.findAllAuthors();
        model.addAttribute("authorDtos", authorDtos);
        return "authorTable";
    }

    @PostMapping(params = "createBookBtn")
    public String createBook(@ModelAttribute AuthorBookDto authorBookDto) {
        authorBookService.addBookToAuthor(authorBookDto.bookDto);
        return "manageAuthorBook";
    }

    @PostMapping(params = "updateBookBtn")
    public String updateBook(@ModelAttribute AuthorBookDto authorBookDto) {
        authorBookService.updateBookFromAuthor(authorBookDto.bookDto);
        return "manageAuthorBook";
    }

    @PostMapping(params = "deleteBookBtn")
    public String deleteBook(@ModelAttribute AuthorBookDto authorBookDto) {
        authorBookService.removeBookFromAuthor(authorBookDto.bookDto.id);
        return "manageAuthorBook";
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
        return "redirect:/authorBookManagement";
    }
}
