package com.bijlipay.service;

import com.bijlipay.model.Device;

import com.bijlipay.model.Role;

import java.util.List;

public interface RoleService {

    Role save(Role role);

    List<Role> listAllRoles();

    List<Role> listActiveRoles(boolean is_active);

    Role getRoleById(int id);

    Role getRoleByName(String roleName);

    List<Role> findRoleByHierarchy(int hierarchyId);

    void deactivateRole(int id);

    Role getByPredecessor(Role role);

}
