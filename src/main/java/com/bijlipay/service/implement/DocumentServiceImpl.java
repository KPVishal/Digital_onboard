package com.bijlipay.service.implement;

import com.bijlipay.config.Constants;
import com.bijlipay.model.DocumentCategory;
//import com.bijlipay.model.DocumentCategoryMapping;
import com.bijlipay.model.DocumentType;
import com.bijlipay.model.MccReason;
import com.bijlipay.model.SubDocumentType;
//import com.bijlipay.repository.DocumentCategoryMappingRepository;
import com.bijlipay.repository.DocumentCategoryRepository;
import com.bijlipay.repository.DocumentTypeRepository;
import com.bijlipay.repository.MccReasonRepository;
import com.bijlipay.repository.SubDocumentTypeRepository;
import com.bijlipay.service.DocumentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DocumentServiceImpl implements DocumentService {

    private static final Logger logger = LoggerFactory.getLogger(DocumentServiceImpl.class);

    @Autowired
    private DocumentCategoryRepository documentCategoryRepository;
    @Autowired
    private DocumentTypeRepository documentTypeRepository;
//    @Autowired
//    private DocumentCategoryMappingRepository documentCategoryMappingRepository;
    @Autowired
    private SubDocumentTypeRepository subDocumentTypeRepository;

    @Autowired
    private MccReasonRepository mccReasonRepository;

    @Override
    public DocumentCategory isCategoryExists(DocumentCategory documentCategory) {
        return documentCategoryRepository.fetchByCategoryName(documentCategory.getName());
    }

    @Override
    public DocumentCategory createCategory(DocumentCategory documentCategory) {
        documentCategory.setActive(true);
        return documentCategoryRepository.save(documentCategory);
    }

    @Override
    public List<DocumentCategory> listOfCategory() {
        return documentCategoryRepository.findAll();
    }

    @Override
    public DocumentCategory updateCategory(long id, DocumentCategory documentCategory) {
        DocumentCategory category = documentCategoryRepository.checkCategoryById(id);
        if (category != null) {
            category.setName(documentCategory.getName());
            category.setCategoryDescription(documentCategory.getCategoryDescription());
            category.setUploadAnyOne(documentCategory.isUploadAnyOne());
            category.setCategoryIdentify(documentCategory.getCategoryIdentify());
            category.setActive(true);
            return documentCategoryRepository.save(category);
        }
        return null;
    }

    @Override
    public DocumentCategory getCategory(long id) {
        return documentCategoryRepository.checkCategoryById(id);
    }

    @Override
    public void deActiveCategory(long id) {
        DocumentCategory documentCategory = documentCategoryRepository.checkCategoryById(id);
        if (documentCategory != null) {
            documentCategory.setActive(false);
            documentCategoryRepository.save(documentCategory);
        }
    }

    @Override
    public List<DocumentCategory> getActiveCategory(Boolean aBoolean) {
        return documentCategoryRepository.fetchActivePlans(aBoolean);
    }

    @Override
    public DocumentType isTypeExists(DocumentType documentType) {
        return documentTypeRepository.fetchByTypeName(documentType.getName());
    }

    @Override
    public DocumentType CreateType(DocumentType documentType) {
        documentType.setActive(true);
        return documentTypeRepository.save(documentType);
    }

    @Override
    public List<DocumentType> getAllTypes() {
        return documentTypeRepository.findAll();
    }

    @Override
    public List<DocumentType> getActiveTypes(Boolean aBoolean) {
        return documentTypeRepository.fetchActiveTypes(aBoolean);
    }

    @Override
    public DocumentType checkDocumentType(long id) {
        return documentTypeRepository.checkDocumentById(id);
    }

    @Override
    public DocumentType updateType(long id, DocumentType documentType) {
        DocumentType type = documentTypeRepository.checkDocumentById(id);
        if (type != null) {
            type.setName(documentType.getName());
            type.setActive(true);
            return documentTypeRepository.save(type);
        }
        return null;
    }

    @Override
    public void deleteType(long id) {
        DocumentType type = documentTypeRepository.checkDocumentById(id);
        if (type != null) {
            type.setActive(false);
            documentTypeRepository.save(type);
        }
    }

//    @Override
//    public DocumentCategoryMapping isMappingExists(DocumentCategoryMapping documentCategoryMapping) {
//        DocumentCategoryMapping categoryMapping = null;
//        if (documentCategoryMapping.getMerchantCategoryCode() == null) {
//            categoryMapping = documentCategoryMappingRepository.fetchCategoryMappingWithOutMcc(documentCategoryMapping.getDocumentCategory(), documentCategoryMapping.getSubDocumentType());
//        } else {
//            categoryMapping = documentCategoryMappingRepository.fetchCategoryMappingWithMcc(documentCategoryMapping.getDocumentCategory(), documentCategoryMapping.getSubDocumentType(), documentCategoryMapping.getMerchantCategoryCode());
//        }
//        return categoryMapping;
//    }
//
//    @Override
//    public DocumentCategoryMapping createDocumentCategory(DocumentCategoryMapping documentCategoryMapping) {
//        return documentCategoryMappingRepository.save(documentCategoryMapping);
//    }
//
//    @Override
//    public List<DocumentCategoryMapping> getSingleMapping(Optional<Long> id) {
//        return documentCategoryMappingRepository.fetchSingleDocumentMapping(id);
//    }
//
//    @Override
//    public List<DocumentCategoryMapping> getAllMapping() {
//        return documentCategoryMappingRepository.findAll();
//    }
//
//    @Override
//    public DocumentCategoryMapping getCategoryMap(long id) {
//        return documentCategoryMappingRepository.fetchSingleDocumentMappings(id);
//    }
//
//    @Override
//    public DocumentCategoryMapping updateCategoryMapping(long id, DocumentCategoryMapping documentCategoryMapping) {
//        DocumentCategoryMapping categoryMapping = documentCategoryMappingRepository.fetchSingleDocumentMappings(id);
//        if (categoryMapping != null) {
//            documentCategoryMappingRepository.delete(categoryMapping);// deleting old entry
//            documentCategoryMappingRepository.save(documentCategoryMapping);// creating a new entry
//        }
//        return null;
//    }
//
//    @Override
//    public void deleteCategoryMapping(DocumentCategoryMapping categoryMapping) {
//        documentCategoryMappingRepository.delete(categoryMapping);
//    }

    @Override
    public SubDocumentType isMappingExists(SubDocumentType subDocumentType) {
        SubDocumentType categoryMapping = null;
        if (subDocumentType.getMerchantCategoryCode() == null) {
            categoryMapping = subDocumentTypeRepository.fetchCategoryMappingWithOutMcc(subDocumentType.getDocumentCategory(), subDocumentType.getName());
        } else {
            categoryMapping = subDocumentTypeRepository.fetchCategoryMappingWithMcc(subDocumentType.getDocumentCategory(), subDocumentType.getName(), subDocumentType.getMerchantCategoryCode());
        }
        return categoryMapping;
    }

//        @Override
//    public List<DocumentCategoryMapping> getBizDocuments(String mcc) {
//        List<DocumentCategoryMapping> mappingList = new ArrayList<>();
//        DocumentCategoryMapping documents = documentCategoryMappingRepository.fetchBizCategoryDocuments(mcc);
//        if (documents != null) {
//            mappingList.add(documents);
//        } else {
//            int value = 2; // with out mcc documents based on mccCategoryIndicator
//            List<DocumentCategoryMapping> documents2 = documentCategoryMappingRepository.findDocumentsByIndicator(value);
//            mappingList.addAll(documents2);
//        }
//        return mappingList;
//    }

    @Override
    public List<SubDocumentType> getBizDocuments(String mcc) {
        List<SubDocumentType> mappingList = new ArrayList<>();
        SubDocumentType documents = subDocumentTypeRepository.fetchBizCategoryDocuments(mcc);
        if (documents != null) {
            mappingList.add(documents);
        } else {
            int value = 2; // with out mcc documents based on mccCategoryIndicator
            List<SubDocumentType> documents2 = subDocumentTypeRepository.findDocumentsByIndicator(value);
            mappingList.addAll(documents2);
        }
        return mappingList;
    }

    //    @Override
//    public List<DocumentCategoryMapping> getBasicDocuments(int value) {
//        return documentCategoryMappingRepository.findDocumentsByIndicator(value);
//    }

    @Override
    public List<SubDocumentType> getBasicDocuments(int value) {
        return subDocumentTypeRepository.findDocumentsByIndicator(value);
    }

    @Override
    public SubDocumentType isSubDocumentExists(SubDocumentType subDocumentType) {
        return subDocumentTypeRepository.fetchSubDocumentByName(subDocumentType.getName());
    }

    @Override
    public SubDocumentType createSubDocument(SubDocumentType subDocumentType) {
        subDocumentType.setActive(true);
        return subDocumentTypeRepository.save(subDocumentType);
    }

    @Override
    public List<SubDocumentType> getDocumentActiveTypes(Boolean aBoolean) {
        return subDocumentTypeRepository.findByActive(aBoolean);
    }

    @Override
    public List<SubDocumentType> getDocumentAllTypes() {
        return subDocumentTypeRepository.findAll();
    }

    @Override
    public SubDocumentType checkSubDocumentType(long id) {
        return subDocumentTypeRepository.findBySubDocumentId(id);
    }

    @Override
    public SubDocumentType updateSubDocumentType(long id, SubDocumentType documentType) {
        SubDocumentType subDocumentType = subDocumentTypeRepository.findBySubDocumentId(id);
        if (subDocumentType != null) {
            subDocumentType.setName(documentType.getName());
            subDocumentType.setSignzyVerificationRequired(documentType.isSignzyVerificationRequired());
            subDocumentType.setDocumentType(documentType.getDocumentType());
            subDocumentType.setDocumentCategory(documentType.getDocumentCategory());
            subDocumentType.setMccCategoryIndicator(documentType.getMccCategoryIndicator());
            subDocumentType.setMerchantCategoryCode(documentType.getMerchantCategoryCode());
            subDocumentType.setMandatory(documentType.isMandatory());
            subDocumentType.setActive(true);
            return subDocumentTypeRepository.save(subDocumentType);
        }
        return null;
    }

    @Override
    public void deleteSubDocumentType(long id) {
        SubDocumentType subDocumentType = subDocumentTypeRepository.findBySubDocumentId(id);
        if (subDocumentType != null) {
            subDocumentType.setActive(false);
            subDocumentTypeRepository.save(subDocumentType);
        }
    }

    @Override
    public List<SubDocumentType> collectImplementationDocuments() {
        return subDocumentTypeRepository.fetchDocumentTypeByMccIndicator(Constants.MCC_CATEGORY_INDICATOR_IMPLEMENT);
    }

    @Override
    public MccReason createMccReason(MccReason mccReason) {
        MccReason reason = mccReasonRepository.findByReason(mccReason.getReason());
        if(reason !=null) {
            mccReason.setActive(true);
            return mccReasonRepository.save(mccReason);
        }
        return reason;
    }

    @Override
    public List<MccReason> getMccReasonList() {
        return mccReasonRepository.getAllMccReasonList();
    }

    @Override
    public MccReason updateMccReasonList(Long id,MccReason mccReason) {
        MccReason reason = mccReasonRepository.findByMccId(id);
        if(reason !=null){
            reason.setReason(mccReason.getReason());
            reason.setActive(mccReason.isActive());
        }
        return mccReasonRepository.save(reason);
    }

    @Override
    public List<SubDocumentType> getBankAccountDocuments() {
        List<SubDocumentType> mappingList = new ArrayList<>();
        int value =5; // bank account document
        List<SubDocumentType> documents = subDocumentTypeRepository.findDocumentsByIndicator(value);
        mappingList.addAll(documents);
        return mappingList;
    }

    @Override
    public List<SubDocumentType> getOSVDocumentList() {
        List<SubDocumentType> mappingList = new ArrayList<>();
        int value =6; // osv document list
        List<SubDocumentType> documents = subDocumentTypeRepository.findDocumentsByIndicator(value);
        mappingList.addAll(documents);
        return mappingList;
    }
    @Override
    public List<SubDocumentType> getNegativeCheckDocumentList() {
        List<SubDocumentType> mappingList = new ArrayList<>();
        int value =7; // Negative check document list
        List<SubDocumentType> documents = subDocumentTypeRepository.findDocumentsByIndicator(value);
        mappingList.addAll(documents);
        return mappingList;
    }

}
