# Facilita
<h4 align="center"> 
<img src="https://i.imgur.com/YTIHcCa.png">
</h4> 

<div id='sobre'/>  

## Sobre o Projeto
### Apresentação do Problema
As pessoas costumam ter dificuldade em encontrar prestadores de serviço qualificados para solucionar pequenos problemas cotidianos. Entre esses serviços, podemos citar: reparos elétricos, limpeza, reparos hidráulicos, instalação e montagem de móveis, serviços de marcenaria, etc.
Em contrapartida, os profissionais que prestam estes serviços de maneira informal, costumam ter dificuldade em encontrar novos clientes e comprovar suas experiências e referências na execução dos trabalhos.
Agravando o problema, temos o medo (provocado pelo aumento da violência urbana), vivido por ambas as partes. O cliente, de deixar um desconhecido, sem referências, entrar em sua casa. E do prestador de serviço de ir a um local desconhecido, também sem referências, levando seu equipamento de trabalho. 

### Apresentação da Solução
Criação de um meio de comunicação, onde clientes e prestadores de serviço possam se conectar. Clientes previamente atendidos por esses prestadores, podem deixar classificação de confiabilidade e qualidade dos serviços prestados. Da mesma forma, os prestadores podem avaliar e deixar informações sobre os clientes para que outros prestadores se informem.

<div id='sumario'/>  

## Sumário

* [Sobre](#sobre)
* [Sumário](#sumario)
* [Descrição](#dsc)
* [Setup](#stp)
* [Desafio extra : Frontend](#desafio)
* [Autores](#autores)
* [Relatório Final](https://docs.google.com/document/d/1zljkuIHTbY5ccxNhm79-vIanueB_9vSxUbPIT3JEsfo/edit?usp=sharing)
<div id='dsc'/> 

## Descrição
Foram contruídos dois microserviços diferentes com as seguintes funções:
* Post-Services : Cadastro das postagens com os serviços oferecidos pelos prestadores
* Pessoa-Service: Cadastro dos prestadores na plataforma


Para construção desses microserviços foi utilizados os seguintes recursos:
* Banco de dados: Amazon DynamoDB  
* Linguagem java com framework Spring Boot 
* Framework JUnit 5 para testes 
* Plataforma Postman para testes da API

<div id='stp'/>  

## Setup

Para executar esse projeto em sua maquina devemos inicialmente clonar o repositorio [SDM](https://github.com/alineferlini/sdm.git).
```shell
$ git clone https://github.com/alineferlini/sdm.git
```

### Passo a Passo para execução Local
Obs.: A descrição da execução local abaixo foi elaborada considerando o uso da IDE Eclipse
* 1° passo: No terminal do windows navegue até a pasta contendo o arquivo pessoa_services-0.0.1-snapshot
* 2° passo: Execute o comando: java -jar -Dspring.config.location=C:/Users/USER/sdm/pessoa_services/ pessoa_services-0.0.1-snapshot.jar
* 3° passo: Aguarde até que a mensagem: "Started Application in xx.xx seconds" ser exibida

 Dica: Não clique dentro do console para não bloquear a execução. Se ocorrer, aperte Enter para liberar. 


### Passo a Passo para execução local com docker
Obs.: A descrição de execução abaixo considera que o usuario tem o aplicativo docker instalado em sua máquina
* 1° passo: navegar até a pasta docker contida no repositorio clonado
* 2° passo: 
<ul>
  <li> 1°passo </li>
  <li> 2°passo. </li> 

</ul>

<div id='desafio'/> 

## Desafio extra : Frontend
Presente no repositório [FrontEnd - FAcilita](https://github.com/ldevLucasl/sdm-rn.git)

# Autores
| <img src="https://avatars.githubusercontent.com/u/102473494?v=4" width=115 > | <img src="https://avatars.githubusercontent.com/u/89555322?v=4" width=115 >| <img src="https://avatars.githubusercontent.com/u/89614560?v=4" width=115 > | <img src="https://avatars.githubusercontent.com/u/81639502?v=4" width=115 >
|---|---|---|---|
| [Anna Conde](https://github.com/annaconde) | [Aline Ferlini](https://github.com/alineferlini) | [Jéssica Amaral](https://github.com/JessicaKAmaral) | [ Leonardo   Marinho ](https://github.com/leozaomarinho) 

| <img src="https://avatars.githubusercontent.com/u/62044186?v=" width=115 > | <img src="https://avatars.githubusercontent.com/u/80972527?v=4" width=115 > | <img src="https://avatars.githubusercontent.com/u/102559866?v=4" width=115 >
|---|---|---|
| [Lucas Valadares](https://github.com/ldevLucasl) | [Sanderson Maxwell](https://github.com/SandersonMaxwell) | [Vinicius Mello](https://github.com/viniciusMelloo) 

