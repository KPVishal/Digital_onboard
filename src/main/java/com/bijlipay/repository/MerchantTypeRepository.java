package com.bijlipay.repository;

import com.bijlipay.model.MerchantType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MerchantTypeRepository extends JpaRepository<MerchantType,Integer> {


    @Query(value="Select mt from MerchantType mt where mt.isActive=1")
    List<MerchantType> getAll();
}
