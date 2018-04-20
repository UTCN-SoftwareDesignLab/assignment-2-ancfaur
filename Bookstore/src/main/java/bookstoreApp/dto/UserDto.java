package bookstoreApp.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class UserDto {

    public Long id;

    @Pattern(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    public String username;

    @Pattern(regexp ="^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$")
    public String password;

    @NotNull(message = "Role is mandatory")
    public String role;
}

