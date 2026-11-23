package com.bijlipay.repository;

import com.bijlipay.model.DocumentCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentCategoryRepository extends JpaRepository<DocumentCategory, Long> {

    @Query("select c from DocumentCategory c where c.name =?1")
    DocumentCategory fetchByCategoryName(String name);

    @Query("select c from DocumentCategory c where c.isActive =?1")
    List<DocumentCategory> fetchActivePlans(Boolean aBoolean);

    @Query("select c from DocumentCategory c where c.id =?1")
    DocumentCategory checkCategoryById(long id);
}
