# JMS-ActiveMQ-Java
Java message service - P2P - Pub Sub

- Point to point - Producer put message on queue and consume by only one receiver
- Pub / sub - Producer put message on topic and brocast to multiple subscriber

![](/screenshots/Point%20to%20Point.jpg)

![](/screenshots/Publish%20Subscriber.jpg)

#JMS1.0

![](/screenshots/JSM1.0.jpg)

#JMS2.0 ( Popular & easy )

* Simple API - JMSContext - > Connection + Session 
* Create Producer and send message to queue
* Create consumer and receive message from queue
* JSMConsumer and JSMProducer Both implements AutoClosable.

# Message Headers and Properties 

![](/screenshots/Headers.jpg)
![](/screenshots/Properties.jpg)

# P2P Patient Eligibility App 

![](/screenshots/P2P-PatienceEligibilityApp.jpg)

# P2P Async 

![](/screenshots/P2PAsync.jpg)

# Pub Sub Employee Management 

![](/screenshots/Pub-Sub-EmployeeManagement.jpg)

# Three projects
- JmsFundamentals
- P2P
- PubSub

# 5 Types Message

* Text 
* Byte
* Object
* Stream
* Map

# Points
- Set Message Priority
- Message Filtering
- Message Delay
- Message expiration 
- Request - Reply ( Ack ) message
- Message divided in 3 parts ( headers,body, payload )

# Active MQ

- For this project used activemq Artemis
- Create broker with below command 
```
xoxo$ cd apache-artemis-2.14.0/bin
xoxo$ ./artemis create /users/xoxo/documents/apache-artemis-2.14.0/custombroker/
```
- Start broker 
```
xoxo$ cd /Users/xoxo/Documents/apache-artemis-2.14.0/custombroker/bin
xoxo$ ./artemis run
```

http://localhost:8161/admin/ 
User:admin
Password :admin by default
