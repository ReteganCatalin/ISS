package ro.ubb.iss.CMS.Services;

import ro.ubb.iss.CMS.domain.Paper;

import java.util.List;
import java.util.Optional;

public interface PaperService {

  Optional<Paper> findPaper(int paperID);

  List<Paper> findAll();

  Paper updatePaper(int paperID, String format, String byteFileLocation);

  Paper savePaper(String format, String byteFileLocation);

  void deletePaper(int paperID);
}
