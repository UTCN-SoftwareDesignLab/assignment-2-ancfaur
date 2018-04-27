package bookstoreApp.service.user;

import bookstoreApp.dto.UserDto;

import java.util.List;

public interface UserService {
    void update(UserDto userDto);
    void delete(Long id);
    List<UserDto> findAll();
    UserDto register(UserDto userDto);

    // for testing purposes
    UserDto findById(Long id);
    boolean checkPasswords(String rawPass, String encodedPass);
    void deleteAll();
}
