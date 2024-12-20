# Stage 1: Build the application
FROM openjdk:8 AS build
WORKDIR /app
COPY . .
RUN apt-get update && apt-get install -y maven
RUN mvn package

# Stage 2: Create the final image
FROM openjdk:8
COPY --from=build /app/target/online_food_app.jar /online_food_app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/online_food_app.jar"]
