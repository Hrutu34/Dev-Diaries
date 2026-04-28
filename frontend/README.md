# Frontend Setup Instructions

## Prerequisites
- Node.js 18+ and npm
- Angular CLI 17+

## Installation

### 1. Install dependencies
```bash
cd frontend
npm install
```

### 2. Start the development server
```bash
npm start
```

The frontend will be available at `http://localhost:4200`

## Project Structure

```
src/
├── app/
│   ├── core/               # Core services, guards, interceptors
│   │   ├── services/
│   │   │   └── auth.service.ts
│   │   ├── guards/
│   │   │   └── auth.guard.ts
│   │   └── interceptors/
│   │       └── auth.interceptor.ts
│   ├── features/           # Feature modules
│   │   ├── auth/
│   │   │   ├── login/
│   │   │   └── signup/
│   │   └── dashboard/
│   ├── shared/             # Shared components and models
│   │   ├── components/
│   │   └── models/
│   ├── app.component.ts
│   ├── app.routes.ts
│   └── app.config.ts
├── styles.css              # Global styles with Tailwind
└── main.ts
```

## Features (Week 1 - Authentication)

### Components
- **Login Component** - User login with email and password
- **Signup Component** - User registration with validation
- **Dashboard Component** - Main page after authentication

### Services
- **AuthService** - Handles authentication, token management, and user state

### Guards
- **AuthGuard** - Protects routes that require authentication

### Interceptors
- **AuthInterceptor** - Automatically adds JWT token to API requests

## Authentication Flow

1. User signs up or logs in
2. Backend returns JWT token
3. Token is stored in localStorage
4. AuthInterceptor adds token to all API requests
5. AuthGuard protects authenticated routes

## Styling

- **Tailwind CSS** - Utility-first CSS framework
- **Responsive Design** - Mobile-friendly layouts
- **Dark/Light Mode** - Ready for implementation

## Build for Production

```bash
npm run build-prod
```

Output will be in `dist/dev-diaries-frontend`

## Environment Configuration

Create `src/environments/environment.ts`:
```typescript
export const environment = {
  production: false,
  apiUrl: 'http://localhost:8080/api'
};
```

For production, create `src/environments/environment.prod.ts` with production API URL.

## Next Steps

- [ ] Implement diary entry creation and management
- [ ] Add diary listing and filtering
- [ ] Implement calendar view
- [ ] Add rich text editor
- [ ] Implement search functionality
- [ ] Add user settings/profile page
- [ ] Implement mood tracking
- [ ] Add dark mode toggle
