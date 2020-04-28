package ro.ubb.iss.CMS.converter;

import org.springframework.stereotype.Component;
import ro.ubb.iss.CMS.domain.MetaInformation;
import ro.ubb.iss.CMS.dto.MetaInfoDto;

@Component
public class MetaInfoConverter implements BaseConverter<MetaInformation, MetaInfoDto> {
  @Override
  public MetaInformation convertDtoToModel(MetaInfoDto metaInfoDto) {
    return MetaInformation.builder()
        .keywords(metaInfoDto.getKeywords())
        .metaInfoId(metaInfoDto.getMetaInfoId())
        .name(metaInfoDto.getName())
        .topics(metaInfoDto.getTopics())
        .build();
  }

  @Override
  public MetaInfoDto convertModelToDto(MetaInformation metaInformation) {
    return MetaInfoDto.builder()
        .keywords(metaInformation.getKeywords())
        .metaInfoId(metaInformation.getMetaInfoId())
        .name(metaInformation.getName())
        .topics(metaInformation.getTopics())
        .build();
  }
}
