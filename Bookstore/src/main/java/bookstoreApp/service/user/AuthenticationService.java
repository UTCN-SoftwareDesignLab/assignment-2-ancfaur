package bookstoreApp.service.user;

import bookstoreApp.dto.UserDto;

public interface AuthenticationService {

    boolean register(UserDto userDto);

    UserDto login(UserDto userDto) throws AuthenticationException;

}