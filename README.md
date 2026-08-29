# 📖 Journal App

A secure and feature-rich **personal journal application** built with **Spring Boot**.

The application provides **JWT authentication, Google OAuth 2.0 login, journal management, sentiment analysis, Redis caching, email notifications, Apache Kafka messaging, and interactive Swagger API documentation**.

---

# 🚀 Live Demo

## 🌐 Live Application

👉 **[Open Journal App](https://journal-app-jcq7.onrender.com/)**

## 📚 API Documentation

👉 **[Explore Swagger API Documentation](https://journal-app-jcq7.onrender.com/swagger-ui/index.html)**

## 🔐 Google Authentication

Users can securely sign in using their Google account directly from the live application.

> ⚠️ **Note:** The Render free tier may take a few moments to wake up after inactivity.

---

# ✨ Features

## 🔐 Authentication & Security

- JWT-based authentication
- Secure password encryption using BCrypt
- Google OAuth 2.0 authentication
- Spring Security integration
- Role-based authorization
- Protected API endpoints
- User-specific data isolation
- Environment-based secret management

## 📖 Journal Management

Users can:

- Create journal entries
- View journal entries
- Update journal entries
- Delete journal entries
- Access only their own journal data
- Perform complete CRUD operations

## 🤖 Sentiment Analysis

- Analyze journal content
- Detect sentiment and mood
- Track emotional patterns
- Generate sentiment insights

## 📧 Email Notifications

- SMTP-based email integration
- Automated email notifications
- Weekly sentiment reports

## ⚡ Performance & Messaging

- Redis caching for improved performance
- Apache Kafka for event-driven messaging
- MongoDB Atlas for cloud database storage

## 📚 API Documentation

Interactive Swagger/OpenAPI documentation allows users to:

- Explore available APIs
- Test endpoints directly
- View request and response formats
- Authenticate using JWT tokens

👉 **[Open Swagger Documentation](https://journal-app-jcq7.onrender.com/swagger-ui/index.html)**

---

# 🛠️ Tech Stack

| Technology | Usage |
|---|---|
| ☕ Java | Programming Language |
| 🌱 Spring Boot 2.7.16 | Backend Framework |
| 🔐 Spring Security | Authentication & Authorization |
| 🪪 JWT | Token-based Authentication |
| 🔵 Google OAuth 2.0 | Social Authentication |
| 🍃 MongoDB Atlas | Database |
| ⚡ Redis Cloud | Caching |
| 📨 Apache Kafka | Event-driven Messaging |
| 📧 Spring Mail | Email Notifications |
| 🌦️ External APIs | Weather Integration |
| 📚 Swagger / OpenAPI 3 | API Documentation |
| 🧪 JUnit 5 | Testing |
| 🎭 Mockito | Mocking & Unit Testing |
| ☁️ Render | Cloud Deployment |

---

# 🏗️ Architecture

The application follows a layered architecture:

```text
                         ┌─────────────────────┐
                         │     Client / UI     │
                         └──────────┬──────────┘
                                    │
                                    ▼
                         ┌─────────────────────┐
                         │   REST Controllers  │
                         └──────────┬──────────┘
                                    │
                                    ▼
                         ┌─────────────────────┐
                         │    Service Layer    │
                         └──────────┬──────────┘
                                    │
                  ┌─────────────────┼─────────────────┐
                  │                 │                 │
                  ▼                 ▼                 ▼
          ┌──────────────┐  ┌──────────────┐  ┌──────────────┐
          │   MongoDB    │  │    Redis     │  │    Kafka     │
          │   Database   │  │    Cache     │  │   Messaging  │
          └──────────────┘  └──────────────┘  └──────────────┘

# 🔐 Authentication Flow

## 🪪 JWT Authentication

```text
User
  │
  ▼
POST /public/login
  │
  ▼
Validate Credentials
  │
  ▼
Generate JWT Token
  │
  ▼
Return JWT Token
  │
  ▼
Access Protected APIs
```

---

## 🔵 Google OAuth 2.0 Authentication

```text
User
  │
  ▼
Click "Continue with Google"
  │
  ▼
Google Sign-In
  │
  ▼
Google Authentication
  │
  ▼
/auth/google/callback
  │
  ▼
Find Existing User
       OR
Create New User
  │
  ▼
Generate JWT Token
  │
  ▼
🎉 Successfully Signed In
```

---

# 📋 API Endpoints

## 🔓 Public Endpoints

| Method | Endpoint | Description |
|---|---|---|
| POST | `/public/signup` | Create a new user account |
| POST | `/public/login` | Login and receive JWT token |
| GET | `/public/health-check` | Check application health |
| GET | `/auth/google/login` | Start Google OAuth login |
| GET | `/auth/google/callback` | Handle Google OAuth callback |

---

## 📖 Journal APIs

| Method | Endpoint | Description |
|---|---|---|
| GET | `/journal/v2` | Get all journal entries |
| POST | `/journal/v2` | Create a journal entry |
| GET | `/journal/v2/id/{id}` | Get a specific journal entry |
| PUT | `/journal/v2/id/{id}` | Update a journal entry |
| DELETE | `/journal/v2/id/{id}` | Delete a journal entry |

🔐 **Journal APIs require JWT authentication.**

---

## 👤 User APIs

| Method | Endpoint | Description |
|---|---|---|
| GET | `/user` | Get user profile |
| PUT | `/user` | Update user profile |
| DELETE | `/user` | Delete user account |

🔐 **User APIs require JWT authentication.**

---

## 👨‍💼 Admin APIs

| Method | Endpoint | Description |
|---|---|---|
| POST | `/admin/create-admin-user` | Create an admin user |
| GET | `/admin/all-users` | Retrieve all users |
| GET | `/admin/clear-app-cache` | Clear application cache |

🔐 **Admin APIs require the `ADMIN` role.**

---

# 🚀 Quick Start

## 1️⃣ Create an Account

### Request

```http
POST /public/signup
Content-Type: application/json
```

### Request Body

```json
{
  "userName": "user@example.com",
  "email": "user@example.com",
  "password": "password123"
}
```

---

## 2️⃣ Login & Get JWT Token

### Request

```http
POST /public/login
Content-Type: application/json
```

### Request Body

```json
{
  "userName": "user@example.com",
  "password": "password123"
}
```

### Example Response

```json
{
  "token": "YOUR_JWT_TOKEN"
}
```

---

## 3️⃣ Use the JWT Token

Add the JWT token to the `Authorization` header:

```text
Authorization: Bearer YOUR_JWT_TOKEN
```

You can now access protected APIs.

---

## 4️⃣ Create a Journal Entry

### Request

```http
POST /journal/v2
Authorization: Bearer YOUR_JWT_TOKEN
Content-Type: application/json
```

### Example Request Body

```json
{
  "title": "My Day",
  "content": "Today was an amazing day!"
}
```

---

# 🔵 Google OAuth 2.0

The application supports authentication using **Google OAuth 2.0**.

## Authentication Endpoint

```text
/auth/google/login
```

## Authentication Process

1. User clicks **Continue with Google**
2. User signs in using their Google account
3. Google authenticates the user
4. Google redirects the user to `/auth/google/callback`
5. The application retrieves the Google account information
6. The application checks whether the user already exists
7. A new user is created if necessary
8. A JWT token is generated
9. The user is redirected to a successful sign-in page

---

# ⚙️ Environment Variables

The application uses environment variables to protect sensitive configuration.

```properties
PORT=8081

MONGODB_URI=your_mongodb_connection_string

REDIS_HOST=your_redis_host
REDIS_PASSWORD=your_redis_password

GOOGLE_CLIENT_ID=your_google_client_id
GOOGLE_CLIENT_SECRET=your_google_client_secret

GOOGLE_REDIRECT_URI=http://localhost:8081/auth/google/callback

WEATHER_API_KEY=your_weather_api_key

JAVA_EMAIL=your_email
JAVA_EMAIL_PASSWORD=your_email_password

KAFKA_SERVERS=your_kafka_servers
```

> ⚠️ Never commit sensitive credentials or API keys to GitHub.

---

# 🌐 Production Google Redirect URI

For the deployed Render application, configure the following redirect URI in **Google Cloud Console**:

```text
https://journal-app-jcq7.onrender.com/auth/google/callback
```

For local development:

```text
http://localhost:8081/auth/google/callback
```

Both redirect URIs should be configured in your Google OAuth credentials if you use both environments.

---

# 💻 Run Locally

## 1️⃣ Clone the Repository

```bash
git clone https://github.com/LikhithaSaggurthi/journalApp.git
```

## 2️⃣ Navigate to the Project

```bash
cd journalApp
```

## 3️⃣ Configure Environment Variables

Set all required environment variables before starting the application.

For local Google OAuth:

```properties
GOOGLE_REDIRECT_URI=http://localhost:8081/auth/google/callback
```

## 4️⃣ Run the Application

Using Maven:

```bash
./mvnw spring-boot:run
```

Or run the main Spring Boot application class directly from your IDE.

---

# 🖥️ Local URLs

## 🌐 Application

```text
http://localhost:8081
```

## 📚 Swagger API Documentation

```text
http://localhost:8081/swagger-ui/index.html
```

## 🔵 Google OAuth Login

```text
http://localhost:8081/auth/google/login
```

---

# ☁️ Deployment

The application is deployed on **Render**.

## 🌐 Live Application

👉 **[Open Journal App](https://journal-app-jcq7.onrender.com/)**

## 📚 Swagger Documentation

👉 **[Open Swagger UI](https://journal-app-jcq7.onrender.com/swagger-ui/index.html)**

## 🚀 Deployment Features

- Automatic deployment from GitHub
- Secure environment variable management
- HTTPS enabled
- Google OAuth production configuration
- MongoDB Atlas integration
- Redis Cloud integration
- Kafka cloud integration

> ⚠️ The Render free tier may take some time to start after inactivity.

---

# 🔒 Security Features

The application implements several security mechanisms:

- JWT token authentication
- BCrypt password hashing
- Spring Security authorization
- Google OAuth 2.0 authentication
- Protected journal endpoints
- User-specific data isolation
- Role-based admin access
- Environment variables for sensitive credentials
- HTTPS in production

---

# 🧪 Testing

The project uses:

- **JUnit 5** for unit testing
- **Mockito** for mocking dependencies
- **Spring Boot Test** for integration testing

---

# 🌟 Project Highlights

This project demonstrates practical backend development concepts including:

- REST API development
- Spring Boot layered architecture
- JWT authentication
- Google OAuth 2.0 integration
- Spring Security
- MongoDB integration
- Redis caching
- Apache Kafka messaging
- Email automation
- Sentiment analysis
- Swagger/OpenAPI documentation
- Cloud deployment with Render
- Environment variable management

---

# 🔗 Important Links

| Resource | Link |
|---|---|
| 🌐 Live Application | [Open Journal App](https://journal-app-jcq7.onrender.com/) |
| 📚 API Documentation | [Swagger UI](https://journal-app-jcq7.onrender.com/swagger-ui/index.html) |
| 💻 GitHub Repository | [View Source Code](https://github.com/LikhithaSaggurthi/journalApp) |

---

# 👩‍💻 Author

**Likhitha Saggurthi**

Built with ❤️ using:

**Spring Boot · MongoDB · Redis · Kafka · JWT · Google OAuth 2.0 · Swagger/OpenAPI · Render**

---

# ⭐ Support

If you found this project useful:

- ⭐ Star the repository
- 🍴 Fork the repository
- 📝 Share your feedback

---

### 🚀 Thank you for checking out the Journal App!
