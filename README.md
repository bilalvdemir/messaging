# Spring Boot + Angular Messaging Example MVC
Used simple pojo for showing MongDB CRUD operations and validations

## Getting Started
This project includes:
 - CREATE, Retrieve, Update, Delete Operations
 - MVC Pattern
 - Web Service
 - Logger
 - Exceptions
 - Global exception handler
 - Cassandra DB
 
# Used Annotations

 - @Service
 - @RestController
 - @ResponseBody
 - @GetMapping
 - @PostMapping
 - @PutMapping
 - @DeleteMapping
 - @ControllerAdvice
 - @ExceptionHandler
 - @Table
 - @Column
 - @PrimaryKey

# Start Application With Docker
 - Learn Docker version
   ```
   docker -v
   ```
   if docker dont installed: 
   * [Docker For Windows](https://docs.docker.com/docker-for-windows/install/) - Download Setup
   
 - Build docker in root directory
   ```
   docker build -f Dockerfile -t spring-angular-crud-example .
   ```
 - Show docker builded images
   ```
   docker images
   ```
 - Run docker image with exposed port 
   ```
   docker run -p 9090:9090 spring-angular-crud-example
   ```

## Contributing

Please read [CHANGELOG.md](https://github.com/bilalvdemir/messaging/blob/master/CHANGELOG.md) for details on our code of conduct, and the process for submitting pull requests to us.

## Authors

* **Bilal Demir** - *Initial work* - [bilalvdemir](https://github.com/bilalvdemir)
