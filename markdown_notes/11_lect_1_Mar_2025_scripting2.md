# Shell Scripting-2
## Overview
Shell scripting allows automation of tasks and efficient execution of commands. This lecture covers:
- Command-line arguments
- Logging and error handling
- System information retrieval
- Log analysis
- File backups
- Project structure creation

---

## Command Line Arguments
Command-line arguments allow passing values to the script at execution time. Data can be passed during execution and utilized within the shell script.

#### Passing Arguments to a Script
```sh
$ sh f1.sh 44
```

### Special Variables for Arguments
| Symbol | Description |
|--------|-------------|
| `$#` | Total number of arguments passed |
| `$0` | Script file name |
| `$1` | First argument |
| `$2` | Second argument |
| `$*` | All command-line arguments |

#### Example Script
```sh
#!/bin/bash

echo "Total Args/info passed : $#"
echo "Script file name : $0"
echo "First Command Line Arg : $1"
echo "Second Command Line Arg : $2"
echo "All Command Line Args : $*"
```

**Execution**
```sh
sh 12-script.sh Telusko DevOps Learning
```
**Output:**
```plaintext
Total Args/info passed : 3
Script file name : 12-script.sh
First Command Line Arg : Telusko
Second Command Line Arg : DevOps
All Command Line Args : Telusko DevOps Learning
```

### Performing Operations with Arguments
```sh
#!/bin/bash
RESULT=$(($1 + $2))
echo "Sum of Data passed is : $RESULT"
```
**Execution:**
```sh
sh 13-script.sh 4 4
```
**Output:**
```plaintext
Sum of Data passed is : 8
```

---

## Comments in Shell Scripting
- Comments are used to provide metadata about scripts and commands.
- Metadata refers to data about data or information about information.
- Comments are not part of execution, as they are ignored by Bash.
- They help make the script easily understandable to anyone reading it.
  
### Single-line Comments
```sh
# This is a single-line comment

#To get the total number of args
echo "Total Args/info passed : $#"
# To get the script file name
echo "Script file name : $0"
```

### Multi-line Comments
```sh
<<COMMENT
This is a multi-line comment
used in shell scripting
COMMENT
```

---

## Working with Date
Date command retrieves the date on the terminal.

### Fetch and Store Date in a File
```sh
date > telusko.txt   # Overwrites the file
date >> telusko.txt  # Appends to the file
```

### Redirecting Output and Errors
| Operator | Function |
|----------|----------|
| `>`  | Overwrites output to a file |
| `>>` | Appends output to a file |
| `&>` | Redirects error messages |

#### Example:
```sh
mkdir alien &> error.log
cat error.log
```

---

## Logging System Messages
```sh
#!/bin/bash

#Define log file path
LOG_FILE=myapp.log

#function to log messages
log_message() {
    #local timestamp=$date (It will use by default pattern)
    local timestamp=$(date +"%Y-%m-%d %T")  #to get date in defined format
    local message=$1
    echo "[$timestamp] $message" >> $LOG_FILE
}

#call log fucntion
log_message "Script Execution Started"
echo "Regular message - 01"
echo "Regular message - 02"

#simulate error
mkdir java   # Intentional error

#call log function
log_message "Script Execution Completed"
```
### Redirect Logs
```sh
#redirect error messages to log file
exec 2>> $LOG_FILE
#redirect standard messages to log file
exec 1>> $LOG_FILE
```

---

## Fetching System Information
```sh
#!/bin/bash
#display current date and time
echo "Date & Time : $(date)"

#display host name of the system
echo "Hostname : $(hostname)"

#display uptime of the system
echo "System Uptime : $(uptime)"

#display disc usage
echo "Disk Usage:"; df -h

#display memory usage
echo "Memory Usage:"; free -h
```

---

## Log File Analysis
```sh
#!/bin/bash
LOG_FILE="/var/log/syslog"

#count error message occurrences
ERROR_COUNT=$(grep -c "ERROR" "$LOG_FILE")

#printing count of error messages
echo "Number of errors in system log file: $ERROR_COUNT"
```
**Execution:**
```sh
sh log-analyzer.sh
```

- System logs are stored in the /var directory, which contains a predefined subdirectory called log. Within log, there is a directory named syslog that holds all system log files.
---

## Creating File Backups (tar file)
A shell script for creating a file backup (tar file) requires two specifications:
1. Source Directory – The location from which files are collected for backup.
2. Target File – The tar file where the backed-up files will be stored.
   
### Backup Process Script
```sh
#!/bin/bash
SOURCE_DIR=/home/ubuntu/
TARGET_DIR=/home/ubuntu

echo "Backup process starting..."
tar -czvf "$SOURCE_DIR/backup_$(date +%Y%m%d).tar.gz" "$TARGET_DIR"
echo "Backup is completed with a tar file"
```
**Execution:**
```sh
sh backup.sh
```

---

## Creating a Project Structure
### Script to Generate Project Directories
```sh
#!/bin/bash
PROJECT_NAME="teluskoapp"
ROOT_DIR=$(pwd)

create_project() {
    mkdir $1
    mkdir $1/src
    mkdir $1/tests
    mkdir $1/docs
    mkdir $1/config
    touch $1/config/config.yaml
    touch $1/docs/README.md
    touch $1/src/app.js
    touch $1/src/main.css
    touch $1/src/index.html
    touch $1/tests/test.js
}

create_project $ROOT_DIR/$PROJECT_NAME
```
### Install `tree` to View Project Structure
```sh
sudo apt install tree
sudo snap install tree
tree teluskoapp
```

---

## Summary
| Feature | Description |
|---------|-------------|
| **Command Line Arguments** | Pass dynamic values to scripts |
| **Logging** | Store script outputs and errors in log files |
| **System Info** | Retrieve hostname, uptime, disk, and memory usage |
| **Log Analysis** | Count error occurrences in system logs |
| **File Backup** | Create compressed backups of directories |
| **Project Structure** | Automate the creation of folders and files |

---

