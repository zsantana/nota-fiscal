package com.example.demo.dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class ReceiptItemDTO {
    private String code;
    private String description;
    private Integer quantity;
    private String unit;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
}
