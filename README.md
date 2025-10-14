📋  Pré-requisitos

    Para executar com Maven:

        Java 17 ou superior

        Maven 3.6+

        PostgreSQL

    Para executar com Docker:

        Docker

        Docker Compose

🚀 Execução do Projeto

Método 1: Execução com Maven
```
Configure o arquivo application.properties:
spring.datasource.url=jdbc:postgresql://localhost:5432/compridb
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```
Execute o backend:
```
cd backend
mvn spring-boot:run
```
