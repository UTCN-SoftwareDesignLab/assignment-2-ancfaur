package bookstoreApp.service.user;
import bookstoreApp.converter.UserConverter;
import bookstoreApp.dto.UserDto;
import bookstoreApp.entity.User;
import bookstoreApp.repository.role.RoleRepository;
import bookstoreApp.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final EncodeService encodeService;
    private final UserConverter userConverter;

    @Autowired
    public AuthenticationServiceImpl(UserRepository userRepository, RoleRepository roleRepository, EncodeService encodeService, UserConverter userConverter) {
        this.userRepository = userRepository;
        this.encodeService = encodeService;
        this.userConverter = userConverter;
    }

    @Override
    public boolean register(UserDto userDto) {
        userDto.password = encodeService.encode(userDto.password);
        User user = userConverter.fromUserDtoToUser(userDto);
        userRepository.save(user);
        return true;
    }

    @Override
    public UserDto login(String username, String password) throws AuthenticationException {
       User user =userRepository.findByUsernameAndPassword(username, encodeService.encode(password));
       UserDto userDto = userConverter.fromUserToUserDto(user);
       return userDto;
    }
}
