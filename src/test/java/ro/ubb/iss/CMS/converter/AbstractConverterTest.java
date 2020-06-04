package ro.ubb.iss.CMS.converter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ro.ubb.iss.CMS.domain.Abstract;
import ro.ubb.iss.CMS.dto.AbstractDto;

import static org.junit.jupiter.api.Assertions.*;

class AbstractConverterTest {

  Abstract entity;
  AbstractDto dto;
  AbstractConverter converter;
  @BeforeEach
  void setUp() {

    entity = Abstract.builder().abstractID(1).byteFileLocation("/dummy.txt").format(".txt").build();
    dto = AbstractDto.builder().abstractID(1).byteFileLocation("/dummy.txt").format(".txt").build();
    converter = new AbstractConverter();
  }

  @AfterEach
  void tearDown() {}

  @Test
  void convertDtoToModel() {

    assertEquals(entity, converter.convertDtoToModel(dto));

  }

  @Test
  void convertModelToDto() {

    assertEquals(dto, converter.convertModelToDto(entity));


  }
}