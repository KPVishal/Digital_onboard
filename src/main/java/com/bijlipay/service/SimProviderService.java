package com.bijlipay.service;

import com.bijlipay.model.SimProvider;

import java.util.List;

public interface SimProviderService {
    SimProvider saveSim(SimProvider simProvider);

    List<SimProvider> getAll();

    SimProvider deactivate(long simId);

    SimProvider getSim(long simId);

    SimProvider updateSim(SimProvider updateSim,SimProvider simProvider);
}
