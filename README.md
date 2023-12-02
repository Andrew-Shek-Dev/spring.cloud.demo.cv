# Java Spring Cloud with Microservices under AWS
The course will demo how to use Java Spring Cloud building Microservice architectures featuring AWS as Cloud Service Provider.

## Overall Exercises
We will develope following microservices in the lectures (with Java Spring Boot):
- Local Weather
- Login
- Take Attendance
- Apply Leave

Next, above spring boot applications(microservices) are managed under `Spring Cloud` Components such as `API Gateway`,`Config Server`, `Authorization Server(SSO)`,`Service Registry` and `Stream`, so that Microservice architectures is built under `Spring Cloud` which co-operating AWS Features such as `Cognito`, `Lambda`, `AppSync` and `DynamoDB`.

Finally, testing the Microservice architectures, we will deploy to `Cloud Foundry` as testing purpose.

## Lesson#1 Java Fundamental (4 hours)
- OOP and IDE (IntelliJ)
- Java Advance Knowledge: Inversion of Control(IoC)
    * Basic Annotations :`@Configuration`,`@Bean`,`@Component`(`@RestController`,`@Service` and `@Repository`) and`@Autowired`
    * Advanced Annotations : `@SpringBootApplication` - `@EnableAutoConfiguration`,`@ComponentScan` and `@Configuration`
    * Many Annotations will be appeared in coming Lectures!
- Demo Example : Get Local Weather Spring Boot Application

### Lesson#1 Exercise - Login System Spring Boot Application 
To create the Spring Boot Application with login page (Tips: please use `spring-boot-starter-web` and `spring-boot-starter-security`).

## Lesson#2 Concept of Microservice Architecture and Spring Cloud (4 hours)
- 12-factors
- Spring Cloud
- Config Server

### Lesson#2 Exercise - Config Client
To update the Local Weather Spring Boot Application in Lesson 1, let it can get the weather information from the config server.

## Lesson#3 Login by SSO (4 hours)
- Authorization Server
- AWS Cognito

### Lesson#3 Exercise
To restrict the access of the Local Weather Spring Boot Application, please update following spring boot application:
- Login System Spring Boot Application at Lesson 1
- Local Weather Spring Boot Application at Lesson 2

## Lesson#4 Microservice Deployment (4 hours)
- Cloud Foundry
- Demo : Deploy Login System Spring Boot Application to Cloud Foundry.

### Lesson 4 Exercise
- Deploy the Local Weather Spring Boot Application to Cloud Foundry.

## Lesson#5 Service Discovery and Routing under Microservice Architecture (4 hours)
- Service Registry
- API Gateway
- Demo : register the Login System Spring Boot Application

### Lesson 5 Exercise
- Setup Service and API of Local Weather Spring Boot Application and deploy it.

## Lesson#6 Communication between Microservices (4 hours)
- Stream
- Demo : Create Apply Leave Web Service and Approval Leave Service

### Lesson 6 Exercise
- To create Spring Applications (microservices) which Taking Attendance and Storing Attendance and deploy it.

## Lesson#7 Introduction to GraphQL (8 hours)
- GraphQL Concept
- GraphQL Client
- GraphQL Server

### Lesson#7 Exercise
- Writing GraphQL Client in Spring Boot Application

## Lesson#8 GraphQL on AWS (8 hours)
- AWS Lambda
- Spring Cloud Function
- AWS DynamoDB
- AWS AppSync
- Demo : Create Spring Boot Application showing Applied Leave Records.

### Lesson 8 Exercise
- Writing Spring Boot Application showing Attending Records and deploy it.


## Spring Cloud Overall Pictures

                                |--->`AWS Lambda, AppSync and DynamoDB`#
                                |                                 |--->`Circuit Breaker(Failover Handling)`
                                |                                 |--->`Stream (Messaging Platform)`#
                                |                                 |--->`Distributed Tracing`
`Client`=>`API Gateway*`#=>`Spring Boot Apps(Microservices)`# --->|--->`Service Registry (Service Discovery)`#
          `(with Load-Balancing)`                                 |--->`Config Server`#
                                                                  |--->`SSO(Authorization Server)`# -> 
                                                                       `AWS Cognito`#
                                                                  |--->`Sleuth (Monitoring)`
                                                                  |--->`Contract (Testing)`
            |== deploy to ==> `Cloud Foundry`#
                                    |== deploy to ==> `AWS EC2`

* Zuul2 is faded-out because of Zuul2 longer development time, so `API Gateway` is recommended now. Java Spring is still no road-map on integrating Zuul2 into Spring Cloud now.
<sup>#</sup> This component will be taught in the lecture.

### Distributed/versioned configuration - Spring Cloud Config
`Spring Cloud Config` can store and distribute configuration across multiple micro-services and environment.
The configuration will be stored under `Git` repo. Although it support configuration format such as Environment, PropertySource, or @Value,
it can be used in any environment and programming languages.

References:
https://blog.csdn.net/qq_40837310/article/details/106587158
https://docs.spring.io/spring-cloud-config/docs/current/reference/html/
https://github.com/eugenp/tutorials/tree/master/spring-cloud-modules/spring-cloud-config

### Spring Cloud AWS
#### TBC
#### Access AWS S3 Storage
TBC
### TBC


## References
- https://www.1ju.org/spring-cloud/index