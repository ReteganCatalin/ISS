package ro.ubb.iss.CMS.dto;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@ToString
@Builder
public class AbstractDto {
    private Integer abstractID;
    private String format;
    private String byteFileLocation;
}
