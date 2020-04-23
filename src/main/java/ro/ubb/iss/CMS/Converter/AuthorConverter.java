package ro.ubb.iss.CMS.Converter;

import org.springframework.stereotype.Component;
import ro.ubb.iss.CMS.domain.Author;
import ro.ubb.iss.CMS.dto.AuthorDto;

@Component
public class AuthorConverter implements BaseConverter<Author, AuthorDto> {
    @Override
    public Author convertDtoToModel(AuthorDto authorDto) {
        return Author.builder().authorId(authorDto.getAuthorId()).name(authorDto.getName()).build();
    }

    @Override
    public AuthorDto convertModelToDto(Author author) {
        return AuthorDto.builder().authorId(author.getAuthorId()).name(author.getName()).build();
    }
}
