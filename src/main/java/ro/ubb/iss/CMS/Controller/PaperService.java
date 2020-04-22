package ro.ubb.iss.CMS.Controller;

import ro.ubb.iss.CMS.domain.MyTicket;
import ro.ubb.iss.CMS.domain.Paper;
import ro.ubb.iss.CMS.domain.Section;
import ro.ubb.iss.CMS.domain.User;

import java.util.List;
import java.util.Optional;

public interface PaperService {

    Optional<Paper> findPaper(int paperID);

    List<Paper> findAll();

    Paper updatePaper(int paperID, String format, String byteFileLocation);

    Paper savePaper(String format, String byteFileLocation);

    void deletePaper(int paperID);

}
