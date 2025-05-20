# Docker

## Tools & Roles

- **Docker**: Containerization platform  
- **Kubernetes (K8s)**: Container orchestration system  
- **Jenkins**: Continuous Integration/Continuous Delivery (CI/CD) tool  


## Application Architecture

### 1. Frontend (User Interface)
- **Technologies**: HTML, CSS, JS, Angular, React, etc.  
- **Purpose**: Interacts directly with the end-user  

### 2. Backend (Business Logic)
- **Technologies**: Java, Python, .NET, etc.  
- **Purpose**: Processes application logic and handles requests  

### 3. Database (Storage)
- **Technologies**: MySQL, PostgreSQL, MongoDB, Oracle, etc.  
- **Purpose**: Persistent data storage  


## Example Application Stack

- **Java** (Backend)  
- **Angular 16** (Frontend)  
- **MySQL** (Database)  
- **Tomcat** (Application Server)


## Application Environments

- **DEV**: Developers test code  
- **SIT**: Testers perform System Integration Testing  
- **UAT**: Client team validates application  
- **PILOT**: Pre-production testing environment  
- **PROD**: Live application accessible by users  

> DevOps engineers are responsible for setting up and maintaining consistent environments across these stages.

---

## Docker: Containerization

### What is Docker?
Docker is an open-source platform that simplifies application deployment by packaging the application and its dependencies into containers.

### Key Concepts:
- **Container**: Lightweight isolated environment with app + dependencies  
- **Portable**: Runs on any OS/machine with Docker installed  

### Docker Architecture

| **Component**       | **Description**                                                                 |
|---------------------|---------------------------------------------------------------------------------|
| **Dockerfile**       | Blueprint for creating Docker image (contains code + dependencies)             |
| **Docker Image**     | Packaged app (code + dependencies) built from Dockerfile                      |
| **Docker Registry**  | Remote repo (e.g., Docker Hub) to store images                                 |
| **Docker Container** | Running instance of a Docker image                                             |


## Installing Docker on Linux EC2 (Amazon Linux)

```bash
sudo yum update -y
sudo yum install docker -y
sudo service docker start
sudo usermod -aG docker ec2-user
logout   # (then reconnect to apply group changes)

#Verify Docker Installation
docker -v
```

## Basic Docker Commands

```bash
docker images           # List downloaded images
docker ps               # Running containers
docker ps -a            # All containers (running + stopped)
docker pull <image>     # Download image

docker run <image>      # Run image, create container
docker stop <container> # Stop a running container
docker start <container># Start stopped container
docker rm <container>   # Remove container
docker rmi <image>      # Remove image

docker system prune -a  # Delete stopped containers & unused images
```

## Running a Docker Container (Detached + Port Mapping)

```bash
docker run -d -p 8484:8080 telusko/spring-boot-app
```
- `-d`: Detached mode (runs in background)

- `-p`: Port mapping (host:container)

- Ensure host port (e.g., 8484) is allowed in EC2 security group

> **Two containers cannot share the same host port.**


## Dockerfile

The Dockerfile is the **blueprint** for creating Docker images. It tells Docker where the app code is and what dependencies are required.

### Key Dockerfile Instructions

| **Keyword**     | **Purpose**                                      |
|------------------|--------------------------------------------------|
| `FROM`           | Base image to build from                         |
| `MAINTAINER`     | Author info (optional)                           |
| `RUN`            | Execute commands to install dependencies         |
| `CMD`            | Default command to run                           |
| `COPY`           | Copy files from local system to container        |
| `ADD`            | Like `COPY` but can also fetch remote URLs       |
| `WORKDIR`        | Set working directory inside container           |
| `EXPOSE`         | Define container port to expose                  |
| `ENTRYPOINT`     | Container execution command                      |
| `USER`           | Set user to run the container                    |


