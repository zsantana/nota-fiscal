package com.example.demo.controller;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.FiscalReceiptDTO;
import com.example.demo.dto.ReceiptFilterDTO;
import com.example.demo.dto.ReceiptSummaryDTO;
import com.example.demo.service.FiscalReceiptService;

import java.util.List;

@RestController
@RequestMapping("/api/receipts")
public class FiscalReceiptController {
    
    private final FiscalReceiptService receiptService;
    
    public FiscalReceiptController(FiscalReceiptService receiptService) {
        this.receiptService = receiptService;
    }
    
    @PostMapping
    public ResponseEntity<FiscalReceiptDTO> registerReceipt(@Valid @RequestBody FiscalReceiptDTO receiptDTO) {
        FiscalReceiptDTO savedReceipt = receiptService.registerReceipt(receiptDTO);
        return new ResponseEntity<>(savedReceipt, HttpStatus.CREATED);
    }
    
    @GetMapping("/{nfceNumber}")
    public ResponseEntity<FiscalReceiptDTO> getReceiptByNfceNumber(@PathVariable String nfceNumber) {
        FiscalReceiptDTO receiptDTO = receiptService.getReceiptByNfceNumber(nfceNumber);
        return ResponseEntity.ok(receiptDTO);
    }
    
    @GetMapping("/access-key/{accessKey}")
    public ResponseEntity<FiscalReceiptDTO> getReceiptByAccessKey(@PathVariable String accessKey) {
        FiscalReceiptDTO receiptDTO = receiptService.getReceiptByAccessKey(accessKey);
        return ResponseEntity.ok(receiptDTO);
    }
    
    @GetMapping
    public ResponseEntity<List<ReceiptSummaryDTO>> getAllReceipts() {
        List<ReceiptSummaryDTO> receipts = receiptService.getAllReceipts();
        return ResponseEntity.ok(receipts);
    }
    
    @PostMapping("/search")
    public ResponseEntity<List<ReceiptSummaryDTO>> searchReceipts(@RequestBody ReceiptFilterDTO filterDTO) {
        List<ReceiptSummaryDTO> receipts = receiptService.searchReceipts(filterDTO);
        return ResponseEntity.ok(receipts);
    }
    
    @PatchMapping("/{nfceNumber}/status")
    public ResponseEntity<FiscalReceiptDTO> updateReceiptStatus(
            @PathVariable String nfceNumber,
            @RequestParam String status) {
        FiscalReceiptDTO updatedReceipt = receiptService.updateReceiptStatus(nfceNumber, status);
        return ResponseEntity.ok(updatedReceipt);
    }
    
    @DeleteMapping("/{nfceNumber}")
    public ResponseEntity<Void> deleteReceipt(@PathVariable String nfceNumber) {
        receiptService.deleteReceipt(nfceNumber);
        return ResponseEntity.noContent().build();
    }
}