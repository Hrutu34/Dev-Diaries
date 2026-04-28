# Dev-Diaries Project Setup

Complete setup for Java Spring Boot Backend and Angular Frontend - Authentication & UI Foundation

## Project Overview

**Dev-Diaries** is a secure, modern journaling web application built with:
- **Backend**: Java Spring Boot with JWT Authentication
- **Frontend**: Angular with Tailwind CSS for UI
- **Database**: MongoDB
- **First Week Focus**: User Authentication and Basic Dashboard UI

---

## Backend Setup (Java Spring Boot)

### Prerequisites
- Java 17+
- Maven 3.8+
- MongoDB (local or remote)

### Quick Start

```bash
cd backend

# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
```

**Backend will run on**: `http://localhost:8080`

### Backend Architecture

```
backend/
├── src/main/java/com/devdiaries/
│   ├── auth/
│   │   ├── controller/    # REST endpoints
│   │   ├── service/       # Business logic
│   │   ├── model/         # User entity
│   │   ├── repository/    # Data access
│   │   ├── dto/           # Request/Response DTOs
│   │   └── security/      # JWT utilities
│   ├── config/            # Spring Security & CORS config
│   ├── common/
│   │   └── exception/     # Global exception handling
│   └── DevDiariesApplication.java
└── pom.xml
```

### Authentication Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/auth/signup` | Register new user |
| POST | `/api/auth/login` | Login user |
| GET | `/api/auth/health` | Health check |

### Example API Calls

**Signup:**
```bash
curl -X POST http://localhost:8080/api/auth/signup \
  -H "Content-Type: application/json" \
  -d '{
    "email": "user@example.com",
    "username": "johndoe",
    "password": "password123",
    "firstName": "John",
    "lastName": "Doe"
  }'
```

**Login:**
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "user@example.com",
    "password": "password123"
  }'
```

### Key Features (Week 1)
- ✅ User registration with validation
- ✅ Secure login with password hashing (BCrypt)
- ✅ JWT token generation and validation
- ✅ Stateless authentication
- ✅ Global exception handling
- ✅ CORS support for frontend

---

## Frontend Setup (Angular)

### Prerequisites
- Node.js 18+
- npm (comes with Node.js)
- Angular CLI 17+ (optional, will use npx)

### Quick Start

```bash
cd frontend

# Install dependencies
npm install

# Start development server
npm start
```

**Frontend will run on**: `http://localhost:4200`

### Frontend Architecture

```
frontend/src/
├── app/
│   ├── core/
│   │   ├── services/      # AuthService
│   │   ├── guards/        # AuthGuard
│   │   └── interceptors/  # AuthInterceptor
│   ├── features/
│   │   ├── auth/
│   │   │   ├── login/     # Login component
│   │   │   └── signup/    # Signup component
│   │   └── dashboard/     # Dashboard (after login)
│   ├── shared/
│   │   ├── models/        # TypeScript interfaces
│   │   └── components/    # Reusable components
│   ├── app.component.ts   # Root component
│   ├── app.routes.ts      # Route configuration
│   └── app.config.ts      # App configuration
├── styles.css             # Global Tailwind styles
└── main.ts               # Entry point
```

### Pages (Week 1)

| Route | Component | Description |
|-------|-----------|-------------|
| `/auth/signup` | SignupComponent | User registration |
| `/auth/login` | LoginComponent | User login |
| `/dashboard` | DashboardComponent | Main dashboard (protected) |

### Key Features (Week 1)
- ✅ Beautiful signup form with validation
- ✅ Login form with error handling
- ✅ JWT token management
- ✅ Protected routes with AuthGuard
- ✅ Auto-attach JWT to API requests
- ✅ User profile display
- ✅ Logout functionality
- ✅ Responsive Tailwind CSS UI

### Build for Production

```bash
npm run build-prod
```

---

## Database Configuration

### MongoDB Setup

**Local Installation:**
```bash
# macOS
brew install mongodb-community
brew services start mongodb-community

# Windows (with choco)
choco install mongodb

# or download from https://www.mongodb.com/try/download/community
```

**Connection String** (in `backend/src/main/resources/application.properties`):
```properties
spring.data.mongodb.uri=mongodb://localhost:27017/devdiaries
```

**Cloud (MongoDB Atlas):**
```properties
spring.data.mongodb.uri=mongodb+srv://username:password@cluster.mongodb.net/devdiaries
```

---

## Full Application Flow

1. User visits `http://localhost:4200`
2. Redirected to login page if not authenticated
3. User can **signup** or **login**
4. On successful auth, JWT token is stored in localStorage
5. User is redirected to dashboard
6. All subsequent API requests include JWT token via interceptor
7. AuthGuard protects the dashboard route

---

## Environment Variables

### Backend (Production)
```bash
APP_JWT_SECRET=your-secret-key-here
MONGODB_URI=mongodb://connection-string
SERVER_PORT=8080
```

### Frontend (Production)
```bash
API_URL=https://api.devdiaries.com
```

---

## Development Workflow

### Running Both Services

**Terminal 1 - Backend:**
```bash
cd backend
mvn spring-boot:run
```

**Terminal 2 - Frontend:**
```bash
cd frontend
npm start
```

Visit `http://localhost:4200` in your browser.

---

## Testing Authentication

### Test User Credentials

1. **Sign up a new account**
   - Email: test@example.com
   - Username: testuser
   - Password: test123456
   - First Name: Test
   - Last Name: User

2. **Or use test account directly**
   - Email: demo@example.com
   - Password: demo123456

---

## Common Issues & Solutions

### Backend won't start
- ✅ Check if MongoDB is running: `mongod`
- ✅ Check if port 8080 is available
- ✅ Ensure Java 17+ is installed: `java -version`

### Frontend won't start
- ✅ Delete `node_modules` and run `npm install` again
- ✅ Clear npm cache: `npm cache clean --force`
- ✅ Check Node version: `node -v` (should be 18+)

### CORS errors
- ✅ Backend CORS config allows `localhost:4200`
- ✅ Check `SecurityConfig.java` for allowed origins

### Token not being sent
- ✅ Check browser console for errors
- ✅ Verify `localStorage` has token after login
- ✅ Check `AuthInterceptor` is registered

---

## Next Steps

### Week 2 Features
- [ ] Create diary entries
- [ ] Edit/delete entries
- [ ] Rich text editor
- [ ] List all entries

### Week 3 Features
- [ ] Calendar view
- [ ] Tagging system
- [ ] Search functionality
- [ ] Mood tracking

### Week 4+ Features
- [ ] Auto-save drafts
- [ ] Timeline view
- [ ] Filter by tags/moods
- [ ] Weekly summaries
- [ ] Dark mode toggle
- [ ] User settings

---

## File References

### Backend Documentation
- See [backend/README.md](backend/README.md) for detailed backend setup

### Frontend Documentation
- See [frontend/README.md](frontend/README.md) for detailed frontend setup

---

## Deployment

### Backend (Heroku/AWS)
```bash
# Build JAR
mvn clean package -DskipTests

# Deploy JAR file
```

### Frontend (Vercel/Netlify)
```bash
npm run build-prod
# Deploy dist/dev-diaries-frontend folder
```

---

## Support & Troubleshooting

For issues or questions:
1. Check the respective README.md files
2. Review error messages in console/logs
3. Verify all prerequisites are installed
4. Check MongoDB connection

---

**Happy Journaling! 📔✨**
