# Shell Scripting Notes 📝

## Introduction to Shell Scripting 🔍

Shell scripting is a way to automate tasks in Unix/Linux systems. It allows us to write a sequence of commands in a file and execute them together.

## 1. Shebang Line in Linux
**Theory:** The shebang (#!) is a special marker that tells the system which interpreter to use to run the script. It must be the first line of the script.

```bash
#!/bin/bash
```

**What it does:** When you execute a script, the system looks at the shebang line to determine which program should interpret the rest of the file.

## 2. Variables & Input/Output
**Theory:** Variables store data that can be referenced and manipulated throughout the script. Linux shell supports user input/output operations for interactive scripts.

### Variable Rules
* ❌ No digits at start
* ❌ No special characters (., @, $, #)
* ✅ UPPERCASE names recommended

### Basic I/O Example:
```bash
#!/bin/bash

echo "Enter Your First Name"
read FNAME

echo "Enter Your Last Name"
read LNAME

echo "Your Full Name: $FNAME $LNAME"
```

### Variable Types:

1. **System Variables**
**Theory:** Predefined variables set by the system that hold important system information.
```bash
$ echo $SHELL
$ echo $USER
$ echo $PATH
```

2. **User Defined Variables**
**Theory:** Custom variables created by users to store temporary data during script execution.
```bash
FNAME=ankit
LNAME=Singh
AGE=22
```

## 3. Working with Variables
**Theory:** Variables can be accessed, modified, and managed using specific shell commands. They can also be made permanent for future shell sessions.

### Access & Manage Variables:
```bash
# Get variable value
$ echo $VARIABLE_NAME

# Create new variable
$ export COURSE=DevOpsWithAWS

# Check variable value
$ echo $COURSE

# Remove variable
$ unset COURSE
```

## 4. Arithmetic Operations
**Theory:** Shell scripting supports basic arithmetic operations using special syntax. These operations are performed on numeric variables.

### Available Operations:
| Operation | Syntax |
|-----------|--------|
| Addition | `$((num1 + num2))` |
| Subtraction | `$((num1 - num2))` |
| Multiplication | `$((num1 * num2))` |
| Division | `$((num1 / num2))` |
| Modulus | `$((num1 % num2))` |


### Calculator Example:
```bash
#!/bin/bash

echo "Enter First Number to ADD"
read FNUM

echo "Enter Second Number to ADD"
read SNUM

echo "Result of Addition: $((FNUM+SNUM))"
```

**What it does:** Takes two numbers as input and performs arithmetic operations using shell arithmetic expansion $(()).

## 5. Control Structures
**Theory:** Control structures help in making decisions and controlling the flow of execution in scripts based on conditions.

### If-Else Statement (Number Comparison):
```bash
#!/bin/bash

echo "Enter the First Number"
read NUM1
echo "Enter the Second Number"
read NUM2

if [ $NUM1 -eq $NUM2 ]; then
    echo "Numbers are Equal"
else
    echo "Numbers are Not Equal"
fi
```

**What it does:** Compares two numbers and prints whether they are equal or not.

### If-Elif-Else Statement (Number Type Check):
```bash
#!/bin/bash

echo "Enter The Number"
read NUM1

if [ $NUM1 -gt 0 ]; then
    echo "Positive Number"
elif [ $NUM1 -lt 0 ]; then
    echo "Negative Number"
else
    echo "Number is Zero"
fi
```

**What it does:** Takes a number as input and determines if it's positive, negative, or zero using if-elif-else construct.

## 6. Loops
**Theory:** Loops allow repetitive execution of a set of commands until a specific condition is met.

### For Loop:
**Usage:** Used when you know the number of iterations in advance.
```bash
#!/bin/bash

for((i=1;i<=6;i++))
do
    echo "$i"
done
```

### While Loop:
**Usage:** Used when the number of iterations depends on a condition.
```bash
#!/bin/bash

echo "Enter the Number"
read NUM

while [ $NUM -le 6 ]
do
    echo "$NUM"
    let NUM++
done
```

## 7. Functions
**Theory:** Functions are reusable blocks of code that perform specific tasks. They help in organizing code and avoiding repetition.

### Basic Function:
**Usage:** Simple function demonstrating code organization.
```bash
#!/bin/bash

function greeting() {
    echo "Hello all!"
    echo "Welcome to Telusko!"
    echo "DevOps!"
}

greeting
```

### File Management Function:
**Usage:** Advanced function showing file operations and condition handling.
```bash
#!/bin/bash

function fileManager() {
    echo "Enter the file name"
    read FILENAME
    
    if [ -f "$FILENAME" ]; then
        echo "file is present the content is shown below"
        cat $FILENAME
    else
        echo "File is not present with name hence creating new file"
        touch $FILENAME
        echo "File is created"
    fi
}

fileManager
```

## Running Scripts 🚀
**Theory:** Scripts need proper permissions and can be executed in multiple ways. Different execution methods provide flexibility in how you run your scripts.

### Ways to Execute Scripts:

1. Make script executable:
```bash
chmod +x script_name.sh
./script_name.sh
```

2. Using bash interpreter:
```bash
bash script_name.sh
```

3. Using sh interpreter:
```bash
sh script_name.sh
```

> **💡 Pro Tips:**
> - Always test scripts in a safe environment
> - Use comments to explain complex logic
> - Follow consistent naming conventions
> - Keep scripts modular and reusable
> - `./script_name.sh` requires executable permissions
> - `bash` or `sh` works without changing file permissions


---

*These notes cover the fundamentals of shell scripting with explanations of what each concept does and how it works. Practice each concept to better understand them.*
