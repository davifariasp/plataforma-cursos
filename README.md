<h1 align="center" style="font-weight: bold;">Plataforma de Cursos 💻</h1>

<p align="center">
  <a href="#page_with_curl-sobre">Sobre</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#hammer-tecnologias">Tecnologias</a>
  &nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#sos-desafios">Desafios</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#rotating_light-próximos-passos">Próximos passos</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#golf-rotas">Rotas</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#books-requisitos">Requisitos</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#rocket-iniciando">Iniciando</a>
</p>

<div align="center">
    <img alt="Tela de tela de login" src="https://i.imgur.com/2bGOjLV.png" width="350" />
    <img alt="Tela de registro" src="https://i.imgur.com/tT9wnoJ.png" width="350" />
    <img alt="Tela de dos cursos" src="https://i.imgur.com/PnS2rZr.png" width="350" />
</div>

## :page_with_curl: Sobre

<div align="center">
    <img alt="System Design" src="https://i.imgur.com/fob7sA2.png" width="700" />
</div>

O desafio consistia na criação de uma plataforma de cursos online, utilizando Java com Spring Boot no back, arquitetura de microsserviços e RabbitMQ para gerenciamento de filas. Também era necessário a utilização de concorrência e WebSocket para comunicação em tempo real, além de implementar autenticação JWT com o OAuth2. Já no front-end era mais simples, utilizei NextJs com React, junto com Talwhind para estilização. Também foi implementado a conexão com WebSocket do microsserviço API. 

## :hammer: Tecnologias

- Java 23
- Maven
- NextJS 13
- Spring Boot
- Spring AMQP
- Spring JPA
- Spring OAuth2
- Spring Security
- Spring Validation
- Lombok
- Jackson
- RabbitMQ
- Docker
- Postgres


## :sos: Desafios

O desafio não é só na questão técnica, mas também na forma que você se organiza para completá-lo. O tempo limite era 72h (3 dias) e uma série de requisitos para serem satisfeitos. Mas acabou que deixei de apenas implementar os testes, tanto os unitários quanto o de integração, do front e do back. 

Uma coisa que eu queria ter implementado mas pelo tempo não rolou, foi a questão da criação das imagens para o docker e o deploy em uma cloud, juntamente com uma API Gateway (com NGINX ou Spring Cloud).

## :books: Requisitos

As tecnologias a seguir são necessárias para conseguir rodar o projeto em sua máquina.

- Ter [**Git**](https://git-scm.com/) para clonar o projeto.
- Ter o Java 23 instalado.
- Ter o NodeJs instalado.
- Ter [**Docker**](https://www.docker.com/get-started/) para executar o projeto.

## :golf: Rotas
A API oferece Swagger para documentação das rotas

### Swagger Backend
http://localhost:8081/swagger-ui/index.html#

### Swagger API
http://localhost:8082/swagger-ui/index.html#

## :rocket: Iniciando
``` bash
  # Clonar o projeto:
  $ git clone git@github.com:davifariasp/plataforma-cursos.git

  # Entrar no diretório:
  $ cd plataforma-cursos

  # Compor o projeto:
  $ docker compose up -d

  # Entrar no serviço backend:
  $ cd backend

  # Entrar no serviço api:
  $ cd api

  # Entrar da spa:
  $ cd frontend
```
