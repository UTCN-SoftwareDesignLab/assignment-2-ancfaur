package bookstoreApp.dto;

import javax.validation.constraints.Pattern;

public class AuthorDto {
    public Long id;
    @Pattern(regexp = "^[a-zA-Z\\s]+", message="Author name is invalid, it should only contain letters")
    public String name;

    public AuthorDto(){}

    public AuthorDto(String name){
        this.name= name;
    }

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
