package com.bijlipay.repository;

import com.bijlipay.model.ServiceRequestClients;
import com.bijlipay.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface ServiceRequestClientsRepository extends JpaRepository<ServiceRequestClients, Long> {

    @Query("select c from ServiceRequestClients c where c.userId= ?1")
    ServiceRequestClients findClientDetailsByUser(User user);

    @Transactional
    @Modifying
    @Query("delete from ServiceRequestClients d where d.userId= ?1")
    void deleteByUser(User update);

    @Query("select count(n) from ServiceRequestClients n where n.name= ?1")
    int findClientByName(String name);
}
