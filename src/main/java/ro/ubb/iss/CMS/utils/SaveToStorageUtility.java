package ro.ubb.iss.CMS.utils;

import ro.ubb.iss.CMS.MyExceptions.UnableToCreateStorageDirectoryException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SaveToStorageUtility {

  private static String getAlphaNumericString(int n) {
    // chose a Character random from this String
    String AlphaNumericString =
        "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";
    // create StringBuffer size of AlphaNumericString
    StringBuilder sb = new StringBuilder(n);

    for (int i = 0; i < n; i++) {
      // generate a random number between
      // 0 to AlphaNumericString variable length
      int index = (int) (AlphaNumericString.length() * Math.random());
      // add Character one by one in end of sb
      sb.append(AlphaNumericString.charAt(index));
    }
    return sb.toString();
  }

  private static String getCurrentDateInString() {
    Date date = Calendar.getInstance().getTime();
    DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss");
    return dateFormat.format(date);
  }

  private static String getFileName(String pathString) {
    Path path = Paths.get(pathString);

    // call getFileName() and get FileName path object
    Path fileName = path.getFileName();

    // print FileName
    return fileName.toString();
  }

  public static String saveFileToStorage(String mainStorage, String sourceFile) {
    String currentDate = getCurrentDateInString();
    String randomString = getAlphaNumericString(10);
    String fileName = getFileName(sourceFile);
    File storageDirectory = new File(mainStorage);

    if (!storageDirectory.exists()) {
      if (!storageDirectory.mkdirs())
        throw new UnableToCreateStorageDirectoryException("Unable to create the storage directory");
    }

    String destinationLocation =
        mainStorage
            + File.separator
            + currentDate
            + "_"
            + randomString
            + "_"
            + fileName;
    Path source = Paths.get(sourceFile);
    Path destination = Paths.get(destinationLocation);
    try {
      Files.copy(source, destination);
    } catch (IOException e) {
      return null;
    }
    return destinationLocation;
  }
}
