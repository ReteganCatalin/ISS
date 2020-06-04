package ro.ubb.iss.CMS.converter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ro.ubb.iss.CMS.domain.Abstract;
import ro.ubb.iss.CMS.domain.MetaInformation;
import ro.ubb.iss.CMS.dto.AbstractDto;
import ro.ubb.iss.CMS.dto.MetaInfoDto;

import static org.junit.jupiter.api.Assertions.*;

class MetaInfoConverterTest {

  MetaInformation entity;
  MetaInfoDto dto;
  MetaInfoConverter converter;
  @BeforeEach
  void setUp() {

    entity = MetaInformation.builder().metaInfoId(1).topics("topic").name("names").keywords("keywords").build();
    dto = MetaInfoDto.builder().metaInfoId(1).topics("topic").name("names").keywords("keywords").build();
    converter = new MetaInfoConverter();
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


  }}