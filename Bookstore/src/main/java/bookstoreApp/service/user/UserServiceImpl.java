package bookstoreApp.service.user;

import bookstoreApp.converter.UserConverter;
import bookstoreApp.dto.UserDto;
import bookstoreApp.entity.User;
import bookstoreApp.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;
    private UserConverter userConverter;
    private final BCryptPasswordEncoder encoder =  new BCryptPasswordEncoder();

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserConverter userConverter){
        this.userRepository = userRepository;
        this.userConverter = userConverter;
    }


    @Override
    public void update(UserDto userDto) {
        userDto.password = encoder.encode(userDto.password);
        userRepository.save(userConverter.fromUserDtoToUser(userDto));
    }

    @Override
    public void delete(Long id) {
        User user = userRepository.findById(id).orElse(null);
        userRepository.delete(user);
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

    @Override
    public UserDto register(UserDto userDto) {
        userDto.password = encoder.encode(userDto.password);
        User user = userConverter.fromUserDtoToUser(userDto);
        User back =userRepository.save(user);
        return userConverter.fromUserToUserDto(back);
    }

    // for testing purposes
    @Override
    public UserDto findById(Long id){
        User user =userRepository.findById(id).orElse(null);
        return userConverter.fromUserToUserDto(user);
    }

    @Override
    public boolean checkPasswords(String rawPass, String encodedPass){
       return encoder.matches(rawPass, encodedPass);
    }

    @Override
    public void deleteAll(){
        userRepository.deleteAll();
    }

}
