package ro.ubb.iss.CMS.Converter;

import org.springframework.stereotype.Component;
import ro.ubb.iss.CMS.domain.Abstract;
import ro.ubb.iss.CMS.dto.AbstractDto;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class AbstractConverter implements BaseConverter<Abstract, AbstractDto> {

  @Override
  public Abstract convertDtoToModel(AbstractDto abstractDto) {
    return Abstract.builder()
        .abstractID(abstractDto.getAbstractID())
        .byteFileLocation(abstractDto.getByteFileLocation())
        .format(abstractDto.getFormat())
        .build();
  }

  @Override
  public AbstractDto convertModelToDto(Abstract anAbstract) {
    return AbstractDto.builder()
        .abstractID(anAbstract.getAbstractID())
        .byteFileLocation(anAbstract.getByteFileLocation())
        .format(anAbstract.getFormat())
        .build();
  }
}
