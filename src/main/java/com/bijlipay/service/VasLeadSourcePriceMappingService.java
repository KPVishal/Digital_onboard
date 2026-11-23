package com.bijlipay.service;

import com.bijlipay.model.VasLeadSourcePriceMapping;

import java.util.List;

public interface VasLeadSourcePriceMappingService {
//       VasLeadSourcePriceMapping create(VasLeadSourcePriceMappingDto vasLeadSourcePriceMappingDto);

       VasLeadSourcePriceMapping createVasPrice(VasLeadSourcePriceMapping vasLeadSourcePriceMapping);

       List<VasLeadSourcePriceMapping> getAllVasLeadSourcePrice();

      // VasLeadSourcePriceMapping deactivate(int id);
}
