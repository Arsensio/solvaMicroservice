package com.example.solva.store;

import com.example.solva.models.LimitEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LimitRepository extends JpaRepository<LimitEntity,Long> {

    LimitEntity findByUserAccountAndLimitCategory(Long account,String category);

    LimitEntity findFirstByUserAccountAndLimitCategoryOrderByLimitSettingDateDesc(String account,String category);
}
