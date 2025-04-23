# ⬛ Stage 1: Build the Java application with Gradle
FROM gradle:8.8-jdk21-alpine AS build-stage
WORKDIR /workspace

# Use a clean .dockerignore to minimize context size
COPY . .

# Build the application without running tests
RUN ./gradlew clean build -x test --no-daemon

# ⬛ Stage 2: Extract layers from the Spring Boot fat jar
FROM ghcr.io/graalvm/jdk-community:21 AS extract-stage
WORKDIR /extracted

# Copy the built JAR from the previous stage
COPY --from=build-stage /workspace/build/libs/*.jar app.jar

# Extract Spring Boot layers using layertools
RUN java -Djarmode=layertools -jar app.jar extract

# ⬛ Stage 3: Create a minimal runtime image
FROM ghcr.io/graalvm/jdk-community:21 AS runtime-stage
WORKDIR /app

# Copy layers from extract-stage
COPY --from=extract-stage /extracted/dependencies/ ./
COPY --from=extract-stage /extracted/spring-boot-loader/ ./
COPY --from=extract-stage /extracted/snapshot-dependencies/ ./
COPY --from=extract-stage /extracted/application/ ./

EXPOSE 9000

ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]