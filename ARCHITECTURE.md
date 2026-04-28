# Dev-Diaries Architecture

## System Architecture Diagram

```
┌─────────────────────────────────────────────────────────────────────┐
│                         Dev-Diaries System                          │
└─────────────────────────────────────────────────────────────────────┘

                    CLIENT TIER
┌──────────────────────────────────────┐
│          Angular Frontend             │
│      (http://localhost:4200)         │
├──────────────────────────────────────┤
│  Components:                         │
│  - LoginComponent                    │
│  - SignupComponent                   │
│  - DashboardComponent                │
└────────────────┬─────────────────────┘
                 │
                 │ (HTTP/HTTPS)
                 │ JWT Token in Header
                 │
         ┌───────▼────────┐
         │  AuthInterceptor│
         │  + JWT Attach   │
         └────────┬────────┘
                 │
    API TIER    │
┌──────────────▼──────────────────────┐
│      Spring Boot Backend             │
│    (http://localhost:8080)          │
├──────────────────────────────────────┤
│  Controllers:                        │
│  - AuthController (/api/auth)        │
├──────────────────────────────────────┤
│  Services:                           │
│  - AuthService                       │
├──────────────────────────────────────┤
│  Security Layer:                     │
│  - JwtTokenProvider                  │
│  - JwtAuthenticationFilter           │
│  - SecurityConfig                    │
├──────────────────────────────────────┤
│  Exception Handling:                 │
│  - GlobalExceptionHandler            │
└────────────────┬─────────────────────┘
                 │
                 │ (MongoDB Protocol)
                 │
     DATABASE TIER
    ▼─────────────────────────────────
  ┌──────────────────────────────────┐
  │      MongoDB Database             │
  │   (localhost:27017)              │
  ├──────────────────────────────────┤
  │  Collections:                    │
  │  - users (User documents)        │
  │  - entries (Diary entries)       │
  │  - tags (Category tags)          │
  └──────────────────────────────────┘
```

---

## Data Flow: User Authentication

```
┌──────────────────────────────────────────────────────────────────────┐
│                    SIGNUP/LOGIN FLOW                                │
└──────────────────────────────────────────────────────────────────────┘

1. SIGNUP REQUEST
   User Form → POST /api/auth/signup
   {email, username, password, firstName, lastName}
                  │
                  ▼
   AuthController.signup()
                  │
                  ├─► Check if user exists (UserRepository)
                  │
                  ├─► Validate input (Spring Validation)
                  │
                  ├─► Hash password (BCryptPasswordEncoder)
                  │
                  ├─► Save user to MongoDB (UserRepository.save())
                  │
                  ├─► Generate JWT token (JwtTokenProvider)
                  │
                  ▼
   Return AuthResponse {token, user details}
                  │
                  ▼
   Frontend: Store token in localStorage


2. LOGIN REQUEST
   User Form → POST /api/auth/login
   {email, password}
                  │
                  ▼
   AuthController.login()
                  │
                  ├─► Find user by email (UserRepository)
                  │
                  ├─► Verify password (BCryptPasswordEncoder.matches)
                  │
                  ├─► Generate JWT token (JwtTokenProvider)
                  │
                  ▼
   Return AuthResponse {token, user details}
                  │
                  ▼
   Frontend: Store token in localStorage


3. AUTHENTICATED REQUEST
   API Call with JWT header
   GET /api/protected
   Authorization: Bearer {JWT_TOKEN}
                  │
                  ▼
   JwtAuthenticationFilter
                  │
                  ├─► Extract token from header
                  │
                  ├─► Validate token (JwtTokenProvider)
                  │
                  ├─► Set SecurityContext with user
                  │
                  ▼
   Request proceeds to protected endpoint


4. LOGOUT
   Frontend: Remove token from localStorage
   Clear user state
   Redirect to /auth/login
```

---

## Component Interaction Diagram

```
Frontend                          Backend
─────────                         ───────

┌─────────────┐
│ LoginComponent
└──────┬──────┘
       │ calls
       ▼
┌──────────────┐
│ AuthService  │──────► POST /api/auth/login ──► AuthController
└──────┬───────┘                                        │
       │                                                ├─► AuthService
       │                                                │
       │                                                ├─► JwtTokenProvider
       │                                                │
       │◄─────── Response {token, user} ──────────────┘
       │
       ├─► Store token in localStorage
       │
       ├─► Set user$ BehaviorSubject
       │
       └─► AuthGuard checks isAuthenticated()
           │
           └─► If true → Navigate to /dashboard
               If false → Stay on /auth/login
```

---

## Security Architecture

```
┌──────────────────────────────────────────────────┐
│           Security Layers                        │
└──────────────────────────────────────────────────┘

CLIENT LAYER:
├─ AuthService: Manages token storage
├─ AuthGuard: Protects routes
├─ AuthInterceptor: Adds JWT to requests
└─ Local Storage: Securely (note: consider HttpOnly cookies for production)

API LAYER:
├─ SecurityConfig: Spring Security configuration
├─ JwtAuthenticationFilter: Validates JWT tokens
├─ JwtTokenProvider: Generates/validates tokens
├─ Password Encoder: BCrypt hashing
└─ CORS Config: Allows only trusted origins

DATABASE LAYER:
├─ User passwords: Hashed with BCrypt
├─ Unique constraints: Email/username uniqueness
└─ Audit fields: Created/updated timestamps
```

