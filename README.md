# 🚗 Projeto LPOO - Sistema de Locadora de Veículos

Sistema desenvolvido em Java para gerenciamento de uma locadora de veículos, permitindo o cadastro e gerenciamento de clientes, funcionários, carros e locações.

Este projeto foi desenvolvido como atividade da disciplina de **Linguagem de Programação Orientada a Objetos (LPOO)**.

---

## 📖 Funcionalidades

### 🚙 Gerenciamento de Carros
- Cadastro de veículos
- Listagem de veículos
- Atualização de informações
- Remoção de veículos
- Controle de disponibilidade

### 👤 Gerenciamento de Clientes
- Cadastro de clientes
- Listagem de clientes
- Exclusão de clientes

### 👨‍💼 Gerenciamento de Funcionários
- Cadastro de funcionários
- Listagem de funcionários
- Busca por ID
- Atualização de dados
- Remoção de funcionários

### 📋 Gerenciamento de Locações
- Criação de locações
- Associação entre cliente, funcionário e veículo
- Controle automático da disponibilidade do carro
- Atualização de locações
- Cancelamento de locações
- Consulta de locações cadastradas

---

# 🏗 Estrutura do Projeto

```
ProjetoLPOO
│
├── src
│   ├── Modelo
│   │   ├── Pessoa.java
│   │   ├── Cliente.java
│   │   ├── Funcionario.java
│   │   ├── Carro.java
│   │   └── Locacao.java
│   │
│   ├── Repositorio
│   │
│   ├── Servicos
│   │
│   ├── Interface
│   │
│   └── Main.java
│
└── README.md
```

---

# 🛠 Tecnologias Utilizadas

- Java
- Programação Orientada a Objetos (POO)
- IntelliJ IDEA
- Git
- GitHub

---

# 📚 Conceitos Aplicados

O projeto utiliza diversos conceitos de Programação Orientada a Objetos, como:

- Encapsulamento
- Herança
- Polimorfismo
- Abstração
- Organização em camadas
- Repositórios
- Serviços
- Manipulação de coleções (`ArrayList`)
- Singleton
- Tratamento de exceções
- Organização por pacotes

---

# ▶ Como executar

1. Clone o repositório

```bash
git clone https://github.com/PedroGabriel200/Projeto_LPOO.git
```

2. Abra o projeto no IntelliJ IDEA.

3. Execute a classe:

```
Main.java