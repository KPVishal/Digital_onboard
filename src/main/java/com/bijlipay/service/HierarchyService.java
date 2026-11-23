package com.bijlipay.service;

import com.bijlipay.model.Hierarchy;

import java.util.List;

public interface HierarchyService {

    Hierarchy isHierarchyExists(Hierarchy hierarchy);


    Hierarchy save(Hierarchy hierarchy);

    List<Hierarchy> listActiveHierarchy(boolean isActive);

    List<Hierarchy> listAllHierarchy();

    Hierarchy fetchHierarchyById(int hierarchyId);

    Hierarchy update(int id, Hierarchy hierarchy);
    void deactivateHierarchy(int id);
}
