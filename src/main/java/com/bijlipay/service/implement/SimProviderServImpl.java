package com.bijlipay.service.implement;

import com.bijlipay.model.SimProvider;
import com.bijlipay.repository.SimRepository;
import com.bijlipay.service.SimProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SimProviderServImpl implements SimProviderService {

    @Autowired
    private SimRepository simRepository;

    @Override
    public SimProvider saveSim(SimProvider simProvider) {
        simProvider.setActive(true);
        return simRepository.save(simProvider);
    }

    @Override
    public List<SimProvider> getAll() {
        return simRepository.findAll();
    }

    @Override
    public SimProvider deactivate(long simId) {
        SimProvider simProvider = simRepository.findSimById(simId);
        if (simProvider != null) {
            simProvider.setActive(false);
            return simRepository.save(simProvider);
        }
        return null;
    }

    @Override
    public SimProvider getSim(long simId) {
        return simRepository.findSimById(simId);
    }

    @Override
    public SimProvider updateSim(SimProvider updateSim,SimProvider simProvider) {
        updateSim.setNetworkName(simProvider.getNetworkName());
       return simRepository.save(updateSim);
    }
}
