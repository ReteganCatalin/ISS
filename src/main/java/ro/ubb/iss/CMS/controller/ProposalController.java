package ro.ubb.iss.CMS.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import ro.ubb.iss.CMS.Services.AbstractService;
import ro.ubb.iss.CMS.Services.ProposalService;
import ro.ubb.iss.CMS.converter.AbstractConverter;
import ro.ubb.iss.CMS.converter.ProposalConverter;
import ro.ubb.iss.CMS.domain.*;
import ro.ubb.iss.CMS.dto.AbstractDto;
import ro.ubb.iss.CMS.dto.AbstractsDto;
import ro.ubb.iss.CMS.dto.ProposalDto;
import ro.ubb.iss.CMS.dto.ProposalsDto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@RestController
public class ProposalController {

    public static final Logger log = LoggerFactory.getLogger(ProposalController.class);

    @Autowired
    private ProposalService service;

    @Autowired private ProposalConverter converter;

    @PersistenceContext // or even @Autowired
    private EntityManager entityManager;

    @RequestMapping(value = "/proposals", method = RequestMethod.GET)
    public ProposalsDto getAllProposals() {
        log.trace("getAllProposals - method entered");
        ProposalsDto result = new ProposalsDto(converter.convertModelsToDtos(service.findAll()));
        log.trace("getAllProposals - method finished: result={}", result);
        return result;
    }

    @RequestMapping(value = "/proposals/{id}", method = RequestMethod.GET)
    public ProposalDto getProposal(@PathVariable Integer id) {
        log.trace("getProposal - method entered id={}", id);
        Optional<Proposal> proposal = service.findProposal(id);
        ProposalDto result = null;
        if (proposal.isPresent()) result = converter.convertModelToDto(proposal.get());
        log.trace("getProposal - method finished: result={}", result);
        return result;
    }

    @RequestMapping(value = "/proposals", method = RequestMethod.POST)
    public ProposalDto saveProposal(@RequestBody ProposalDto proposalDto) {
        log.trace("saveProposal - method entered abstractDto={}", proposalDto);
        Proposal result =
                service.saveProposal(
                        entityManager.getReference(UserInfo.class,proposalDto.getUserInfoId()),
                        entityManager.getReference(Paper.class,proposalDto.getPaperId()),
                        entityManager.getReference(MetaInformation.class,proposalDto.getMetaInfoId()),
                        entityManager.getReference(Abstract.class,proposalDto.getAbstractId()));

        ProposalDto resultToReturn = converter.convertModelToDto(result);
        log.trace("saveProposal - method finished: result={}", resultToReturn);
        return resultToReturn;
    }

    @RequestMapping(value = "/proposals", method = RequestMethod.PUT)
    public ProposalDto updateProposal(@RequestBody ProposalDto proposalDto) {
        log.trace("updateProposal - method entered: proposalDto={}", proposalDto);
        ProposalDto result =
                converter.convertModelToDto(
                        service.updateProposal(
                                proposalDto.getProposalId(),
                                entityManager.getReference(UserInfo.class,proposalDto.getUserInfoId()),
                                entityManager.getReference(Paper.class,proposalDto.getPaperId()),
                                entityManager.getReference(MetaInformation.class,proposalDto.getMetaInfoId()),
                                entityManager.getReference(Abstract.class,proposalDto.getAbstractId())
                        ));
        log.trace("updateProposal - method finished: result={}", result);
        return result;
    }

    @RequestMapping(value = "/proposals/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteProposal(@PathVariable Integer id) {
        log.trace("deleteProposal - method entered: id={}", id);
        try {
            service.deleteProposal(id);
        } catch (RestClientException ex) {
            log.trace("deleteProposal - exception caught ex={}", ex.getMessage());
            log.trace("deleteProposal - method finished bad");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        log.trace("deleteProposal - method finished");

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
