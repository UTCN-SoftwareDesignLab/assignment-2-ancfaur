package bookstoreApp.dto;

public class AuthorBookDto {
    public BookDto bookDto;
    public AuthorDto authorDto;

    public AuthorBookDto(){}

    public AuthorBookDto(AuthorDto authorDto, BookDto bookDto){
        this.authorDto = authorDto;
        this.bookDto = bookDto;
    }

    public BookDto getBookDto() {
        return bookDto;
    }

    public void setBookDto(BookDto bookDto) {
        this.bookDto = bookDto;
    }

    public AuthorDto getAuthorDto() {
        return authorDto;
    }

    public void setAuthorDto(AuthorDto authorDto) {
        this.authorDto = authorDto;
    }

}
