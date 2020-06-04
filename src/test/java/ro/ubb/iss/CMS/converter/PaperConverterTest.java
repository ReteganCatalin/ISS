package ro.ubb.iss.CMS.converter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ro.ubb.iss.CMS.domain.Abstract;
import ro.ubb.iss.CMS.domain.Paper;
import ro.ubb.iss.CMS.dto.AbstractDto;
import ro.ubb.iss.CMS.dto.PaperDto;

import static org.junit.jupiter.api.Assertions.*;

class PaperConverterTest {

  Paper entity;
  PaperDto dto;
  PaperConverter converter;
  @BeforeEach
  void setUp() {

    entity = Paper.builder().byteFileLocation("/file.txt").format(".txt").paperId(1).build();
    dto = PaperDto.builder().byteFileLocation("/file.txt").format(".txt").paperId(1).build();
    converter = new PaperConverter();
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