---

## Request/Response Lifecycle

```
SIGNUP REQUEST:
┌────────────────────────────────────────────────────┐
│ User submits form                                  │
│ {                                                  │
│   "email": "user@example.com",                     │
│   "username": "johndoe",                          │
│   "password": "password123",                       │
│   "firstName": "John",                             │
│   "lastName": "Doe"                                │
│ }                                                  │
└─────────────┬───────────────────────────────────────┘
              │
              ▼ SignupComponent.onSubmit()
              
              ▼ AuthService.signup(request)
              
              ▼ HTTP POST to /api/auth/signup
              
              ▼ AuthController.signup(request)
              
              ▼ AuthService.signup() - business logic
                  ├─ Check email exists
                  ├─ Check username exists
                  ├─ Hash password
                  ├─ Save to MongoDB
                  └─ Generate JWT
              
              ▼ Return 201 Created with AuthResponse
              
              ▼ SignupComponent handles response
              
              ▼ AuthService stores token
              
              ▼ Router navigates to /dashboard

LOGIN REQUEST:
[Similar flow but]
  ├─ Fetch user from database
  ├─ Verify password (BCrypt.matches)
  └─ Return 200 OK (not 201)

PROTECTED REQUEST:
  ├─ Include "Authorization: Bearer {token}" header
  ├─ AuthInterceptor adds header
  ├─ JwtAuthenticationFilter validates
  ├─ SecurityContext set with user
  └─ Request allowed to proceed
```

---

## File Structure Details

```
backend/
├── pom.xml                                 # Maven dependencies
├── src/main/java/com/devdiaries/
│   ├── DevDiariesApplication.java         # Main entry point
│   ├── auth/
│   │   ├── controller/AuthController.java # REST endpoints
│   │   ├── service/AuthService.java       # Business logic
│   │   ├── model/User.java                # User entity
│   │   ├── repository/UserRepository.java # Data access
│   │   ├── dto/                           # Request/Response DTOs
│   │   │   ├── SignUpRequest.java
│   │   │   ├── LoginRequest.java
│   │   │   └── AuthResponse.java
│   │   └── security/
│   │       ├── JwtTokenProvider.java      # JWT utilities
│   │       └── JwtAuthenticationFilter.java
│   ├── config/
│   │   ├── SecurityConfig.java            # Spring Security
│   │   └── CorsConfig.java                # CORS configuration
│   └── common/exception/
│       ├── GlobalExceptionHandler.java
│       ├── ErrorResponse.java
│       ├── ResourceAlreadyExistsException.java
│       ├── ResourceNotFoundException.java
│       └── UnauthorizedException.java
└── src/main/resources/
    └── application.properties              # Configuration

frontend/
├── package.json                           # npm dependencies
├── angular.json                          # Angular config
├── tsconfig.json                         # TypeScript config
├── tailwind.config.js                    # Tailwind config
├── src/
│   ├── main.ts                           # Entry point
│   ├── index.html                        # HTML template
│   ├── styles.css                        # Global styles
│   └── app/
│       ├── app.component.ts              # Root component
│       ├── app.routes.ts                 # Routing
│       ├── app.config.ts                 # App configuration
│       ├── core/
│       │   ├── services/auth.service.ts
│       │   ├── guards/auth.guard.ts
│       │   └── interceptors/auth.interceptor.ts
│       ├── features/
│       │   ├── auth/
│       │   │   ├── login/login.component.*
│       │   │   └── signup/signup.component.*
│       │   └── dashboard/dashboard.component.*
│       └── shared/
│           ├── models/auth.model.ts
│           └── components/
```

---

## Deployment Architecture (Future)

```
Production Deployment:

┌─────────────────────────────────────┐
│         CDN / Static Hosting        │
│    (Vercel, Netlify, GitHub Pages)  │
│   Angular Frontend (Build Output)   │
└──────────────┬──────────────────────┘
               │ HTTPS
               │
        ┌──────▼────────┐
        │    Users      │
        └──────┬────────┘
               │ API Calls
               │
┌──────────────▼────────────────────┐
│      Cloud Platform               │
│  (AWS, Heroku, DigitalOcean)     │
├──────────────────────────────────┤
│ Spring Boot Backend (Docker)      │
│ - Auto-scaling                    │
│ - Load balancing                  │
│ - SSL/TLS encryption              │
└──────────────┬────────────────────┘
               │
┌──────────────▼────────────────────┐
│   Database Service                │
│ (MongoDB Atlas Cloud)             │
│ - Backups                         │
│ - Replication                     │
│ - Encryption at rest              │
└───────────────────────────────────┘
```

