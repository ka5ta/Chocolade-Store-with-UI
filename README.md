# Chocolade-Store
Test project. Simple app for ordering my favorite chocolate. I didn't concentrate on front-end because main purpose was to have working backend.
## Technologies
* Spring Boot
* H2 database
* Thymeleaf + JS
* JUnit 5
* bootstrap

## Endpoints
1. [Register](http://localhost:8080/shopping/register)
2. [Signin](http://localhost:8080/shopping/signin)
3. [Basket](http://localhost:8080/shopping)
4. [Change Password](http://localhost:8080/shopping/password)
5. [Logout](http://localhost:8080/shopping/logout)

## Instructions
Just run the project and navigate to 'http://localhost:8080/shopping/register'. Register and navigate through pages. Create Order. Enjoy.

## Database
h2 db in memory is loaded from 'import.sql' file. To login to database: http://localhost:8080/h2-console/
```
user: sa
password: pass
```
