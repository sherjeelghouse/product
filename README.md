# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.2.6.RELEASE/gradle-plugin/reference/html/)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.2.6.RELEASE/reference/htmlsingle/#boot-features-developing-web-applications)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)

### Additional Links
These additional references should also help you:

* [Gradle Build Scans â€“ insights for your project's build](https://scans.gradle.com#gradle)

Demonstrates a simple Spring Boot Microservce using Kotlin, JPA, H2 database, Kafka. It demonstrates
various patterns like Microservice Config, Messaging, DDD
The code is build as Docker container
and deployed to Kubernetes

1. Checkout project
   
   git clone https://github.com/sherjeelghouse/product.git

2. Build

   cd product
  ./gradlew clean build
  
3. Start Kafka Cluster using wurstmeister kafka image

   docker-compose -f docker-compose-wurstmeister.yml up -d

4. Build docker image

   docker build . -t  sherjeelghouse/product

5. Run docker image

   docker run  -p 8080:8080 -d  sherjeelghouse/product

6. Test the app

   Get existing product
   http://localhost:8080/api/v1/product/1
   
   Add a new product
   curl -X POST 'http://localhost:8080/api/v1/product' \
   -H 'Content-Type: application/json' \
   -d '{ 
     "name": "Goldfish", 
     "desc": "TBD"
   }'
   
   Update a Product
   curl -X PUT 'http://localhost:8080/api/v1/product/3' \
      -H 'Content-Type: application/json' \
      -d '{ 
        "name": "Goldfish", 
        "desc": "Crackers"
      }'
   
   Delete a Product
   curl -X DELETE 'http://localhost:8080/api/v1/product/3'
   
   Verify Kafka Producer Publishes Message
   
   Verify Kafka Consumer Receives Message

7. Deploy to Kubernetes using Docker stack (Mac Docker Desktop)

   export DOCKER_ORCHESTRATOR=kubernetes
   docker stack deploy web-platform -c docker-compose.yml
   
   Check Kubernetes Service is up
   kubectl get svc
   
   Check the Pods are running
   kubectl get pods
   
   Test the app
   http://localhost:8080/product/1
   
8. Deploy to Kubernetes using kubectl (Optional)

   First remove the Kubernetes stack:
   docker stack rm web-platform

   kubectl apply -f manifest.yml
   
   Check Kubernetes Service is up
   kubectl get svc
   
   Check the Pods are running
   kubectl get pods
   
   Test the app
   http://localhost:8080/product/1
