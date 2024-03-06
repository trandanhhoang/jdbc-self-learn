package com.example.jdbcselflearn.db.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "bank_user_info")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BankUserInfoEntity implements Serializable, Persistable<String> {
  @Id
  @Column(name = "id")
  private String id;

  @Override
  public boolean isNew() {
    return true;
  }

  @Override
  public String getId() {
    return id;
  }

  @Column(name = "bank_register", nullable = false)
  private String bankRegister;

  @Column(name = "issuing_bank", nullable = false)
  private String issuingBank;

  @Column(name = "user_id", nullable = false)
  private String userId;

  @Column(name = "m_pin")
  private String mPin;

  @Column(name = "bank_user_id")
  private String bankUserId;

  @Column(name = "bank_connector_code")
  private String bankConnectorCode;

  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "created_at")
  private Date createdAt;


  public BankUserInfoEntity(String id) {
    this.id = id;
    this.bankRegister = "";
    this.issuingBank = "VCB";
    this.userId="1327";
    this.mPin="123";
  }
}