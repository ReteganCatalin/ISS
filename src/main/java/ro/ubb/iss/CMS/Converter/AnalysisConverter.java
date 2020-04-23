package ro.ubb.iss.CMS.Converter;

import org.springframework.stereotype.Component;
import ro.ubb.iss.CMS.domain.Analysis;
import ro.ubb.iss.CMS.domain.AnalysisKey;
import ro.ubb.iss.CMS.dto.AnalysisDto;

@Component
public class AnalysisConverter implements BaseConverter<Analysis, AnalysisDto> {
    @Override
    public Analysis convertDtoToModel(AnalysisDto analysisDto) {
        return Analysis.builder().analysisKey(new AnalysisKey(analysisDto.getBidID(),analysisDto.getUserID(),analysisDto.getProposalID())).briefAnalysis(analysisDto.getBriefAnalysis()).refuse(analysisDto.getRefuse()).build();
    }

    @Override
    public AnalysisDto convertModelToDto(Analysis analysis) {
        return AnalysisDto.builder().bidID(analysis.getAnalysisKey().getBidID()).proposalID(analysis.getAnalysisKey().getProposalID()).
                userID(analysis.getAnalysisKey().getUserID())
                .briefAnalysis(analysis.getBriefAnalysis()).refuse(analysis.getRefuse()).build();
    }
}
