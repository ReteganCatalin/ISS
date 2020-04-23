package ro.ubb.iss.CMS.dto;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@ToString
@Builder
public class AuthorDto {

    private Integer authorId;
    private String name;
    private Integer proposalId;


}
