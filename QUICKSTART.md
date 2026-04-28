# Quick Start Scripts

## One-Command Setup (requires Docker & Docker Compose)

### Start Development Environment
```bash
docker-compose up -d
```

This will:
- Start MongoDB on port 27017
- Start Spring Boot backend on port 8080
- Create dev-diaries network
- Set up volumes for data persistence

### Stop Development Environment
```bash
docker-compose down
```

### View Logs
```bash
docker-compose logs -f
docker-compose logs backend
docker-compose logs mongodb
```

### Rebuild Services
```bash
docker-compose down
docker-compose build --no-cache
docker-compose up -d
```

---

## Manual Setup (Without Docker)

### Prerequisites
- Java 17+
- Node.js 18+
- MongoDB 7.0+
- Maven 3.8+

### Step 1: Start MongoDB
```bash
# macOS
brew services start mongodb-community

# Windows (if installed with choco)
net start MongoDB

# Linux
sudo systemctl start mongod

# Or if running MongoDB Docker only
docker run -d -p 27017:27017 --name mongodb mongo:7.0
```

### Step 2: Build & Run Backend
```bash
cd backend
mvn clean install
mvn spring-boot:run
```

Backend will be available at: http://localhost:8080

### Step 3: Install & Run Frontend
```bash
cd frontend
npm install
npm start
```

Frontend will be available at: http://localhost:4200

---

## Development Workflow

### Terminal 1 - MongoDB (if not using Docker)
```bash
mongod
```

### Terminal 2 - Backend
```bash
cd backend
mvn spring-boot:run
```

### Terminal 3 - Frontend
```bash
cd frontend
npm start
```

### Terminal 4 - Optional: Watch Backend Changes
```bash
cd backend
mvn clean
mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Dspring.devtools.restart.enabled=true"
```

---

## Common Scripts

### Clean Build
```bash
# Backend
cd backend
mvn clean install -DskipTests

# Frontend
cd frontend
rm -rf node_modules dist
npm install
```

### Build for Production
```bash
# Backend
cd backend
mvn clean package -DskipTests

# Frontend
cd frontend
npm run build-prod
```

### Run Tests
```bash
# Backend
cd backend
mvn test

# Frontend
cd frontend
npm test
```

### Lint & Format
```bash
# Frontend
cd frontend
npm run lint
```

---

## Docker Commands (Advanced)

### Build Individual Services
```bash
# Build backend image
docker build -t devdiaries-backend:latest .

# Build with specific tag
docker build -t devdiaries-backend:1.0.0 .
```

### Run Individual Containers
```bash
# Run MongoDB only
docker run -d -p 27017:27017 --name devdiaries-db mongo:7.0

# Run Backend only
docker run -d \
  -p 8080:8080 \
  -e SPRING_DATA_MONGODB_URI=mongodb://host.docker.internal:27017/devdiaries \
  --name devdiaries-api \
  devdiaries-backend:latest
```

### Docker Compose Advanced
```bash
# Run specific service
docker-compose up mongodb

# Rebuild and run
docker-compose up --build

# Run in background
docker-compose up -d

# Kill and remove volumes
docker-compose down -v

# Scale services (if load balanced)
docker-compose up -d --scale backend=3
```

---

## Troubleshooting Scripts

### Reset Development Environment
```bash
# Full reset with Docker
docker-compose down -v
docker-compose build --no-cache
docker-compose up -d

# Or manually
# 1. Stop all services
# 2. Delete node_modules and dist
# 3. Delete target directory
# 4. Restart from scratch
```

### Check Service Health
```bash
# Backend health
curl http://localhost:8080/api/auth/health

# MongoDB connection
mongosh localhost:27017

# Frontend (if running)
curl http://localhost:4200
```

### View Logs
```bash
# Docker compose logs
docker-compose logs

# Backend logs
docker logs devdiaries-backend

# MongoDB logs
docker logs devdiaries-mongodb
```

### Clean Docker Resources
```bash
# Remove stopped containers
docker container prune

# Remove unused images
docker image prune

# Remove unused volumes
docker volume prune

# Full cleanup
docker system prune -a
```

---

## Environment File

Create `.env` in project root for local configuration:
```bash
cp .env.example .env
# Edit .env with your local settings
```

