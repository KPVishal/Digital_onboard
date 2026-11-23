package com.bijlipay.service;

import com.bijlipay.model.DocumentCategory;
//import com.bijlipay.model.DocumentCategoryMapping;
import com.bijlipay.model.DocumentType;
import com.bijlipay.model.MccReason;
import com.bijlipay.model.SubDocumentType;

import java.util.List;
import java.util.Optional;

public interface DocumentService {

    DocumentCategory isCategoryExists(DocumentCategory documentCategory);

    DocumentCategory createCategory(DocumentCategory documentCategory);

    List<DocumentCategory> listOfCategory();

    DocumentCategory updateCategory(long id, DocumentCategory documentCategory);

    DocumentCategory getCategory(long id);

    void deActiveCategory(long id);

    List<DocumentCategory> getActiveCategory(Boolean aBoolean);

    DocumentType isTypeExists(DocumentType documentType);

    DocumentType CreateType(DocumentType documentType);

    List<DocumentType> getAllTypes();

    List<DocumentType> getActiveTypes(Boolean aBoolean);

    DocumentType checkDocumentType(long id);

    DocumentType updateType(long id, DocumentType documentType);

    void deleteType(long id);

//    DocumentCategoryMapping isMappingExists(DocumentCategoryMapping documentCategoryMapping);
//
//    DocumentCategoryMapping createDocumentCategory(DocumentCategoryMapping documentCategoryMapping);
//
//    List<DocumentCategoryMapping> getSingleMapping(Optional<Long> id);
//
//    List<DocumentCategoryMapping> getAllMapping();
//
//    DocumentCategoryMapping getCategoryMap(long id);
//
//    DocumentCategoryMapping updateCategoryMapping(long id, DocumentCategoryMapping documentCategoryMapping);
//
//    void deleteCategoryMapping(DocumentCategoryMapping categoryMapping);

    SubDocumentType isMappingExists(SubDocumentType subDocumentType);

    //    List<DocumentCategoryMapping> getBizDocuments(String mcc);
    List<SubDocumentType> getBizDocuments(String mcc);

    //  List<DocumentCategoryMapping> getBasicDocuments(int value);
    List<SubDocumentType> getBasicDocuments(int value);

    SubDocumentType isSubDocumentExists(SubDocumentType subDocumentType);

    SubDocumentType createSubDocument(SubDocumentType subDocumentType);

    List<SubDocumentType> getDocumentActiveTypes(Boolean aBoolean);

    List<SubDocumentType> getDocumentAllTypes();

    SubDocumentType checkSubDocumentType(long id);

    SubDocumentType updateSubDocumentType(long id, SubDocumentType documentType);

    void deleteSubDocumentType(long id);

    List<SubDocumentType> collectImplementationDocuments();

    MccReason createMccReason(MccReason mccReason);

    List<MccReason> getMccReasonList();

    MccReason updateMccReasonList(Long id,MccReason mccReason);

    List<SubDocumentType> getBankAccountDocuments();

    List<SubDocumentType> getOSVDocumentList();

    List<SubDocumentType> getNegativeCheckDocumentList();
}
