package com.bijlipay.repository;

import com.bijlipay.model.AggregatorData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AggregatorRepository extends JpaRepository<AggregatorData, Long> {

    @Query("SELECT COUNT(*) FROM AggregatorData a WHERE a.aggName =?1")
    int isAggExists(String aggregator);

    @Query("SELECT a FROM AggregatorData a WHERE a.isActive =?1")
    List<AggregatorData> getAllAggregator(boolean active);
}
