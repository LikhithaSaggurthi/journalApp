📖 Journal App

A secure and feature-rich **personal journal application** built with **Spring Boot**. The application provides JWT-based authentication, Google OAuth 2.0 login, journal management, sentiment analysis, caching, email notifications, event-driven messaging, and interactive API documentation.


🚀 Live Demo

🌐 Application

👉 [Open Journal App](https://journal-app-jcq7.onrender.com/)

📚 API Documentation

👉[Explore Swagger API Documentation](https://journal-app-jcq7.onrender.com/swagger-ui/index.html)

### 🔐 Google Authentication

Users can securely sign in using their Google account directly from the live application.


✨ Features

🔐 Authentication & Security

- JWT-based authentication
- Secure password encryption using BCrypt
- Google OAuth 2.0 authentication
- Protected API endpoints using Spring Security
- Role-based authorization
- Secure environment variable configuration

📖 Journal Management

Users can:

- Create journal entries
- View their journal entries
- Update existing entries
- Delete journal entries
- Access only their own personal journal data

🤖 Sentiment Analysis

- Analyze journal content
- Detect sentiment and mood
- Track emotional patterns in journal entries
- Generate weekly sentiment insights

📧 Email Notifications

- Automated email functionality
- Weekly sentiment reports
- SMTP integration using Spring Mail

⚡ Performance & Scalability

- Redis caching
- MongoDB Atlas database
- Apache Kafka event-driven messaging
- Cloud-based deployment using Render

### 
Interactive API documentation is available through Swagger/OpenAPI.

Users can:

- Explore all available APIs
- Test endpoints directly
- View request and response formats
- Authenticate using JWT tokens

👉 [Open API Documentation](https://journal-app-jcq7.onrender.com/swagger-ui/index.html)



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



# 🏗️ Architecture

The application follows a layered architecture:


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
                 ▼                 ▼                 ▼
        ┌──────────────┐   ┌──────────────┐  ┌──────────────┐
        │   MongoDB    │   │    Redis     │  │    Kafka     │
        └──────────────┘   └──────────────┘  └──────────────┘

🔐 Authentication Flow
Standard JWT Authentication
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

🔵 Google OAuth 2.0 Authentication
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
Google OAuth Callback
/auth/google/callback
  │
  ▼
Find Existing User / Create New User
  │
  ▼
Generate JWT Token
  │
  ▼
🎉 Successfully Signed In

📋 API Endpoints

🔓 Public Endpoints

Method	Endpoint	Description
POST	/public/signup	Create a new user account
POST	/public/login	Login and receive JWT token
GET	/public/health-check	Check application health
GET	/auth/google/login	Start Google OAuth login
GET	/auth/google/callback	Google OAuth callback
🔒 Journal APIs

Method	Endpoint	Description
GET	/journal/v2	Get all journal entries
POST	/journal/v2	Create a journal entry
GET	/journal/v2/id/{id}	Get a specific journal entry
PUT	/journal/v2/id/{id}	Update a journal entry
DELETE	/journal/v2/id/{id}	Delete a journal entry

🔐 Journal APIs require JWT authentication.

👤 User APIs

Method	Endpoint	Description
GET	/user	Get user profile
PUT	/user	Update user profile
DELETE	/user	Delete user account

👨‍💼 Admin APIs

The application also provides protected administrative functionality.

Method	Endpoint	Description
POST	/admin/create-admin-user	Create an admin user
GET	/admin/all-users	Retrieve all users
GET	/admin/clear-app-cache	Clear application cache

🔐 Admin APIs require the ADMIN role.

🚀 Quick Start

1️⃣ Create an Account

Request
POST /public/signup
Content-Type: application/json
{
  "userName": "user@example.com",
  "email": "user@example.com",
  "password": "password123"
}

2️⃣ Login

Request
POST /public/login
Content-Type: application/json
{
  "userName": "user@example.com",
  "password": "password123"
}
Response
{
  "token": "YOUR_JWT_TOKEN"
}

3️⃣ Use the JWT Token

Add the token to the Authorization header:

Authorization: Bearer YOUR_JWT_TOKEN

You can now access protected endpoints.

4️⃣ Create a Journal Entry

POST /journal/v2
Authorization: Bearer YOUR_JWT_TOKEN
Content-Type: application/json

Example:

{
  "title": "My Day",
  "content": "Today was an amazing day!"
}


🔵 Google OAuth 2.0

The application supports authentication using Google OAuth 2.0.

Authentication Endpoint
/auth/google/login

The flow:

User clicks Continue with Google
User signs in with Google
Google redirects to the application callback
The application retrieves user information
A new user is created if necessary
Authentication is completed successfully
The user sees a success page with account information


⚙️ Environment Variables

The application uses environment variables to keep sensitive information secure.

Example:

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
Production Google Redirect URI

For the deployed application:

https://journal-app-jcq7.onrender.com/auth/google/callback


💻 Run Locally
Clone the Repository
git clone https://github.com/VaishnaviSaggurthi/journalApp.git
Navigate to the Project
cd journalApp
Configure Environment Variables

Set all required environment variables before running the application.

For local Google OAuth:

GOOGLE_REDIRECT_URI=http://localhost:8081/auth/google/callback
Run the Application
./mvnw spring-boot:run

Or run the main Spring Boot application class from your IDE.

The application will start at:

http://localhost:8081

Swagger documentation:

http://localhost:8081/swagger-ui/index.html


☁️ Deployment

The application is deployed on Render.

🌐 Live Application

👉 https://journal-app-jcq7.onrender.com/

📚 Swagger Documentation

👉 https://journal-app-jcq7.onrender.com/swagger-ui/index.html


Deployment Features
Automatic deployment from GitHub
Secure environment variable management
HTTPS enabled
Google OAuth production configuration
MongoDB Atlas integration
Redis Cloud integration
Kafka cloud integration

🔒 Security Features
JWT token authentication
BCrypt password hashing
Spring Security authorization
Google OAuth 2.0 authentication
User data isolation
Protected journal endpoints
Role-based admin access
Environment variables for sensitive credentials
HTTPS in production

🧪 Testing

The project uses:

JUnit 5 for unit testing
Mockito for mocking dependencies
Spring Boot Test for integration testing


🌟 Project Highlights

This project demonstrates practical backend development concepts including:

REST API development
Spring Boot architecture
JWT authentication
Google OAuth 2.0 integration
Spring Security
MongoDB integration
Redis caching
Apache Kafka messaging
Email automation
Sentiment analysis
Swagger/OpenAPI documentation
Cloud deployment with Render
Environment variable management

🔗 Important Links
Resource	Link
🌐 Live Application	Open Journal App
📚 API Documentation	Swagger UI
💻 GitHub Repository	View Source Code

👩‍💻 Author

Likhitha Saggurthi

Built with ❤️ using Spring Boot, MongoDB, Redis, Kafka, JWT, Google OAuth 2.0, and modern cloud technologies.

⭐ Support
If you found this project useful:

⭐ Star the repository
🍴 Fork the repository
📝 Share your feedback
        
