# TodoList API

Esta API foi desenvolvida com Spring Boot para fins de aprendizado, oferecendo funcionalidades básicas para o gerenciamento de tarefas (tasks).

### 📌 Funcionalidades  

- **Cadastro de usuários** (*users*)  
- **Criação de tarefas** associadas a um usuário  
- **Listagem de tarefas** cadastradas  
- **Edição de tarefas** existentes  

## 🚀 Deploy
A API está hospedada em: [TodoList API](https://todolist-api-6ouf.onrender.com)

## 🌐 Endpoints

### Usuário (User)
- **Criar Usuário**
  - **`POST /users`**
  - Body JSON:
    ```json
    {
      "name": "Nome do Usuário",
      "username": "Nomde único do usuário na aplicação"
      "password": "1234"
    }
    ```

### Tasks
- **Criar Task**
  - **`POST /tasks`**
  - Body JSON:
    ```json
    {
      "description": "My description",
      "title": "My title",    
      "startAt": "2025-04-08T16:17:18",
      "endAt": "2025-04-08T16:17:19",
      "priority": "Alta"
    }
    ```

- **Listar Tasks**
  - **`GET `/tasks`**

- **Alterar Task**
  - **`PATCH `/tasks/{id}`**
  - Body JSON:
    ```json
    {
      "title": "Novo Título",
      "description": "Nova Descrição"
    }
    ```

## Tecnologias Utilizadas
- Java 17
- Spring Boot
- Banco de Dados H2
- [Render](https://render.com/) (para deploy)

## Como Executar Localmente
1. Clone o repositório
2. Configure o banco de dados no `application.properties`
3. Execute o projeto com `mvn spring-boot:run` ou rode a aplicação na sua IDE

## Autor
Desenvolvido por Letícia Cardoso Rodrigues.

