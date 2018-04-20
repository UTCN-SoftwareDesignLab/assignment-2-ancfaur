package bookstoreApp.converter;

import bookstoreApp.dto.UserDto;
import bookstoreApp.entity.User;

public interface UserConverter {
    UserDto fromUserToUserDto(User user);
    User fromUserDtoToUser(UserDto userDto);
}
