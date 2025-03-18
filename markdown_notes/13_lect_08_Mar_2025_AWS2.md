# AWS EC2 & EBS Notes

## EC2 (Elastic Compute Cloud)
EC2 is a scalable virtual machine service provided by AWS. To create an EC2 instance, the following are required:
- **AMI (Amazon Machine Image):** Pre-configured OS image for launching an instance.
- **Instance Type:** Defines CPU, memory, and network capacity.
- **Key Pair:** Required for secure SSH or RDP access.
  - **Public Key:** Stored by AWS.
  - **Private Key (.pem file):** Downloaded and used for authentication.
  - Cannot be changed once created.
- **Security Groups:** Firewall rules to control inbound and outbound traffic.
- **VPC (Virtual Private Cloud):** Provides network isolation.
- **EBS (Elastic Block Store):** Persistent storage attached to EC2 instances.

---
## Practical Steps for EC2 VM Setup

### 1. Create a Key Pair (.pem file)
- Public key is stored in AWS, private key is downloaded.
- Required for secure authentication to create an instance.
- Cannot be modified after creation.

### 2. Create a Security Group
Security groups can be created and modified before, during, or after the creation of an EC2 (virtual machine).
- Defines Inbound and Outbound traffic rules.
- **Inbound Rules:**
  - RDP (Windows) → Port **3389** (Source: Anywhere)
  - SSH → Port **22**
  - HTTP → Port **80**
  - HTTPS → Port **443**
  - MySQL → Port **3306**
- **Outbound Rules:**
  - Controls outgoing traffic destinations.

### 3. Create an EC2 Instance
- Select **AMI**
- Choose **Instance Type**
- Select **Key Pair**
- Configure **Security Groups**
- Attach **EBS Storage**
- Launch the instance.

### 4. Connect to an EC2 Instance
- **Windows:** Use Remote Desktop Connection (RDP)
  - Retrieve **password** from RDP client after uploading the private key.
  - Paste **Public DNS** in RDP client.
  - Default username: **Administrator**
- **Mac:** Use Microsoft Remote Desktop.

---
## Types of IPs in AWS

1. **Private IP:**
   - Fixed internal IP for communication within a **VPC**.

2. **Public IP:**
   - **Dynamic IP** assigned by AWS.
   - Changes upon instance restart.

**Example**:
After Creating the Instance:
- Public IPv4 Address: `13.203.198.254` | Open Address
- Private IPv4 Address: `172.31.7.4`

After Restart the Instance:
- Public IPv4 Address: `13.233.66.166` | Open Address
- Private IPv4 Address: `172.31.7.4`

**Note:**
  - Public IP changes after every restart.
  - Private IP remains the same throughout the instance lifecycle.
  - To maintain a consistent public IP, an Elastic IP can be assigned to the instance.


3. **Elastic IP:**
   - **Static public IP** for consistent external access.
   - Requires manual allocation and incurs charges if unassociated.

### Steps to Allocate Elastic IP:
- Go to **Network & Security** → **Elastic IPs**
- Click **Allocate Elastic IP Address** → **Allocate**
- Associate Elastic IP with an **EC2 Instance**
- The public ID will now be the same as the elastic ID, and neither the public nor the elastic ID will change after restarting the instance.
- To remove:
  - **Dissociate** Elastic IP
  - **Release** Elastic IP back to AWS

---
## EBS (Elastic Block Store)
EBS provides persistent block storage for EC2 instances.

### Features:
- EBS (Elastic Block Store) is attached to an EC2 instance to provide storage.
- There are two types of EBS volumes:
    1. Root Volume (Primary storage)
    2. Additional Volume (Secondary storage)
- EBS serves as both primary and secondary storage for EC2 instances.
- When launching an EC2 instance, a default root volume is provided:
    Windows VM: 30GB
    Linux VM: 8GB
- Root volume is mandatory for EC2 instances. Removing it will make the instance unusable.
- Additional EBS volumes are optional and can be added or removed as needed.
- An EC2 instance can have multiple EBS volumes, but one EBS volume can be attached to only one EC2 instance at a time.
- EBS volumes are Availability Zone specific, if an EC2 instance is in ap-south-1a, the EBS volume must also be in ap-south-1a to attach it.

### Types of EBS Volumes:
| Type | Min Size | Max Size |
|------|---------|---------|
| General Purpose SSD (gp3) | 1 GiB | 16 TiB |
| Provisioned IOPS | 4 GiB | 16 TiB |
| Cold HDD | 125 GiB | 16 TiB |
| Throughput Optimized | 125 GiB | 16 TiB |
| Magnetic | 1 GiB | 1 TiB |

---
## EBS Snapshots
- Snapshots provide **backup** for EBS volumes.
- EBS Sanpshots are regional Specific while Volumes are Availability zone specific.
- They cannot be attached directly to EC2.

### Steps to Create a Snapshot:
1. Create a Volume:
   - Type: General Purpose SSD (gp3)
   - Size: 10 GiB
   - IOPS: 3000
   - Throughput: 125 MiB/s
   - Availability Zone: e.g., ap-south-1b
2. Create a Snapshot from the Volume.
3. Create a New Volume from the Snapshot in a Different Availability zone.
4. Attach the New Volume to an EC2 Instance.

### Commands to Use After Attaching Volume:
```sh
$ lsblk  # Check attached volumes
$ sudo mkfs -t ext4 /dev/xvdb  # Format volume (only once)
$ mkdir dir1  # Create a directory for mounting
$ sudo mount /dev/xvdb dir1  # Mount the volume
$ cd dir1  # Navigate to the directory
$ sudo touch f1.txt f2.txt  # Create files
```

### Reattaching to Another EC2 Instance:
- Detach the volume from EC2-1.
- Attach the volume to a new EC2 instance.
- Run the following commands on the new instance:
```sh
$ lsblk  # Check volumes
$ mkdir dir1
$ sudo mount /dev/xvdb dir1  # Mount volume
$ ls -l dir1  # Verify stored files
```

---
## Lifecycle Manager
- Automates **creation, retention, copy, and deletion** of snapshots & AMIs.

### Steps to Set Up Lifecycle Policy:
1. **Create a Lifecycle Policy** → Select **Custom Policy**
2. **Add Target Resource:**
   - Resource Type: **Volume**
   - Resource Tags: **Key-Value Pair**
3. **Configure Schedule:**
   - Set **Schedule Name**
   - Choose **Frequency** (e.g., every 12 hours)
   - Set **Retention Type** (Count-based)
   - Define number of **snapshots to retain**
4. **Review and Enable Policy**

---
## Summary
- **EC2 Instances** require AMI, instance type, key pair, security groups, and EBS storage.
- **Security Groups** control inbound/outbound traffic.
- **Public IPs** are dynamic and use Elastic IP for a fixed address.
- **EBS** provides persistent storage with multiple volume types.
- **Snapshots** enable backups and cross-AZ volume creation.
- **Lifecycle Manager** automates backup and retention policies.

