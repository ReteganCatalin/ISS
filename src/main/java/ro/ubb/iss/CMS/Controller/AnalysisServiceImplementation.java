package ro.ubb.iss.CMS.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.iss.CMS.Controller.AnalysisService;
import ro.ubb.iss.CMS.domain.Analysis;
import ro.ubb.iss.CMS.domain.AnalysisKey;
import ro.ubb.iss.CMS.Repository.AnalysisRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AnalysisServiceImplementation implements AnalysisService {
    private static final Logger log = LoggerFactory.getLogger(AnalysisServiceImplementation.class);

    @Autowired
    private AnalysisRepository analysisRepository;


    @Override
    public Optional<Analysis> findAnalysis(AnalysisKey analysisKey) {
        log.trace("findAnalysis - method entered analysisKey={}",analysisKey);
        Optional<Analysis> result = analysisRepository.findById(analysisKey);
        log.trace("findAnalysis - method exit result={}",result);
        return result;
    }

    @Override
    public List<Analysis> findAll() {
        log.trace("findAll - method entered");
        List<Analysis> result = analysisRepository.findAll();
        log.trace("findAll - method exit result={}",result);
        return result;
    }

    @Override
    @Transactional
    public Analysis updateAnalysis(AnalysisKey analysisKey, String briefAnalysis, Boolean refuse) {
        log.trace("updateAnalysis - method entered: analysisKey={}, briefAnalysis={}, refuse={}", analysisKey,briefAnalysis,refuse);

        Optional<Analysis> abstractOptional = analysisRepository.findById(analysisKey);

        abstractOptional.ifPresent(
                newAnalysis -> {
                    newAnalysis.setBriefAnalysis(briefAnalysis);
                    newAnalysis.setRefuse(refuse);
                    log.debug("updateAnalysis - updated: newAnalysis={}", newAnalysis);
                });
        log.trace("updateAnalysis - method finished result={}",abstractOptional);
        return abstractOptional.orElse(null);    }

    @Override
    public Optional<Analysis> saveAnalysis(AnalysisKey analysisKey, String briefAnalysis, Boolean refuse) {
        log.trace("saveAnalysis - method entered: analysisKey={}, briefAnalysis={}, refuse={}", analysisKey,briefAnalysis,refuse);
        Analysis newAnalysis = Analysis.builder().analysisKey(analysisKey).briefAnalysis(briefAnalysis).refuse(refuse).build();
        Optional<Analysis> checkForPresence = analysisRepository.findById(analysisKey);

        checkForPresence.ifPresentOrElse(analysis -> {},()->analysisRepository.save(newAnalysis));

        log.trace("saveAnalysis - method finished result={}",checkForPresence);
        return checkForPresence;

    }

    @Override
    public void deleteAnalysis(AnalysisKey analysisKey) {
        log.trace("deleteAnalysis - method entered: analysisKey={}", analysisKey);
        analysisRepository.deleteById(analysisKey);
        log.trace("deleteAnalysis - method finished");

    }
}
