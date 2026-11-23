package com.bijlipay.service;

import com.bijlipay.model.AggregatorData;

import java.util.List;

public interface AggregatorService {
    boolean isAggregatorExists(AggregatorData aggregator);

    AggregatorData saveAggregator(AggregatorData aggregator);

    List<AggregatorData> fetchAllAggList();
}
