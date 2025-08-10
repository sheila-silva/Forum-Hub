# 📚 ForumHub – API REST com Spring Boot e JWT

O **ForumHub** é uma API REST para gerenciamento de tópicos, respostas e usuários em um fórum online.  
Possui autenticação **JWT** e controle de acesso por **roles** (`ROLE_USER` e `ROLE_ADMIN`).
Este projeto contém os requisitos básicos e extras da formação ONE - Java Backend promovida pela ORACLE em parceria com a Alura. 
Ele foi Desenvolvido seguindo boas práticas de arquitetura com **DTOs**, **Services** e **Controllers**.

---
**ForunHub** contém permissões separadas seguindo o princíprio de privilégio mínimo 

## 🔒 Segurança

- **ROLE_USER** → pode criar tópicos, responder e visualizar.
- **ROLE_ADMIN** → pode excluir ou editar qualquer tópico/resposta.

![Lista dos principais endpoints da API](./assets/endpoints.png)

---

## 🚀 Tecnologias utilizadas

- ☕ **Java 17**
- 🌱 **Spring Boot 3**
- 🗄 **Spring Data JPA**
- 🔒 **Spring Security + JWT**
- 🐬 **MySQL**
- 🛠 **Flyway** (migrations)
- 🧩 **Lombok**
- ✅ **Spring Validation**
- 📜 **Auth0 Java JWT**
- 📖 **Springdoc OpenAPI / Swagger**
- 🌙 **Insomnia** 
