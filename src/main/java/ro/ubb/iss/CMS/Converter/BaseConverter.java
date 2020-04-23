package ro.ubb.iss.CMS.Converter;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public interface BaseConverter<Model, Dto> {

  Model convertDtoToModel(Dto dto);

  Dto convertModelToDto(Model model);

  default Set<Dto> convertModelsToDtos(Collection<Model> models) {
    return models.stream().map(this::convertModelToDto).collect(Collectors.toSet());
  }
}
