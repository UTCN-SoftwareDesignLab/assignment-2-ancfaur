package bookstoreApp.dto;

import javax.validation.constraints.NotNull;
import java.util.HashSet;

public class AuthorDto {
    @NotNull(message = "Author is mandatory")
    public String name;
}
