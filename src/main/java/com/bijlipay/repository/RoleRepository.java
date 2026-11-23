package com.bijlipay.repository;

import com.bijlipay.model.Hierarchy;
import com.bijlipay.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {

    Role findById(int roleId);

    @Query("SELECT r FROM Role r WHERE r.role like ?1")
    Role fetchByRoleName(String role);

    @Query("SELECT r FROM Role r")
    List<Role> findAll();

    @Query("SELECT r FROM Role r WHERE r.isActive = ?1")
    List<Role> fetchActiveRoles(boolean is_active);

    @Query("SELECT r FROM Role r LEFT JOIN r.hierarchy h WHERE h.id = ?1 AND r.isActive =1 ORDER BY r.roleLevel ASC")
    List<Role> getRoleByHierarchy(int hierarchyId);


    @Query("Select r from Role r where r.isActive=1 and r.roleLevel=?1")
    Role findByRoleLevel(int roleLevel);

    @Query("Select r from Role r where r.isActive=1 and  r.roleLevel=?1 and r.hierarchy=?2")
    Role findByPredecessor(int roleLevel, Hierarchy hierarchy);

    @Query("Select r from Role r where r.isActive=1 and r.roleId=?1")
    Role getAllRoles(int roleId);

    @Query("Select r from Role r where r.isActive=1 and r.roleLevel <=?1")
    List<Role> getAll(int rolelevel);


    @Query("Select r from Role r where r.role=?1")
    Role getRoleByName(String role);
//
//
//    @Query("SELECT id FROM Role r WHERE r.role like ?1%")
//    List<Integer> findAllRole(String role);
}
