# Governance Policy Management System with Audit Logging
##  Project Overview
This project is a distributed backend system designed to manage governance policies through their entire lifecycle while maintaining a strictly immutable audit log. It demonstrates a Microservices Architecture using Event-Driven Communication to ensure high decoupling and scalability.
The system consists of two primary services:
 - **Governance Service**:Manages policy creation, submissions, and approvals.
 - **Audit Service**:Asynchronously consumes lifecycle events via Kafka and persists them for traceability.
## Tech Stack & Tools Requirement (Installation Guide)
To run this project locally, you need to install and configure the following tools:

### 1. Java Development Kit (JDK 26)
- **Purpose**: The core language environment.

- **Installation**: Download from Oracle or OpenJDK.

- **Verification**: Run java -version in your terminal.

### 2. Apache Kafka Setup (KRaft Mode)
This project uses Kafka 4.0 in KRaft mode, which does not require a separate Zookeeper instance.

### Installation Steps:
- Download Kafka 4.0 from kafka.apache.org.
- Extract the files to C:\kafka..

### 3. PostgreSQL 15+
- **Purpose**: Relational database for policy and audit persistence.

- **Installation**: Download from postgresql.org.

- **Setup**: Ensure the service is running on port 5432.

- **Database Creation**:
```cmd
CREATE DATABASE policy_db;
CREATE DATABASE audit_db;
```

### 4. Maven 3.8+
- **Purpose**: Dependency management and build tool.

- **Installation**: Usually comes bundled with IntelliJ IDEA or download from maven.apache.org.

### 5. Postman
- **Purpose**: To test the REST API endpoints.

- **Installation**: Download from postman.com.

### 6. IntelliJ IDEA (Recommended)
- **Purpose**: Integrated Development Environment (IDE) for Java.
 ## System Architecture
The system follows an asynchronous event-driven pattern:
  1. Client sends a request to the Governance Service.
  2. Governance Service updates the policy_db and publishes a message to the policy-topic in Kafka.
  3.  Audit Service listens to the policy-topic, processes the incoming message, and records the action in the audit_db.
## Execution Steps (How to Run)
### 1. Clone the Repository:
```cmd
git clone <your-repo-url>
```
### 2. Database Configuration:
Open src/main/resources/application.properties in both services and update your PostgreSQL username and password:
```cmd
spring.datasource.username=your_username
spring.datasource.password=your_password
```
### 3.  Start Infrastructure:
 ### Steps (Windows Run Kafka):
  Open your terminal and run the following commands in order:
  #### Set Heap Options (Performance):
 ```cmd
  set KAFKA_HEAP_OPTS=-Xmx1G -Xms1G
```
  #### Navigate to Kafka Directory:
  ```cmd
  cd C:\kafka
```
  #### Format Storage Directory (Run once):
  This initializes the cluster metadata.
```cmd
  .\bin\windows\kafka-storage.bat format -t NbMQyK8UR6yWW_Iu1djUEQ -c .\config\server.properties --standalone
```
  #### Start Kafka Server:
  ```cmd
  .\bin\windows\kafka-server-start.bat .\config\server.properties
```
  The server is now running on localhost:9092 and ready to handle events.
 
 ### Build and Run Applications:
 1. **Governance Service**: Run GovernanceServiceApplication.java from your IDE.
 2. **Audit Service**: Run AuditServiceApplication.java from your IDE.

    
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
-  Functional Requirements (Create, Submit, Approve, Reject).
-  Event-Driven Architecture using Kafka.
-  Data Persistence with PostgreSQL.
-  Clean and Modular Architecture.
-  Professional Documentation (README).

| Author    |  Amanuel Asmare(Backend Intern Candidate)    |
|----------|-----------------------------------------------| 
| Email     | amanuelasmare18@gmail.com                    |
| Date      | April 2026                                   |
