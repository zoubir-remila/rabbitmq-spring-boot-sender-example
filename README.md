# Event Messaging for Micro-services with Spring Boot and RabbitMQ

In a micro-service environment or any other distributed system you may come upon the requirement to exchange events between services. This article shows how to implement a messaging solution with RabbitMQ.

## Getting Started

In this project We Create an event producer which is pretty straightforward. We make use of the RabbitTemplate provided by the AMQP starter and call the method convertAndSend() to send an event. The event in the code example only contains a String. If the message should contain a complex object, you can make use of message converters.

The RabbitTemplate automatically uses the connection settings provided in the application.properties.

Including the Spring Boot AMQP Starter 
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-amqp</artifactId>
</dependency>
```
Our Service
```
@Service
public class ServiceSender {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceSender.class);
    @Autowired
    private Queue queue;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    ObjectMapper objectMapper;

    public void sendSimpleMessage() {
        UserDto user = new UserDto("JHON", "DOE", 99);
        try {
            rabbitTemplate.convertAndSend(queue.getName(), objectMapper.writeValueAsString(user));
        } catch (JsonProcessingException e) {
            LOGGER.error("An Error Occurred the Process");
        }

    }
}
```

Adding configuration of host where rabbitMq server is running  

```
spring.rabbitmq.host=127.0.0.1
spring.rabbitmq.port=5672
spring.rabbitmq.username=guest
spring.rabbitmq.password=guest
```

In RabbitMq server, add a new queue with the same name as in the properties

```
queue.name=queu1
```

## Prerequisites

* [RabbitMQ](https://www.rabbitmq.com/download.html) - Download & install RabbitMq server in your machine. 

## Installing

At first, you have to clone the project into your workspace

```
git clone ...
```

Run maven clean install

```
mvn clean install
```

Run the  application

## Process

After running the application, go to your browser and put the  url : 

```
http://localhost:8080/rabbitmq/sender
```

If you haven't run your receiver(Consumer), you will find a message in the queue at the server RabbitMq.


## Author

* **Zoubir REMILA** 

## Receiver

Link to our Receiver [rabbitmq-receiver](git@gitlab.com:zoubir/rabbitmq-receiver.git)
