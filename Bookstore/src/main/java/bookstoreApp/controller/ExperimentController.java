package bookstoreApp.controller;

import bookstoreApp.dto.AuthorBookDto;
import bookstoreApp.dto.AuthorDto;
import bookstoreApp.dto.BookDto;
import bookstoreApp.dto.UserDto;
import bookstoreApp.entity.Role;
import bookstoreApp.repository.role.RoleRepository;
import bookstoreApp.service.authorBook.AuthorBookService;
import bookstoreApp.service.filter.FilterBookService;
import bookstoreApp.service.report.ReportOutOfStockService;
import bookstoreApp.service.user.AuthenticationService;
import bookstoreApp.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ExperimentController {
private FilterBookService filteringService;
private AuthorBookService authorBookService;
private AuthenticationService authenticationService;
private RoleRepository roleRepository;
private UserService userService;
private ReportOutOfStockService reportOutOfStock;

    @Autowired
    public ExperimentController(FilterBookService bookservice, AuthorBookService authorBookService, AuthenticationService authenticationService, RoleRepository roleRepository, UserService userService, ReportOutOfStockService reportOutOfStock){
        this.filteringService = bookservice;
        this.authorBookService = authorBookService;
        this.authenticationService = authenticationService;
        this.roleRepository = roleRepository;
        this.userService = userService;
        this.reportOutOfStock = reportOutOfStock;


        addAuthors();
        updateAuthor();

        addBooksToAuthors();


         // sellBook();


        // updateBook();


         booksFilter();


        // removeBook();

       // removeAuthor();



        addRoles();
        addUsers();
        Role role = new Role();
        role.setId(new Long(1));
        role.setName("administrator");

       // deleteUser();
       // updateUser();

    }

    private void booksFilter() {
    List<AuthorBookDto> authorBookDtos =filteringService.filterByTitleAndGenreAndAuthor("comedy");
    System.out.println("******************************"+authorBookDtos.size()+"*******************");
    }

    private void updateAuthor() {
        AuthorDto authorDto = new AuthorDto();
        authorDto.id = new Long(1);
        authorDto.name= "Sabin Faur Andrei";
        authorBookService.updateAuthor(authorDto);
    }


     public void addAuthors(){
        AuthorDto authorDto1 = new AuthorDto();
        authorDto1.name="Faur Andrei";

        AuthorDto authorDto2 = new AuthorDto();
        authorDto2.name="Simedru Vlad";

        AuthorDto authorDto3 = new AuthorDto();
        authorDto3.name="Florin comedy Vlad";

        // create authors
        authorBookService.createAuthor(authorDto1);
        authorBookService.createAuthor(authorDto2);
        authorBookService.createAuthor(authorDto3);
    }

     public void addBooksToAuthors(){
         BookDto bookDto1 = new BookDto();
         bookDto1.authorId= new Long(1);
         bookDto1.isbn="12345";
         bookDto1.genre ="romance";
         bookDto1.price=234.6F;
         bookDto1.quantity=100;
         bookDto1.name = "Viata de istoric e comedy";

         BookDto bookDto2 = new BookDto();
         bookDto2.authorId= new Long(1);
         bookDto2.genre ="comedy";
         bookDto2.isbn="23456";
         bookDto2.price=234.6F;
         bookDto2.quantity=0;
         bookDto2.name = "Ce de carti";


         BookDto bookDto3 = new BookDto();
         bookDto3.authorId= new Long(2);
         bookDto3.genre ="thriller";
         bookDto3.isbn="34567";
         bookDto3.price=234.6F;
         bookDto3.quantity=0;
         bookDto3.name = "Sa ne distram";

         BookDto bookDto4= new BookDto();
         bookDto4.authorId= new Long(3);
         bookDto4.genre ="castravete";
         bookDto4.isbn="54323";
         bookDto4.price=23.6F;
         bookDto4.quantity=3232;
         bookDto4.name = "Trala comedy";


         // add books to authors
         authorBookService.addBookToAuthor(bookDto1);
         authorBookService.addBookToAuthor(bookDto2);
         authorBookService.addBookToAuthor(bookDto3);
         authorBookService.addBookToAuthor(bookDto4);

     }



    public void removeAuthor(){
        authorBookService.deleteAuthor(new Long(2));
    }

    public void addRoles(){
        Role adminRole = new Role("ADMINISTRATOR");
        Role empRole = new Role("EMPLOYEE");
        roleRepository.save(adminRole);
        roleRepository.save(empRole);
    }

    public void addUsers(){
        UserDto admin = new UserDto();
        admin.username= "administrator@yahoo.com";
        admin.password= "Administrator1#";
        admin.role = "ADMINISTRATOR";
        authenticationService.register(admin);

        UserDto employ1 = new UserDto();
        employ1.username= "employee@yahoo.com";
        employ1.password= "Employee1#";
        employ1.role = "EMPLOYEE";
        authenticationService.register(employ1);

        UserDto employ2 = new UserDto();
        employ2.username= "frateIubit@yahoo.com";
        employ2.password= "Limbric3#";
        employ2.role = "EMPLOYEE";
        authenticationService.register(employ2);

    }

    public void deleteUser(){
        userService.delete(new Long(3));
    }

    public void updateUser(){
        UserDto userDto = new UserDto();
        userDto.id = new Long(1);
        userDto.username="mami e frumoasa";
        userDto.password="Lumina2#";
        userDto.role="ROLE_EMPLOYEE";
        userService.update(userDto);
    }

    public void updateBook(){
        BookDto bookDto = new BookDto();
        bookDto.id = new Long(1);
        bookDto.authorId = new Long(2);      // new authorBook id
        bookDto.isbn= "12345";
        bookDto.name="ce frumos zambesti";
        bookDto.quantity = 5000;
        bookDto.price = 12.3F;
        bookDto.genre ="drama";
        authorBookService.updateBookFromAuthor(bookDto);
    }

    public void removeBook(){
        authorBookService.removeBookFromAuthor( new Long(1));
    }


}
