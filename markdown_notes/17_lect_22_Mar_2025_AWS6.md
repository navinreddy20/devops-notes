# Elastic Beanstalk and AWS Lambda

## Cloud Service Models
### 1. IaaS (Infrastructure as a Service)
- Provides raw compute, storage, and networking resources.
- You have to manage OS, applications, runtime, etc by your own.
- Examples:
  - Amazon EC2 (Virtual Machines)
  - Amazon S3 (Storage)
  - Amazon RDS (Relational Database)
  - Amazon VPC (Virtual Private Cloud)

### 2. PaaS (Platform as a Service)
- Provides a managed platform to build, run, and deploy applications without managing infrastructure.
- Example:
  - AWS Elastic Beanstalk

### 3. SaaS (Software as a Service)
- Software applications delivered over the internet, fully managed by the provider.
- Examples:
  - Zoom
  - Microsoft Teams

---

## Web App Deployment (Manual EC2 Setup)
Deploying a web application manually on AWS EC2 involves the following steps:
1. **Create a VPC (Virtual Private Cloud)**  
   - A logically isolated network where you can launch AWS resources.
2. **Create Security Groups**  
   - Acts as a firewall to control inbound and outbound traffic to instances.
3. **Enable Inbound Rules**  
   - Allow HTTP/HTTPS and SSH access to EC2 instances.
4. **Create EC2 Instances**  
   - Launch virtual servers where you deploy and run your application.
5. **Install Required Software**  
   - E.g., Java, Apache Tomcat, etc., to run your application code.
6. **Set Up Load Balancer**  
   - Distributes incoming traffic across multiple instances for reliability.
7. **Configure Auto Scaling Group**  
   - Ensures availability and scalability by adjusting the number of EC2 instances based on load.
8. **Deploy Your Application**  
   - Upload and configure your app to start serving traffic.

---

## AWS Elastic Beanstalk (PaaS)
Elastic Beanstalk is AWS's **Platform as a Service** (PaaS) that simplifies deploying web apps by automatically handling:
- Environment provisioning
- Load balancing
- Scaling
- Monitoring

### Key Features
- End-to-end web app management
- No need to manually provision infrastructure
- Automatically manages EC2, Load Balancer, Auto Scaling, etc.
- No fixed cost for Elastic Beanstalk. Need to pay only for the resources created (e.g., EC2, ELB, RDS, etc.).

**Note**: When using Elastic Beanstalk, the first **7 steps** of EC2 deployment (networking, security, scaling, etc.) are automatically handled. You only need to focus on deploying your application code.

### Deploy App using Elastic Beanstalk
### Step 1: Create IAM Roles
1. **Trusted Entity Type**: AWS Service  
2. **Use Case**: EC2  
3. **Attach Permission Policies**:
   - `AWSElasticBeanstalkMulticontainerDocker`
   - `AWSElasticBeanstalkWebTier`
   - `AWSElasticBeanstalkWorkerTier`

### Step 2: Create Beanstalk Application
1. **Choose Environment Tier**: Web server environment
2. **Provide**:
   - Environment Name
   - Domain Name
3. **Select Platform**:
   - Platform Type: Managed
   - Platform: Java (or as per your app)
   - Platform Version: Choose latest or required version
4. **Application Code**: Start with sample app or upload your code
5. **Presets**: Choose *Single Instance* (for free-tier)
6. **Service Role**: Create new or use an existing one
7. **Networking**:
   - Add Virtual Private Cloud (VPC)
   - Activate Public IP
   - Select all instance subnets
8. **Keep other settings as default**

### Step 3: Create Environment
- AWS provisions and configures all resources.
- After setup, you get a **Beanstalk-managed URL (DNS)** to access your web application.


---

## AWS Lambda (Serverless Computing)
AWS Lambda is a serverless compute service that runs the code without provisioning or managing any servers. 

### Benefits
- No server provisioning or maintenance
- Automatically scales with number of requests
- Billing based on usage duration, only pay for the compute time your function consumes.

### Use Case
Event-driven microservices, APIs, file processing, automation, etc.

### Creating a AWS Lambda Function
1. **Function Name**: Choose a meaningful name
2. **Runtime**: Choose the language (Node.js, Python, Java, etc.)
3. **Architecture**: Default (x86_64 or ARM)
4. **Execution Role**:
   - Create a new role with basic Lambda permissions
5. **Additional Configurations**:
   - Enable **Function URL**
   - **Auth Type**: None (for public access, or use AWS IAM/Auth if needed)
6. **Code Source**:
   - Use default code editor or upload a ZIP/JAR file

### After Creation
- AWS provides:
  - A visual **function diagram**
  - A **function URL** to trigger the Lambda
  - Built-in editor to edit and test the code

---

## Summary

| **AWS Service**       | **Cloud Model**                        | **Best Suited For**                                         | **Billing Method**                                           |
|-----------------------|----------------------------------------|-------------------------------------------------------------|--------------------------------------------------------------|
| **EC2**               | IaaS (Infrastructure as a Service)     | Complete control over infrastructure with manual setup      | Billed based on compute time (hourly or per second)          |
| **Elastic Beanstalk** | PaaS (Platform as a Service)           | Quick deployment of applications with minimal infrastructure management | Billed for underlying AWS resources (e.g., EC2, Load Balancer) |
| **AWS Lambda**        | Serverless / FaaS (Function as a Service) | Event-driven tasks and microservices without server management | Billed per execution request and compute duration            |

