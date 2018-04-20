package bookstoreApp.dto;

import javax.validation.constraints.NotNull;

public class AuthorDto {
    public Long id;
    @NotNull(message = "Author is mandatory")
    public String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
