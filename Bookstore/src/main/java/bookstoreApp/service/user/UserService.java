package bookstoreApp.service.user;

import bookstoreApp.dto.UserDto;

import java.util.List;

public interface UserService {
    void update(UserDto userDto);
    void delete(Long id);
    UserDto findById(Long id);
    List<UserDto> findAll();
}
