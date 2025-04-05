# Load Balancer, Autoscaling, AWS RDS & AWS S3

## Load Balancers
AWS offers multiple types of load balancers to distribute incoming application traffic across multiple targets (like EC2 instances) in one or more availability zones.

### 1. Application Load Balancer (ALB)
- Operates at **Layer 7 (Application Layer)** of the OSI model.
- Used for HTTP, HTTPS, and advanced routing features such as:
  - Path-based routing
  - Host-based routing
  - Microservices communication

### 2. Network Load Balancer (NLB)
- Works at **Layer 4 (Transport Layer)**
- Supports TCP and UDP protocols.
- Designed for high throughput, ultra-low latency performance.
- Common use cases: gaming, video streaming, real-time communication.

### 3. Gateway Load Balancer
- Operates at **Layer 3 (Network Layer)**
- Integrates third-party virtual appliances (e.g., firewalls, VPNs).
- Suitable for environments requiring advanced security and monitoring.

### Load Balancer Setup
1. **Create a Target Group**
   - Protocol: TCP
   - Register EC2 instances to this target group.
2. **Create Load Balancer**
   - Choose the type (e.g., Network Load Balancer).
   - Attach the target group.
3. **Get Public DNS**
   - Use this DNS to access your load-balanced application.

**Note:** To delete a load balancer, delete the target group first, then the load balancer itself.

---
## Auto Scaling
Auto Scaling automatically adjusts the number of EC2 instances to meet demand and reduce costs. <br> <br>
**Auto Scaling Groups** manages instances based on incoming traffic. When demand increases (e.g., higher number of user requests), new instances are automatically launched to ensure performance and availability. Conversely, when demand decreases, unused instances are terminated to optimize cost-efficiency.

### Benefits:
1. Fault Tolerance
2. High Availability
3. Cost Management

### Steps to Create Auto Scaling Group:
1. Choose a **Launch Template** (contains instance config).
2. Define instance launch options.
3. Integrate with other AWS services (optional).
4. Configure **group size** and scaling policies.
5. Add **notifications** (optional).
6. Add **tags** for resource tracking.
7. Review and create.

**Launch Template** - A reusable configuration for launching EC2 instances.

**Deployment of App to New VMs through Auto Scaling Group can be done by using:**
1. Custom AMI
2. User Data Script
3. Kubernetes (EKS)

### EC2 Instance Types
AWS provides multiple types of EC2 instances to meet different workloads:
1. **General Purpose:** Balanced CPU, memory, and networking.
2. **Compute Optimized:** Best for compute-bound applications.
3. **Storage Optimized:** High IOPS and throughput for large data volumes.
4. **Memory Optimized:** High memory for large in-memory databases.
5. **Accelerated Computing:** GPU-based processing.
6. **High Performance Computing (HPC):** Used for scientific modeling, simulations.

### Recover EC2 Access Without PEM File
If PEM file is lost:
1. Launch a **temporary EC2** with a new key pair.
2. Detach the **root volume** of the old instance.
3. Attach it to the temporary instance.
4. Access files using the new key.
5. Reattach the volume to the old instance and assign the new PEM file.

---
## RDS (Relational Database Service)
RDS is a fully managed cloud database service supporting several engines:
- MySQL, PostgreSQL, Oracle, MariaDB, SQL Server

### Advantages of RDS over On-Premise:
Setting up and managing an on-premise database comes with several limitations, such as:
- Security vulnerabilities
- Network reliability issues
- Manual and error-prone backup procedures
- High administrative overhead

To overcome these challenges, AWS offers **Relational Database Service (RDS)**—a fully managed, scalable, and secure cloud-based database solution. 
- It eliminates the need to handle routine database tasks manually and provides high availability, automated backups, and built-in security.

### RDS Setup Steps:
1. Choose database engine (e.g., MySQL)
2. Provide DB identifier, master username & password
3. Configure storage (default or customized)
4. Configure connectivity options
5. Enable **public access** (optional)
6. Add to **security group** (open port **3306** for MySQL)
7. Set additional options like database name, backup retention, etc.
8. Launch the RDS instance

**Note:** Ensure MySQL port 3306 is allowed in the security group for external access.

---
## S3 (Simple Storage Service)
S3 is a cloud-based object storage service for storing and retrieving any amount of data.

### Features:
- Unlimited Storage
- Object-based (stores files as objects)
- File types supported: TXT, PDF, Excel, Audio, Video, etc.

### Key Concepts:
- **Bucket:** A container used to organize and store objects in S3.
- **Object:** Any file or data stored in a bucket, accompanied by metadata.

 **Common Operations in S3**:
1. Create a Bucket – Define a globally unique name and configure settings like access, encryption, and versioning.
2. Upload an Object – Add files such as images, documents, or videos to your bucket.
3. Download an Object – Retrieve files from the bucket for use or modification.
4. Host a Static Website – Serve HTML/CSS/JS files directly from an S3 bucket to the web.

### Steps to Create an S3 Bucket:
1. Provide a **unique bucket name**
2. Choose **bucket type**: General Purpose
3. Enable **Object ownership**: ACLs Enabled
4. **Unblock public access** (for public hosting)
5. Enable **Versioning**
6. Choose **Server-side encryption** (S3 managed keys)
7. Enable **Bucket key** (optional)

---
## Summary
- **EC2:** Core compute with flexible configuration
- **EBS:** Persistent block-level storage
- **Load Balancers:** Traffic distribution across instances
- **Auto Scaling:** Scalable and cost-effective infrastructure
- **RDS:** Fully-managed relational databases
- **S3:** Durable, scalable object storage
