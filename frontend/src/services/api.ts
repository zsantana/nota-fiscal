import axios from 'axios';
import { FiscalReceiptDTO, ReceiptFilterDTO, ReceiptSummaryDTO } from '../types/api';

const api = axios.create({
  baseURL: 'http://localhost:8080/api'
});

export const receiptsApi = {
  getAllReceipts: () => 
    api.get<ReceiptSummaryDTO[]>('/receipts').then(res => res.data),

  getReceiptByNfceNumber: (nfceNumber: string) =>
    api.get<FiscalReceiptDTO>(`/receipts/${nfceNumber}`).then(res => res.data),

  getReceiptByAccessKey: (accessKey: string) =>
    api.get<FiscalReceiptDTO>(`/receipts/access-key/${accessKey}`).then(res => res.data),

  registerReceipt: (receipt: FiscalReceiptDTO) =>
    api.post<FiscalReceiptDTO>('/receipts', receipt).then(res => res.data),

  searchReceipts: (filter: ReceiptFilterDTO) =>
    api.post<ReceiptSummaryDTO[]>('/receipts/search', filter).then(res => res.data),

  updateReceiptStatus: (nfceNumber: string, status: string) =>
    api.patch<FiscalReceiptDTO>(`/receipts/${nfceNumber}/status?status=${status}`).then(res => res.data),

  deleteReceipt: (nfceNumber: string) =>
    api.delete(`/receipts/${nfceNumber}`).then(res => res.data)
};