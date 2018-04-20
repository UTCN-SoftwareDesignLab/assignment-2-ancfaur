package bookstoreApp.service.user;

import bookstoreApp.converter.UserConverter;
import bookstoreApp.dto.UserDto;
import bookstoreApp.entity.User;
import bookstoreApp.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;
    private EncodeService encodeService;
    private UserConverter userConverter;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, EncodeService encodeService, UserConverter userConverter){
        this.userRepository = userRepository;
        this.encodeService = encodeService;
        this.userConverter = userConverter;
    }


    @Override
    public void update(UserDto userDto) {
        userDto.password = encodeService.encode(userDto.password);
        userRepository.save(userConverter.fromUserDtoToUser(userDto));
    }

    @Override
    public void delete(Long id) {
        User user = userRepository.findById(id).get();
        userRepository.delete(user);
    }

    @Override
    public UserDto findById(Long id) {
        User user= userRepository.findById(id).get();
        UserDto userDto = userConverter.fromUserToUserDto(user);
        return userDto;
    }

    @Override
    public List<UserDto> findAll() {
      List<User> users = userRepository.findAll();
      List<UserDto> userDtos = new ArrayList<>();
      for (User user:users){
          userDtos.add(userConverter.fromUserToUserDto(user));
      }
      return userDtos;
    }
}
