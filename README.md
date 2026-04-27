# Dev-Diaries WebApp

A quick, modern, secure, and elegant web application for writing and managing daily personal journal entries. Built with a focus on clean UX, privacy, and scalability, this app allows users to document their thoughts, track moods, and revisit memories in a structured digital format.

---

## Features

### Authentication
- User Signup & Login
- Secure password hashing
- JWT-based session management
- Optional OAuth (Google login)

---

### Diary Management
- Create, edit, and delete entries
- Rich text editor support
- Auto-save drafts
- Tagging system for categorization

---

### Organization & Navigation
- Calendar-based view of entries
- Timeline of daily logs
- Filter by tags, moods, or date

---

### Search & Insights
- Full-text search across entries
- Mood tracking per entry
- Weekly/monthly summaries (future scope)

---

### UI/UX
- Minimal, distraction-free writing interface
- Dark / Light mode
- Responsive design (mobile-friendly)
- Smooth animations and transitions

---

### Optional Enhancements
- AI-powered journaling summaries
- End-to-end encryption for privacy
- Offline support (Progressive Web App)
- Activity heatmap (GitHub-style consistency tracker)

---

## Tech Stack

### Frontend
- Angular
- Tailwind CSS
- RxJS
- Three.js (for subtle UI enhancements only)

---

### Backend
- Node.js + Express  

---

### Database
- MongoDB *(flexible schema for entries)*  

---

### Authentication
- JWT (JSON Web Tokens)
- bcrypt (password hashing)

---

## Architecture
Frontend (Angular)
↓
Backend API (Node.js)
↓
Database (MongoDB / PostgreSQL)

---

## Project Structure (Frontend)
```
src/
├── app/
│ ├── core/ # Auth services, guards
│ ├── shared/ # Reusable components
│ ├── features/
│ │ ├── auth/
│ │ ├── diary/
│ │ ├── dashboard/
│ ├── layouts/
│ └── app-routing.module.ts
├── assets/
└── environments/
```

---

## Project Structure (Backend)

```
server/
├── controllers/
├── models/
├── routes/
├── middleware/
├── services/
├── config/
└── server.js
```

---

## API Endpoints (Sample)

### Auth
- POST /api/auth/register
- POST /api/auth/login
- GET /api/auth/profile

### Diary Entries
- GET /api/entries
- POST /api/entries
- GET /api/entries/:id
- PUT /api/entries/:id
- DELETE /api/entries/:id

---

## Sample Data Model

```json
{
  "userId": "12345",
  "title": "A productive day",
  "content": "Today I worked on my project...",
  "date": "2026-04-27",
  "mood": "happy",
  "tags": ["work", "learning"],
  "createdAt": "timestamp",
  "updatedAt": "timestamp"
}
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

