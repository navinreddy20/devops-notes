# Terraform
Terraform is a powerful **open-source tool developed by HashiCorp** used to provision and manage infrastructure in a declarative way across multiple cloud providers including AWS, Azure, GCP, and others.

### Key Features
- Supports **Infrastructure as Code (IaC)**
- Uses **HCL (HashiCorp Configuration Language)**
- It works across most cloud platforms
- Declarative syntax to define, plan, and apply infrastructure
- Tracks infrastructure state using `terraform.tfstate`

---

## Installing Terraform

### On Windows

1. Download Terraform:  
   [https://developer.hashicorp.com/terraform/install](https://developer.hashicorp.com/terraform/install)

2. Extract the `.zip` file

3. Add the folder path to **System Environment Variables**

4. Open CMD and verify:

```bash
terraform            # lists all commands
terraform version    # check version
terraform help       # detailed help
```

### On Linux (Amazon EC2)
```bash
sudo yum install -y yum-utils shadow-utils
sudo yum-config-manager --add-repo https://rpm.releases.hashicorp.com/AmazonLinux/hashicorp.repo
sudo yum -y install terraform
terraform -v
```

---

## Terraform Architecture & Lifecycle
>  Reference official terraform, tutorial : https://developer.hashicorp.com/ACterraform/tutorials/aws-get-started

```bash
.tf → init → fmt → validate → plan → apply → destroy
```


| **Command**         | **Purpose**                                                       |
|---------------------|-------------------------------------------------------------------|
| `terraform init`    | Initializes the project directory and downloads necessary providers |
| `terraform fmt`     | Formats the configuration files for readability and consistency    |
| `terraform validate`| Validates the syntax and configuration files (optional but recommended) |
| `terraform plan`    | Creates and displays an execution plan without making changes      |
| `terraform apply`   | Provisions infrastructure as described in the configuration files  |
| `terraform destroy` | Destroys all infrastructure managed by the current configuration   |

---

##  Sample Script – Create an EC2 Instance
```bash
provider "aws" {
  region     = "ap-south-1"
  access_key = "YOUR_ACCESS_KEY"
  secret_key = "YOUR_SECRET_KEY"
}

resource "aws_instance" "linux_vm" {
  ami           = "ami-002f6e91abff6eb96"
  instance_type = "t2.micro"
  key_name      = "terraform"
  security_groups = ["default"]

  tags = {
    Name = "Telusko-Linux_VM"
  }
}
```
- After terraform apply, a terraform.tfstate file is created that tracks your infrastructure state.

---

## Secure AWS Keys Using Environment Variables
Avoid hardcoding credentials. Use:
```bash
export AWS_ACCESS_KEY_ID="YOUR_ACCESS_KEY"
export AWS_SECRET_ACCESS_KEY="YOUR_SECRET_KEY"
```

And use this simplified provider block:
```bash
provider "aws" {
  region = "ap-south-1"
}

resource "aws_instance" "linux-vm" {
	ami = "ami-002f6e91abff6eb96"
	instance_type = "t2.micro"
	key_name = "terraform"
	security_groups = ["default"]
	tags = {
			Name = "Telusko-Linux_VM"
	}
}
```

---

## Creating EC2 VM with User Data

### 1. Create Script file
```bash
$ vi installHttpd.sh

#! /bin/bash
sudo su
yum install httpd -y
cd /var/www/html
echo "<html><h1> Welcome to Telusko WebServer </h1></html>" > index.html
service httpd start

chmod u+x installHttpd.sh
```

### 2. Terraform Script with User Data
```bash
resource "aws_instance" "linux_vm" {
  ami           = "ami-002f6e91abff6eb96"
  instance_type = "t2.micro"
  key_name      = "terraform"
  security_groups = ["default"]
  user_data     = file("installHttpd.sh")

  tags = {
    Name = "DevOps-Linux_VM"
  }
}
```

---

## Using Variables in Terraform
Terraform uses variables to **make configurations flexible and reusable**. Instead of hardcoding values, you can supply dynamic inputs and outputs.
### 1. Input Variables
- Input variables allow you to **parameterize** your Terraform scripts.  
- This makes your configuration reusable and avoids hardcoding values like AMI IDs, instance types, bucket names, etc.

### 2. Output Variables
- Output variables return useful information after Terraform finishes applying a configuration.
- You define them using the output block to expose details such as:EC2 instance public IP address, IAM user name, S3 bucket ARN, etc.

### Script:
```bash
# provider.tf
provider "aws" {
  region = "ap-south-1"
}
```

```bash
# input-vars.tf
variable "ami" {
  default = "ami-002f6e91abff6eb96"
}

variable "instance_type" {
  default = "t2.micro"
}
```

```bash
# main.tf
resource "aws_instance" "linux_vm" {
  ami           = var.ami
  instance_type = var.instance_type
  key_name      = "terraform"
  security_groups = ["default"]

  tags = {
    Name = "var-Linux_VM"
  }
}
```

```bash
# output.tf
output "ec2_vm_info" {
  value = aws_instance.linux_vm
}

output "ec2_vm_public_ip" {
  value = aws_instance.linux_vm.public_ip
}
```

---

## Creating an S3 Bucket:
```bash
resource "aws_s3_bucket" "telusko_bucket" {
  bucket = "telusko4343" # Must be globally unique
  acl    = "private"

  versioning {
    enabled = true
  }
}
```

---

## Creating an IAM User with Permissions
```bash
# Create IAM user
resource "aws_iam_user" "example_user" {
  name = "my-example-user"
}

# Attach policy to IAM user
resource "aws_iam_user_policy_attachment" "example_user_policy" {
  user       = aws_iam_user.example_user.name
  policy_arn = "arn:aws:iam::aws:policy/AdministratorAccess"
}
```

---

## Terraform Destroy
```bash
terraform destroy --auto-approve
```







