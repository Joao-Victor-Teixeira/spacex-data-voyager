# 🚀 SpaceX Data Voyager

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen)
![MySQL](https://img.shields.io/badge/MySQL-8-blue)
![MongoDB](https://img.shields.io/badge/MongoDB-NoSQL-green)
![Docker](https://img.shields.io/badge/Docker-Compose-blue)

**SpaceX Data Voyager** é uma aplicação de processamento em lote (**Batch Processing**) robusta, projetada para extrair, filtrar, transformar e expor dados técnicos da API oficial da SpaceX, utilizando uma arquitetura moderna baseada no ecossistema Spring.

![Visualização dos Dados no MongoDB](assets/mongo.png)

---

## 📌 Sobre o Projeto

O projeto demonstra a implementação de um pipeline **ETL (Extract, Transform, Load)** utilizando **Spring Batch**, aliado a uma **API REST documentada e testada**, responsável por disponibilizar os dados processados.

A aplicação gerencia fluxos complexos entre:
- APIs externas (SpaceX)
- Processamento em lote
- Persistência poliglota (Relacional + NoSQL)
- Exposição via API REST com boas práticas de design

---

## ✨ Destaques Técnicos

- **Processamento Orientado a Chunks**  
  Processamento eficiente de grandes volumes de dados em lotes configuráveis, otimizando uso de memória.

- **Arquitetura Poliglota**  
  - **MySQL**: controle de estado, metadados e execuções do Spring Batch  
  - **MongoDB**: persistência dos dados de domínio (rockets, launches, missions)

- **API REST Moderna**  
  - Endpoints paginados
  - Filtros por status (ativos/inativos)
  - Implementação de **HATEOAS**

- **Documentação OpenAPI / Swagger**  
  Documentação automática dos endpoints para fácil exploração e entendimento da API.

- **Testes Automatizados**  
  - Testes unitários de serviços (JUnit + Mockito)
  - Testes de integração de API com **RestAssured**
  - Cobertura monitorada com **JaCoCo**

---

## 🧱 Arquitetura Geral

API SpaceX
↓
Spring Batch (ETL)
↓
MongoDB (Dados de Domínio)
↓
Spring Boot REST API
↓
Swagger / Consumers


---

## ☁️ Infraestrutura e Execução

O projeto utiliza **Docker Compose** para garantir um ambiente padronizado e reproduzível.

### Serviços

- **MySQL 8**
  - Porta: `3306`
  - Banco: `spacex_metadata`
  - Responsável pelos metadados do Spring Batch

- **MongoDB**
  - Porta: `27017`
  - Banco: `spacex_voyager`
  - Coleções: `rockets`, `launches`, `missions`

- **phpMyAdmin**
  - Porta: `8081`
  - Monitoramento das tabelas de controle do Batch

---

## 📊 Estado Atual do Projeto (Jan/2026)

✔️ **Concluído**
- Pipeline Batch para ingestão de foguetes
- Persistência em MongoDB
- API REST para Rockets
- Paginação e filtros
- HATEOAS
- Swagger / OpenAPI
- Testes unitários (Service Layer)
- Testes de integração (RestAssured)
- Relatórios de cobertura com JaCoCo
- Dockerização completa

🚧 **Em Desenvolvimento**
- Batch de **Launches**
- Relacionamento Rocket → Launch
- Relacionamento Launch → Mission

---

## 🛠️ Próximos Passos

- [ ] Finalizar Batch de Launches
- [ ] Criar serviço de Missões
- [ ] Relacionar foguetes, lançamentos e missões
- [ ] Criar serviço de relatório semanal (quantidade de lançamentos por foguete)
- [ ] Otimização para execução em **Raspberry Pi / HomeLab**
- [ ] Avaliar módulo adicional com **Apache Cassandra**

---

## 🧠 Desafios Técnicos Superados

- Configuração de **múltiplos DataSources**
- Integração Batch + API REST no mesmo projeto
- Correção de mapeamento Entity → DTO
- Testes de integração com servidor embarcado
- Controle de cobertura sem poluir métricas irrelevantes

---

## 🎯 Competências Desenvolvidas

- Spring Boot 3
- Spring Batch
- REST APIs
- HATEOAS
- OpenAPI / Swagger
- Testes automatizados (JUnit, Mockito, RestAssured)
- Docker e Docker Compose
- Arquitetura poliglota
- Boas práticas de versionamento e commits

---

## 👨‍💻 Autor

**João Dev**  
Projeto desenvolvido como estudo avançado de backend, batch processing e arquitetura de sistemas modernos.

🚀 *“Aprendendo a aprender, todos os dias.”*
