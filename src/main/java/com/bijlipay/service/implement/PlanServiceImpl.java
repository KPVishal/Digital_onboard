package com.bijlipay.service.implement;

import com.bijlipay.model.Plan;
import com.bijlipay.repository.PlanRepository;
import com.bijlipay.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PlanServiceImpl implements PlanService {

    @Autowired
    private PlanRepository planRepository;

    @Override
    public Plan isPlanExists(Plan plan) {
        return planRepository.fetchByPlanName(plan.getPlanName());
    }

    @Override
    public Plan createPlan(Plan plan) {
        plan.setActive(true);
        return planRepository.save(plan);
    }

    @Override
    public List<Plan> listOfPlan() {
        return planRepository.findAll();
    }

    @Override
    public Plan checkPlan(long id) {
        return planRepository.getReferenceById(id);
    }

    @Override
    public void deActivePlan(long id) {
        Plan plan = planRepository.getReferenceById(id);
        if(plan != null){
            plan.setActive(false);
            planRepository.save(plan);
        }
    }

    @Override
    public List<Plan> getActivePlan(boolean value) {
        return planRepository.fetchActivePlans(value);
    }

    @Override
    public Plan findPlanById(long plan) {
        return planRepository.findPlanById(plan);
    }

    @Override
    public Plan findPlanByName(String daily) {
        return planRepository.fetchByPlanName(daily);
    }
}
