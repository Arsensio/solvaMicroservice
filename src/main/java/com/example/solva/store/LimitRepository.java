package com.example.solva.store;

import com.example.solva.models.LimitEntity;
import com.example.solva.models.LimitId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LimitRepository extends JpaRepository<LimitEntity,Long> {

    LimitEntity getById(LimitId id);
}
