# Dev-Diaries WebApp - Week 1 Implementation

A quick, modern, secure, and elegant web application for writing and managing daily personal journal entries.

## 🚀 Quick Start

### Backend (Java Spring Boot)
```bash
cd backend
mvn spring-boot:run
# Runs on http://localhost:8080
```

### Frontend (Angular)
```bash
cd frontend
npm install
npm start
# Runs on http://localhost:4200
```

**📖 For detailed setup instructions, see [PROJECT_SETUP.md](PROJECT_SETUP.md)**

---

## Week 1: Authentication & Basic UI ✅

### ✅ Backend Implementation
- User signup with email, username, password validation
- Secure login with password hashing (BCrypt)
- JWT token generation & validation
- Global exception handling
- CORS configuration for frontend
- MongoDB integration

**Endpoints:**
- `POST /api/auth/signup` - Register new user
- `POST /api/auth/login` - Login user
- `GET /api/auth/health` - Health check

### ✅ Frontend Implementation
- Responsive signup form with validation
- Clean login interface
- Protected dashboard page
- JWT token management in localStorage
- Auto-inject JWT to API requests
- User logout functionality
- Tailwind CSS styling

**Pages:**
- `/auth/signup` - User registration
- `/auth/login` - User login
- `/dashboard` - Main dashboard (protected)

---

## Tech Stack

### Backend
- **Framework**: Spring Boot 3.2
- **Language**: Java 17
- **Security**: Spring Security + JWT (JJWT)
- **Password Hashing**: BCrypt
- **Database**: MongoDB
- **Build Tool**: Maven

### Frontend
- **Framework**: Angular 17
- **Language**: TypeScript
- **Styling**: Tailwind CSS
- **Package Manager**: npm
- **HTTP Client**: HttpClient

### Database
- **MongoDB** - NoSQL database for flexible schema

---

## 📁 Project Structure

```
Dev-Diaries/
├── backend/
│   ├── src/main/java/com/devdiaries/
│   │   ├── auth/           (controllers, services, models)
│   │   ├── config/         (security, CORS)
│   │   └── common/         (exception handling)
│   ├── pom.xml
│   └── README.md
├── frontend/
│   ├── src/app/
│   │   ├── core/           (services, guards, interceptors)
│   │   ├── features/       (auth, dashboard)
│   │   └── shared/         (models, components)
│   ├── package.json
│   ├── angular.json
│   └── README.md
├── PROJECT_SETUP.md        (Detailed setup guide)
└── README.md              (This file)
```

---

## 📋 Features Checklist

### Week 1 ✅
- [x] User registration with validation
- [x] Secure user login
- [x] JWT token management
- [x] Protected dashboard
- [x] Logout functionality
- [x] Clean, responsive UI
- [x] Error handling

### Week 2 (Coming Soon)
- [ ] Create diary entries
- [ ] Edit/delete entries
- [ ] View all entries
- [ ] Rich text editor

### Week 3 (Coming Soon)
- [ ] Calendar view
- [ ] Search functionality
- [ ] Tag/mood system
- [ ] Filter entries

### Future Enhancements
- [ ] Auto-save drafts
- [ ] Timeline view
- [ ] Dark mode
- [ ] OAuth login (Google)
- [ ] Email verification
- [ ] Password reset
- [ ] User profile settings

---

## 🔐 Security Features

- ✅ Password hashing with BCrypt
- ✅ JWT-based stateless authentication
- ✅ Secure token storage (localStorage)
- ✅ CORS protection
- ✅ Input validation (backend & frontend)
- ✅ Protected routes with AuthGuard
- ✅ Automatic token refresh (infrastructure ready)

---

## 🧪 Testing the Application

1. **Visit Frontend**: `http://localhost:4200`
2. **Sign Up**: Create a new account
3. **Login**: Use created credentials
4. **Dashboard**: View your profile and available features
5. **Logout**: Return to login page

---

## 📚 Documentation

- [Backend Setup Guide](backend/README.md) - Java Spring Boot configuration
- [Frontend Setup Guide](frontend/README.md) - Angular application setup
- [Full Project Setup](PROJECT_SETUP.md) - Complete development workflow

---

## 🛠️ Development Commands

### Backend
```bash
cd backend
mvn clean install          # Install dependencies
mvn spring-boot:run        # Run dev server
mvn test                   # Run tests
mvn clean package          # Build production JAR
```

### Frontend
```bash
cd frontend
npm install                # Install dependencies
npm start                  # Run dev server
npm run build-prod         # Build for production
npm test                   # Run tests
```

---

## 🌐 API Documentation

### Authentication Endpoints

#### Signup
```
POST /api/auth/signup
Content-Type: application/json

{
  "email": "user@example.com",
  "username": "johndoe",
  "password": "password123",
  "firstName": "John",
  "lastName": "Doe"
}

Response (201 Created):
{
  "token": "eyJhbGciOiJIUzUxMiJ9...",
  "type": "Bearer",
  "email": "user@example.com",
  "username": "johndoe",
  "firstName": "John",
  "lastName": "Doe",
  "id": "507f1f77bcf86cd799439011"
}
```

#### Login
```
POST /api/auth/login
Content-Type: application/json

{
  "email": "user@example.com",
  "password": "password123"
}

Response (200 OK):
{
  "token": "eyJhbGciOiJIUzUxMiJ9...",
  "type": "Bearer",
  "email": "user@example.com",
  "username": "johndoe",
  "firstName": "John",
  "lastName": "Doe",
  "id": "507f1f77bcf86cd799439011"
}
```

#### Health Check
```
GET /api/auth/health

Response (200 OK):
"Backend is running"
```

---

## 🐛 Troubleshooting

### Backend Issues
- MongoDB not running: `mongod`
- Port 8080 already in use: Change in `application.properties`
- Java version: Ensure Java 17+

### Frontend Issues
- Node modules corrupt: `rm -rf node_modules && npm install`
- Port 4200 in use: `ng serve --port 4300`
- Dependencies missing: `npm install` again

### CORS Errors
- Backend CORS configured for `localhost:4200`
- Check browser console for actual error
- Verify backend is running

---

## 📞 Support

For issues or questions:
1. Check the setup guides
2. Review error messages in console/logs
3. Verify MongoDB is running
4. Ensure both backend and frontend are running

---

## 📝 License

This project is part of the Dev-Diaries application.

---

**Start journaling today! 📔✨**
```

---

## Roadmap

### Week 1
- Authentication system
- Basic UI setup
- Create & view entries

### Week 2
- Edit / Delete entries
- Search functionality
- UI improvements

### Week 3
- Tags & mood tracking
- Calendar view

### Week 4
- Deployment
- Performance optimization

---

## Deployment Options
- Frontend: Vercel / Netlify
- Backend: Render / Railway / AWS
- Database: MongoDB Atlas / Supabase

