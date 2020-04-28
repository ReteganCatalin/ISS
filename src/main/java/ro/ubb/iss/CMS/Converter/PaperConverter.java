package ro.ubb.iss.CMS.Converter;

import org.springframework.stereotype.Component;
import ro.ubb.iss.CMS.domain.Paper;
import ro.ubb.iss.CMS.dto.PaperDto;

@Component
public class PaperConverter implements BaseConverter<Paper, PaperDto> {
  @Override
  public Paper convertDtoToModel(PaperDto paperDto) {
    return Paper.builder()
        .paperId(paperDto.getPaperId())
        .paperId(paperDto.getPaperId())
        .byteFileLocation(paperDto.getByteFileLocation())
        .build();
  }

  @Override
  public PaperDto convertModelToDto(Paper paper) {
    return PaperDto.builder()
        .byteFileLocation(paper.getByteFileLocation())
        .format(paper.getFormat())
        .paperId(paper.getPaperId())
        .build();
  }
}
