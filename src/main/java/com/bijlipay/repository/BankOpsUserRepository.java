package com.bijlipay.repository;

import com.bijlipay.model.BankOpsUser;
import com.bijlipay.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankOpsUserRepository extends JpaRepository<BankOpsUser,Long> {

    @Query("Select l.leadSource.id from BankOpsUser l where l.user=?1")
    List<Long> findByUser(User l);
}
