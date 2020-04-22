package ro.ubb.iss.CMS.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.iss.CMS.Repository.PermissionForRoleRepository;
import ro.ubb.iss.CMS.domain.PermissionForRole;
import ro.ubb.iss.CMS.domain.PermissionForRoleKey;

import java.util.List;
import java.util.Optional;

@Service
public class PermissionForRoleServiceImplementation implements PermissionForRoleService{

    private static final Logger log = LoggerFactory.getLogger(PermissionForRoleServiceImplementation.class);

    @Autowired
    PermissionForRoleRepository permissionForRoleRepository;


    @Override
    public Optional<PermissionForRole> findPermissionForRole(PermissionForRoleKey permissionForRoleKey) {
        log.trace("findPermissionForRole - method entered permissionForRoleKey={}",permissionForRoleKey);
        Optional<PermissionForRole> result = permissionForRoleRepository.findById(permissionForRoleKey);
        log.trace("findPermissionForRole - method exit result={}",result);
        return result;
    }

    @Override
    public List<PermissionForRole> findAll() {
        log.trace("findAll - method entered");
        List<PermissionForRole> result = permissionForRoleRepository.findAll();
        log.trace("findAll - method exit result={}",result);
        return result;
    }

    @Override
    public Optional<PermissionForRole> savePermissionForRole(PermissionForRoleKey permissionForRoleKey) {
        log.trace("savePermissionForRole - method entered: permissionForRoleKey={}", permissionForRoleKey);
        PermissionForRole newPermissionForRole = PermissionForRole.builder().PermissionForRoleKey(permissionForRoleKey).build();
        Optional<PermissionForRole> checkForPresence = permissionForRoleRepository.findById(permissionForRoleKey);

        checkForPresence.ifPresentOrElse(analysis -> {},()->permissionForRoleRepository.save(newPermissionForRole));

        log.trace("savePermissionForRole - method finished result={}",checkForPresence);
        return checkForPresence;
    }

    @Override
    public void deletePermissionForRole(PermissionForRoleKey permissionForRoleKey) {
        log.trace("deletePermissionForRole - method entered: permissionForRoleKey={}", permissionForRoleKey);
        permissionForRoleRepository.deleteById(permissionForRoleKey);
        log.trace("deletePermissionForRole - method finished");

    }
}
