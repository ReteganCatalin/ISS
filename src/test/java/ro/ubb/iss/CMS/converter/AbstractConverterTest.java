package ro.ubb.iss.CMS.converter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ro.ubb.iss.CMS.domain.Abstract;

import static org.junit.jupiter.api.Assertions.*;

class AbstractConverterTest {

  @BeforeEach
  void setUp() {

    Abstract anAbstract = Abstract.builder().abstractID(1).byteFileLocation("/dummy.txt").format(".txt").build();
    Abstract anAbstract2 = Abstract.builder().abstractID(2).byteFileLocation("/dummy.txt").format(".txt").build();

  }

  @AfterEach
  void tearDown() {}

  @Test
  void convertDtoToModel() {}

  @Test
  void convertModelToDto() {}
}