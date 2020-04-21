package ro.ubb.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.domain.Analysis;
import ro.ubb.domain.AnalysisKey;
import ro.ubb.domain.User;
import ro.ubb.repository.AnalysisRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AnalysisServiceImplementation implements AnalysisService{
    private static final Logger log = LoggerFactory.getLogger(AnalysisServiceImplementation.class);

    @Autowired
    private AnalysisRepository analysisRepository;


    @Override
    public Optional<Analysis> findAnalysis(AnalysisKey analysisKey) {
        log.trace("findAnalysis - method entered");
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

        analysisRepository.save(newAnalysis);
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
