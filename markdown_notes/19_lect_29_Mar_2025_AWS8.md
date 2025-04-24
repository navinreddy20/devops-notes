# AWS - VPC (Virtual Private Cloud)

A **Virtual Private Cloud (VPC)** is a logically isolated section of the AWS cloud where you can define and control your networking environment, including:
- IP address ranges
- Subnets (public/private)
- Route tables
- Network gateways
- Security policies

It enables you to **securely manage AWS resources** by customizing routing, access control, and traffic management.

### VPC Features
- Provides **isolation** for resources inside the AWS cloud.
- Gives **control over IP address ranges**, security groups, and routing.
- Supports **custom firewall rules, access control policies**, and **traffic flow design**.
- Enables **secure networking** for EC2, RDS, Lambda, and more.

---

## Core VPC Terminologies

| Term               | Description |
|--------------------|-------------|
| **VPC**            | A virtual private network in AWS. Can contain subnets, route tables, gateways. |
| **Subnet**         | Subdivision of a VPC. Can be public or private. |
| **CIDR Block**     | Defines IP address range (e.g., 10.0.0.0/16). |
| **Route Table**    | Determines traffic routing for subnets. |
| **Internet Gateway** | Enables internet access for instances in a VPC. |
| **NAT Gateway**    | Allows instances in private subnets to access the internet. |
| **VPC Peering**    | Enables communication between VPCs. |
| **Security Groups**| Instance-level firewall settings. |
| **NACL (Network ACL)** | Subnet-level stateless firewall. |

---

## 🌐 Public vs Private Subnets

- **Public Subnet**:
  - Can communicate with the internet.
  - Connected to Internet Gateway.

- **Private Subnet**:
  - Used for internal communication only.
  - No direct internet access.

---

## IP Addressing

### IP Versions

| IP Version | Size       | Example                      | Capacity                        |
|------------|------------|------------------------------|----------------------------------|
| **IPv4**   | 32-bit     | `192.168.1.1`                | ~4.3 Billion addresses           |
| **IPv6**   | 128-bit    | `2001:db8:85a3::8a2e:0370:7334` | 340 undecillion addresses      |

> IPv6 was introduced due to the shortage of IPv4 addresses with growing device connectivity.

### IP Address Types

- **Public IP**: Accessible over the internet.
- **Private IP**: Used within a private network.
- **Static IP**: Fixed IP that does not change.
- **Dynamic IP**: Automatically assigned and may change.

---

## VPC Sizing & CIDR Blocks

- VPC Sizing is done using **CIDR (Classless Inter-Domain Routing)**.
- Formula: `2^(32 - subnet mask)`

| CIDR Block     | No. of IPs  | Notes                             |
|----------------|-------------|-----------------------------------|
| `10.0.0.0/16`  | 65,536      | Large VPC                         |
| `10.0.0.0/24`  | 256         | Recommended subnet size           |
| `10.0.0.1/28`  | 16          | Smallest allowed by AWS (/28)     |
| `10.0.0.1/32`  | 1           | Invalid (used for single host)    |

- **Valid subnet sizes**: `/16` to `/28`

---

## Hands-On: Create and Configure a VPC

### Step 1: Create a VPC

- Go to **VPC → Create VPC**
- **Name**: `telusko-vpc`
- **IPv4 CIDR block**: `10.0.0.0/16`
- **IPv6 CIDR block**: None
- **Tenancy**: Default

### Step 2: Create Route Table
- Go to Route Tables section
- A default route table is already created for the VPC — rename it to: `private-telusko-rt`
- Create a new route table: `public-telusko-rt`

### Step 3: Create Route Tables
1. **Default Route Table**: Rename to `private-telusko-rt`
2. **Create New Route Table**: Name it `public-telusko-rt`

###  Step 4: Associate Subnets with Route Tables
- Associate `telusko-public-sn` with `public-telusko-rt`
- Associate `telusko-private-sn` with `private-telusko-rt`

### Step 5: Create Internet Gateway
- Name: `telusko-igw`
- Attach it to your VPC
- Edit **Routes** in `public-telusko-rt`:
  - Add Route → `0.0.0.0/0` → Target: `telusko-igw`
    
### Step 6: Configure Routes
- Go to `public-telusko-rt → Routes → Edit`
- Add Route: `0.0.0.0/0` → **Target**: `telusko-igw`

### Step 7: Launch EC2 Instances
- Launch **2 EC2 Instances** in custom VPC:
  - One in `telusko-public-sn`
  - One in `telusko-private-sn`
- Connect to **Public EC2** (has internet)
- Try connecting to **Private EC2**, it will fail (no internet)

>  Connect Private EC2 via Public EC2
- SSH into **public EC2**
- From there, SSH into **private EC2** using its private IP (bastion/jump host setup)

---

## Summary Points
- Always plan CIDR ranges ahead based on your scale.
- Subnet sizing must accommodate AWS’s reserved IPs.
- Use NAT Gateway if private subnet needs outbound internet.
- Use VPC Peering or Transit Gateway for cross-VPC communication.
- Secure resources using Security Groups and NACLs at different levels.
