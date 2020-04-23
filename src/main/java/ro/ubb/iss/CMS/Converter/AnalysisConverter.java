package ro.ubb.iss.CMS.Converter;

import org.springframework.stereotype.Component;
import ro.ubb.iss.CMS.domain.Analysis;
import ro.ubb.iss.CMS.dto.AnalysisDto;

@Component
public class AnalysisConverter implements BaseConverter<Analysis, AnalysisDto> {
    @Override
    public Analysis convertDtoToModel(AnalysisDto analysisDto) {
        return Analysis.builder().analysisKey(analysisDto.getAnalysisKey()).briefAnalysis(analysisDto.getBriefAnalysis()).refuse(analysisDto.getRefuse()).build();
    }

    @Override
    public AnalysisDto convertModelToDto(Analysis analysis) {
        return AnalysisDto.builder().analysisKey(analysis.getAnalysisKey()).briefAnalysis(analysis.getBriefAnalysis()).refuse(analysis.getRefuse()).build();
    }
}
