package com.example.jdbcselflearn.db;

import com.example.jdbcselflearn.db.entity.BankUserInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface BankUserInfoRepository extends JpaRepository<BankUserInfoEntity, String> {

  @Transactional
  void removeById(String id);

  List<BankUserInfoEntity> findTop50ByIdGreaterThanEqualOrderById(String id);
}
