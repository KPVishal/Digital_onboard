package com.bijlipay.service;

import com.bijlipay.model.MerchantType;

import java.util.List;

public interface MerchantTypeService {

    MerchantType saveMerchantType(MerchantType merchantType);

    List<MerchantType> getAll();

    MerchantType getMerchantByid(int merchanttypeid);

    MerchantType updateMerchantType(int merchanttypeid,MerchantType updatemerchanttype);

     MerchantType deactivate(MerchantType merchantType);
}
