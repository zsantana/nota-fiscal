package com.example.demo.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TaxInformationDTO {

    private String taxType;
    private BigDecimal amount;
    
}
