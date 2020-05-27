package ro.ubb.iss.CMS.Services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.iss.CMS.Repository.PcMemberRepository;
import ro.ubb.iss.CMS.domain.PcMember;
import ro.ubb.iss.CMS.domain.PcMemberKey;

import java.util.List;
import java.util.Optional;

@Service
public class PcMemberServiceImplementation implements PcMemberService {

  private static final Logger log =
      LoggerFactory.getLogger(PermissionForRoleServiceImplementation.class);

  @Autowired PcMemberRepository pcMemberRepository;

  @Override
  public Optional<PcMember> findPcMember(PcMemberKey pcMemberKey) {
    log.trace("findPcMember - method entered pcMemberKey={}", pcMemberKey);
    Optional<PcMember> result = pcMemberRepository.findById(pcMemberKey);
    log.trace("findPcMember - method exit result={}", result);
    return result;
  }

  @Override
  public List<PcMember> findAll() {
    log.trace("findAll - method entered");
    List<PcMember> result = pcMemberRepository.findAll();
    log.trace("findAll - method exit result={}", result);
    return result;
  }

  @Override
  public Optional<PcMember> savePcMember(PcMemberKey pcMemberKey) {
    log.trace("savePcMember - method entered: pcMemberKey={}", pcMemberKey);
    PcMember newPcMember = PcMember.builder().pcMemberKey(pcMemberKey).build();
    Optional<PcMember> checkForPresence = pcMemberRepository.findById(pcMemberKey);

    checkForPresence.ifPresentOrElse(analysis -> {}, () -> pcMemberRepository.save(newPcMember));

    log.trace("savePcMember - method finished result={}", checkForPresence);
    return checkForPresence;
  }

  @Override
  public void deletePcMember(PcMemberKey pcMemberKey) {
    log.trace("deletePcMember - method entered: pcMemberKey={}", pcMemberKey);
    pcMemberRepository.deleteById(pcMemberKey);
    log.trace("deletePcMember - method finished");
  }
}
