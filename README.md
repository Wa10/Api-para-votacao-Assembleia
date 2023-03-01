# API Rest para Votação 
Desenvolvimento de uma api para votação de associados em pautas individuais. Essas votações ocorrem por meio de uma Assembléia(Sessão) que é definida por um período de votação e pela pauta selecionada para a mesma, com o inicio de uma sessão é permitida a votação dos associados que podem votar somente uma vez e uma pauta pode ter somente uma assembléia para votação. O associado vota no Sim ou Não para a pauta em sessão, depois do tempo de expiração da Assembléia não é mais permitido a votação.   


## Objetivo
- Cadastrar uma nova pauta
- Abrir uma sessão de votação em uma pauta (a sessão de votação deve ficar aberta por
um tempo determinado na chamada de abertura ou 1 minuto por default)
- Receber votos dos associados em pautas (os votos são apenas &#39;Sim&#39;/&#39;Não&#39;. Cada
associado é identificado por um id único e pode votar apenas uma vez por pauta)
- Contabilizar os votos e dar o resultado da votação na pauta

## Funcionalidades
- Cadastro e listagem de Associados
- Cadastro e listagem de Pautas
- Cadastro e listagem de Sessões
- Cadastro e listagem de Votações


## Postman
https://documenter.getpostman.com/view/14662521/2s93CSpqrP

## Tecnologias
- Java 11
- Spring boot
- Maven
- lombok
- JPA
- hibernate
- Postman
- RDS - Postgres
- AWS
