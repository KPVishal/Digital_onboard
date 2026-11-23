package com.bijlipay.model.dto;

import com.bijlipay.model.*;

public class DocumentCategoryMappingDto {

    private DocumentCategory documentCategory;
    private DocumentType documentType;
    private MerchantCategoryCode merchantCategoryCode;
    private int mccCategoryIndicator;

    public DocumentCategory getDocumentCategory() {
        return documentCategory;
    }

    public void setDocumentCategory(DocumentCategory documentCategory) {
        this.documentCategory = documentCategory;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    public MerchantCategoryCode getMerchantCategoryCode() {
        return merchantCategoryCode;
    }

    public void setMerchantCategoryCode(MerchantCategoryCode merchantCategoryCode) {
        this.merchantCategoryCode = merchantCategoryCode;
    }

    public int getMccCategoryIndicator() {
        return mccCategoryIndicator;
    }

    public void setMccCategoryIndicator(int mccCategoryIndicator) {
        this.mccCategoryIndicator = mccCategoryIndicator;
    }
}
