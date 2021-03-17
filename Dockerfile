FROM openjdk:8
ADD target/ClassRoom*.jar ClassRoom.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","ClassRoom.jar"]