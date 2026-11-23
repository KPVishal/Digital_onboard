package com.bijlipay.repository;

import com.bijlipay.model.MerchantCategoryCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MerchantCategoryCodeRepository extends JpaRepository<MerchantCategoryCode, Long> {

    @Query("SELECT m FROM MerchantCategoryCode m where m.mccId = ?1 AND m.isActive = 1")
    MerchantCategoryCode findMccCodeId(Long id);

    @Query("SELECT m FROM MerchantCategoryCode m where m.isActive =1 ")
    List<MerchantCategoryCode> findAllMccList();

    @Query("SELECT m FROM MerchantCategoryCode m where m.isActive =1 and m.isSmallRestricted =0 and m.isOtherRestricted =0")
    List<MerchantCategoryCode> findRestrictedSmallMccList();

    @Query("SELECT m FROM MerchantCategoryCode m where m.isActive =1 and m.isOtherRestricted =0 ")
    List<MerchantCategoryCode> findRestrictedOtherMccList();

    @Query("SELECT COUNT(m) FROM MerchantCategoryCode m where m.mccCode=?1")
    int existsByMcc(String mccCode);

    @Query("SELECT m FROM MerchantCategoryCode m WHERE m.mccCode=?1 AND m.isActive=0")
    MerchantCategoryCode findMccCode(String mccCode);

    @Query("SELECT m FROM MerchantCategoryCode m " +
            "WHERE m.isActive = 1 " +
            "AND (m.mccCode LIKE %?1% " +
            "OR m.industryName LIKE %?1%)")
    List<MerchantCategoryCode> findByString(String search);

    @Query("SELECT m FROM MerchantCategoryCode m Where m.isActive =1 And m.isSmallRestricted =0 And m.isOtherRestricted =0"+ "And (m.mccCode LIKE %?1% " +" OR m.industryName LIKE %?1%)")
    List<MerchantCategoryCode> findRestrictedSmallMccListSearch(String search);

    @Query("SELECT m FROM MerchantCategoryCode m"+" where m.isActive =1 "+"and m.isOtherRestricted =0"+" And (m.mccCode LIKE %?1% " +" OR m.industryName LIKE %?1%)")
    List<MerchantCategoryCode> findRestrictedOtherMccListSearch(String search);
    @Query("SELECT m FROM MerchantCategoryCode m WHERE m.mccCode=?1")
    MerchantCategoryCode findMccCode1(String mccCode);

}
