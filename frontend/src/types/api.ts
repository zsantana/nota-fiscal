export interface FiscalReceiptDTO {
  nfceNumber: string;
  series: string;
  issueDateTime: string;
  cnpj: string;
  companyName: string;
  address: string;
  accessKey: string;
  totalValue: number;
  paymentMethod: string;
  consumerIdentification: string;
  status: string;
  items: ReceiptItemDTO[];
  taxes: TaxInformationDTO[];
}

export interface ReceiptItemDTO {
  code: string;
  description: string;
  quantity: number;
  unit: string;
  unitPrice: number;
  totalPrice: number;
}

export interface TaxInformationDTO {
  taxType: string;
  amount: number;
}

export interface ReceiptFilterDTO {
  startDate?: string;
  endDate?: string;
  cnpj?: string;
  status?: string;
  consumerIdentification?: string;
}

export interface ReceiptSummaryDTO {
  nfceNumber: string;
  issueDateTime: string;
  companyName: string;
  totalValue: number;
  status: string;
}