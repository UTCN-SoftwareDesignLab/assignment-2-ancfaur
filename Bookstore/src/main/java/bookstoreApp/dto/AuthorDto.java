package bookstoreApp.dto;

import javax.validation.constraints.NotNull;

public class AuthorDto {
    public Long id;
    @NotNull(message = "Author is mandatory")
    public String name;
}
