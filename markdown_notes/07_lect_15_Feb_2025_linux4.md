# Linux User Management and File Permissions

## SSH Configuration and User Management

### EC2 to Linux VM SSH Setup

#### Initial Connection Setup
```bash
# First connect to EC2 instance using .pem file
ssh -i "your-key.pem" ec2-user@your-ec2-ip
```

#### Enable Password Authentication
```bash
# Step 1: Display current SSH configuration
$ sudo cat /etc/ssh/sshd_config
# Look for line: PasswordAuthentication no

# Step 2: Edit SSH configuration
$ sudo vi /etc/ssh/sshd_config

# Step 3: Make these changes in the file:
# Press 'i' for insert mode
PasswordAuthentication yes    # Change from 'no' to 'yes'
# Press 'esc' then type ':wq' and press enter to save

# Step 4: Restart SSH service to apply changes
$ sudo systemctl restart sshd
```

#### Practical Example
```bash
# 1. Initial connection with .pem file
$ ssh -i "myserver.pem" ec2-user@13.235.67.89

# 2. Check current SSH config
$ sudo cat /etc/ssh/sshd_config | grep PasswordAuthentication
# Output: PasswordAuthentication no

# 3. Edit configuration
$ sudo vi /etc/ssh/sshd_config
# Change line to: PasswordAuthentication yes

# 4. Verify changes
$ sudo cat /etc/ssh/sshd_config | grep PasswordAuthentication
# Output: PasswordAuthentication yes

# 5. Restart SSH
$ sudo systemctl restart sshd

# 6. Test new user connection from another terminal
$ ssh newuser@13.235.67.89
# It will now prompt for password instead of key
```

#### Common Issues and Solutions
```bash
# Issue 1: Permission denied (publickey)
# Solution: Check if PasswordAuthentication is yes
$ sudo cat /etc/ssh/sshd_config | grep PasswordAuthentication

# Issue 2: Connection refused
# Solution: Check if SSH service is running
$ sudo systemctl status sshd

# Issue 3: SSH service won't restart
# Solution: Check syntax in sshd_config
$ sudo sshd -t  # Test configuration
```

### Creating and Managing Users
```bash
# Connected to Linux VM as ec2-user with pem file
# Create new user
$ sudo useradd harsh

# Set password for new user
$ sudo passwd harsh

# Configure user in sudoers file
$ sudo visudo
# Add line: harsh ALL=(ALL:ALL) ALL --> esc --> ctrl+x+y+enter

# Enable PasswordBasedAuthentication
$ sudo vi /etc/ssh/sshd_config --> save:wq

# Restart SSH service
$ sudo systemctl restart sshd

# Connecting as new user 'harsh'
# Method 1: From same machine
$ ssh harsh@localhost
Password: (enter harsh's password)

# Method 2: From different machine (using EC2 public IP)
$ ssh harsh@13.235.67.89
Password: (enter harsh's password)

# Verify successful login
$ whoami
harsh

# Verify sudo access
$ sudo ls /root
[sudo] password for harsh: (enter harsh's password)

# Check user information
$ id harsh
uid=1001(harsh) gid=1001(harsh) groups=1001(harsh)

# If permission denied occurs, verify:
1. SSH service is running:
   $ sudo systemctl status sshd
2. Password authentication is enabled:
   $ sudo cat /etc/ssh/sshd_config | grep PasswordAuthentication
3. User has proper permissions:
   $ sudo grep harsh /etc/passwd
   $ sudo grep harsh /etc/shadow
```

## File Permissions in Linux

### Basic Permission Structure
Linux file permissions are divided into three categories for three different user types:

1. User/Owner (u)
2. Group (g)
3. Others (o)

Each category has three basic permission types:
- Read (r) → 4
- Write (w) → 2
- Execute (x) → 1

### Understanding Permission Notation

#### Symbolic Format
```
r w x   r w x   r w x
│ │ │   │ │ │   │ │ │
│ │ │   │ │ │   │ │ └── Others: Execute
│ │ │   │ │ │   │ └──── Others: Write
│ │ │   │ │ │   └────── Others: Read
│ │ │   │ │ └────────── Group: Execute
│ │ │   │ └──────────── Group: Write
│ │ │   └────────────── Group: Read
│ │ └──────────────────── User: Execute
│ └────────────────────── User: Write
└──────────────────────── User: Read
```

#### Numeric Format
Permission values (0-7):
```
0 = --- = No permission
1 = --x = Execute only
2 = -w- = Write only
3 = -wx = Write & Execute
4 = r-- = Read only
5 = r-x = Read & Execute
6 = rw- = Read & Write
7 = rwx = Read, Write & Execute
```

### Chmod Command Usage

#### Symbolic Method
```bash
# Basic syntax
chmod [u|g|o][+|-|=][r|w|x] filename

# Examples
chmod u+x file.txt    # Add execute permission for user
chmod g+w file.txt    # Add write permission for group
chmod o-x file.txt    # Remove execute permission for others
chmod o=w file.txt    # Set only write permission for others
chmod g+rwx file.txt  # Give all permissions to group
```

#### Numeric Method
```bash
# Common permission combinations
chmod 777 file.txt    # rwxrwxrwx (Full permissions)
chmod 755 file.txt    # rwxr-xr-x (Directory default)
chmod 644 file.txt    # rw-r--r-- (File default)
chmod 111 file.txt    # --x--x--x (Execute only)
chmod 444 file.txt    # r--r--r-- (Read only)
chmod 764 file.txt    # rwxrw-r-- (Custom setting)
```

### Default Permissions
```bash
# Default directory permissions
755 → rwxr-xr-x

# Default file permissions
644 → rw-r--r--

# Highest permissions
777 → rwxrwxrwx
```

## Ownership Management

### Chown Command
```bash
# Change owner
$ sudo chown username file/directory

# Change group
$ sudo chown :groupname file/directory

# Change both owner and group
$ sudo chown username:groupname file/directory
```

### Key Differences
- `chmod`: Changes file/directory permissions
- `chown`: Changes ownership (user/group)

### File Search
```bash
# Find file location
$ fs file location search
```

## Reference Quick Guide

### Permission Groups
- u → user (owner)
- g → group
- o → others

### Permission Types
- r → read (4)
- w → write (2)
- x → execute (1)

### Common Permission Patterns
```
rwx rwx rwx = 777 (Full access)
rwx r-x r-x = 755 (Directory default)
rw- r-- r-- = 644 (File default)
```