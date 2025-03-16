import React from 'react';
import { useForm } from 'react-hook-form';
import { Search } from 'lucide-react';
import { ReceiptFilterDTO } from '../types/api';

interface SearchFormProps {
  onSearch: (filter: ReceiptFilterDTO) => void;
}

export const SearchForm: React.FC<SearchFormProps> = ({ onSearch }) => {
  const { register, handleSubmit } = useForm<ReceiptFilterDTO>();

  return (
    <form onSubmit={handleSubmit(onSearch)} className="bg-white p-6 rounded-lg shadow-sm mb-6">
      <div className="grid grid-cols-1 md:grid-cols-3 gap-4">
        <div>
          <label className="block text-sm font-medium text-gray-700">Data Inicial</label>
          <input
            type="datetime-local"
            {...register('startDate')}
            className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
          />
        </div>
        <div>
          <label className="block text-sm font-medium text-gray-700">Data Final</label>
          <input
            type="datetime-local"
            {...register('endDate')}
            className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
          />
        </div>
        <div>
          <label className="block text-sm font-medium text-gray-700">CNPJ</label>
          <input
            type="text"
            {...register('cnpj')}
            className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
            placeholder="00.000.000/0000-00"
          />
        </div>
        <div>
          <label className="block text-sm font-medium text-gray-700">Status</label>
          <select
            {...register('status')}
            className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
          >
            <option value="">Todos</option>
            <option value="ACTIVE">Ativo</option>
            <option value="CANCELLED">Cancelado</option>
            <option value="PENDING">Pendente</option>
          </select>
        </div>
        <div>
          <label className="block text-sm font-medium text-gray-700">Identificação do Consumidor</label>
          <input
            type="text"
            {...register('consumerIdentification')}
            className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
            placeholder="CPF/CNPJ"
          />
        </div>
        <div className="flex items-end">
          <button
            type="submit"
            className="w-full bg-indigo-600 text-white px-4 py-2 rounded-md hover:bg-indigo-700 flex items-center justify-center gap-2"
          >
            <Search size={18} />
            Pesquisar
          </button>
        </div>
      </div>
    </form>
  );
};