# Nota Fiscal

Este repositório contém um projeto para gerenciamento de notas fiscais, integrando backend e frontend. O objetivo é fornecer uma solução completa para emissão, consulta e manipulação de notas fiscais.

## Tecnologias Utilizadas

### Backend
- **Linguagem**: Java
- **Framework**: Spring Boot
- **Banco de Dados**: PostgreSQL
- **Autenticação**: Spring Security
- **APIs**: OpenAPI/Swagger

### Frontend
- **Linguagem**: TypeScript
- **Framework**: React
- **Gerenciamento de Estado**: Context API / Redux
- **UI**: Material-UI

## Estrutura do Repositório
- `.vscode/` - Configurações do VS Code
- `backend/` - Código-fonte do servidor
- `frontend/` - Código-fonte da interface do usuário

## Como Executar o Projeto

### Requisitos
- Docker e Docker Compose (opcional, mas recomendado)
- JDK 17+
- Node.js 18+
- PostgreSQL 14+

### Passos
1. Clone o repositório:
   ```sh
   git clone https://github.com/zsantana/nota-fiscal.git
   cd nota-fiscal
   ```
2. Configure as variáveis de ambiente conforme o modelo `.env.example`.
3. Para iniciar o backend:
   ```sh
   cd backend
   ./mvnw spring-boot:run
   ```
4. Para iniciar o frontend:
   ```sh
   cd frontend
   npm install
   npm start
   ```

## Contribuição
Contribuições são bem-vindas! Para contribuir, abra um pull request ou reporte issues.

## Licença
Este projeto está sob a licença MIT. Para mais informações, consulte o arquivo `LICENSE`.

