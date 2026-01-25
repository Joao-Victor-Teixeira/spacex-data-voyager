# 🚀 SpaceX API – Spring Boot + Batch

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

| Versão | Descrição                                              |
|------|----------------------------------------------------------|
| v1   | Endpoints simples, entidades desacopladas                 |
| v2   | Entidades agregadas (Missões → Lançamentos → Foguetes)    |

### Exemplo

```http
GET /api/v2/missions/{id}

🧪 Testes

✅ Testes unitários

✅ Testes de integração

✅ RestAssured para validação dos endpoints

Os testes validam:

Contratos da API

Status HTTP

Estrutura dos responses

⚙️ Tecnologias Utilizadas

Java 17+

Spring Boot

Spring Batch

Spring Data MongoDB

RestAssured

JUnit 5

Maven

Docker / Docker Compose

📊 Exemplos de Dados (MongoDB)

![Exemplo de coleções persistidas após execução dos jobs batch.]

(assets/mongo.png)

▶️ Como Executar o Projeto

# subir bancos
docker compose up -d

# rodar aplicação
mvn spring-boot:run

🧠 Aprendizados

Uso prático do Spring Batch

Trade-offs entre padrão técnico e clareza para o consumidor da API

Importância do versionamento de APIs

Testes como parte do design da aplicação

📌 Próximos Passos

Refinar agregações na v2

Melhorar documentação dos endpoints

Expandir cobertura de testes

👨‍💻 Autor

João Víctor Teixeira da Costa Rossi

Projeto educacional com foco em aprendizado profundo e decisões técnicas conscientes.
