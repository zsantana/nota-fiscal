import React from 'react';
import { format } from 'date-fns';
import { FileText, Trash2, RefreshCw } from 'lucide-react';
import { ReceiptSummaryDTO } from '../types/api';

interface ReceiptListProps {
  receipts: ReceiptSummaryDTO[];
  onViewDetails: (nfceNumber: string) => void;
  onDelete: (nfceNumber: string) => void;
  onUpdateStatus: (nfceNumber: string) => void;
}

export const ReceiptList: React.FC<ReceiptListProps> = ({
  receipts,
  onViewDetails,
  onDelete,
  onUpdateStatus
}) => {
  return (
    <div className="overflow-x-auto">
      <table className="min-w-full bg-white rounded-lg overflow-hidden">
        <thead className="bg-gray-100">
          <tr>
            <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">NFCe</th>
            <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Data</th>
            <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Empresa</th>
            <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Valor</th>
            <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Status</th>
            <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Ações</th>
          </tr>
        </thead>
        <tbody className="divide-y divide-gray-200">
          {receipts.map((receipt) => (
            <tr key={receipt.nfceNumber} className="hover:bg-gray-50">
              <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-900">{receipt.nfceNumber}</td>
              <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-900">
                {format(new Date(receipt.issueDateTime), 'dd/MM/yyyy HH:mm')}
              </td>
              <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-900">{receipt.companyName}</td>
              <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-900">
                {new Intl.NumberFormat('pt-BR', { style: 'currency', currency: 'BRL' }).format(receipt.totalValue)}
              </td>
              <td className="px-6 py-4 whitespace-nowrap">
                <span className={`px-2 inline-flex text-xs leading-5 font-semibold rounded-full 
                  ${receipt.status === 'ACTIVE' ? 'bg-green-100 text-green-800' : 
                    receipt.status === 'CANCELLED' ? 'bg-red-100 text-red-800' : 
                    'bg-yellow-100 text-yellow-800'}`}>
                  {receipt.status}
                </span>
              </td>
              <td className="px-6 py-4 whitespace-nowrap text-sm font-medium">
                <div className="flex space-x-2">
                  <button
                    onClick={() => onViewDetails(receipt.nfceNumber)}
                    className="text-indigo-600 hover:text-indigo-900"
                  >
                    <FileText size={18} />
                  </button>
                  <button
                    onClick={() => onUpdateStatus(receipt.nfceNumber)}
                    className="text-yellow-600 hover:text-yellow-900"
                  >
                    <RefreshCw size={18} />
                  </button>
                  <button
                    onClick={() => onDelete(receipt.nfceNumber)}
                    className="text-red-600 hover:text-red-900"
                  >
                    <Trash2 size={18} />
                  </button>
                </div>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};