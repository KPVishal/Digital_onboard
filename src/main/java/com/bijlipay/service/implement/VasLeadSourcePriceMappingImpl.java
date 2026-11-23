package com.bijlipay.service.implement;

import com.bijlipay.model.VasLeadSourcePriceMapping;
import com.bijlipay.repository.LeadSourceRepo;
import com.bijlipay.repository.VasLeadSourcePriceMappingRepo;
import com.bijlipay.repository.VasRepository;
import com.bijlipay.service.VasLeadSourcePriceMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VasLeadSourcePriceMappingImpl implements VasLeadSourcePriceMappingService {

    @Autowired
    private VasLeadSourcePriceMappingRepo vasLeadSourcePriceMappingRepo;

    @Autowired
    private LeadSourceRepo leadSourceRepo;

    @Autowired
    private VasRepository vasRepository;

    @Override
    public VasLeadSourcePriceMapping createVasPrice(VasLeadSourcePriceMapping vasLeadSourcePriceMapping) {
        return vasLeadSourcePriceMappingRepo.save(vasLeadSourcePriceMapping);
    }

    @Override
    public List<VasLeadSourcePriceMapping> getAllVasLeadSourcePrice() {
        return vasLeadSourcePriceMappingRepo.findAll();
    }



}
