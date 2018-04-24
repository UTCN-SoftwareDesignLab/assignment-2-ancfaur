package bookstoreApp.controller;

import bookstoreApp.dto.AuthorBookDto;
import bookstoreApp.dto.FilterDto;
import bookstoreApp.dto.SaleBookDto;
import bookstoreApp.service.authorBook.AuthorBookService;
import bookstoreApp.service.filter.FilterBookService;
import bookstoreApp.service.sale.LimittedStockException;
import bookstoreApp.service.sale.SaleService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value="empl/employeeMenu")
public class EmployeeController implements WebMvcConfigurer {
    private AuthorBookService authorBookService;
    private SaleService saleService;
    private FilterBookService filterBookService;

    public EmployeeController(AuthorBookService authorBookService, SaleService saleService, FilterBookService filterBookService){
        this.authorBookService = authorBookService;
        this.saleService = saleService;
        this.filterBookService = filterBookService;

    }

    @GetMapping()
    public String employeeForms(Model model) {
        model.addAttribute(new FilterDto());
        model.addAttribute(new SaleBookDto());
        return "employeeMenu";
    }

    private void reintroduceAttributes(Model model, SaleBookDto saleBookDto){
        model.addAttribute(new FilterDto());
        model.addAttribute(saleBookDto);
    }

    @PostMapping(value = "/showAllBooks", params = "showAllBooksBtn")
    public String showAllBooks(Model model) {
        List<AuthorBookDto> authorBookDtos = authorBookService.findAllBooksAuthors();
        model.addAttribute("authorBookDtos", authorBookDtos);
        return "bookTable";
    }

    @PostMapping(params = "sellOneBookTypeBtn")
    public String sellOneBookType(@ModelAttribute @Valid SaleBookDto saleBookDto, BindingResult bindingResult, Model model) throws LimittedStockException {
        if (bindingResult.hasErrors()) {
            reintroduceAttributes(model, saleBookDto);
            return "employeeMenu";
        }
        float price = saleService.sellBook(saleBookDto);
        saleBookDto.price = price;
        model.addAttribute("newSaleBookDto", saleBookDto);
        return "saleSummary";
    }

    @PostMapping(params = "filterBtn")
    public String filter(@ModelAttribute FilterDto filterDto, Model model){
        model.addAttribute("authorBookDtos", filterBookService.filterByTitleAndGenreAndAuthor(filterDto.common));
        return "bookTable";
    }

    @PostMapping(value = "/logout", params="logoutBtn")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }
}
