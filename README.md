# Governance Policy Management System with Audit Logging
##  Project Overview
This project is a distributed backend system designed to manage governance policies through their entire lifecycle while maintaining a strictly immutable audit log. It demonstrates a Microservices Architecture using Event-Driven Communication to ensure high decoupling and scalability.
The system consists of two primary services:
 - **Governance Service**:Manages policy creation, submissions, and approvals.
 - **Audit Service**:Asynchronously consumes lifecycle events via Kafka and persists them for traceability.
## Tech Stack
- **Language**: Java 26 (OpenJDK)
- **Framework**: Spring Boot 4.0.5
- **Messaging**: Apache Kafka (Event Broker)
- **Database**: PostgreSQL (Relational Persistence)
- **ORM**: Spring Data JPA / Hibernate
- **Documentation**: Swagger UI (OpenAPI 3.0)
- **Tooling**: Lombok, Maven
 ## System Architecture
The system follows an asynchronous event-driven pattern:
  1. Client sends a request to the Governance Service.
  2. Governance Service updates the policy_db and publishes a message to the policy-topic in Kafka.
  3.  Audit Service listens to the policy-topic, processes the incoming message, and records the action in the audit_db.
 ## Getting Started
### Prerequisites
- Docker (Optional) or Local Installations of:
  - PostgreSQL 15+
  - Apache Kafka & Zookeeper
  - JDK 26
### Database Setup
Create two separate databases in PostgreSQL:
 ```cmd
SQLCREATE DATABASE policy_db;
CREATE DATABASE audit_db;
```
### Running the System
#### 1. Start Kafka:
Ensure Zookeeper and Kafka Broker are running on localhost:9092.
#### 2. Governance Service:
 ```cmd
cd governance-service
mvn spring-boot:run
```
### 3. Audit Service:
 ```cmd
cd audit-service
mvn spring-boot:run
```
## API Endpoints
### Governance Service (Port 8080)
| Method   | Endpoint                    | Description                            |
|----------|-----------------------------|----------------------------------------|
| POST     | /api/policies               | Create a new policy (Status: DRAFT)    |
| GET      | /api/policies               | Retrieve all policies                  |
| PUT      | /api/policies/{id}/submit   | Transition policy to PENDING_APPROVAL  |
| PUT      | /api/policies/{id}/approve  | Transition policy to APPROVED          |
| POST     | /api/policies/{id}/reject   | Transition policy to REJECTED          |
### Audit Service (Port 8081)
| Method   | Endpoint      | Description                    |
|----------|---------------|--------------------------------|
| GET      | /api/audits   | View all captured audit logs   |
## Policy Lifecycle
StatesThe system enforces the following state transitions:
DRAFT ➡️ PENDING_APPROVAL ➡️ APPROVED or REJECTED
## Audit Event Mapping
Every action in the Governance Service triggers a specific event type in the Audit Service:
- Policy Created → POLICY_CREATION
- Policy Submitted → POLICY_SUBMISSION
- Policy Approved → POLICY_APPROVAL
- Policy Rejected → POLICY_REJECTION
## Evaluation Criteria Checklist 
> Functional Requirements (Create, Submit, Approve, Reject).
>  Event-Driven Architecture using Kafka.
[x] Data Persistence with PostgreSQL.
[x] Clean and Modular Architecture.
[x] Professional Documentation (README).

| Author    |  Amanuel Asmare(Backend Intern Candidate)    |
|----------|-----------------------------------------------| 
| Email     | amanuelasmare18@gmail.com                    |
| Date      | April 2026                                   |
