package ro.ubb.iss.CMS.converter;

import org.springframework.stereotype.Component;
import ro.ubb.iss.CMS.domain.Qualifier;
import ro.ubb.iss.CMS.dto.QualifierDto;

@Component
public class QualifierConverter implements BaseConverter<Qualifier, QualifierDto> {
  @Override
  public Qualifier convertDtoToModel(QualifierDto qualifierDto) {
    return Qualifier.values()[qualifierDto.getOrdinal()];
  }

  @Override
  public QualifierDto convertModelToDto(Qualifier qualifier) {
    return QualifierDto.builder()
        .ordinal(qualifier.ordinal())
        .value(qualifier.getQualifier_value())
        .build();
  }
}
