# Blog Management API

The Blog Management API is a RESTful service built using Spring Boot and PostgreSQL. It allows users to manage blog posts and their associated comments, as well as interact with features like "likes" on posts.
I Tried to deploy it but i got errors while deploying





## Overview

This API provides the following core functionalities:
- **Blog Posts: Create, retrieve, update, delete, and like blog posts.
- **Comments: Add, update, and delete comments on blog posts.
- **Partial Updates: When updating a blog post, if only one field (e.g., title) is provided, the other field (e.g., content) retains its previous value.

## Technologies

- *Java 17
- **Spring Boot**
- **Spring Data JPA
- **PostgreSQL
- **Maven
- **Jackson for json serialization
- git and github for version control


DataBse Connection settings(application.yml) (postgresql)
**spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/own_app
    username: postgres
    password: rewansh@12345
    driver-class-name: org.postgresql.Driver
  jpa:
    properties:
      hibernate:
        dialect: org.hibernate.dialect.PostgreSQLDialect
    hibernate:
      ddl-auto: update
    show-sql: true

server:
  port:
    8080**
**Request and Response Examples tested on Postman**

**Create a Blog Post


URL: POST /api/blogposts
Headers:
Content-Type: application/json

Request Body:(json-raw)
{
    "title": "My First Blog Post",
    "content": "This is the content of my first blog post."
}

Response:
Status: 201 Created

Body:

{
    "id": 1,
    "title": "My First Blog Post",
    "content": "This is the content of my first blog post.",
    "likes": 0,
    "comments": []
}


** Get All Blog Posts

URL: GET /api/blogposts

Response:
Status: 200 OK
Body:


    {
        "id": 1,
        "title": "My First Blog Post",
        "content": "This is the content of my first blog post.",
        "likes": 0,
        "comments": []
    },
    {
        "id": 2,
        "title": "Another Post",
        "content": "Additional content.",
        "likes": 3,
        "comments": []
    }

    **Get a Single Blog Post

    
URL: GET /api/blogposts/{id}
Example: GET /api/blogposts/1
Response:
Status: 200 OK
Body:


{
    "id": 1,
    "title": "My First Blog Post",
    "content": "This is the content of my first blog post.",
    "likes": 0,
    "comments": [
        {
            "content": "Great post!"
        }]



  **Update a Blog Post

  
URL: PUT /api/blogposts/{id}
Example: PUT /api/blogposts/1
Headers:
Content-Type: application/json
Request Body: (Only fields to be updated are provided; others retain their values)

{
    "title": "Updated Blog Title"
}
Response:
Status: 200 OK
Body:

{
    "id": 1,
    "title": "Updated Blog Title",
    "content": "This is the content of my first blog post.",
    "likes": 0,
    "comments": [
        {
            "content": "Great post!"
        }
    
}

**Delete a Blog Post

URL: DELETE /api/blogposts/{id}
Example: DELETE /api/blogposts/1
Response:
Status: 204 No Content

**Like a Blog Post

URL: POST /api/blogposts/{id}/like
Example: POST /api/blogposts/1/like
Response:
Status: 200 OK
Body:

{
    "id": 1,
    "title": "My First Blog Post",
    "content": "This is the content of my first blog post.",
    "likes": 1,
    "comments": []
}
    

**Add a Comment to a Blog Post

URL: POST /api/blogposts/{postId}/comments
Example: POST /api/blogposts/1/comments
Headers:
Content-Type: application/json
Request Body:

{
    "content": "This is a comment on the blog post."
}

Response:
Status: 201 Created
Body:

{
    "id": 1,
    "content": "This is a comment on the blog post."
}


**Update a Comment


URL: PUT /api/blogposts/{postId}/comments/{commentId}

Example: PUT /api/blogposts/1/comments/1
Headers:
Content-Type: application/json
Request Body:

{
    "content": "Updated comment content."
}
Response:
Status: 200 OK
Body:

{
    "id": 1,
    "content": "Updated comment content."
}

** Delete a Comment


URL: DELETE /api/blogposts/{postId}/comments/{commentId}

Example: DELETE /api/blogposts/1/comments/1
Response:
Status: 204 No Content









