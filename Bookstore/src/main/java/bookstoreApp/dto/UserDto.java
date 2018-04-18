package bookstoreApp.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserDto {
    @NotNull(message = "Username is mandatory")
    public String username;

    @NotNull(message = "Password is mandatory")
    public String password;

    @NotNull(message = "Password is mandatory")
    public String role;
}

