package com.bijlipay.service.implement;

import com.bijlipay.model.Region;
import com.bijlipay.repository.RegionRepository;
import com.bijlipay.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionServiceImpl implements RegionService {

    @Autowired
    private RegionRepository regionRepository;


    @Override
    public Region save(Region region) {
        region.setActive(true);
        return regionRepository.save(region);
    }

    @Override
    public boolean isExistsRegion(String region) {
        Region result = regionRepository.findByRegionAreaName(region);
        return result != null;
    }

    @Override
    public List<Region> getList() {
        return regionRepository.findAll();
    }

    @Override
    public Region getRegionById(Integer id) {
        return regionRepository.findRegionById(id);
    }

    @Override
    public Region update(int id, Region region) {
        Region regionToUpdate = regionRepository.findRegionById(id);
        if (regionToUpdate != null) {
            regionToUpdate.setRegionAreaName(region.getRegionAreaName());
            return regionRepository.save(regionToUpdate);
        }
        return null;
    }

    @Override
    public Region delete(int id) {
        Region regionToDelete = regionRepository.findRegionById(id);
        if (regionToDelete != null) {
            regionToDelete.setActive(false);
            return regionRepository.save(regionToDelete);
        }
        return null;
    }
}
