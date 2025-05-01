# Trello Clone Project

This project is a Trello clone built with ReactJS frontend, Spring Boot backend, and MySQL database.

## Backend Setup

1. Navigate to the `backend` directory.
2. Configure your MySQL database and update the credentials in `src/main/resources/application.properties`.
3. Run the backend server:
   ```bash
   ./mvnw spring-boot:run
   ```
   or if you have Maven installed:
   ```bash
   mvn spring-boot:run
   ```

The backend server will run on `http://localhost:8080`.

## Frontend Setup

1. Navigate to the `frontend` directory.
2. Install dependencies:
   ```bash
   npm install
   ```
3. Start the frontend development server:
   ```bash
   npm start
   ```

The frontend will run on `http://localhost:3000`.

## Features

- Boards, Lists, and Cards management.
- REST API backend with Spring Boot.
- ReactJS frontend with Tailwind CSS styling.
- Basic CORS configuration for frontend-backend communication.

## Notes

- Make sure MySQL server is running and the database `trelloclone` exists.
- You can extend the project with authentication, drag and drop, and more features.
