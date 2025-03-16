import React, { useEffect, useState } from 'react';
import { ReceiptList } from './components/ReceiptList';
import { SearchForm } from './components/SearchForm';
import { receiptsApi } from './services/api';
import { ReceiptSummaryDTO, ReceiptFilterDTO } from './types/api';
import { Plus } from 'lucide-react';

function App() {
  const [receipts, setReceipts] = useState<ReceiptSummaryDTO[]>([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    loadReceipts();
  }, []);

  const loadReceipts = async () => {
    try {
      const data = await receiptsApi.getAllReceipts();
      setReceipts(data);
    } catch (error) {
      console.error('Error loading receipts:', error);
    } finally {
      setLoading(false);
    }
  };

  const handleSearch = async (filter: ReceiptFilterDTO) => {
    setLoading(true);
    try {
      const data = await receiptsApi.searchReceipts(filter);
      setReceipts(data);
    } catch (error) {
      console.error('Error searching receipts:', error);
    } finally {
      setLoading(false);
    }
  };

  const handleDelete = async (nfceNumber: string) => {
    if (window.confirm('Tem certeza que deseja excluir esta nota fiscal?')) {
      try {
        await receiptsApi.deleteReceipt(nfceNumber);
        await loadReceipts();
      } catch (error) {
        console.error('Error deleting receipt:', error);
      }
    }
  };

  const handleUpdateStatus = async (nfceNumber: string) => {
    const newStatus = prompt('Digite o novo status (ACTIVE, CANCELLED, PENDING):');
    if (newStatus) {
      try {
        await receiptsApi.updateReceiptStatus(nfceNumber, newStatus);
        await loadReceipts();
      } catch (error) {
        console.error('Error updating receipt status:', error);
      }
    }
  };

  return (
    <div className="min-h-screen bg-gray-100">
      <div className="max-w-7xl mx-auto py-6 sm:px-6 lg:px-8">
        <div className="px-4 py-6 sm:px-0">
          <div className="flex justify-between items-center mb-6">
            <h1 className="text-3xl font-bold text-gray-900">Notas Fiscais</h1>
            <button
              className="bg-indigo-600 text-white px-4 py-2 rounded-md hover:bg-indigo-700 flex items-center gap-2"
              onClick={() => alert('Funcionalidade em desenvolvimento')}
            >
              <Plus size={18} />
              Nova Nota
            </button>
          </div>

          <SearchForm onSearch={handleSearch} />

          {loading ? (
            <div className="flex justify-center items-center h-64">
              <div className="animate-spin rounded-full h-12 w-12 border-b-2 border-indigo-600"></div>
            </div>
          ) : (
            <ReceiptList
              receipts={receipts}
              onViewDetails={(nfceNumber) => alert(`Visualizar detalhes: ${nfceNumber}`)}
              onDelete={handleDelete}
              onUpdateStatus={handleUpdateStatus}
            />
          )}
        </div>
      </div>
    </div>
  );
}

export default App;