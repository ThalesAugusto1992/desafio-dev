# Desafio CNAB

## Stack
- Java 8
- Spring Boot
- Maven
- Hibernate
- Lombok
- Apache Commons
- Swagger
- PostgreSQL (Para o banco de dados)

## Informações do banco
Os dados para acesso ao banco pela aplicação ficam no arquivo application.properties

spring.datasource.url=URL_DE_CONEXAO
spring.datasource.username=USUARIO_BANCO
spring.datasource.password=SENHA_USUARIO_BANCO

Por default já deixei os dados para acesso local. Se atende a alterar o usuario e senha de acordo com seu ambiente;


## Informação para build e rodar o projeto

Depois de alterar as informações do banco você pode fazer o build do projeto rodando o comando:

```bash
mvn clean package
```

Quando finalizar esse processo ele vai gerar uma pasta com o nome target e dentro dela você vai encontrar um arquivo .jar

Para executar o jar você precisa rodar o seguinte comando:
```bash
 java -jar .\target\Desafio-CNAB-0.0.1-SNAPSHOT.jar
```
Lembrando que o nome do JAR vai ser alterado de acordo com a versão que você definir no arquivo pom.xml
Por padrão a porta utilizada vai ser 8080

## Acessando a Documentação da API (Swagger)

Você pode acessar o swagger pelo link: host/api/swagger-ui.html

Caso você esteja rodando local o link vai ser: http://localhost:8080/api/swagger-ui.html

Nele você encontra ss informações das duas API´s criadas (Upload do arquivo e buscar transações por loja)


## Requisitos do projeto

Ter instalado o Maven: https://maven.apache.org/download.cgi
Ter instalado o Java 8: https://www.oracle.com/java/technologies/downloads/#java8
Ter instalado o banco PostgreSQL: https://www.postgresql.org/download/
