package com.bijlipay.repository;

import com.bijlipay.model.Hierarchy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HierarchyRepository extends JpaRepository<Hierarchy, Integer> {

    @Query("select h from Hierarchy h where h.hierarchy =?1")
    Hierarchy findByHierarchyName(String hierarchyName);

    @Query("select h from Hierarchy h where h.hierarchyCode =?1")
    Hierarchy findByHierarchyCode(String hierarchyCode);

    @Query("select h from Hierarchy h where h.isActive =?1")
    List<Hierarchy> fetchActiveHierarchy(boolean activeFlag);

    @Query("select h from Hierarchy h where h.hierarchyId=?1")
    Hierarchy fetchHierarchyById(int id);

}
