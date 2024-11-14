# Plataforma de Gerenciamento de Cursos Online

Este projeto implementa uma aplicação Java para gerenciar uma plataforma de cursos online. Utiliza Hibernate ORM para tratar o mapeamento entre as entidades e Spring Boot para expor APIs RESTful que gerenciam alunos, cursos e inscrições.

## Visão Geral

A aplicação oferece:
- Gerenciamento de alunos, cursos e inscrições.
- Registro de alunos e cursos.
- Inscrição de alunos em cursos.
- Listar todos os cursos nos quais um aluno está inscrito.
- Listar todos os alunos inscritos em um curso específico.

## Tecnologias Utilizadas

- Java 23
- Spring Boot 3.3.5
- Hibernate ORM
- Banco de Dados H2 (em memória)
- Spring Data JPA

## Entidades Principais

1. **Aluno**
   - `id`: Long (Chave Primária)
   - `nome`: String
   - `email`: String
   - `dataCadastro`: LocalDateTime

2. **Curso**
   - `id`: Long (Chave Primária)
   - `nome`: String
   - `descricao`: String
   - `dataCriacao`: LocalDateTime

3. **Inscrição**
   - `id`: Long (Chave Primária)
   - `aluno_id`: Long (Chave Estrangeira para `Aluno`)
   - `curso_id`: Long (Chave Estrangeira para `Curso`)
   - `dataInscricao`: LocalDateTime

## Endpoints RESTful

### Cursos

- **POST /courses/new**: Cadastrar um novo curso.

- **GET /courses/{id}**: Obter um curso pelo ID.

### Alunos

- **POST /students/new**: Cadastrar um novo aluno.

- **GET /students/{id}**: Obter um aluno pelo ID.

### Inscrições

- **POST /enrollments/enroll**: Inscrever um aluno em um curso.

- **GET /enrollments/courses/{studentId}**: Listar todos os cursos de um aluno.

- **GET /enrollments/students/{courseId}**: Listar todos os alunos inscritos em um curso.

## Banco de Dados

O projeto utiliza o banco de dados H2 para persistência, configurado com Hibernate ORM. Ele está pré-configurado para criação automática do esquema.

## DTOs

- **StudentDTO**: Contém os dados do aluno expostos pela API.
- **CourseDTO**: Contém os dados do curso expostos pela API.
- **EnrollmentDTO**: Contém os dados da inscrição, representando a relação entre alunos e cursos.

## Populator

A classe `EnrollmentPopulator` é responsável por converter as entidades (`Student`, `Course`, `Enrollment`) em seus respectivos DTOs.

## Como Rodar a Aplicação

1. Clone este repositório.
2. Navegue até a pasta do projeto e execute a aplicação utilizando Maven:
   ```bash
   mvn spring-boot:run
