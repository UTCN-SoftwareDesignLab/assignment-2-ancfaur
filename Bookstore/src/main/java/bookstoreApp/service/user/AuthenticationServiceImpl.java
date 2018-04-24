package bookstoreApp.service.user;

import bookstoreApp.converter.UserConverter;
import bookstoreApp.dto.UserDto;
import bookstoreApp.entity.User;
import bookstoreApp.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder =  new BCryptPasswordEncoder();
    private final UserConverter userConverter;

    @Autowired
    public AuthenticationServiceImpl(UserRepository userRepository, UserConverter userConverter) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;

    }

    @Override
    public boolean register(UserDto userDto) {
        userDto.password = encoder.encode(userDto.password);
        User user = userConverter.fromUserDtoToUser(userDto);
        userRepository.save(user);
        return true;
    }

    @Override
    public UserDto login(UserDto userDto) throws AuthenticationException {
       User user =userRepository.findByUsernameAndPassword(userDto.username, encoder.encode(userDto.password));
       UserDto userDtoBack = userConverter.fromUserToUserDto(user);
       return userDtoBack;
    }
}
