# 🧱 Microservices App (Spring Boot + Docker Compose)

This project demonstrates a multi-module microservices architecture using Spring Boot, Gradle, Docker, and Docker Compose. It includes the following services:

- ✅ **Accounts Service**
- ✅ **Cards Service**
- ✅ **Loans Service**

---

## 🚀 Tech Stack

- Java 21
- Spring Boot
- Gradle (wrapper + multi-module)
- Docker (multi-stage build)
- Docker Compose

---

## 🛠 Project Structure

```
micro-jdk21/
├── accounts/           # Accounts microservice
├── cards/              # Cards microservice
├── loans/              # Loans microservice
├── docker-compose.yml
├── runbuild-compose.yml
├── README.md
```

---

## 📦 Build Docker Images

You can either use `docker-compose` to build or build them manually.

### ✅ Option 1: Auto Build & Run

```bash
docker compose -f docker-compose.build.yml up --build
```

> This uses `build:` in `docker-compose.yml` and runs all services.

### ✅ Option 2: Manual Build + Run

```bash
# Build services
docker build -t radomkhoem/accounts-jdk21 ./accounts
docker build -t radomkhoem/cards-jdk21 ./cards
docker build -t radomkhoem/loans-jdk21 ./loans

# Run services
docker compose -f docker-compose.yml up
```

---

## 🌐 Service URLs

| Service   | Port     | URL                          |
|-----------|----------|------------------------------|
| Accounts  | `8080`   | http://localhost:8080        |
| Cards     | `9000`   | http://localhost:9000        |
| Loans     | `8090`   | http://localhost:8090        |

---

## 📄 Sample Dockerfile (Multi-Stage)

Each service uses a multi-stage Dockerfile for optimized image size:

```Dockerfile
# Stage 1: Build
FROM gradle:8.8-jdk21-alpine AS build
WORKDIR /workspace
COPY . .
RUN ./gradlew clean build -x test --no-daemon

# Stage 2: Extract
FROM ghcr.io/graalvm/jdk-community:21 AS extract
WORKDIR /extracted
COPY --from=build /workspace/build/libs/*.jar app.jar
RUN java -Djarmode=layertools -jar app.jar extract

# Stage 3: Runtime
FROM ghcr.io/graalvm/jdk-community:21
WORKDIR /app
COPY --from=extract /extracted/dependencies/ ./
COPY --from=extract /extracted/spring-boot-loader/ ./
COPY --from=extract /extracted/snapshot-dependencies/ ./
COPY --from=extract /extracted/application/ ./
EXPOSE 8080
ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]
```

---

## 🧪 Healthcheck (Optional)

You can configure healthchecks for each container if needed.

---

## 🧾 License

This project is open-source and free to use for educational and commercial purposes.

---

## ✍️ Author

Made with 💻 by **Radom Khoem**

```

---

Let me know if you want to:
- add MongoDB or PostgreSQL service
- include Swagger docs
- automate builds with GitHub Actions or Jenkins