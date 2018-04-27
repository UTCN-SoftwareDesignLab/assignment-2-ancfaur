package bookstoreApp.constants;

import bookstoreApp.dto.AuthorDto;
import bookstoreApp.dto.BookDto;
import bookstoreApp.dto.UserDto;
import bookstoreApp.entity.Role;
import bookstoreApp.repository.role.RoleRepository;
import bookstoreApp.service.authorBook.AuthorBookService;
import bookstoreApp.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import static bookstoreApp.constants.ApplicationConstants.Roles.ADMINISTRATOR;
import static bookstoreApp.constants.ApplicationConstants.Roles.EMPLOYEE;

@Component
public class Bootstrap {
    private AuthorBookService authorBookService;
    private RoleRepository roleRepository;
    private UserService userService;

    @Autowired
    public Bootstrap( AuthorBookService authorBookService, RoleRepository roleRepository, UserService userService){
        this.authorBookService = authorBookService;
        this.roleRepository = roleRepository;
        this.userService = userService;

    }

    @PostConstruct
    public void populateDb(){
        addRoles();
        addUsers();

        addAuthors();
        addBooksToAuthors();

    }

    public void addRoles(){
        Role adminRole = new Role(ADMINISTRATOR);
        Role empRole = new Role(EMPLOYEE);
        roleRepository.save(adminRole);
        roleRepository.save(empRole);
    }

    public void addUsers(){
        UserDto admin = new UserDto();
        admin.username= "administrator@yahoo.com";
        admin.password= "Administrator1#";
        admin.role = ADMINISTRATOR;
        userService.register(admin);

        UserDto employ1 = new UserDto();
        employ1.username= "employee@yahoo.com";
        employ1.password= "Employee1#";
        employ1.role = EMPLOYEE;
        userService.register(employ1);

        UserDto employ2 = new UserDto();
        employ2.username= "editMe@yahoo.com";
        employ2.password= "Limbric3#";
        employ2.role = EMPLOYEE;
        userService.register(employ2);

    }

    public void addAuthors(){
        AuthorDto authorDto1 = new AuthorDto();
        authorDto1.name="Faur Andrei";

        AuthorDto authorDto2 = new AuthorDto();
        authorDto2.name="Simedru Vlad";

        AuthorDto authorDto3 = new AuthorDto();
        authorDto3.name="Anca Comedy";

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
        bookDto1.name = "Viata de istoric";

        BookDto bookDto2 = new BookDto();
        bookDto2.authorId= new Long(1);
        bookDto2.genre ="comedy";
        bookDto2.isbn="23456";
        bookDto2.price=12.7F;
        bookDto2.quantity=0;
        bookDto2.name = "Ce de carti";


        BookDto bookDto3 = new BookDto();
        bookDto3.authorId= new Long(2);
        bookDto3.genre ="thriller";
        bookDto3.isbn="34567";
        bookDto3.price=100.6F;
        bookDto3.quantity=0;
        bookDto3.name = "Sa ne distram";

        BookDto bookDto4= new BookDto();
        bookDto4.authorId= new Long(3);
        bookDto4.genre ="biography";
        bookDto4.isbn="54323";
        bookDto4.price=19.26F;
        bookDto4.quantity=3232;
        bookDto4.name = "Human comedy";


        // add books to authors
        authorBookService.addBookToAuthor(bookDto1);
        authorBookService.addBookToAuthor(bookDto2);
        authorBookService.addBookToAuthor(bookDto3);
        authorBookService.addBookToAuthor(bookDto4);

    }


}
