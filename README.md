## Instalação

A API utiliza [Java 11](https://www.oracle.com/br/java/technologies/javase/jdk11-archive-downloads.html), [Maven](https://maven.apache.org/), um banco [H2](https://www.h2database.com/html/main.html) local, e [Flyway](https://flywaydb.org/) para realizar a migração dos dados.

Para instalar o projeto, basta rodar o seguiunte comando:

```sh
mvn install
```

Para realizar a migração do banco de dados, é necessário rodar o comando:

```sh
mvn flyway:migrate
```


## Desenvolvimento
Para rodar o ambiente de desenvolvimento, basta rodar o comando:

```sh
mvn spring-boot:run
```
### Requests
O projeto possui um arquivo .json com uma collection do postman, contendo todos os endpoints da aplicação.

### Testes
Os testes foram escritos utilizando [Spock](https://spockframework.org/) e [Groovy](https://groovy-lang.org/). O arquivo de testes está localizado em `src/test/java/com/test/demo/service/EmployeeServiceTest`