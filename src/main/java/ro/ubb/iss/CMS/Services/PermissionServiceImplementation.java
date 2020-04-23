package ro.ubb.iss.CMS.Services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.iss.CMS.Repository.PermissionRepository;
import ro.ubb.iss.CMS.domain.Permission;

import java.util.List;
import java.util.Optional;

@Service
public class PermissionServiceImplementation implements PermissionService{

    private static final Logger log = LoggerFactory.getLogger(PermissionServiceImplementation.class);

    @Autowired
    PermissionRepository permissionRepository;


    @Override
    public Optional<Permission> findPermission(int permissionID) {
        log.trace("findPermission - method entered");
        Optional<Permission> result = permissionRepository.findById(permissionID);
        log.trace("findPermission - method exit result={}",result);
        return result;
    }

    @Override
    public List<Permission> findAll() {
        log.trace("findAll - method entered");
        List<Permission> result = permissionRepository.findAll();
        log.trace("findAll - method exit result={}",result);
        return result;
    }

    @Override
    @Transactional
    public Permission updatePermission(int permissionID, String name) {
        log.trace("updatePermission - method entered: permissionID={}, name={}",permissionID,name);

        Optional<Permission> permissionOptional = permissionRepository.findById(permissionID);

        permissionOptional.ifPresent(
                newPermission -> {
                    newPermission.setPermissionName(name);
                    log.debug("updatePermission - updated: newPermission={}", newPermission);
                });
        log.trace("updatePermission - method finished result={}",permissionOptional);
        return permissionOptional.orElse(null);
    }

    @Override
    public Permission savePermission(String name) {
        log.trace("savePermission - method entered: name={}",name);
        Permission newPermission = Permission.builder().permissionName(name).build();

        permissionRepository.save(newPermission);

        log.trace("savePermission - method finished result={}",newPermission);
        return newPermission;
    }

    @Override
    public void deletePermission(int permissionID) {
        log.trace("deletePermission - method entered: permissionID={}", permissionID);
        permissionRepository.deleteById(permissionID);
        log.trace("deletePermission - method finished");

    }
}
