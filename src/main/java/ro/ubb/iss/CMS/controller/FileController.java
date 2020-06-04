package ro.ubb.iss.CMS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ro.ubb.iss.CMS.Services.FileService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class FileController {
/*


  @GetMapping("/files/{filename:.+}")
  @ResponseBody
  public ResponseEntity<Resource> getFile(@PathVariable String filename) {
    Resource file = fileService.loadFile(filename);
    return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
            .body(file);
  }

  @GetMapping("/getAllFiles")
  public ResponseEntity<List<String>> getListFiles(Model model) {
    List<String> fileNames = files
            .stream().map(fileName -> MvcUriComponentsBuilder
                    .fromMethodName(FileController.class, "getFile", fileName).build().toString())
            .collect(Collectors.toList());

    return ResponseEntity.ok().body(fileNames);
  }

  @PostMapping("/uploadFile")
  public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {

  }*/
}
