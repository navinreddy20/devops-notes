## Dockerfile Instructions Reference

### 🏗 FROM

* Specifies the **base image** for the application

```dockerfile
FROM tomcat:9.0
FROM openjdk:17
FROM node:19.4
FROM mysql:8.5
FROM python:3.3
```

---

### 👤 MAINTAINER

* Declares the **author** of the Dockerfile

```dockerfile
MAINTAINER Nagaraj <najg@telusko.com>
```

---

### ⚙️ RUN

* Executes **commands during image creation**
* Can have **multiple RUN** instructions

```dockerfile
RUN git clone <url>
RUN mvn clean package
```

---

### 🚀 CMD

* Executes **commands during container creation**
* If multiple CMDs are used, **only the last one is processed**

```dockerfile
CMD java -jar app.jar
CMD app.py
```

---

### 🚀 ENTRYPOINT

* Also executes during container creation
* **Cannot be overridden** via CLI like CMD

```dockerfile
ENTRYPOINT ["java", "-jar", "app.jar"]
ENTRYPOINT ["python", "app.py"]
```

---

### 📁 COPY

* Copies files from **host to container**

```dockerfile
COPY target/app.war /usr/app/tomcat/webapps/app.war
```

> 🔸 File must exist in the host machine.

---

### ➕ ADD

* Copies files just like COPY but also supports **URL & extraction**

```dockerfile
ADD target/app.war /usr/app/tomcat/webapps/app.war
ADD http://example.com/app.jar /usr/app/app.jar
```

---

### 📂 WORKDIR

* Set the **working directory**

```dockerfile
COPY target/app.jar /usr/app/app.jar
WORKDIR /usr/app
CMD java -jar app.jar
```

---

### 📡 EXPOSE

* Declare the **port number** the app will run on (for information only)

```dockerfile
EXPOSE 8080
```

---

### 👤 USER

* Used to set the **user** to run container processes

```dockerfile
USER appuser
```

---

## Sample Dockerfile

```dockerfile
FROM ubuntu
MAINTAINER Mayank mayank@telusko.com

RUN echo 'hello instruction 1 from run'
RUN echo 'hello instruction 2 from run'

CMD echo 'hi instruction 1 from cmd1'
CMD echo 'hi instruction 2 from cmd2'
```

### Build Image

```bash
$ docker build -t img-1 .
```

### Run Container

```bash
$ docker run img-1
```

---

## Push Docker Image to Docker Hub

### Step-by-Step

1. **Login to Docker Hub**

```bash
$ docker login
```

Enter your Docker Hub **username and password**

2. **Tag the Docker image**

```bash
$ docker tag img-1 <username>/img-1
# Example:
$ docker tag img-1 haidertelusko/img-1
```

3. **Push the Docker image**

```bash
$ docker push haidertelusko/img-1
```

---

## Dockerizing Web Applications

### 🧱 Dockerizing Java Web App

### 🌱 Dockerizing Spring Boot App

### 🐍 Dockerizing Python Web App

> These are high-level tasks where Dockerfile includes app code + dependencies + exposed ports + run instructions

---
