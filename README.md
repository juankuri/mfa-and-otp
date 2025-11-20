# MFA & OTP Authentication System

## Project Description
This project implements a secure authentication system that combines **traditional login** with **One-Time Password (OTP) verification** sent via email.  
It allows users to:
- Register with email, username, and password
- Log in using email and password
- Receive an OTP to verify their login for enhanced security
- Validate the OTP to complete authentication

## How to Run

### Backend (Spring Boot)
1. Install **Java 17+** and **PostgreSQL**.
2. Create the database `otp` in PostgreSQL using the provided schema.
3. Configure your database connection in `application.properties`.
4. Run the backend:
   ```bash
   ./mvnw spring-boot:run
5. Backend endpoints:
    POST /user → create a new user (signup)
    POST /auth/login → login
    POST /auth/validate → verify OTP

### Frontend

1. Navigate to the frontend project folder.
2. Install dependencies:
3. npm install

Start the React application:

1. npm start
2. The frontend will open in your browser at http://localhost:3000.
3. The app will guide the user through login, signup, and OTP verification.
4. Make sure both frontend and backend are running at the same time.

### Example Flow: Login + OTP

1. Go to SignUp page
2. Enter username, email, password, and confirm password.
3. Check you have entered all infromation.
4. Click Sign Up.
5. If successful, you are redirected to the Login screen.
6. On Login*
7. Enter your registered email and password.
8. Click Login.
9. If credentials are correct, you are redirected to the OTP screen.
10. OTP Verification
11. Check your email for the 6-digit OTP.
12. Enter the OTP in the input fields.
13. Click Verify OTP.
14. If the OTP is correct, you will see a success message: OTP Verified! Logged in.