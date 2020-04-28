package ro.ubb.iss.CMS.dto;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@ToString
@Builder
public class PaperDto {

  private Integer paperId;

  private String format;

  private String byteFileLocation;
}
