package com.bijlipay.repository;

import com.bijlipay.model.DocumentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentTypeRepository extends JpaRepository<DocumentType, Long> {

    @Query("select t from DocumentType t where t.name =?1")
    DocumentType fetchByTypeName(String name);

    @Query("select t from DocumentType t where t.isActive =?1")
    List<DocumentType> fetchActiveTypes(Boolean aBoolean);

    @Query("select t from DocumentType t where t.id =?1")
    DocumentType checkDocumentById(long id);
}
