package com.bijlipay.repository;

import com.bijlipay.model.Vas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VasRepository extends JpaRepository<Vas, Integer> {

    Vas findVasById(int id);

    @Query("SELECT v FROM Vas v WHERE v.isActive = 1")
    List<Vas> getAll();

}
