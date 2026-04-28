# API Testing Guide

Quick reference for testing Dev-Diaries API endpoints with curl

## Prerequisites
- Backend running on `http://localhost:8080`
- MongoDB running

## 1. Health Check

```bash
curl -X GET http://localhost:8080/api/auth/health
```

Expected Response:
```
"Backend is running"
```

---

## 2. User Signup

```bash
curl -X POST http://localhost:8080/api/auth/signup \
  -H "Content-Type: application/json" \
  -d '{
    "email": "john@example.com",
    "username": "johndoe",
    "password": "password123",
    "firstName": "John",
    "lastName": "Doe"
  }'
```

Expected Response (201):
```json
{
  "token": "eyJhbGciOiJIUzUxMiJ9...",
  "type": "Bearer",
  "id": "507f1f77bcf86cd799439011",
  "email": "john@example.com",
  "username": "johndoe",
  "firstName": "John",
  "lastName": "Doe"
}
```

---

## 3. User Login

```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "john@example.com",
    "password": "password123"
  }'
```

Expected Response (200):
```json
{
  "token": "eyJhbGciOiJIUzUxMiJ9...",
  "type": "Bearer",
  "id": "507f1f77bcf86cd799439011",
  "email": "john@example.com",
  "username": "johndoe",
  "firstName": "John",
  "lastName": "Doe"
}
```

---

## 4. Using JWT Token in Requests

Save the token from login/signup response:
```bash
TOKEN="eyJhbGciOiJIUzUxMiJ9..."
```

Use token in Authorization header:
```bash
curl -X GET http://localhost:8080/api/protected \
  -H "Authorization: Bearer $TOKEN"
```

---

## Error Examples

### Duplicate Email on Signup
```bash
curl -X POST http://localhost:8080/api/auth/signup \
  -H "Content-Type: application/json" \
  -d '{
    "email": "existing@example.com",
    "username": "newuser",
    "password": "password123",
    "firstName": "Jane",
    "lastName": "Doe"
  }'
```

Response (409 Conflict):
```json
{
  "message": "User with email existing@example.com already exists",
  "status": 409,
  "timestamp": "2026-04-28T10:30:00",
  "path": "/api/auth/signup"
}
```

### Invalid Login
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "john@example.com",
    "password": "wrongpassword"
  }'
```

Response (401 Unauthorized):
```json
{
  "message": "Invalid email or password",
  "status": 401,
  "timestamp": "2026-04-28T10:30:00",
  "path": "/api/auth/login"
}
```

### Missing Fields
```bash
curl -X POST http://localhost:8080/api/auth/signup \
  -H "Content-Type: application/json" \
  -d '{
    "email": "test@example.com",
    "username": "testuser"
  }'
```

Response (400 Bad Request):
```json
{
  "message": "Validation error: password: Password is required, firstName: First name is required",
  "status": 400,
  "timestamp": "2026-04-28T10:30:00",
  "path": "/api/auth/signup"
}
```

---

## Testing with Frontend

1. Navigate to `http://localhost:4200`
2. Click "Sign up" to create account
3. Fill in form fields:
   - Email: your-email@example.com
   - Username: your-username
   - Password: at-least-6-chars
   - First Name: Your Name
   - Last Name: Last Name
4. Click "Sign Up"
5. You'll be redirected to dashboard
6. Click "Logout" to test logout

---

## Common Issues

### JWT Validation Failed
- Token expired: Generate new token by logging in again
- Invalid token: Clear localStorage and re-login
- Token malformed: Check token format in API response

### CORS Error
- Make sure backend is running on http://localhost:8080
- Frontend should be on http://localhost:4200
- Check browser console for actual error

### MongoDB Connection Error
- Ensure MongoDB is running: `mongod`
- Check connection string in `application.properties`
- Verify MongoDB service is active

---

## Next API Endpoints (Week 2+)

- `GET /api/entries` - List all diary entries
- `POST /api/entries` - Create new entry
- `GET /api/entries/:id` - Get specific entry
- `PUT /api/entries/:id` - Update entry
- `DELETE /api/entries/:id` - Delete entry
- `GET /api/entries/search` - Search entries
- `GET /api/user/profile` - Get user profile
- `PUT /api/user/profile` - Update user profile

