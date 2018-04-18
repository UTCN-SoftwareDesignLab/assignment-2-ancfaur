package bookstoreApp.service.user;

import bookstoreApp.dto.UserDto;
import bookstoreApp.entity.Role;
import bookstoreApp.entity.User;
import bookstoreApp.repository.role.RoleRepository;
import bookstoreApp.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public AuthenticationServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public boolean register(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.username);
        user.setPassword(encodePassword(userDto.password));
        Role role = roleRepository.findByName(userDto.role);
        List<Role> roles = new ArrayList<Role>();
        roles.add(role);
        user.setRoles(roles);
        userRepository.save(user);
        return true;
    }

    @Override
    public User login(String username, String password) throws AuthenticationException {
        return userRepository.findByUsernameAndPassword(username, encodePassword(password));
    }


    private String encodePassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }



}
