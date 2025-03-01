# Linux Command Line Notes

## 1. Find and Locate Commands
### locate
```bash
# Install locate
$ sudo yum install locate    # For RedHat/CentOS
$ sudo apt install locate    # For Debian/Ubuntu

# Search for apache
$ locate apache
```

### find
```bash
# Search for files with name demo.txt
$ sudo find /home -name demo.txt

# Search empty files
$ sudo find /home -type f -empty

# Search empty directories
$ sudo find /home -type d -empty

# Delete 1 hour old files
$ sudo find /home -mmin +60 -delete

# Delete 30 days old files
$ sudo find /home -mtime 30 -delete
```

## 2. Working with ZIP Files
```bash
# Create zip with txt files
$ zip telusko *.txt

# Display zip content
$ zip -sf telusko.zip

# Add new file to zip
$ zip -r telusko.zip alien4.txt

# Delete file from zip
$ zip -d telusko.zip alien4.txt

# Create password protected zip
$ zip -e telusko *.txt

# Extract zip
$ unzip telusko.zip

# Delete zip
$ rm telusko.zip
```

## 3. Networking Commands
```bash
# Check connectivity
$ ping www.google.com
$ ping ifconfig

# Download files using wget
$ wget https://wordpress.org/latest.zip
$ wget https://downloads.apache.org/tomcat/tomcat-9/v9.0.85/bin/apache-tomcat-9.0.85.zip

# Download using curl
$ curl -O https://wordpress.org/latest.zip
$ curl -o tomcat.zip https://downloads.apache.org/tomcat/tomcat-9/v9.0.85/bin/apache-tomcat-9.0.85.zip

# System monitoring
$ free    # Display memory details
$ top     # Display running processes
$ htop    # Display processes in table format
```

## 4. AWK Command
- Versatile text processing tool in Linux
- Used to manipulate and extract data from text files
- Processes line by line based on patterns

```bash
# Syntax
awk 'pattern {action}' file

# Examples
$ awk '{print $1}' employee.txt              # Print first column
$ awk '{print $1,$4}' employee.txt           # Print 1st and 4th column
$ awk '/manager/ {print $1}' employee.txt    # Print 1st column if line contains 'manager'
```

## 5. Linux Links
Similar to shortcuts in Windows, in Linux we can create:

### Hard Links
```bash
# Create hard link
$ ln <original file> <link file>
$ ln ft.txt ft1.txt     # ft1.txt is hard link to ft.txt
$ ls -li    # Check inode number

# Properties
- Same inode for both files
- Data added to main file reflects in link file
- If main file is removed, hard link stays
```

### Soft Links
```bash
# Create soft link
$ ln -s <originalfile> <softlink file>
$ ln -s originalfile softlink.file   # softlink.file is a soft link
$ ls -s                              # To see soft links

# Properties
- Soft link is like a shortcut link in Windows
- Both files will have different inode number
- If we delete original data then the shortcut link file will also be deleted
- Changes in original file will be affected in the new soft link file as well
```

## 6. Package Managers
Used to install/update/manage software packages in Linux machines

### Distribution-specific managers:
- Amazon Linux/RedHat/CentOS: yum
- Debian/Ubuntu: apt

```bash
# Install git
$ sudo yum install git    # For RedHat/CentOS
$ sudo apt install git    # For Debian/Ubuntu

# Install Java
$ sudo yum install java   # For RedHat/CentOS
$ sudo apt install openjdk-11-jdk   # For Debian/Ubuntu

# Check versions
$ git --version
$ java --version
```