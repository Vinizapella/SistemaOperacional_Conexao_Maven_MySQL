# 🗄️ SistemaOperacional — Conexão Maven + MySQL

> Projeto de estudo sobre conexão com banco de dados **MySQL** utilizando **Maven**, no contexto da disciplina de Sistema Operacional.

---

## 🗂️ Sobre

Este repositório demonstra como configurar e utilizar o **Maven** para gerenciar dependências e estabelecer uma conexão com o banco de dados **MySQL** em uma aplicação Java.

---

## ⚙️ Pré-requisitos

Antes de rodar o projeto, certifique-se de ter instalado:

- ☕ [Java JDK](https://www.oracle.com/java/technologies/downloads/) (versão 11 ou superior)
- 📦 [Apache Maven](https://maven.apache.org/)
- 🐬 [MySQL Server](https://dev.mysql.com/downloads/)

---

## 🛠️ Configuração

### 1. Clone o repositório

```bash
git clone https://github.com/seu-usuario/SistemaOperacional_Conexao_Maven_MySQL.git
cd SistemaOperacional_Conexao_Maven_MySQL
```

### 2. Configure o banco de dados

```sql
CREATE DATABASE nome_do_banco;
```

### 3. Ajuste as credenciais

Edite o arquivo de configuração com seus dados:

```
URL:      jdbc:mysql://localhost:3306/nome_do_banco
Usuário:  seu_usuario
Senha:    sua_senha
```

### 4. Execute o projeto

```bash
mvn compile
mvn exec:java
```

---

## 📦 Dependência Maven (pom.xml)

```xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.33</version>
</dependency>
```

---

## 📁 Estrutura

```
📦 SistemaOperacional_Conexao_Maven_MySQL
 ┣ 📂 src
 ┃ ┗ 📂 main
 ┃   ┗ 📂 java
 ┃     ┗ 📄 Main.java
 ┣ 📄 pom.xml
 ┗ 📄 README.md
```

---

## 👤 Autor

Feito com 🖤 para a disciplina de Sistema Operacional.

---

<p align="center">
  <img src="https://img.shields.io/badge/Java-ED8B00?style=flat-square&logo=openjdk&logoColor=white" />
  <img src="https://img.shields.io/badge/Maven-C71A36?style=flat-square&logo=apachemaven&logoColor=white" />
  <img src="https://img.shields.io/badge/MySQL-4479A1?style=flat-square&logo=mysql&logoColor=white" />
  <img src="https://img.shields.io/badge/status-concluído-brightgreen?style=flat-square" />
</p>
