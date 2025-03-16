package com.example.demo.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.FiscalReceiptDTO;
import com.example.demo.dto.ReceiptFilterDTO;
import com.example.demo.dto.ReceiptSummaryDTO;
import com.example.demo.entity.FiscalReceipt;
import com.example.demo.entity.ReceiptItem;
import com.example.demo.entity.TaxInformation;
import com.example.demo.expection.ResourceNotFoundException;
import com.example.demo.repository.FiscalReceiptRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FiscalReceiptService {
    
    private final FiscalReceiptRepository receiptRepository;
    private final ModelMapper modelMapper;
    
    public FiscalReceiptService(FiscalReceiptRepository receiptRepository, ModelMapper modelMapper) {
        this.receiptRepository = receiptRepository;
        this.modelMapper = modelMapper;
    }
    
    @Transactional
    public FiscalReceiptDTO registerReceipt(FiscalReceiptDTO receiptDTO) {
        // Check if the receipt already exists
        if (receiptRepository.findByNfceNumber(receiptDTO.getNfceNumber()).isPresent()) {
            throw new IllegalArgumentException("Receipt with NFCe number " + receiptDTO.getNfceNumber() + " already exists");
        }
        
        FiscalReceipt receipt = convertToEntity(receiptDTO);
        FiscalReceipt savedReceipt = receiptRepository.save(receipt);
        return convertToDTO(savedReceipt);
    }
    
    @Transactional(readOnly = true)
    public FiscalReceiptDTO getReceiptByNfceNumber(String nfceNumber) {
        FiscalReceipt receipt = receiptRepository.findByNfceNumber(nfceNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Receipt not found with NFCe number: " + nfceNumber));
        return convertToDTO(receipt);
    }
    
    @Transactional(readOnly = true)
    public FiscalReceiptDTO getReceiptByAccessKey(String accessKey) {
        FiscalReceipt receipt = receiptRepository.findByAccessKey(accessKey)
                .orElseThrow(() -> new ResourceNotFoundException("Receipt not found with access key: " + accessKey));
        return convertToDTO(receipt);
    }
    
    @Transactional(readOnly = true)
    public List<ReceiptSummaryDTO> getAllReceipts() {
        return receiptRepository.findAll().stream()
                .map(this::convertToSummaryDTO)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public List<ReceiptSummaryDTO> searchReceipts(ReceiptFilterDTO filterDTO) {
        List<FiscalReceipt> receipts;
        
        if (filterDTO.getStartDate() != null && filterDTO.getEndDate() != null) {
            receipts = receiptRepository.findByIssueDateTimeBetween(filterDTO.getStartDate(), filterDTO.getEndDate());
        } else if (filterDTO.getCnpj() != null && !filterDTO.getCnpj().isEmpty()) {
            receipts = receiptRepository.findByCnpj(filterDTO.getCnpj());
        } else if (filterDTO.getStatus() != null && !filterDTO.getStatus().isEmpty()) {
            receipts = receiptRepository.findByStatus(filterDTO.getStatus());
        } else if (filterDTO.getConsumerIdentification() != null && !filterDTO.getConsumerIdentification().isEmpty()) {
            receipts = receiptRepository.findByConsumerIdentification(filterDTO.getConsumerIdentification());
        } else {
            receipts = receiptRepository.findAll();
        }
        
        return receipts.stream()
                .map(this::convertToSummaryDTO)
                .collect(Collectors.toList());
    }
    
    @Transactional
    public FiscalReceiptDTO updateReceiptStatus(String nfceNumber, String status) {
        FiscalReceipt receipt = receiptRepository.findByNfceNumber(nfceNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Receipt not found with NFCe number: " + nfceNumber));
        
        receipt.setStatus(status);
        FiscalReceipt updatedReceipt = receiptRepository.save(receipt);
        return convertToDTO(updatedReceipt);
    }
    
    @Transactional
    public void deleteReceipt(String nfceNumber) {
        FiscalReceipt receipt = receiptRepository.findByNfceNumber(nfceNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Receipt not found with NFCe number: " + nfceNumber));
        
        receiptRepository.delete(receipt);
    }
    
    private FiscalReceipt convertToEntity(FiscalReceiptDTO dto) {
        FiscalReceipt receipt = modelMapper.map(dto, FiscalReceipt.class);
        
        if (dto.getItems() != null) {
            receipt.setItems(dto.getItems().stream()
                    .map(itemDTO -> modelMapper.map(itemDTO, ReceiptItem.class))
                    .collect(Collectors.toList()));
        }
        
        if (dto.getTaxes() != null) {
            receipt.setTaxes(dto.getTaxes().stream()
                    .map(taxDTO -> modelMapper.map(taxDTO, TaxInformation.class))
                    .collect(Collectors.toList()));
        }
        
        return receipt;
    }
    
    private FiscalReceiptDTO convertToDTO(FiscalReceipt entity) {
        return modelMapper.map(entity, FiscalReceiptDTO.class);
    }
    
    private ReceiptSummaryDTO convertToSummaryDTO(FiscalReceipt entity) {
        return modelMapper.map(entity, ReceiptSummaryDTO.class);
    }
}