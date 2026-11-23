package com.bijlipay.repository;

import com.bijlipay.model.DocumentCategory;
import com.bijlipay.model.DocumentType;
import com.bijlipay.model.MerchantCategoryCode;
import com.bijlipay.model.SubDocumentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubDocumentTypeRepository extends JpaRepository<SubDocumentType, Long> {

    @Query("select s from SubDocumentType s where s.name =?1")
    SubDocumentType fetchSubDocumentByName(String name);

    @Query("select s from SubDocumentType s where s.isActive =?1")
    List<SubDocumentType> findByActive(Boolean aBoolean);

    @Query("select s from SubDocumentType s where s.subDocumentTypeId =?1")
    SubDocumentType findBySubDocumentId(long id);

    @Query("SELECT s FROM SubDocumentType s WHERE s.mccCategoryIndicator =? 1")
    List<SubDocumentType> findDocumentsByIndicator(int value);

    @Query("select s from SubDocumentType s where s.merchantCategoryCode.mccCode= ?1")
    SubDocumentType fetchBizCategoryDocuments(String mcc);

    @Query("select s from SubDocumentType s where s.name=?2 and s.documentCategory=?1")
    SubDocumentType fetchCategoryMappingWithOutMcc(DocumentCategory documentCategory, String name);

    @Query("select s from SubDocumentType s where s.name=?2 and s.documentCategory=?1 and s.merchantCategoryCode=?3")
    SubDocumentType fetchCategoryMappingWithMcc(DocumentCategory documentCategory, String name, MerchantCategoryCode merchantCategoryCode);

    @Query("select s from SubDocumentType s where s.mccCategoryIndicator =?1")
    List<SubDocumentType> fetchDocumentTypeByMccIndicator(int mccCategoryIndicatorImplement);
}
