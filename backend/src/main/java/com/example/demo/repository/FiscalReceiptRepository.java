package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.FiscalReceipt;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface FiscalReceiptRepository extends JpaRepository<FiscalReceipt, Long> {

    Optional<FiscalReceipt> findByNfceNumber(String nfceNumber);
    List<FiscalReceipt> findByCnpj(String cnpj);
    List<FiscalReceipt> findByIssueDateTimeBetween(LocalDateTime start, LocalDateTime end);
    
    @Query("SELECT fr FROM FiscalReceipt fr WHERE fr.accessKey = :accessKey")
    Optional<FiscalReceipt> findByAccessKey(@Param("accessKey") String accessKey);
    
    List<FiscalReceipt> findByConsumerIdentification(String consumerIdentification);
    List<FiscalReceipt> findByStatus(String status);
}