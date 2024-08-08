
# Fullstack Library Management Application

## Overview

This is a full-stack library management application developed using React, Spring Boot, and MySQL. The application provides a modern interface for users to browse, manage books, and handle borrowing transactions. It aims to simplify library operations and improve user experience.

## Features

- **User Authentication**: Secure login and registration for users using Okta.
- **Book Management**: Browse, search, and filter books. Manage book details including addition and removal.
- **Borrowing Transactions**: Handle book checkouts and returns with transaction history.
- **Admin Dashboard**: For library administrators to manage users, books, and transactions.

## Tech Stack

- **Frontend**: React.js
- **Backend**: Spring Boot
- **Database**: MySQL

## Installation

### Prerequisites

- [Node.js](https://nodejs.org/) (for React)
- [Java](https://www.oracle.com/java/technologies/javase-downloads.html) (for Spring Boot)
- [MySQL](https://dev.mysql.com/downloads/) (for the database)

### Setting Up the Backend

1. Clone the repository:

    ```bash
    git clone Gurunatha14/Fullstack-React-and-SpringBoot-Library-App
    ```

2. Navigate to the backend directory:

    ```bash
    cd 02-backend/spring-boot-library
    ```

3. Install dependencies:

    ```bash
    ./mvnw install
    ```

4. Configure the `application.properties` file with your MySQL database settings.

5. Start the Spring Boot application:

    ```bash
    ./mvnw spring-boot:run
    ```

### Setting Up the Frontend

1. Navigate to the frontend directory:

    ```bash
    cd 01-frontend/react-library
    ```

2. Install dependencies:

    ```bash
    npm install
    ```

3. Start the React application:

    ```bash
    npm start
    ```

## Usage

1. Open your browser and go to `http://localhost:3000` to access the React frontend.
2. Use the application to browse books, manage your library, and handle borrowing transactions.

## Video Demo

Check out the video demonstration of the application:

[Video Demonstration](https://drive.google.com/file/d/1V95yhoiNCctX8jeVxYYkAyvpITOBb56L/view?usp=drive_link).

## Home Page 
![Home Page Screenshot](https://github.com/Gurunatha14/Fullstack-React-and-SpringBoot-Library-App/raw/main/Home_page.jpeg)

## Contributing

Contributions are welcome! Please fork the repository and submit a pull request with your changes.

## Contact

For any questions or feedback, please contact [guruokali14@gmail.com.com](mailto:guruokali14@gmail.com).
