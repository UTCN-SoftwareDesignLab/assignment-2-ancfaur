package bookstoreApp.converter;

import bookstoreApp.dto.UserDto;
import bookstoreApp.entity.Role;
import bookstoreApp.entity.User;
import bookstoreApp.repository.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("userConverter")
public class UserConverterImpl implements UserConverter {
    private RoleRepository roleRepository;

    @Autowired
    public UserConverterImpl(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }


    @Override
    public UserDto fromUserToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.username = user.getUsername();
        userDto.password = "encoded";
        userDto.id = user.getId();
        userDto.role = user.getRoles().get(0).getName();
        return userDto;
    }

    @Override
    public User fromUserDtoToUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.username);
        user.setPassword(userDto.password);
        user.setId(userDto.id);
        user.setRoles(findRoles(userDto.role));
        return user;
    }

    private List<Role> findRoles(String name){
        List<Role> roles = new ArrayList<>();
        Role role = roleRepository.findByName(name);
        roles.add(role);
        return roles;
    }

}
