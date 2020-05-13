package ro.ubb.iss.CMS.Services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.iss.CMS.Repository.QualifierRepository;
import ro.ubb.iss.CMS.Repository.RoleRepository;
import ro.ubb.iss.CMS.domain.Qualifier;
import ro.ubb.iss.CMS.domain.Role;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImplementation implements RoleService {
  private static final Logger log = LoggerFactory.getLogger(RoleServiceImplementation.class);

  @Autowired private RoleRepository roleRepository;

  @Override
  public Optional<Role> findRole(int roleID) {
    log.trace("findRole - method entered roleID={}", roleID);
    Optional<Role> result = roleRepository.findById(roleID);
    log.trace("findRole - method exit result={}", result);
    return result;
  }

  @Override
  public List<Role> findAll() {
    log.trace("findAll - method entered");
    List<Role> result = roleRepository.findAll();
    log.trace("findAll - method exit result={}", result);
    return result;
  }

  @Override
  @Transactional
  public Role updateRole(int roleID, String name) {
    log.trace("updateRole - method entered: roleID={}, name={}", roleID, name);

    Optional<Role> abstractOptional = roleRepository.findById(roleID);

    abstractOptional.ifPresent(
        newRole -> {
          newRole.setName(name);

          log.debug("updateRole - updated: newRole={}", newRole);
        });
    log.trace("updateRole - method finished result={}", abstractOptional);
    return abstractOptional.orElse(null);
  }

  @Override
  public Role saveRole(String name) {
    log.trace("saveRole - method entered: name={}", name);
    Role newRole = Role.builder().name(name).build();

    roleRepository.save(newRole);

    log.trace("saveRole - method finished result={}", newRole);
    return newRole;
  }

  @Override
  public void deleteRole(int roleID) {
    log.trace("deleteRole - method entered: roleID={}", roleID);
    roleRepository.deleteById(roleID);
    log.trace("deleteRole - method finished");
  }
}
