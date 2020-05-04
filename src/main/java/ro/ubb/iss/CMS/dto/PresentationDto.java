package ro.ubb.iss.CMS.dto;

import lombok.*;
import ro.ubb.iss.CMS.domain.Conference;
import ro.ubb.iss.CMS.domain.Section;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@ToString
@Builder
public class PresentationDto {
    private Integer presentationID;
    private Section sectionID;
    private Conference conferenceID;
    private String format;
    private String byteFileLocation;
}
