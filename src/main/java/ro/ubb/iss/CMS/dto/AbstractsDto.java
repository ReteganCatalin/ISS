package ro.ubb.iss.CMS.dto;

import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@ToString
@Builder
public class AbstractsDto {

  private Set<AbstractDto> abstractDtos;
}
