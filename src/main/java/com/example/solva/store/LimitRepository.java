package com.example.solva.store;

import com.example.solva.models.LimitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LimitRepository extends JpaRepository<LimitEntity,Long> {

//    LimitEntity findByUserAccountAndLimitCategory(Long account,String category);

    LimitEntity findFirstByUserAccountAndLimitCategoryOrderByLimitSettingDateDesc(String account,String category);

    @Query("FROM LimitEntity l where l.limitSettingDate = (SELECT MAX(l2.limitSettingDate) FROM LimitEntity l2 where l.limitCategory = l2.limitCategory) AND l.userAccount = ?1")
    List<LimitEntity> findAllCategoryLimitsByUserAccount(String account);


}
