# Employee Leave Management System

A comprehensive RESTful API-based HR management system for employee registration, department management, and leave request processing with role-based access control and approval workflows.

![Java](https://img.shields.io/badge/Java-11-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.7-green)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue)
![Maven](https://img.shields.io/badge/Maven-3.6-red)

## Features

**Admin:**
- Register employees (auto-creates user account with default password)
- Manage employees and departments
- View all pending and approved leave requests
- Approve/reject leave applications

**Employee:**
- Apply for leave (Casual/Earned/Sick/Unpaid)
- View personal leave request history and status

## Tech Stack

- Java 17
- Spring Boot 2.7
- Spring Security (Basic Authentication)
- Hibernate/JPA
- MySQL 8.0
- Maven
- JUnit & Mockito

## Database Schema

**Tables:** Employee, User, Department, LeaveRequest

**Relationships:**
- Employee ↔ User (One-to-One, Cascade Delete)
- Employee → Department (Many-to-One)
- LeaveRequest → Employee (Many-to-One)

**Leave Types:** CASUAL, EARNED, SICK, UNPAID

**Leave Status:** PENDING, APPROVED, REJECTED

## API Endpoints

### Employee Management
- `POST /api/employees/register` - Register employee (Admin)
- `GET /api/employees/allEmployee` - Get all employees
- `GET /api/employees/{id}` - Get employee by ID
- `GET /api/employees/dept/{id}` - Get employees by department
- `PUT /api/employees/update/{id}` - Update employee (Admin)
- `DELETE /api/employees/delete/{id}` - Delete employee (Admin)

### Department Management
- `POST /api/department/register` - Create department (Admin)
- `GET /api/department/allDepartment` - Get all departments
- `GET /api/department/{id}` - Get department by ID
- `PUT /api/department/update/{id}` - Update department (Admin)
- `DELETE /api/department/delete/{id}` - Delete department (Admin)

### Leave Management
- `POST /api/leave/apply` - Apply for leave (Employee)
- `GET /api/leave/myLeave` - View my leave requests (Employee)
- `GET /api/leave/pendingLeaveRequest` - View pending requests (Admin)
- `GET /api/leave/allLeaveRequest` - View all requests (Admin)
- `PUT /api/leave/approve/{id}` - Approve leave (Admin)
- `PUT /api/leave/reject/{id}` - Reject leave (Admin)

## Key Features

✅ Role-based access control (Admin/Employee)
✅ Cascade delete (Employee deletion removes the User account)
✅ Automated user account creation on employee registration
✅ Multiple leave types support
✅ Complete leave approval workflow
✅ Spring Security with BCrypt password encryption
✅ Global exception handling
✅ Input validation
✅ Unit tests with JUnit & Mockito

## Future Improvements

- JWT token-based authentication
- Manager approval hierarchy
- Soft delete for employee records
- Email notifications
- Leave balance tracking
- Docker containerization
- AWS deployment

## Contact

**Pragati**
- Email: pragatiprabhakar28@gmail.com
- LinkedIn: www.linkedin.com/in/pragati-4a34241b7
