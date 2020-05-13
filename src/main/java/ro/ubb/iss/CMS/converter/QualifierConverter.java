package ro.ubb.iss.CMS.converter;

import org.springframework.stereotype.Component;
import ro.ubb.iss.CMS.domain.Qualifier;
import ro.ubb.iss.CMS.dto.QualifierDto;

@Component
public class QualifierConverter implements BaseConverter<Qualifier, QualifierDto> {
  @Override
  public Qualifier convertDtoToModel(QualifierDto qualifierDto) {
    return Qualifier.builder()
        .qualifierID(qualifierDto.getQualifierID())
        .name(qualifierDto.getName())
        .build();
  }

  @Override
  public QualifierDto convertModelToDto(Qualifier qualifier) {
    return QualifierDto.builder()
        .qualifierID(qualifier.getQualifierID())
        .name(qualifier.getName())
        .build();
  }
}
