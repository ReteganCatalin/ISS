package ro.ubb.iss.CMS.Services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ro.ubb.iss.CMS.Repository.RoleForUserRepository;
import ro.ubb.iss.CMS.domain.*;

import java.util.List;
import java.util.Optional;

public class RoleForUserServiceImplementation implements RoleForUserService{

    private static final Logger log =
            LoggerFactory.getLogger(RoleForUserServiceImplementation.class);

    @Autowired
    RoleForUserRepository roleForUserRepository;

    @Override
    public Optional<RoleForUser> findRoleForUser(RoleForUserKey roleForUserKey) {
            log.trace(
                    "findRoleForUser - method entered roleForUserKey={}", roleForUserKey);
            Optional<RoleForUser> result = roleForUserRepository.findById(roleForUserKey);
            log.trace("findRoleForUser - method exit result={}", result);
            return result;
    }

    @Override
    public List<RoleForUser> findAll() {
            log.trace("findAll - method entered");
            List<RoleForUser> result = roleForUserRepository.findAll();
            log.trace("findAll - method exit result={}", result);
            return result;
    }

    @Override
    public Optional<RoleForUser> saveRoleForUser(RoleForUserKey roleForUserKey) {
                log.trace(
                        "saveRoleForUser - method entered: permissionForUserKey={}", roleForUserKey);
            RoleForUser newRoleForUser =
                        RoleForUser.builder().roleForUserKey(roleForUserKey).build();
                Optional<RoleForUser> checkForPresence =
                        roleForUserRepository.findById(roleForUserKey);

                checkForPresence.ifPresentOrElse(
                        analysis -> {}, () -> roleForUserRepository.save(newRoleForUser));

                log.trace("saveRoleForUser - method finished result={}", checkForPresence);
                return checkForPresence;
    }

    @Override
    public void deleteRoleForUser(RoleForUserKey roleForUserKey) {
                log.trace(
                        "deleteRoleForUser - method entered: roleForUserKey={}", roleForUserKey);
                roleForUserRepository.deleteById(roleForUserKey);
                log.trace("deleteRoleForUser - method finished");
    }
}
