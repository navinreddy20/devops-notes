## AWS Networking, Storage, and Infrastructure as Code (IaC) Notes

---

### 🔐 Security Groups vs NACL

#### **Security Groups (SG)**
- Acts as a **firewall** for EC2 instances
- Works at the **resource level**
- **Stateful**: return traffic is automatically allowed
- Contains:
  - Inbound rules (incoming traffic)
  - Outbound rules (outgoing traffic)
- Only allows rules (default deny all)
- Supports up to 50 rules per SG
- Cannot explicitly deny traffic

#### **Network ACL (NACL)**
- Works at the **subnet level**
- **Stateless**: return traffic needs separate rule
- Can allow or deny traffic
- One subnet can be associated with only **one NACL**, but a single NACL can be associated with **multiple subnets**
- Acts as first level of defense (SG second)

---

### 📁 AWS EFS (Elastic File System)

**EFS** is a scalable file storage solution for EC2
- Shared file system for multiple EC2 instances
- Fully managed by AWS
- Highly available and scalable

#### 🧪 Practical Steps:
1. **Login to AWS Console** → Services → EFS → Create File System
2. Copy the file system ID (e.g., `fs-04734653`)
3. **Create 2 EC2 instances**
4. Connect via SSH and run:
   ```bash
   sudo yum install -y amazon-efs-utils
   sudo mkdir efsdir
   sudo mount -t efs -o tls fs-74384 /efsdir
   cd efsdir
   touch testfile.txt
   ```
5. Repeat steps for second EC2 to test shared behavior

---

### 🛠️ CloudFormation (Infrastructure as Code)

- Tool to **provision AWS resources using YAML or JSON**
- Reproducible infrastructure deployments

#### Steps:
- Go to CloudFormation → Create Stack → Use existing template → Upload YAML file

#### ✅ Sample YAML Template
```yaml
AWSTemplateFormatVersion: '2010-09-09'
Description: Telusko - Build Linux Web Server

Parameters:
  LatestAmiId:
    Description: AMI for Amazon Linux 2 EC2 instance
    Type: 'AWS::SSM::Parameter::Value<AWS::EC2::Image::Id>'
    Default: '/aws/service/ami-amazon-linux-latest/amzn2-ami-hvm-x86_64-gp2'

Resources:
  webserver:
    Type: 'AWS::EC2::Instance'
    Properties:
      InstanceType: 't2.micro'
      ImageId: !Ref LatestAmiId
      SecurityGroupIds:
        - !Ref WebserverSecurityGroup
      Tags:
        - Key: Name
          Value: teluskoserver1
      UserData:
        Fn::Base64: !Sub |
          #!/bin/bash -xe
          yum update -y
          yum install httpd -y
          service httpd start
          chkconfig httpd on
          cd /var/www/html
          echo '<h2><b>Telusko Linux Demo</b></h2>' > index.html

  WebserverSecurityGroup:
    Type: 'AWS::EC2::SecurityGroup'
    Properties:
      GroupDescription: 'Enable Port 80 for HTTP access'
      SecurityGroupIngress:
        - IpProtocol: tcp
          FromPort: 80
          ToPort: 80
          CidrIp: 0.0.0.0/0
```
> 🎯 After stack creation, verify instance on the EC2 dashboard.

---

### ☁️ DevOps Kit Overview

List of topics/tools covered in the Maven/DevOps Kit:
- EC2 + LBR + ASG
- EBS, S3, RDS
- IAM, VPC
- Elastic Beanstalk
- CloudWatch & SNS
- AWS CLI, EFS, CloudFormation, Lambda

**IaC Tools**:
- Terraform
- Ansible

**CI/CD & Deployment Tools**:
- Tomcat
- Docker
- Kubernetes (k8s)
- Jenkins
- Nexus
- SonarQube

