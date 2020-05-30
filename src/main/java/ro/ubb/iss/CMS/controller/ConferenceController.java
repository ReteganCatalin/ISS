package ro.ubb.iss.CMS.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import ro.ubb.iss.CMS.Services.ConferenceDataService;
import ro.ubb.iss.CMS.converter.*;
import ro.ubb.iss.CMS.Services.ConferenceService;
import ro.ubb.iss.CMS.domain.*;
import ro.ubb.iss.CMS.dto.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
public class ConferenceController {

  public static final Logger log = LoggerFactory.getLogger(ConferenceController.class);

  @Autowired private ConferenceService service;
  @Autowired private ConferenceDataService conferenceDataService;

  @Autowired private ConferenceConverter converter;
  @Autowired private ProposalConverter proposalConverter;
  @Autowired private UserConverter userConverter;
  @Autowired private SectionConverter sectionConverter;
  @Autowired private BiddingProcessConverter biddingProcessConverter;
  @Autowired private MetaInfoConverter metaInfoConverter;
  @Autowired private AnalysisConverter analysisConverter;
  @Autowired private AbstractConverter abstractConverter;
  @Autowired private PaperConverter paperConverter;

  @RequestMapping(value = "/conferences", method = RequestMethod.GET)
  public ResponseEntity<ConferencesDto> getAllConferences() {
    log.trace("getAllConferences - method entered");
    ConferencesDto result = new ConferencesDto(converter.convertModelsToDtos(service.findAll()));
    log.trace("getAllConferences - method finished: result={}", result);
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @RequestMapping(value = "/conferences/{id}", method = RequestMethod.GET)
  public ResponseEntity<ConferenceDto> getConference(@PathVariable Integer id) {
    log.trace("getConference - method entered id={}", id);
    Optional<Conference> conference = service.findConference(id);
    ConferenceDto result = null;
    if (conference.isPresent()) result = converter.convertModelToDto(conference.get());
    log.trace("getConference - method finished: result={}", result);
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  /*
  SELECT bid.bid_id, bid.conference_id, bid.deadline,
  als.bid_id,als.user_id,als.proposal_id,als.brief_analysis,als.refuse,
  prop.proposal_id, mtf.*
  FROM public."bidding_process" as bid
  INNER JOIN public."analysis" as als ON als.bid_id=bid.bid_id
  INNER JOIN public."proposal" as prop ON prop.proposal_id = als.proposal_id
  INNER JOIN public."meta_info" as mtf ON mtf.meta_info_id = prop.proposal_id
  WHERE bid.conference_id = 1 and als.user_id=1
   */
  // Meta info, proposal only id, analysis everything, bid everyhting
  @RequestMapping(value = "/conferences/detail/{id}/{userId}", method = RequestMethod.GET)
  public Map<String, Object> getConferenceDetail(
      @PathVariable Integer id, @PathVariable Integer userId) {
    log.trace("getConferenceDetail - method entered id={}", id);
    Optional<Conference> conference = service.findConference(id);
    Map<String, Object> result = null;
    if (conference.isPresent()) {
      Optional<User> user =
          conference.get().getPcMembers().stream()
              .map(PcMember::getUser)
              .filter(user1 -> user1.getUserID().equals(userId))
              .findFirst();
      if (user.isPresent()) {
        result = new HashMap<>();
        BiddingProcess biddingProcess = conference.get().getBiddingProcess();
        result.put("bidding_process", biddingProcessConverter.convertModelToDto(biddingProcess));
        //        Map<AnalysisDto,Map<Integer,MetaInfoDto>> proposalMap = analyses.stream()
        //                .collect(Collectors
        //                        .toMap(key->analysisConverter.convertModelToDto(key),
        //                                value->{
        //                                  Map<Integer,MetaInfoDto> resultMap = new HashMap<>();
        //
        // resultMap.put(value.getProposal().getProposalID(),metaInfoConverter.convertModelToDto(value.getProposal().getMetaInformation()));
        //                                  return resultMap;
        //                                }));

        result.put("proposals",conference.get()
                .getProposalsForConference().stream().map(elem->{
                  HashMap<String,Object> map = new HashMap<>();

                  map.put("proposalData",proposalConverter.convertModelToDto(elem.getProposal()));
                  map.put("metaInfoForProposal",metaInfoConverter.convertModelToDto(elem.getProposal().getMetaInformation()));
                  map.put("abstract",abstractConverter.convertModelToDto(elem.getProposal().getAnAbstract()));
                  map.put("paper",paperConverter.convertModelToDto(elem.getProposal().getPaper()));

                  Analysis analysis =
                          biddingProcess.getAnalyses().stream()
                                  .filter(analysis1 -> analysis1.getUser().getUserID().equals(userId))
                                  .filter(analysis1 -> analysis1.getProposal().getProposalID().equals(elem.getProposal().getProposalID()))
                                  .findFirst().orElse(Analysis.builder().analysisKey(new AnalysisKey()).build());

//                  Map<String, Object> InnerMap =
//                          analyses.stream()
//                                  .map(
//                                          currentAnalysis -> {
//                                            Map<String, Object> values = new HashMap<>();
//                                            values.put("analysis_key", currentAnalysis.getAnalysisKey());
//                                            values.put(
//                                                    "analysis_data", analysisConverter.convertModelToDto(currentAnalysis));
//                                            return values;
//                                          })
//                                  .findFirst().get();


                  map.put("analysis",analysisConverter.convertModelToDto(analysis));

                  return map;
                }).collect(Collectors.toList()));

//        List<Map<String, Object>> resultMapList =
//            analyses.stream()
//                .map(
//                    currentAnalysis -> {
//                      Map<String, Object> values = new HashMap<>();
//                      values.put("analysis_key", currentAnalysis.getAnalysisKey());
//                      values.put(
//                          "analysis_data", analysisConverter.convertModelToDto(currentAnalysis));
//                      Map<String, Object> proposalData = new HashMap<>();
//                      proposalData.put(
//                          "proposal_id", currentAnalysis.getProposal().getProposalID());
//                      proposalData.put(
//                          "meta_information",
//                          metaInfoConverter.convertModelToDto(
//                              currentAnalysis.getProposal().getMetaInformation()));
//                      values.put("proposal_data", proposalData);
//                      return values;
//                    })
//                .collect(Collectors.toList());

//        result.put("analyses", resultMapList);

        //        result.put("analysis_data",proposalMap);
      }
    }
    log.trace("getConferenceDetail - method finished: result={}", result);
    return result;
  }

  @RequestMapping(value = "/conferences/{id}/accepted", method = RequestMethod.GET)
  public ResponseEntity<ProposalsDto> getConferenceAcceptedProposals(@PathVariable Integer id) {
    log.trace("getConferenceAccepted - method entered id={}", id);
    Optional<Conference> conference = service.findConference(id);
    ProposalsDto result = null;
    if (conference.isPresent())
      result =
          ProposalsDto.builder()
              .proposalDtoList(
                  proposalConverter.convertModelsToDtos(
                      conference.get().getProposalsForConference().stream()
                          .map(ConferenceProposal::getProposal)
                          .filter(
                              elem -> {
                                Set<Qualifier> qualifierSet =
                                    elem.getReviews().stream()
                                        .map(Review::getQualifier)
                                        .collect(Collectors.toSet());
                                if (qualifierSet.stream()
                                    .anyMatch(elem1 -> elem1.getName().equals("strong reject")))
                                  return false;
                                if (qualifierSet.stream()
                                    .anyMatch(elem1 -> elem1.getName().equals("reject")))
                                  return false;
                                if (qualifierSet.stream()
                                    .anyMatch(elem1 -> elem1.getName().equals("weak reject")))
                                  return false;
                                return true;
                              })
                          .collect(Collectors.toList())))
              .build();
    log.trace("getConferenceAccepted - method finished: result={}", result);
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @RequestMapping(value = "/conferences/{id}/refused", method = RequestMethod.GET)
  public ResponseEntity<ProposalsDto> getConferenceRefusedProposals(@PathVariable Integer id) {
    log.trace("getConferenceRefusedProposals - method entered id={}", id);
    Optional<Conference> conference = service.findConference(id);
    ProposalsDto result = null;
    if (conference.isPresent())
      result =
          ProposalsDto.builder()
              .proposalDtoList(
                  proposalConverter.convertModelsToDtos(
                      conference.get().getProposalsForConference().stream()
                          .map(ConferenceProposal::getProposal)
                          .filter(
                              elem -> {
                                Set<Qualifier> qualifierSet =
                                    elem.getReviews().stream()
                                        .map(Review::getQualifier)
                                        .collect(Collectors.toSet());
                                if (qualifierSet.stream()
                                    .anyMatch(elem1 -> elem1.getName().equals("borderline paper")))
                                  return false;
                                if (qualifierSet.stream()
                                    .anyMatch(elem1 -> elem1.getName().equals("weak accept")))
                                  return false;
                                if (qualifierSet.stream()
                                    .anyMatch(elem1 -> elem1.getName().equals("accept")))
                                  return false;
                                if (qualifierSet.stream()
                                    .anyMatch(elem1 -> elem1.getName().equals("strong reject")))
                                  return false;
                                return true;
                              })
                          .collect(Collectors.toList())))
              .build();
    log.trace("getConferenceRefusedProposals - method finished: result={}", result);
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @RequestMapping(value = "/conferences/{id}/conflicting", method = RequestMethod.GET)
  public ResponseEntity<ProposalsDto> getConferenceConflictingProposals(@PathVariable Integer id) {
    log.trace("getConferenceConflictingProposals - method entered id={}", id);
    Optional<Conference> conference = service.findConference(id);
    ProposalsDto result = null;
    if (conference.isPresent())
      result =
          ProposalsDto.builder()
              .proposalDtoList(
                  proposalConverter.convertModelsToDtos(
                      conference.get().getProposalsForConference().stream()
                          .map(ConferenceProposal::getProposal)
                          .filter(
                              elem -> {
                                Set<Qualifier> qualifierSet =
                                    elem.getReviews().stream()
                                        .map(Review::getQualifier)
                                        .collect(Collectors.toSet());
                                boolean positive =
                                    qualifierSet.stream()
                                        .anyMatch(elem1 -> elem1.getQualifierID() >= 4);
                                boolean negative =
                                    qualifierSet.stream()
                                        .anyMatch(elem1 -> elem1.getQualifierID() <= 3);

                                return positive && negative;
                              })
                          .collect(Collectors.toList())))
              .build();
    log.trace("getConferenceConflictingProposals - method finished: result={}", result);
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @RequestMapping(value = "/conferences/{id}/pc_members", method = RequestMethod.GET)
  @Transactional
  public ResponseEntity<UsersDto> getConferencePcMembers(@PathVariable Integer id) {
    log.trace("getConferencePcMembers - method entered id={}", id);
    Optional<Conference> conference = service.findConference(id);
    UsersDto result = null;
    if (conference.isPresent()) {
      result =
          UsersDto.builder()
              .userDtoList(
                  userConverter.convertModelsToDtos(
                      conference.get().getPcMembers().stream()
                          .map(PcMember::getUser)
                          .collect(Collectors.toList())))
              .build();
    }

    log.trace("getConferencePcMembers - method finished: result={}", result);
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @RequestMapping(value = "/conferences/{id}/sections", method = RequestMethod.GET)
  @Transactional
  public ResponseEntity<SectionsDto> getConferenceSections(@PathVariable Integer id) {
    log.trace("getConferenceSections - method entered id={}", id);
    Optional<Conference> conference = service.findConference(id);
    SectionsDto result = null;
    if (conference.isPresent()) {
      result =
          SectionsDto.builder()
              .sectionDtoList(sectionConverter.convertModelsToDtos(conference.get().getSections()))
              .build();
    }
    log.trace("getConferenceSections - method finished: result={}", result);
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @RequestMapping(value = "/conferences", method = RequestMethod.POST)
  public ResponseEntity<ConferenceDto> saveConference(@RequestBody ConferenceDto conferenceDto) {
    log.trace("saveConference - method entered conferenceDto={}", conferenceDto);
    Conference result =
        service.saveConference(
            conferenceDto.getName(),
            conferenceDto.getStartDate(),
            conferenceDto.getEndDate(),
            conferenceDto.getProposalDeadline(),
            conferenceDto.getPaperDeadline());

    ConferenceDto resultToReturn = converter.convertModelToDto(result);
    log.trace("saveConference - method finished: result={}", resultToReturn);
    return new ResponseEntity<>(resultToReturn, HttpStatus.OK);
  }

  @RequestMapping(value = "/conferences", method = RequestMethod.PUT)
  public ResponseEntity<ConferenceDto> updateConference(@RequestBody ConferenceDto conferenceDto) {
    log.trace("updateConference - method entered: conferenceDto={}", conferenceDto);
    ConferenceDto result =
        converter.convertModelToDto(
            service.updateConference(
                conferenceDto.getConferenceID(),
                conferenceDto.getName(),
                conferenceDto.getStartDate(),
                conferenceDto.getEndDate(),
                conferenceDto.getProposalDeadline(),
                conferenceDto.getPaperDeadline()));
    log.trace("updateConference - method finished: result={}", result);
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @RequestMapping(value = "/conferences/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<?> deleteConference(@PathVariable Integer id) {
    log.trace("deleteConference - method entered: id={}", id);

    try {
      service.deleteConference(id);
    } catch (RestClientException ex) {
      log.trace("deleteConference - exception caught ex={}", ex.getMessage());
      log.trace("deleteConference - method finished bad");
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    log.trace("deleteConference - method finished");

    return new ResponseEntity<>(HttpStatus.OK);
  }
}
