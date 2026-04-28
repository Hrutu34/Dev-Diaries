# Backend Setup Instructions

## Prerequisites
- Java 17 or higher
- Maven 3.8.0 or higher
- MongoDB running locally or remote connection

## Installation

### 1. Ensure MongoDB is running
```bash
# If MongoDB is installed locally, start it
mongod
```

### 2. Build the project
```bash
cd backend
mvn clean install
```

### 3. Run the application
```bash
mvn spring-boot:run
```

The backend will be available at `http://localhost:8080`

## API Endpoints

### Authentication
- **POST** `/api/auth/signup` - Register new user
  ```json
  {
    "email": "user@example.com",
    "username": "username",
    "password": "password123",
    "firstName": "John",
    "lastName": "Doe"
  }
  ```

- **POST** `/api/auth/login` - Login user
  ```json
  {
    "email": "user@example.com",
    "password": "password123"
  }
  ```

- **GET** `/api/auth/health` - Health check

## Configuration

Edit `src/main/resources/application.properties`:
- `spring.data.mongodb.uri` - MongoDB connection string
- `app.jwtSecret` - JWT secret key (use environment variables in production)
- `app.jwtExpirationMs` - JWT expiration time in milliseconds

## Database

MongoDB collections:
- `users` - Stores user accounts

## Environment Variables (for production)
```
APP_JWT_SECRET=your-secret-key
MONGODB_URI=mongodb://connection-string
```

## Dependencies
- Spring Boot 3.2.0
- Spring Security
- Spring Data MongoDB
- JWT (JJWT)
- Lombok
- BCrypt for password hashing

