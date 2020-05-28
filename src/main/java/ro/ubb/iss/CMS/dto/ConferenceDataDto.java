package ro.ubb.iss.CMS.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@ToString
@Builder
public class ConferenceDataDto {
    private Integer conferenceID;
    private String callForPaper;
    private String about;
    private String format;
    private String byteFileLocation;
}
