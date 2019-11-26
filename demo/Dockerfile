FROM java:8-jdk-alpine
  
WORKDIR /usr/app
COPY . /usr/app/
 
RUN ./gradlew build
 
RUN sh -c 'touch build/libs/gs-spring-boot-0.1.0.jar'
ENTRYPOINT ["java","-jar","build/libs/gs-spring-boot-0.1.0.jar"]