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


## Requirements

Create store application. Design your API in RESTful manner. In the examples you get samples of JSONs that your api should receive or send. 
Your store has to support following methods: 

- Register new user. Example request: {“email”:”my@email.com”, “password”:”123”}
- Respond with an appropriate HTTP codes (200 for ok, 409 for existing user)
- Your app must not store password as plain text, use some good approach to identify user.
- Login into system. Example request: {“email”:”my@email.com”, “password”:”123”}
- Respond with JSON containing sessionId.
*(optional) Think about preventing an intruder from bruteforcing. 
*(optional) Reset password.
- Get all products in store.
- Respond with JSON list of items you have, e.g.: 
{“id”:”2411”, “title”:”Nail gun”, “available”:8, “price”: “23.95”} 
- Add item to cart. Example request: {“id”:”363”, “quantity”:”2”}
- Allow adding only one position at a time. If you don’t have this quantity in store - respond with an error. The information has to be session-scoped: once session expires - user will get new empty cart.
- Display your cart content.
- Respond with list of product names with their quantities added. Calculate subtotal. Assign an ordinal to each cart item. 
- Remove an item from user’s cart.
- Modify cart item. Example request: {“id”:2, quantity: 3} - user should be able to modify number of some items in his cart.
Checkout: verify your prices in cart, ensure you still have desired amount of goods. If all is good - send a user confirmation about successful order. 
*(optional) Cancel order: return all products from order back to available status. 
*(optional) Get user’s order list. Should contain order id, date, total, status.
