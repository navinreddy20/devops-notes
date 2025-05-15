## Docker Lecture 4 Notes

### 📦 Docker Networking Recap

* Docker containers can communicate **internally** using Docker networks.
* Containers `C1` and `C2` inside the same **docker-network** can connect directly.
* Example: A Spring Boot app (Java) container can talk to a MySQL database container over a shared Docker network.

```mermaid
graph TD
    docker-server((Docker Server))
    subgraph docker-network
      C1[Spring Boot App] --> C2[MySQL DB]
    end
    docker-server --> docker-network
```

---

### 🖼 Multi-container Architecture

#### Example: Monolithic App Split

* **Frontend (FE)** in container `C1`
* **Backend App** in container `C2`
* **Database** in container `C3`

These three containers run together inside the same network and form an app deployment unit.

```mermaid
graph LR
    FE[Frontend - C1] --> APP[Backend App - C2] --> DB[Database - C3]
```

---

### 🧱 Microservices Setup

* Apps like **Hotels**, **Flights**, **Trains**, and **Cabs** each have their own containers
* Each service can have a separate **database container**

> This forms a microservices architecture: multiple isolated containers communicating via Docker network.

```mermaid
graph LR
    HotelApp --> DB1[Hotel DB]
    Flights --> DB2[Flights DB]
    Trains --> DB3[Trains DB]
    Cabs --> DB4[Cabs DB]
```

> In real-world deployments, Docker Compose or Kubernetes is used to manage these multi-container applications.

---
