package com.bijlipay.service.implement;

import com.bijlipay.model.AggregatorData;
import com.bijlipay.repository.AggregatorRepository;
import com.bijlipay.service.AggregatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AggregatorServiceImpl implements AggregatorService {

    @Autowired
    private AggregatorRepository aggregatorRepository;

    @Override
    public boolean isAggregatorExists(AggregatorData aggregator) {
        return aggregatorRepository.isAggExists(aggregator.getAggName()) > 0;
    }

    @Override
    public AggregatorData saveAggregator(AggregatorData aggregator) {
        return aggregatorRepository.save(aggregator);
    }

    @Override
    public List<AggregatorData> fetchAllAggList() {
        return aggregatorRepository.getAllAggregator(true);
    }
}
