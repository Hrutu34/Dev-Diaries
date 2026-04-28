# Use official Java runtime as a parent image
FROM eclipse-temurin:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy Maven build output from backend directory
COPY backend/target/dev-diaries-backend-*.jar app.jar

# Expose port
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]

# Health check
HEALTHCHECK --interval=30s --timeout=3s --start-period=40s --retries=3 \
  CMD java -cp . org.springframework.boot.loader.JarLauncher || exit 1
