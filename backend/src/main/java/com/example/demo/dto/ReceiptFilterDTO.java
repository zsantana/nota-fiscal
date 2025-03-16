package com.example.demo.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ReceiptFilterDTO {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String cnpj;
    private String status;
    private String consumerIdentification;
}