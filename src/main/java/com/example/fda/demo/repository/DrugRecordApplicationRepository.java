package com.example.fda.demo.repository;

import com.example.fda.demo.model.entity.DrugRecordApplication;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DrugRecordApplicationRepository
    extends JpaRepository<DrugRecordApplication, Long>, JpaSpecificationExecutor {

  Optional<DrugRecordApplication> findByApplicationNumberAndDeletedIsFalse(
      String applicationNumber);

  List<DrugRecordApplication> findAllByDeletedIsFalse();

  List<DrugRecordApplication> findAllByDeletedIsTrue();
}
