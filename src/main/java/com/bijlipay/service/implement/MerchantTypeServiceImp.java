package com.bijlipay.service.implement;

import com.bijlipay.model.MerchantType;
import com.bijlipay.repository.MerchantTypeRepository;
import com.bijlipay.service.MerchantTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MerchantTypeServiceImp implements MerchantTypeService {

    @Autowired
    private MerchantTypeRepository merchantTypeRepo;

    @Override
    public MerchantType saveMerchantType(MerchantType merchantType) {
        merchantType.setActive(true);
        return merchantTypeRepo.save(merchantType);
    }

    @Override
    public List<MerchantType> getAll() {
        return merchantTypeRepo.getAll();
    }

    @Override
    public MerchantType getMerchantByid(int merchanttypeid) {
        Optional<MerchantType> merchanttype = merchantTypeRepo.findById(merchanttypeid);
        if (merchanttype.isPresent()) {
            return merchanttype.get();
        }
        return null;
    }

    @Override
    public MerchantType updateMerchantType(int merchantTypeId, MerchantType updatemerchanttype) {
        MerchantType merchantTypeDetails = getMerchantByid(merchantTypeId);
        merchantTypeDetails.setMerchantTypeName(updatemerchanttype.getMerchantTypeName());
        return merchantTypeRepo.save(merchantTypeDetails);
    }

    @Override
    public MerchantType deactivate(MerchantType merchantType) {
          merchantType.setActive(false);
        return merchantTypeRepo.save(merchantType);
    }


}
