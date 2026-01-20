# 🚀 SpaceX Data Voyager

<div style="display: inline_block"><br>
  <img align="center" alt="Java" src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white">
  <img align="center" alt="Spring" src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white">
  <img align="center" alt="MySQL" src="https://img.shields.io/badge/MySQL-00000f?style=for-the-badge&logo=mysql&logoColor=white">
  <img align="center" alt="MongoDB" src="https://img.shields.io/badge/MongoDB-4EA94B?style=for-the-badge&logo=mongodb&logoColor=white">
  <img align="center" alt="Docker" src="https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white">
</div>

<br>

**SpaceX Data Voyager** é uma aplicação de processamento em lote (**Batch Processing**) robusta, projetada para extrair, filtrar e transformar dados técnicos da API oficial da SpaceX para armazenamento otimizado em larga escala.

![Visualização dos Dados no MongoDB](assets/mongo)

## 📌 Sobre o Projeto

O projeto demonstra a implementação de um pipeline **ETL** (Extract, Transform, Load) utilizando o ecossistema Spring. A aplicação gerencia fluxos complexos de dados entre diferentes tipos de bancos (Relacional e NoSQL) e serviços externos, garantindo integridade e auditabilidade através de metadados.

### ✨ Destaques Técnicos
* **Processamento Orientado a Chunks**: Processamento eficiente de dados em lotes configuráveis, otimizando o consumo de memória.
* **Arquitetura Poliglota**: Integração simultânea com **MySQL** para controle de estado do Job e **MongoDB** para persistência de documentos.
* **Mapeamento de Domínio**: Uso de DTOs para desacoplar a API externa do modelo de dados interno do sistema.
* **Auditabilidade**: Rastreamento completo de execuções, falhas e reinicializações através das tabelas de metadados do Spring Batch.

## ☁️ Infraestrutura e Execução

O sistema utiliza containers Docker para garantir que o ambiente de execução seja idêntico em qualquer máquina, orquestrando três serviços essenciais.

* **MySQL 8**: Repositório de metadados (`spacex_metadata`) rodando na porta padrão `3306`.
* **MongoDB**: Banco NoSQL rodando na porta `27017` para armazenamento da coleção `rockets`.
* **phpMyAdmin**: Interface web na porta `8081` para monitoramento das tabelas de controle do Job.

### Desafios Superados
- **Gestão de Portas**: Migração de portas customizadas para portas padrão de mercado visando compatibilidade total com ferramentas de monitoramento.
- **Transformação de Dados**: Implementação de lógica de processamento para filtrar apenas campos críticos (ID, Nome, Status e Descrição) de uma resposta JSON complexa.

## 🎯 Competências Desenvolvidas

Este projeto consolidou os seguintes conhecimentos práticos:

- [x] Implementação de **Spring Batch Jobs** e **Steps**.
- [x] Configuração de **Múltiplos DataSources** (JDBC e MongoDB).
- [x] Consumo de APIs REST com **RestTemplate**.
- [x] Modelagem de documentos NoSQL e documentos de domínio Java.
- [x] Orquestração de serviços com **Docker Compose**.

---
Desenvolvido por João Dev. 🚀
