package com.example.demo.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class FiscalReceiptDTO {
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
    private String status;
    private List<ReceiptItemDTO> items;
    private List<TaxInformationDTO> taxes;
}