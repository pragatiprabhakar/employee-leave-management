📌 Employee Leave Management System
    A Spring Boot–based backend application for managing employee leave requests, approvals, validations, and workflow automation.
    This project demonstrates industry-level backend development using Spring Boot, REST APIs, Spring Security (optional), MySQL, Hibernate JPA, and follows clean code and layered architecture.

🔥 Features
    Employee CRUD operations
    Apply for leave
    Leave approval/rejection
    View leave history
    Automatic validations (date checks, overlapping leave detection)
    Global Exception Handling
    DTO + Service Layer architecture
    MySQL database integration
    Postman Collection included

🏗️ Tech Stack
    Layer	Technology
    Language	Java 17
    Backend Framework	Spring Boot
    Database	MySQL
    ORM	Hibernate JPA
    Build Tool	Maven
    IDE	IntelliJ / VS Code / Eclipse
    API Testing	Postman
    Version Control	Git + GitHub

📘 API Endpoints
    Employee APIs
    Method	Endpoint	Description
    POST	/employees	Add employee
    GET	/employees	Get all employees
    GET	/employees/{id}	Get employee by ID
    Leave APIs
    Method	Endpoint	Description
    POST	/leave/apply	Apply for leave
    GET	/leave/history/{empId}	Leave history
    PUT	/leave/approve/{leaveId}	Approve leave
    PUT	/leave/reject/{leaveId}	Reject leave
    
🛠️ Global Exception Handling
    The project uses a custom GlobalExceptionHandler to handle:
    Validation errors
    Resource not found
    Bad requests
    Internal server errors
    Each error returns a structured JSON response.

👩‍💻 Author
  Pragati
Pragati Prabhakar
Java Backend Developer
