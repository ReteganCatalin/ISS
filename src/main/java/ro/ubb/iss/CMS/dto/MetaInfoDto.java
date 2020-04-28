package ro.ubb.iss.CMS.dto;

import lombok.*;

import javax.persistence.Column;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@ToString
@Builder
public class MetaInfoDto {

  private Integer metaInfoId;

  private String name;

  private String keywords;

  private String topics;
}
