package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class FiscalReceipt {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nfceNumber;
    private String series;
    private LocalDateTime issueDateTime;
    private String cnpj;
    private String companyName;
    private String address;
    private String accessKey;
    private BigDecimal totalValue;
    private String paymentMethod;
    private String consumerIdentification;
    private String status; // AUTHORIZED, PENDING, etc.
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "fiscal_receipt_id")
    private List<ReceiptItem> items = new ArrayList<>();
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "fiscal_receipt_id")
    private List<TaxInformation> taxes = new ArrayList<>();
}