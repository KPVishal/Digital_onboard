package com.bijlipay.service.implement;

import com.bijlipay.model.MerchantCategory;
import com.bijlipay.model.MerchantCategoryAndCodeMapping;
import com.bijlipay.model.MerchantCategoryCode;
import com.bijlipay.model.dto.MerchantCategoryAndCodeDto;
import com.bijlipay.repository.MerchantCategoryAndCodeMappingRepository;
import com.bijlipay.repository.MerchantCategoryRepository;
import com.bijlipay.service.MerchantCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static com.bijlipay.config.Constants.IS_ACTIVE_TRUE;

@Service
public class MerchantCategoryServiceImpl implements MerchantCategoryService {
    @Autowired
    private MerchantCategoryRepository merchantCategoryRepository;
    @Autowired
    private MerchantCategoryAndCodeMappingRepository merchantCategoryAndCodeMappingRepository;
    @Override
    public boolean existsMerchantCategory(MerchantCategoryAndCodeDto merchantCategoryAndCodeDto) {
        boolean exists =merchantCategoryRepository.existsMerchantCategory(merchantCategoryAndCodeDto.getMerchantCategory().getMerchantCategoryName())>0;
        return exists;
    }

    @Override
    public MerchantCategory createMerchantCategory(MerchantCategoryAndCodeDto merchantCategory) {
        MerchantCategory category=new MerchantCategory();
        if(merchantCategory!=null){
            category.setMerchantCategoryName(merchantCategory.getMerchantCategory().getMerchantCategoryName());
            category.setActive(true);
            MerchantCategory category1 = merchantCategoryRepository.save(category);
            //Mapping Mcc to merchant category
            List<MerchantCategoryAndCodeMapping> mappingList = this.CreateMerchantCategoryMapToMcc(category1, merchantCategory.getMerchantCategoryCodes());
            if(!mappingList.isEmpty()) {
                merchantCategoryAndCodeMappingRepository.saveAll(mappingList);
            }
            return category1;
        }
        return null;
    }

    @Override
    public List<MerchantCategory> getAllMerchantCategory(Boolean isActive) {
        return merchantCategoryRepository.getMerchantCategory(isActive);
    }

    @Override
    public Pair<Boolean, MerchantCategory> update(MerchantCategoryAndCodeDto merchantCategoryAndCodeDto, Long id, Boolean isActive) {
        MerchantCategory category=new MerchantCategory();
        MerchantCategory fromDB=merchantCategoryRepository.getReferenceById(id);
        if(fromDB!=null){
            fromDB.updateValues(merchantCategoryAndCodeDto.getMerchantCategory());
            if(isActive!=null && isActive==IS_ACTIVE_TRUE)
            {
                fromDB.setActive(IS_ACTIVE_TRUE);
            }
            category= merchantCategoryRepository.save(fromDB);
            //delete old mapping of mcc with merchant category
            merchantCategoryAndCodeMappingRepository.deleteMappingByCategory(category);
            //Map new mcc list with merchant category
            List<MerchantCategoryAndCodeMapping> mappingList = this.CreateMerchantCategoryMapToMcc(category, merchantCategoryAndCodeDto.getMerchantCategoryCodes());
            if(!mappingList.isEmpty()){
                merchantCategoryAndCodeMappingRepository.saveAll(mappingList);
            }
            return Pair.of(true,category);
        }
        return Pair.of(false,null);
    }

    @Override
    public String  deActivate(Long id) {
        Function<MerchantCategory,Boolean> myFunc=merchantCategory -> {
            merchantCategory.setActive(false);
            merchantCategoryRepository.save(merchantCategory);
            return true;
        };
        if(validateIdExists(id,myFunc)){
            return "success";
        }else{
            return "Not a valid Id";
        }
    }

     Boolean validateIdExists(Long id, Function<MerchantCategory, Boolean> myFunc) {
        MerchantCategory fromDB=merchantCategoryRepository.getReferenceById(id);
        if(fromDB!=null){
            return myFunc.apply(fromDB);
        }else {
            return false;
        }
    }

    private  List<MerchantCategoryAndCodeMapping> CreateMerchantCategoryMapToMcc(MerchantCategory category1, List<MerchantCategoryCode> merchantCategoryCodes) {
        List<MerchantCategoryAndCodeMapping> codeMappings = new ArrayList<>();
        merchantCategoryCodes.forEach(code ->{
            MerchantCategoryAndCodeMapping categoryAndCodeMapping = new MerchantCategoryAndCodeMapping();
            categoryAndCodeMapping.setMerchantCategory(category1);
            categoryAndCodeMapping.setMerchantCategoryCode(code);
            codeMappings.add(categoryAndCodeMapping);
        });
        return codeMappings;
    }

    @Override
    public MerchantCategory fetchMerchantCategory(long merchantCategoryId) {
        return merchantCategoryRepository.fetchMerchantCategoryById(merchantCategoryId);
    }
}
