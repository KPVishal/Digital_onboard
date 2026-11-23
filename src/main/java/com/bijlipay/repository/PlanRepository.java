package com.bijlipay.repository;

import com.bijlipay.model.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {

    @Query("select p from Plan p where p.planName=?1")
    Plan fetchByPlanName(String planName);

    @Query("select p from Plan p where p.isActive=?1")
    List<Plan> fetchActivePlans(boolean value);

    @Query("SELECT p FROM Plan p WHERE p.id =?1")
    Plan findPlanById(long plan);
}
