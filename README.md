# Recipe Management System API

## Framework and Language
* ### Framework
    * Spring Boot
* ### Language
    * Java

## Data Flow
### 1. Controller

* UserController
  * signup
  * signin
  * signout
  * postRecipe
  * getRecipes
  * getMyRecipes
  * deleteRecipe
  * addComment
  * getComment
  * removeComment
  * updateRecipe

### 2. Services
* CommentService
  * clearCommentsByRecipe
  * addComment
  * findCommentById
  * removeCommentById
  * getCommentOnRecipeId

  
* PasswordEncrypt
    * encrypt


* RecipeService
  * postRecipe
  * getRecipeById
  * getRecipes
  * getMyRecipes
  * deleteRecipeById
  * updateRecipe


* TokenService
    * createToken
    * deleteToken
    * authenticate


* UserService
  * signup
  * signIn
  * signOut
  * postRecipe
  * getMyRecipes
  * deleteRecipe
  * addComment
  * removeComment
  * authorizeCommentRemover
  * updateRecipe
  * getCommentByRecipeId

### 3. Repository

* CommentRepo
  * findByRecipe

* IRecipeRepo
  * findByPostOwner
  * updateTitle
  * updateIngredients
  * updateInstructions

* ITokenRepo
    * findFirstByTokenValue


* IUserRepo
    * findFirstByEmail


### 4. Data Source
```java
  spring.datasource.url=jdbc:mysql://localhost:3306/recipe
  spring.datasource.username=kakarot
  spring.datasource.password=1234
  spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
  spring.jpa.hibernate.ddl-auto=update

  spring.jpa.properties.hibernate.show_sql=true
  spring.jpa.properties.hibernate.use_sql_comments=true
  spring.jpa.properties.hibernate.format_sql=true
```

## Data Sturcture
* List

## Project Summary
A Recipe Management System API is a software interface that allows developers to interact with and manipulate data related to recipes within a Recipe Management System. This API enables various functions and operations, such as creating, retrieving, updating, and deleting recipes, as well as searching and filtering recipes based on specific criteria.

Recipe Management System API is a powerful tool that simplifies the development of applications and services focused on recipe management and cooking, allowing users to efficiently organize, discover, and share their favorite recipes.