package ro.ubb.iss.CMS.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.iss.CMS.Repository.PaperRepository;
import ro.ubb.iss.CMS.domain.MyTicket;
import ro.ubb.iss.CMS.domain.Paper;

import java.util.List;
import java.util.Optional;

@Service
public class PaperServiceImplementation implements PaperService{

    private static final Logger log = LoggerFactory.getLogger(PaperServiceImplementation.class);

    @Autowired
    PaperRepository paperRepository;

    @Override
    public Optional<Paper> findPaper(int paperID) {
        log.trace("findPaper - method entered paperID={}",paperID);
        Optional<Paper> result = paperRepository.findById(paperID);
        log.trace("findPaper - method exit result={}",result);
        return result;

    }

    @Override
    public List<Paper> findAll() {
        log.trace("findAll - method entered");
        List<Paper> result = paperRepository.findAll();
        log.trace("findAll - method exit result={}",result);
        return result;
    }

    @Override
    @Transactional
    public Paper updatePaper(int paperID, String format, String byteFileLocation) {
        log.trace("updatePaper - method entered: paperID={}, format={}, byteFileLocation={}",paperID,format,byteFileLocation);

        Optional<Paper> paperOptional = paperRepository.findById(paperID);

        paperOptional.ifPresent(
                newPaper -> {
                    newPaper.setByteFileLocation(byteFileLocation);
                    newPaper.setFormat(format);
                    log.debug("updatePaper - updated: newPaper={}", newPaper);
                });
        log.trace("updatePaper - method finished result={}",paperOptional);
        return paperOptional.orElse(null);
    }

    @Override
    public Paper savePaper(String format, String byteFileLocation) {
        log.trace("saveMyTicket - method entered: format={}, byteFileLocation={}",format,byteFileLocation);
        Paper newPaper = Paper.builder().byteFileLocation(byteFileLocation).format(format).build();

        paperRepository.save(newPaper);

        log.trace("saveMyTicket - method finished result={}",newPaper);
        return newPaper;
    }

    @Override
    public void deletePaper(int paperID) {
        log.trace("deletePaper - method entered: paperID={}", paperID);
        paperRepository.deleteById(paperID);
        log.trace("deletePaper - method finished");

    }

}
