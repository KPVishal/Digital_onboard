package com.bijlipay.service.implement;

import com.bijlipay.model.Device;
import com.bijlipay.model.Region;
import com.bijlipay.model.Role;
import com.bijlipay.repository.RoleRepository;
import com.bijlipay.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.bijlipay.config.Constants.HIERARCHY_BIJLIPAY_LEVEL;

@Service
public class RoleServiceImpl implements RoleService {

    private static final Logger logger= LoggerFactory.getLogger(RoleServiceImpl.class);

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role save(Role role) {
        role.setActive(true);
        return roleRepository.save(role);
    }

    @Override
    public List<Role> listAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public List<Role> listActiveRoles(boolean is_active) {
        return roleRepository.fetchActiveRoles(is_active);
    }

    @Override
    public Role getRoleById(int id) {
        return roleRepository.findById(id);
    }

    @Override
    public Role getRoleByName(String roleName) {
        return roleRepository.fetchByRoleName(roleName);
    }

    @Override
    public List<Role> findRoleByHierarchy(int hierarchyId) {
        return roleRepository.getRoleByHierarchy(hierarchyId);
    }

    @Override
    public void deactivateRole(int id) {
        Role roleDetail = roleRepository.getReferenceById(id);
        if(roleDetail != null){
            roleDetail.setActive(false);
            roleRepository.save(roleDetail);
        }
    }

    @Override
    public Role getByPredecessor(Role role) {
        int roleLevel= role.getRoleLevel() -1;
        if(roleLevel== HIERARCHY_BIJLIPAY_LEVEL){
            return roleRepository.findByRoleLevel(roleLevel);
        }else {
            return roleRepository.findByPredecessor(roleLevel,role.getHierarchy());
        }
    }


}
