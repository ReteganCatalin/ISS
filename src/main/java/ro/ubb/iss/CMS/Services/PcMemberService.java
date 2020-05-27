package ro.ubb.iss.CMS.Services;

import ro.ubb.iss.CMS.domain.PcMember;
import ro.ubb.iss.CMS.domain.PcMemberKey;

import java.util.List;
import java.util.Optional;

public interface PcMemberService {

  Optional<PcMember> findPcMember(PcMemberKey pcMemberKey);

  List<PcMember> findAll();

  Optional<PcMember> savePcMember(PcMemberKey pcMemberKey);

  void deletePcMember(PcMemberKey pcMemberKey);
}
