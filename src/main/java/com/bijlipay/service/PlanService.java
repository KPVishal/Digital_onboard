package com.bijlipay.service;

import com.bijlipay.model.Plan;
import java.util.List;

public interface PlanService {

    Plan isPlanExists(Plan plan);

    Plan createPlan(Plan plan);

    List<Plan> listOfPlan();

    Plan checkPlan(long id);

    void deActivePlan(long id);

    List<Plan> getActivePlan(boolean value);

    Plan findPlanById(long plan);

    Plan findPlanByName(String daily);
}
