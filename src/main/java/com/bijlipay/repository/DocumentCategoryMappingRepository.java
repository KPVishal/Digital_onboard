//package com.bijlipay.repository;
//
//import com.bijlipay.model.*;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//import java.util.List;
//import java.util.Optional;
//
//@Repository
//public interface DocumentCategoryMappingRepository extends JpaRepository<DocumentCategoryMapping, Long> {
//
//    @Query("select d from DocumentCategoryMapping d where d.subDocumentType=?2 and d.documentCategory=?1 and d.merchantCategoryCode=?3")
//    DocumentCategoryMapping fetchCategoryMappingWithMcc(DocumentCategory documentCategory, SubDocumentType subDocumentType, MerchantCategoryCode merchantCategoryCode);
//
//    @Query("select d from DocumentCategoryMapping d where d.id =?1")
//    List<DocumentCategoryMapping> fetchSingleDocumentMapping(Optional<Long> id);
//
//    @Query("select d from DocumentCategoryMapping d where d.id =?1")
//    DocumentCategoryMapping fetchSingleDocumentMappings(long id);
//
//    @Query("select d from DocumentCategoryMapping d where d.subDocumentType=?2 and d.documentCategory=?1")
//    DocumentCategoryMapping fetchCategoryMappingWithOutMcc(DocumentCategory documentCategory, SubDocumentType subDocumentType);
//
//    @Query("select d from DocumentCategoryMapping d where d.merchantCategoryCode.mccCode= ?1")
//    DocumentCategoryMapping fetchBizCategoryDocuments(String mcc);
//
//    @Query("select d from DocumentCategoryMapping d where d.mccCategoryIndicator= ?1")
//    List<DocumentCategoryMapping> findDocumentsByIndicator(int value);
//}
