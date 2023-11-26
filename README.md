# Education Automation System

### Introduction

An education automation system that allows students to solve tests.

The system provides rest services for CRUD operations for students, quizzes and questions. By adding questions to the
question bank, these questions can be assigned to the desired quizzes. The questions have been added with three answer
options and one correct answer.

Students can be assigned tests on specific dates or indefinitely. Only active tests can be assigned to students.

The test is completed by answering the questions in the test with the `/student/answer-quiz` endpoint. The student's
test results can be queried with the `/student/result-quiz/{studentId}/{quizId}` endpoint.

### Getting Started

#### Versions

* Java 17
* Spring 3.2.0
* Gradle 8.4
* Database -> H2
* Cache -> Caffeine
* Test -> JUnit + Suite

#### Cloning

```$ git clone https://github.com/Isekeroz/education-automation-system.git```

#### Swagger

http://localhost:2323/swagger-ui/index.html

#### Database

http://localhost:2323/h2-console

Username: sa

Password: password



