package ro.ubb.iss.CMS.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import ro.ubb.iss.CMS.Services.ConferenceProposalService;
import ro.ubb.iss.CMS.converter.ConferenceProposalConverter;
import ro.ubb.iss.CMS.domain.ConferenceProposal;
import ro.ubb.iss.CMS.domain.ConferenceProposalKey;
import ro.ubb.iss.CMS.dto.ConferenceProposalDto;
import ro.ubb.iss.CMS.dto.ConferenceProposalDtos;

import java.util.Optional;

@RestController
public class ConferenceProposalController {

    public static final Logger log = LoggerFactory.getLogger(ConferenceController.class);

    @Autowired
    private ConferenceProposalService service;

    @Autowired private ConferenceProposalConverter converter;

    @RequestMapping(value = "/conference_proposal", method = RequestMethod.GET)
    public ConferenceProposalDtos getAllConferenceProposals() {
        log.trace("getAllConferenceProposals - method entered");
        ConferenceProposalDtos result = new ConferenceProposalDtos(converter.convertModelsToDtos(service.findAll()));
        log.trace("getAllConferenceProposals - method finished: result={}", result);
        return result;
    }

    @RequestMapping(value = "/conference_proposal/{proposalId}/{conferenceId}", method = RequestMethod.GET)
    public ConferenceProposalDto getConferenceProposal(@PathVariable Integer proposalId, @PathVariable Integer conferenceId) {
        log.trace("getConferenceProposal - method entered userId={}, conferenceId={}", proposalId, conferenceId);
        ConferenceProposalKey conferenceProposalKey =
                ConferenceProposalKey.builder().proposalID(proposalId).conferenceID(conferenceId).build();
        Optional<ConferenceProposal> conferenceProposal = service.findConferenceProposal(conferenceProposalKey);
        ConferenceProposalDto result = null;
        if (conferenceProposal.isPresent()) result = converter.convertModelToDto(conferenceProposal.get());
        log.trace("getConferenceProposal - method finished: result={}", result);
        return result;
    }

    @RequestMapping(value = "/conference_proposal", method = RequestMethod.POST)
    public ConferenceProposalDto saveConferenceProposal(@RequestBody ConferenceProposalDto conferenceProposalDto) {
        log.trace("saveConferenceProposal - method entered conferenceProposalDto={}", conferenceProposalDto);
        Optional<ConferenceProposal> result =
                service.saveConferenceProposal(
                        ConferenceProposalKey.builder()
                                .conferenceID(conferenceProposalDto.getConferenceID())
                                .proposalID(conferenceProposalDto.getProposalID())
                                .build());

        ConferenceProposalDto resultToReturn = null;
        if (result.isPresent()) resultToReturn = converter.convertModelToDto(result.get());
        log.trace("saveConferenceProposal - method finished: result={}", resultToReturn);
        return resultToReturn;
    }

    @RequestMapping(value = "/conference_proposal/{proposalId}/{conferenceId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteConferenceProposal(
            @PathVariable Integer proposalId, @PathVariable Integer conferenceId) {
        log.trace("deleteConferenceProposal - method entered: proposalId={}, conferenceId={}", proposalId, conferenceId);
        ConferenceProposalKey conferenceProposalKey =
                ConferenceProposalKey.builder().proposalID(proposalId).conferenceID(conferenceId).build();

        try {
            service.deleteConferenceProposal(conferenceProposalKey);
        } catch (RestClientException ex) {
            log.trace("deleteConferenceProposal - exception caught ex={}", ex.getMessage());
            log.trace("deleteConferenceProposal - method finished bad");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        log.trace("deleteConferenceProposal - method finished");

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
