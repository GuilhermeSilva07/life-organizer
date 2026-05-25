# 🗂️ Life Organizer

A personal assistant API with AI integration for managing tasks, finances and habits — built with Java, Spring Boot and Google Gemini.

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.6-green)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-blue)
![Gemini](https://img.shields.io/badge/Gemini-AI-purple)

---

## 🚀 Features

- ✅ **Task Management** — Create, update status and priority, delete tasks
- 💰 **Finance Tracking** — Record income and expenses, view balance summary
- 🔄 **Habit Tracker** — Track daily/weekly habits with completion history
- 🤖 **AI Assistant** — Powered by Google Gemini, analyzes your data and gives personalized suggestions
- 🌐 **Frontend** — Clean dark dashboard built with HTML, CSS and JavaScript

---

## 🛠️ Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 21 |
| Framework | Spring Boot 4.0.6 |
| Database | PostgreSQL 16 |
| ORM | Spring Data JPA + Hibernate |
| AI | Google Gemini API (gemini-2.5-flash) |
| Frontend | HTML, CSS, JavaScript |
| Build | Maven |

---

## 📋 Prerequisites

- Java 21
- PostgreSQL 16+
- Google Gemini API Key ([get here](https://aistudio.google.com))

---

## ⚙️ Setup

**1 — Clone the repository**
```bash
git clone https://github.com/GuilhermeSilva07/life-organizer.git
cd life-organizer
```

**2 — Create the database**
```sql
CREATE DATABASE life_organizer;
```

**3 — Configure environment variables**

Add the following variables to your system:

| Variable | Description |
|---|---|
| `DB_USER` | PostgreSQL username |
| `DB_PASSWORD` | PostgreSQL password |
| `DB_NAME_LIFE_ORGANIZER` | Database name (`life_organizer`) |
| `GEMINI_API_KEY` | Google Gemini API key |

**4 — Run the application**
```bash
./mvnw spring-boot:run
```

The API will be available at `http://localhost:8080`

**5 — Open the frontend**

Open `frontend/index.html` in your browser.

---

## 📡 API Endpoints

### Tasks
| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/tasks` | List all tasks |
| GET | `/api/tasks/{id}` | Get task by ID |
| GET | `/api/tasks/status/{status}` | Filter by status |
| GET | `/api/tasks/priority/{priority}` | Filter by priority |
| POST | `/api/tasks` | Create task |
| PUT | `/api/tasks/{id}` | Update task |
| PATCH | `/api/tasks/{id}/status` | Update status |
| DELETE | `/api/tasks/{id}` | Delete task |

### Finances
| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/finances` | List all transactions |
| GET | `/api/finances/summary/income` | Total income |
| GET | `/api/finances/summary/expenses` | Total expenses |
| GET | `/api/finances/summary/balance` | Current balance |
| POST | `/api/finances` | Create transaction |
| PUT | `/api/finances/{id}` | Update transaction |
| DELETE | `/api/finances/{id}` | Delete transaction |

### Habits
| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/habits` | List all habits |
| GET | `/api/habits/{id}` | Get habit by ID |
| POST | `/api/habits` | Create habit |
| PUT | `/api/habits/{id}` | Update habit |
| PATCH | `/api/habits/{id}/complete` | Mark as done today |
| DELETE | `/api/habits/{id}` | Delete habit |

### AI Assistant
| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/ai/analyze` | Ask the AI assistant |

---

## 🏗️ Architecture
