### How to run in local

1. Change the Application Properties (E.g. username/password of DB) present in `resources/application.properties` according to your local mysql-server.
1. Go to application.properties and comment / uncomment the corresponding front-end url and enter the STRIPE API Keys
1. Create a database called `ecommerce` with `CHARACTER SET utf8mb4` and `COLLATE utf8mb4_0900_ai_ci`. MariaDB does not support `COLLATE utf8mb4_0900_ai_ci`. So, if you are using MariaDB, open `database-dump.sql` file and replace `COLLATE utf8mb4_0900_ai_ci` with `COLLATE utf8mb4_general_ci`
1. Import `database-dump.sql` in it.
1. To run the application, run the command `sh run.sh` i.e. execute the `run.sh` file.
1. After starting application, go to http://localhost:8080/swagger-ui/index.html
