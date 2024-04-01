# Projeto Playwright com Java

Este projeto contém testes automatizados utilizando Playwright com Java com vários tipos de interações.

## Configuração do Ambiente

Certifique-se de ter o seguinte instalado em seu sistema:

- Java Development Kit (JDK) 8 ou superior
- Maven

## Como Baixar o Projeto

Para baixar o projeto, siga os passos abaixo:

1. Clone o repositório do GitHub:

```bash
   git clone https://github.com/driuzzo/playwright-java
```
2. Acesse o diretório do projeto:

```bash
    cd playwright-java
```

## Configuração do Arquivo `local.properties`

Após baixar o projeto, copie o arquivo `local-example.properties` e renomeie-o para `local.properties`. Em seguida, preencha os dados sensíveis conforme necessário:

```bash
USERNAME=your-username-here
PASSWORD=your-password-here
USER=your-lambdatest-user-here
ACCESSKEY=your-lambdatest-accesskey-here
```
Certifique-se de não compartilhar o arquivo `local.properties` com dados sensíveis em repositórios públicos.

## Como Executar os Testes

Para executar os testes automatizados, siga os passos abaixo:

1.  Abra um terminal na raiz do projeto.

2.  Execute o seguinte comando para baixar as dependências do Maven:

```bash
mvn clean install
```
3.  Execute o seguinte comando para executar os testes:
```bash
mvn clean install
```
Os resultados dos testes serão exibidos no terminal.

4. Para executar os demais testes, vá até a pasta src/test/java, abra um arquivo e selecione a opção Run no seu editor.