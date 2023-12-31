
## Technologies


- Java
- Spring Boot
- Spring Data JPA
- Swagger
- MySQL




## Features

- Sign up / Sign in
- Create, update and delete product
- Add product in your wishlist
- Buy product


## Deployment

To install this application, run the following commands:

```bash
  git clone https://github.com/tutungduong/shopme-application.git shopme-application 
  cd shopme-application
```

This will get a copy of the project installed locally. To configure all of its dependencies and start each app, follow the instructions below.

Once MySQL is installed you must configure a username and password. By default the user and password should be `root` . If not, you must configure in the file `application.configure` located in the path `src/main/resources/`.

In the file `application.configure` you must edit the parameters `spring.datasource.username` and `spring.datasource.password` with the values you defined.

Now you just need to create the database where the project will store the information. To create it, just follow the steps below.

```bash
mysql -u {username} -p
```

This command will ask for your MySQL password. Once inside the MySQL monitor you can create the database with the following command.

```SQL
CREATE DATABASE ecommerce_shop;
```

After starting application, go to
```
http://localhost:8080/swagger-ui/index.html
```




## API Documentation


Swagger is responsible to provide a documentation of the API, it break down the endpoints and the models of the application.

![image](https://user-images.githubusercontent.com/77218728/277402837-bdbbf3fb-c8ff-49df-b1ad-ab1f9772d9e1.png)
