package com.bijlipay.service.implement;

import com.bijlipay.model.Hierarchy;
import com.bijlipay.repository.HierarchyRepository;
import com.bijlipay.service.HierarchyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HierarchyServiceImpl implements HierarchyService {


    @Autowired
    private HierarchyRepository hierarchyRepository;

    @Override
    public Hierarchy isHierarchyExists(Hierarchy hierarchy) {
        return hierarchyRepository.findByHierarchyName(hierarchy.getHierarchy());
    }

    @Override
    public Hierarchy save(Hierarchy hierarchy) {
        hierarchy.setActive(true);
        return hierarchyRepository.save(hierarchy);
    }

    @Override
    public List<Hierarchy> listActiveHierarchy(boolean isActive) {
        return hierarchyRepository.fetchActiveHierarchy(isActive);
    }

    @Override
    public List<Hierarchy> listAllHierarchy() {
        return hierarchyRepository.findAll();
    }

    @Override
    public Hierarchy fetchHierarchyById(int hierarchyId) {
        return hierarchyRepository.fetchHierarchyById(hierarchyId);
    }

    @Override
    public Hierarchy update(int id, Hierarchy hierarchy) {
        Hierarchy res = hierarchyRepository.fetchHierarchyById(id);
        if (res != null) {
            res.setHierarchy(hierarchy.getHierarchy());
            res.setHierarchyCode(hierarchy.getHierarchyCode());
            res.setActive(true);
            hierarchyRepository.save(res);
        }
        return res;
    }

    @Override
    public void deactivateHierarchy(int id) {
        Hierarchy hierarchy = hierarchyRepository.fetchHierarchyById(id);
        if (hierarchy != null) {
            hierarchy.setActive(false);
            hierarchyRepository.save(hierarchy);
        }
    }

}
