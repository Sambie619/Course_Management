
# 🎓 Course Management System

A **Java-based console application** for managing students, courses, and enrollments with a MySQL database backend.  
This project demonstrates a **modular, professional design** with DAO patterns, input validation, and password security.

---

## Table of Contents

- [Features](#features)  
- [Tech Stack](#tech-stack)  
- [Setup & Installation](#setup--installation)  
- [Database Schema](#database-schema)  
- [Usage](#usage)  
- [Project Structure](#project-structure)  
- [Future Improvements](#future-improvements)  
- [License](#license)  

---

## Features

- Add, update, view, and delete **students**.  
- Add, update, view, and delete **courses**.  
- Enroll students in courses and view enrollments.  
- Search students by name or email.  
- Search courses by name.  
- **Password security** using SHA-256 hashing.  
- **Input validation** for names, emails, and passwords.  
- Modular design using **DAO pattern** for clean code and separation of concerns.  

---

## Tech Stack

- **Language:** Java 21  
- **Database:** MySQL  
- **Build Tool:** Maven  
- **Libraries:**  
  - MySQL Connector/J  
  - SLF4J (Logging)  
  - Google Protobuf (if needed in future)  

---

## Setup & Installation

1. **Clone the repository**  
```bash
git clone <repository_url>
cd course-management
```

2. **Create the MySQL database**  
```sql
CREATE DATABASE course_mgmt;

USE course_mgmt;

CREATE TABLE student (
    student_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE course (
    course_id INT PRIMARY KEY AUTO_INCREMENT,
    course_name VARCHAR(100) NOT NULL,
    description VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE enrollment (
    enrollment_id INT PRIMARY KEY AUTO_INCREMENT,
    student_id INT NOT NULL,
    course_id INT NOT NULL,
    enrollment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (student_id) REFERENCES student(student_id) ON DELETE CASCADE,
    FOREIGN KEY (course_id) REFERENCES course(course_id) ON DELETE CASCADE
);
```

3. **Configure database credentials** in `DBConnection.java`:

```java
private static final String URL = "jdbc:mysql://localhost:3306/course_mgmt";
private static final String USER = "root";
private static final String PASSWORD = "<your_mysql_password>";
```

4. **Build and run the project** using Maven or your IDE:

```bash
mvn clean install
java -cp target/classes;path_to_mysql_connector Main
```

---

## Database Schema

### `student`
| Field       | Type         | Key | Notes |
|------------|--------------|-----|------|
| student_id | INT          | PK  | Auto-increment |
| name       | VARCHAR(100) |     | Not null |
| email      | VARCHAR(100) | UNI | Not null, unique |
| password   | VARCHAR(255) |     | Hashed using SHA-256 |
| created_at | TIMESTAMP    |     | Default current timestamp |

### `course`
| Field       | Type         | Key | Notes |
|------------|--------------|-----|------|
| course_id  | INT          | PK  | Auto-increment |
| course_name| VARCHAR(100) |     | Not null |
| description| VARCHAR(255) |     | Optional |
| created_at | TIMESTAMP    |     | Default current timestamp |

### `enrollment`
| Field          | Type      | Key | Notes |
|---------------|-----------|-----|------|
| enrollment_id  | INT       | PK  | Auto-increment |
| student_id     | INT       | FK  | References student(student_id) |
| course_id      | INT       | FK  | References course(course_id) |
| enrollment_date| TIMESTAMP |     | Default current timestamp |

---

## Usage

1. **Run the application**  
```bash
java -cp target/classes com.example.coursemgmt.Main
```

2. **Interact via console menu**:
```
--- Menu ---
1. Add Student
2. View Students
3. Update Student
4. Delete Student
5. Add Course
6. View Courses
7. Update Course
8. Delete Course
9. Enroll Student in Course
10. View Enrollments
11. Search Students by Name
12. Search Students by Email
13. Search Courses by Name
0. Exit
Enter choice:
```

- Choose the option by number.  
- Follow prompts to add/update/view/delete students, courses, and enrollments.  

---

## Project Structure

```
src/
 ├─ com.example.coursemgmt/
 │   ├─ Main.java
 │   ├─ dao/
 │   │   ├─ StudentDAO.java
 │   │   ├─ StudentDAOImpl.java
 │   │   ├─ CourseDAO.java
 │   │   ├─ CourseDAOImpl.java
 │   │   ├─ EnrollmentDAO.java
 │   │   └─ EnrollmentDAOImpl.java
 │   ├─ model/
 │   │   ├─ Student.java
 │   │   ├─ Course.java
 │   │   └─ Enrollment.java
 │   └─ util/
 │       ├─ DBConnection.java
 │       ├─ PasswordUtil.java
 │       └─ ValidationUtil.java
└─ pom.xml
```

---

## Future Improvements

- Add **GUI interface** using JavaFX or Swing.  
- Implement **role-based access** (admin, student).  
- Add **reporting and analytics** for enrollments and courses.  
- Implement **email notifications** for enrollment.  

---

## License

This project is licensed under the MIT License.  
Feel free to use, modify, and distribute.  

---

**Author:** Sabin Shah  
**Date:** September 2025
