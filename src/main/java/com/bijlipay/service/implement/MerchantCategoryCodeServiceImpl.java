package com.bijlipay.service.implement;

import com.bijlipay.model.MerchantCategoryCode;
import com.bijlipay.repository.MerchantCategoryCodeRepository;
import com.bijlipay.service.MerchantCategoryCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MerchantCategoryCodeServiceImpl implements MerchantCategoryCodeService {

    @Autowired
    private MerchantCategoryCodeRepository merchantCategoryCodeRepository;

    @Override
    public MerchantCategoryCode createMerchantCategoryCode(MerchantCategoryCode merchantCategoryCode) {

        MerchantCategoryCode categoryCode = new MerchantCategoryCode();
        categoryCode.setCategoryName(merchantCategoryCode.getCategoryName());
        categoryCode.setIndustryName(merchantCategoryCode.getIndustryName());
        categoryCode.setMccCode(merchantCategoryCode.getMccCode());
        categoryCode.setRestricted(merchantCategoryCode.isRestricted());
        categoryCode.setActive(true);
        return merchantCategoryCodeRepository.save(categoryCode);
    }

    @Override
    public List<MerchantCategoryCode> getAllMccList() {
        return merchantCategoryCodeRepository.findAllMccList();
    }
    @Override
    public List<MerchantCategoryCode> getRestrictedMccList(int action,  Optional<String> search) {
        //1 -->SMALL  2---> OTHERS
        System.out.println(action+"-------------------mcc");
        if(action==1){
            if(search.isPresent()){
                return merchantCategoryCodeRepository.findRestrictedSmallMccListSearch(search.get());
            }
            return merchantCategoryCodeRepository.findRestrictedSmallMccList();

        }
        else {
            if(!search.isEmpty()){
                return merchantCategoryCodeRepository.findRestrictedOtherMccListSearch(search.get());
            }
            System.out.println(action+"-------------------mcc");
            return merchantCategoryCodeRepository.findRestrictedOtherMccList();

        }
    }

    @Override
    public MerchantCategoryCode findMccById(Long id) {
        return merchantCategoryCodeRepository.findMccCodeId(id);
    }

    @Override
    public void deleteMccById(Long id) {
        MerchantCategoryCode categoryCode = merchantCategoryCodeRepository.getReferenceById(id);
        if (categoryCode != null) {
            categoryCode.setActive(false);
            merchantCategoryCodeRepository.save(categoryCode);
        }
    }

    @Override
    public boolean existsMcc(String mccCode) {
        boolean mccExists = merchantCategoryCodeRepository.existsByMcc(mccCode) > 0;
        return mccExists;
    }

    @Override
    public MerchantCategoryCode updateMerchantCategoryCode(MerchantCategoryCode merchantCategoryCode) {
        MerchantCategoryCode merchantCategoryCode1 = new MerchantCategoryCode();
        MerchantCategoryCode categoryCode = merchantCategoryCodeRepository.findMccCode(merchantCategoryCode.getMccCode());
        if (categoryCode != null) {
            categoryCode.setActive(true);
            categoryCode.setRestricted(merchantCategoryCode.isRestricted());
            merchantCategoryCode1 = merchantCategoryCodeRepository.save(categoryCode);
        }
        return merchantCategoryCode1;
    }

    @Override
    public List<MerchantCategoryCode> getAllMccListSearch(String search) {
        List<MerchantCategoryCode> merchantCategoryCode=merchantCategoryCodeRepository.findByString(search);
        return merchantCategoryCode;
    }


}
