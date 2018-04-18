package bookstoreApp.service.user;

import bookstoreApp.dto.UserDto;
import bookstoreApp.entity.User;
import bookstoreApp.service.user.AuthenticationException;

public interface AuthenticationService {

    boolean register(UserDto userDto);

    User login(String username, String password) throws AuthenticationException;

   // boolean updateUserAccount(String username, String password, Long userId) ;

}