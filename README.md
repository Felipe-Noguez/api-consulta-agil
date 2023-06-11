# api-consulta-agil
Aceleradora Ágil, turma 24 (2023/2º Semestre)

* O projeto consiste em um sistema para cadastro de Pacientes e de consultas, onde há o cadastro e a listagem dos pacientes. Também é possivel cadastrar a consulta relacionando à um paciente, listar e remover.
* No arquivo data.sql contém inserções manuais no banco para facilitar o uso da aplicação.


## Tecnologias utilizadas no desenvolvimento
* Java JDK 17.0.4
* Spring boot
* Maven 4.0.0
* Swagger 1.6.8
* Banco de dados em memória H2
* Lombok

## Ferramenta utilizada no desenvolvimento
* Intellij IDEA


## Para executar
* Clonar o repositório: api-consulta-agil.
* Configurar login e senha do banco de dados, diretamente no arquivo de configuração application.properties ou através da IDE, conforme imagem abaixo:

![Print da configuração de login e senha do bando H2](https://github.com/Felipe-Noguez/only-assets/blob/main/attornatus-api/IDE1.png?raw=true)

* Executar a aplicação através da classe main.
* Acessar o banco no navegador utilizando a url: http://localhost:8080/h2
* Inserir JDBC url, user name e password conforme configuração do arquivo application.properties, segue imagem abaixo para referência:

![Print de referência para configuração do acesso ao H2](https://github.com/Felipe-Noguez/only-assets/blob/main/attornatus-api/H2.png?raw=true)
* Acessar o Swagger no navegador utilizando a url: http://localhost:8080/swagger-ui/index.html#/
* No Swagger já existem exemplos para realizar cadastros, alterações e listagem.
