package ro.ubb.service;

import ro.ubb.domain.Analysis;
import ro.ubb.domain.AnalysisKey;
import ro.ubb.domain.User;

import java.util.List;
import java.util.Optional;

public interface AnalysisService {
    Optional<Analysis> findAnalysis(AnalysisKey analysisKey);

    List<Analysis> findAll();

    Analysis updateAnalysis(AnalysisKey analysisKey, String briefAnalysis, Boolean refuse);

    Optional<Analysis> saveAnalysis(AnalysisKey analysisKey,String briefAnalysis, Boolean refuse);

    void deleteAnalysis(AnalysisKey analysisKey);

}
