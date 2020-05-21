package ro.ubb.iss.CMS.utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class SaveToStorageUtilityTest {

  @BeforeEach
  void setUp() {}

  @AfterEach
  void tearDown() {}

  @Test
  void Given_NonExistingFile_When_TryToSaveFile_Then_returnsNull() {
    String result = SaveToStorageUtility.saveFileToStorage(".\\storage","nofile_that_exists.txt");
    assertNull(result);
  }

  @Test
  void Given_NonExistingSotrageAndExistingFile_When_TryToSaveFile_Then_CreatesNewStorageAndSavesTheFile() {

    try {
      File myObj = new File("test_file.txt");
      if(!myObj.createNewFile()){
        Assertions.fail("This file shouldn't exist!");
      }
      else{

        String result = SaveToStorageUtility.saveFileToStorage(".\\storage_dummy","test_file.txt");
        File myObjNewFile = new File(result);
        myObjNewFile.delete();
        File emptyDirectiory = new File(".\\storage_dummy");
        emptyDirectiory.delete();
        if(!myObj.delete()){
          Assertions.fail("Couldn't delete the file!");
        }
      }

    } catch (IOException e) {
      Assertions.fail("Cannot create file!");
    }

  }

  @Test
  void Given_ExistingStorageWithExistingFile_When_TryToSaveFile_Then_SavesFileToStorage() {

    try {
      File myObj = new File("test_file.txt");
      if(!myObj.createNewFile()){
        Assertions.fail("This file shouldn't exist!");
      }
      else{

        String result = SaveToStorageUtility.saveFileToStorage(".\\storage","test_file.txt");
        File myObjNewFile = new File(result);
        myObjNewFile.delete();
        if(!myObj.delete()){
          Assertions.fail("Couldn't delete the file!");
        }
      }

    } catch (IOException e) {
      Assertions.fail("Cannot create file!");
    }

  }


}