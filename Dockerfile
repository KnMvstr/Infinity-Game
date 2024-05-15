# Use OpenJDK 21 JDK for building the application
FROM openjdk:21-jdk-slim as build
WORKDIR /app

# Set locale to handle UTF-8
RUN apt-get update && apt-get install -y locales && locale-gen en_US.UTF-8
ENV LANG en_US.UTF-8
ENV LANGUAGE en_US:en
ENV LC_ALL en_US.UTF-8

# Install Maven using apt-get instead of apk
RUN apt-get update && \
    apt-get install -y maven

WORKDIR /app
COPY . /app

# Copy the Maven wrapper and other necessary files
COPY mvnw pom.xml ./
COPY .mvn .mvn/
COPY src src/

# Run Maven build, skip tests to speed it up
RUN mvn package -Dmaven.test.skip=true

# Use OpenJDK 21 for running the application
FROM openjdk:21-jdk-slim
WORKDIR /app

# Copy only the WAR from the build stage
COPY --from=build /app/target/*.war app.war

# Run the WAR file
CMD ["java", "-jar", "app.war"]