package com.example.demo.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ReceiptSummaryDTO {
    private String nfceNumber;
    private LocalDateTime issueDateTime;
    private String companyName;
    private BigDecimal totalValue;
    private String status;
}