package ro.ubb.iss.CMS.Services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileService {

  public String uploadDir="C:/test";
  public Path pathDir;


  public String store(MultipartFile file) {
    Path fileNamePath=this.pathDir.resolve(file.getOriginalFilename());
    try {
      Files.copy(file.getInputStream(), fileNamePath);
    } catch (Exception e) {
      throw new RuntimeException("FAIL!");
    }
    return fileNamePath.toString();
  }

  public Resource loadFile(String filename) {
    try {
      Path file = pathDir.resolve(filename);
      Resource resource = new UrlResource(file.toUri());
      if (resource.exists() || resource.isReadable()) {
        return resource;
      } else {
        throw new RuntimeException("FAIL!");
      }
    } catch (MalformedURLException e) {
      throw new RuntimeException("FAIL!");
    }
  }

  public void deleteAll() {
    FileSystemUtils.deleteRecursively(pathDir.toFile());
  }

  @PostConstruct
  public void init() {
    try {
      pathDir=Paths.get(uploadDir);
      Files.getFileStore(pathDir);
    } catch (IOException e) {
      throw new RuntimeException("Could not initialize storage!");
    }
  }
}
