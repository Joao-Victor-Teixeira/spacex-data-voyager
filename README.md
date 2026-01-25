# 🚀 SpaceX API – Spring Boot + Batch

![Java](https://img.shields.io/badge/Java-17%2B-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen)
![Spring Batch](https://img.shields.io/badge/Spring-Batch-green)
![MongoDB](https://img.shields.io/badge/MongoDB-NoSQL-brightgreen)
![JUnit](https://img.shields.io/badge/JUnit-5-blue)
![RestAssured](https://img.shields.io/badge/RestAssured-API%20Tests-blueviolet)
![Docker](https://img.shields.io/badge/Docker-Compose-blue)

API backend inspirada em dados públicos da SpaceX, desenvolvida com **Spring Boot**, **Spring Batch** e **MongoDB**, com foco em **processamento em lote**, **boas práticas de arquitetura**, **testes automatizados** e **clareza na navegação da API**.

> 📌 Projeto criado como fixação prática dos conteúdos estudados em cursos de Java/Spring (Nelio Alves), com decisões técnicas conscientes voltadas a aprendizado, legibilidade e experiência do consumidor da API.

---

## 🎯 Objetivo do Projeto

- Processar dados de foguetes, lançamentos e missões por meio de **jobs batch**
- Persistir os dados processados no **MongoDB**
- Expor os dados via **API REST**
- Treinar **versionamento de API**, **relacionamento entre entidades** e **testes automatizados**
- Simular um projeto real, indo além do *happy path*

---

## 🧠 Principais Decisões Técnicas

- Utilização do **Spring Batch** para ingestão e processamento de dados
- **API REST** com versionamento por URL (`/v1`, `/v2`)
- **Links HATEOAS mais descritivos**, priorizando clareza para consumidores não técnicos
- Separação clara entre **jobs**, **camada de domínio**, **API** e **testes**

> ⚠️ Observação: algumas descrições de links são propositalmente mais verbosas.  
> A intenção é tornar a navegação da API mais autoexplicativa, inclusive para usuários leigos.

---

## 🧩 Domínio do Projeto

- 🚀 **Rockets**  
  Dados técnicos dos foguetes da SpaceX

- 🛰️ **Launches**  
  Lançamentos associados a foguetes

- 🧭 **Missions**  
  Missões espaciais, agregando lançamentos e foguetes (**v2**)

---

## 🔁 Versionamento da API

| Versão | Descrição                                           |
|------|-------------------------------------------------------|
| v1   | Endpoints simples, entidades desacopladas              |
| v2   | Entidades agregadas (Missões → Lançamentos → Foguetes) |

### Exemplo de Endpoint

```http
GET /api/v2/missions/{id}
````
## 🧪 Testes

- ✅ Testes unitários
- ✅ Testes de integração
- ✅ **RestAssured** para validação dos endpoints

Os testes validam:
- Contratos da API
- Status HTTP
- Estrutura dos responses

## ⚙️ Tecnologias Utilizadas

- Java 17+
- Spring Boot
- Spring Batch
- Spring Data MongoDB
- RestAssured
- JUnit 5
- Maven
- Docker / Docker Compose

## 📊 Exemplos de Dados (MongoDB)

> Exemplo de coleções persistidas após execução dos jobs batch.

![MongoDB Collections](assets/mongo.png)

## ▶️ Como Executar o Projeto

```bash
# subir bancos
docker compose up -d

# rodar aplicação
mvn spring-boot:run

## 👨‍💻 Autor

**João Víctor Teixeira da Costa Rossi**

> Projeto educacional com foco em aprendizado profundo e decisões técnicas conscientes.
