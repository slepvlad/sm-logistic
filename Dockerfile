# ---- Build Stage ----
FROM eclipse-temurin:25-jdk AS builder
WORKDIR /app
COPY gradlew build.gradle settings.gradle ./
COPY gradle ./gradle
# Download dependencies first (layer caching)
RUN ./gradlew dependencies -q
COPY src ./src
RUN ./gradlew bootJar -x test -q

# ---- Run Stage ----
FROM eclipse-temurin:25-jre-alpine
WORKDIR /app

# Non-root user for security
RUN addgroup -S spring && adduser -S spring -G spring
USER spring

COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
