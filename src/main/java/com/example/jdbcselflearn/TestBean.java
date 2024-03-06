package com.example.jdbcselflearn;

import com.example.jdbcselflearn.db.BankUserInfoRepository;
import com.example.jdbcselflearn.db.entity.BankUserInfoEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TestBean {

  private final BankUserInfoRepository bankUserInfoRepository;


  @Bean
  public void test(){
    try {
//      List<BankUserInfoEntity> bankUserInfoEntityList = bankUserInfoRepository.findTop50ByIdGreaterThanEqualOrderById("");
      List<BankUserInfoEntity> bankUserInfoEntityList = new ArrayList<>();
      for(int i =40000;i <40005;i++){
        bankUserInfoEntityList.add(new BankUserInfoEntity(Integer.toString(i)));
      }
      long start1 = System.nanoTime();
      bankUserInfoRepository.saveAll(bankUserInfoEntityList);
      long end1 = System.nanoTime();
      System.out.println("Elapsed Time in seconds: "+ (end1-start1)/10e9);
    } catch (Exception ex) {
      System.out.println("fail to save bankUserInfoEntity: {} with error" + ex);
    }
  }
}
