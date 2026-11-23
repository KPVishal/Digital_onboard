package com.bijlipay.repository;

import com.bijlipay.model.LeadSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LeadSourceRepo extends JpaRepository<LeadSource, Integer> {

    @Query("SELECT ls FROM LeadSource ls where ls.isActive=1")
    List<LeadSource> getLeadSource();

    @Query("Select ls from LeadSource ls where ls.id=?1")
    LeadSource findLeadSourceById(int sourceid);

    @Query("SELECT l FROM LeadSource l WHERE l.id = ?1")
    LeadSource fetchLeadSource(int leadSource);
}
