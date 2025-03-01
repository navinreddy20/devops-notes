# Linux Administration Guide

## 1. Installing Webserver in Linux VM

### Setting up HTTP Server
```bash
# Install webserver
$ sudo yum install httpd

# Start the webserver
$ sudo service httpd start

# Navigate to content directory
$ cd /var/www/html

# Edit content
$ sudo vi index.html
# Use <h1> tags in insert mode, exit with :wq!
```

**Important Notes:**
- HTTP server runs on port 80
- Must enable port 80 in security group inbound rules
- Accessible via EC2 VM public IP

## 2. Systemctl Management

### Key Operations
1. **Service Control Commands:**
   ```bash
   systemctl start service-name    # Start service
   systemctl stop service-name     # Stop service
   systemctl restart service-name  # Restart service
   systemctl reload service-name   # Reload config
   ```

2. **Boot Configuration:**
   ```bash
   systemctl enable service-name   # Enable at boot
   systemctl disable service-name  # Disable at boot
   ```

3. **Status and Information:**
   ```bash
   systemctl status service-name   # Check status
   systemctl list-units --type=service  # List all services
   ```

### SCP (Secure Copy) Between VMs

```
[Source VM] ----SCP----> [Destination VM]
```

**Process Steps:**
1. Upload file to source VM
2. Set permissions:
   ```bash
   $ chmod 400 keyfile.pem
   ```
3. Execute transfer:
   ```bash
   $ scp -i <pem-file> <source-path> username@dest-ip:/dest/path
   ```

**Example:**
```bash
$ sudo scp -i linux-devops-keypair.pem alien.txt \
  ec2-user@13.127.111.178:/home/ec2-user/
```

## 3. Linux Architecture

### Core Components

```
    [Applications]
         ↓
      [Shell]
         ↓
      [Kernel]
         ↓
    [Hardware]
```

1. **Hardware Layer:**
   - Physical components
   - Base infrastructure

2. **Kernel Layer:**
   - Core operating system
   - Manages hardware resources
   - Translates commands to machine code

3. **Shell Layer:**
   - User interface to kernel
   - Interprets commands
   - Provides command-line environment

4. **Application Layer:**
   - User programs
   - System utilities
   - Third-party software

### Command Processing Flow
1. User inputs command in shell
2. Shell interprets command syntax
3. Kernel processes the instruction
4. Hardware executes the operation

## 4. Shell Scripting

### Basic Commands
```bash
whoami  # Current user
pwd     # Present working directory
date    # System date/time
ls -l   # Detailed directory listing
cal     # Calendar
```

### Script Creation
```bash
# Create new script
$ vi first-script.sh

# View script content
$ cat first-script.sh

# Execute script
$ sh first-script.sh
```

### File System Operations
- File handling operations
- Text editing and processing
- User account management
- Permission controls
- Ownership management
- Network configuration
- Package management
- System documentation

## Linux Architecture Overview

Linux is a:
- CLI-based free and open source OS
- Secure multi-user system
- Highly recommended for servers:
  - Docker
  - Jenkins
  - k8s
  - Nexus
  - Sonar
  - Web servers