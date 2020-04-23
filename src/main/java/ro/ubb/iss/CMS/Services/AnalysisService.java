package ro.ubb.iss.CMS.Services;

import ro.ubb.iss.CMS.domain.Analysis;
import ro.ubb.iss.CMS.domain.AnalysisKey;

import java.util.List;
import java.util.Optional;

public interface AnalysisService {
    Optional<Analysis> findAnalysis(AnalysisKey analysisKey);

    List<Analysis> findAll();

    Analysis updateAnalysis(AnalysisKey analysisKey, String briefAnalysis, Boolean refuse);

    Optional<Analysis> saveAnalysis(AnalysisKey analysisKey,String briefAnalysis, Boolean refuse);

    void deleteAnalysis(AnalysisKey analysisKey);

}
