# Spring-Inc
# Fleet Management Microservices Application

## Overview
This is a military **Fleet Management Application** built using Spring Boot, designed to enable an admiral to effectively manage squadrons, commanders, and pilots. The application allows CRUD operations for managing squadrons and transferring personnel.

The project follows a microservices architecture and adheres to industry best practices for code quality, maintainability, and security.

---

## Features
- **Squadron Management**: Create, update, delete, and retrieve squadrons.
- **Commander and Pilot Management**: Create, assign, transfer, and remove commanders or pilots.
- **Edge Case Handling**:
  - Squadron capacity limits.
  - Managing squadrons with no assigned commanders.
  - Managing pilots with no assigned commanders.
  - Handling pilots when squadrons are deleted.
- **Service Discovery**: Eureka server for microservices registration and discovery.
- **API Gateway**: Spring Cloud Gateway for routing requests.
- **Database**: MySQL for persistent data storage.

---

## Technologies Used
- **Backend**:
  - Spring Boot (REST APIs)
  - Spring Cloud Gateway
  - Eureka Service Discovery
- **Database**:
  - MySQL
- **Additional Tools**:
  - Swagger for API documentation
  - JUnit for unit testing

---

## Architecture
This application is divided into multiple microservices:

1. **Commander Service**:
   - Manages CRUD operations for commanders.
   - Allows assigning and transferring commanders between squadrons.

2. **Pilot Service**:
   - Manages CRUD operations for pilots.
   - Allows assigning and transferring pilots between squadrons.

3. **Squadron Service**:
   - Handles CRUD operations for squadrons.
   - Includes capacity checks and edge case handling.

4. **Eureka Server**:
   - Acts as the service registry for microservices.

5. **Spring Cloud Gateway**:
   - Provides a unified API gateway for routing client requests to appropriate microservices.

---

## Setup and Installation

### Prerequisites
- Java 17
- Maven
- MySQL

### Steps
1. Clone the repository:
   ```bash
   git clone https://github.com/stevenhu9/Spring-Inc.git
   cd Spring-Inc
