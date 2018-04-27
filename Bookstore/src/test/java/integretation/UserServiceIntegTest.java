package integretation;

import bookstoreApp.dto.UserDto;
import bookstoreApp.entity.Role;
import bookstoreApp.repository.role.RoleRepository;
import bookstoreApp.service.user.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringRunner;

import static bookstoreApp.constants.ApplicationConstants.Roles.ADMINISTRATOR;
import static bookstoreApp.constants.ApplicationConstants.Roles.EMPLOYEE;

@RunWith(SpringRunner.class)
@Configuration
@PropertySource("classpath:testing.properties")
@EnableJpaRepositories(basePackages = "bookstoreApp.repository")
@ComponentScan({"bookstoreApp.launcher", "bookstoreApp.entity", "bookstoreApp.dto", "bookstoreApp.repository", "bookstoreApp.service", "bookstoreApp.controller", "bookstoreApp.converter", "bookstoreApp.config"})
@EntityScan(basePackages ={"bookstoreApp.entity"})
public class UserServiceIntegTest {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserService userService;
    @Before
    public void setup(){
        roleRepository.deleteAll();
        userService.deleteAll();

        Role adminRole = new Role(ADMINISTRATOR);
        Role empRole = new Role(EMPLOYEE);
        roleRepository.save(adminRole);
        roleRepository.save(empRole);
    }

    @Test
    public void findAll(){
        int noBefore = userService.findAll().size();

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

        Assert.assertEquals(noBefore +2, userService.findAll().size());
    }

    @Test
    public void register(){
        UserDto admin = new UserDto();
        admin.username= "administrator1@yahoo.com";
        admin.password= "Administrator1#";
        admin.role = ADMINISTRATOR;
        UserDto back =userService.register(admin);
        UserDto registered= userService.findById(back.id);
        Assert.assertEquals("administrator1@yahoo.com", registered.username);
        Assert.assertTrue(userService.checkPasswords("Administrator1#", registered.password));
    }

    @Test
    public void updateUser(){
        UserDto admin = new UserDto();
        admin.username= "administrator1@yahoo.com";
        admin.password= "Administrator1#";
        admin.role = ADMINISTRATOR;
        UserDto back= userService.register(admin);
        back.setUsername("changedUsername@yahoo.com");
        back.setPassword("ChangedPass#1");
        userService.update(back);
        UserDto updated =userService.findById(back.id);
        Assert.assertEquals("changedUsername@yahoo.com", updated.username);
        Assert.assertTrue(userService.checkPasswords("ChangedPass#1", updated.password));
    }

    @Test
    public void deleteUser(){
        UserDto admin = new UserDto();
        admin.username= "administrator1@yahoo.com";
        admin.password= "Administrator1#";
        admin.role = ADMINISTRATOR;
        UserDto back= userService.register(admin);
        userService.delete(back.id);
        Assert.assertNull(userService.findById(back.id));
    }
}
