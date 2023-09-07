# twttrr
A backend clone of twitter (X !) made with Java.

## Microservices
![image](https://github.com/rashm1n/twttrr/assets/33457890/f5aa8ec7-ade6-4705-aa7d-dfcc0d016ac5)


## tweet-write-service

A Java microservice written using Spring Boot/ Spring Cloud/ Kafka/ Oracle. It exposes several REST endpoints via `/tweets/` request mapping.

#### Endpoints

- `POST /tweets` - Create a tweet
- `PUT /tweets` - Editing a tweet
- `GET /tweets/{uuid}` -  Get tweet via tweet uuid.
- `DELETE /tweets/{uuid}` - Delete a tweet via the uuid.

**Kafka** is used to send events to other supporting microservices. - Via the dependancy `org.springframework.kafka.spring-kafka`

**Oracle Database 23C** is used as a data store for storing tweets. - - Via the dependancy `com.oracle.database.jdbc.ojdbc11`

## follower-service

A Java microservice written using Spring Boot/ Spring Cloud/ Redis. Exposes several REST endpoints via `/followers/` request mapping. This service deals with the creation/deletion and fetching of user followers.

#### Endpoints

- `POST /followers/` - Adding a new follower to a user
- `DELETE /followers/{user-id}/{follower-id}` - Deleting/Removing a follower from a user
- `GET /followers/{user-id}` - Aquirinf a list of followers of a certain user.

**Redis** is used to store the user followers.

## fanout-service

A Java microservice written using Spring Boot/ Spring Cloud/ Kafka. Implements a set of Kafka listeners which listen to a set of Kafka topics for new tweet activity, and distributes/fans-out the tweets accordingly. 

When a new tweet is created the message is recieved via Kafka from the tweet-write-service. Then the follower-service is called internally via HTTP to aquire the user followers. After the user-followers are fetched, the received tweet is distributed to the follower timelines via the timeline-service.

## timeline-service

A Java microservice written using Spring Boot/ Spring Cloud/ Kafka/ Redis. Implements a set of Kafka listeners which listens to the fanout queue for new tweet fanouts. User timelines are implemented in Redis as Sorted Sets. When a fanout message is received the user timeline is altered accordingly.

## timeline-mixer service

A Java microservice written using Spring Boot/ Spring Cloud. Implements a set of REST endpoints for getting the user timeline feeds and user profile-feeds.
