# Ansible 
Ansible is an **open-source automation tool** used for configuration management, application deployment, and task automation. It helps manage multiple machines from a central system using SSH and YAML playbooks.

## System Roles
- **CN (Control Node)**: The main node where Ansible is installed and from which playbooks/commands are run.
- **MN (Managed Node)**: The remote systems controlled by the control node via SSH.

## Ansible Essentials

- **Ad-hoc Commands**: One-line commands for quick tasks
- **Playbook**: YAML files containing sets of instructions (tasks) to be executed on managed nodes.

**Playbook Sections**
1. Hosts section
2. Variable section
3. Tasks section

### Initial Setup
- Start all control node and managed node machines
- Connect to control node
  
```bash
# Switch to Ansible user
sudo su ansible

# Navigate to Ansible directory
cd ~  # OR cd ../ansible

# Current directory
pwd
# Output: /home/ansible/
```

## Ansible Playbook Structure
```yaml
- hosts: all       # Host section
  vars:            # Variable section (optional)
  tasks:           # Task section
    - name: Task name
      module_name:
        option1: value1
```

## Verbosity Levels
```bash
ansible-playbook 01-ping.yml
ansible-playbook 01-ping.yml -v     # Basic verbosity
ansible-playbook 01-ping.yml -vv    # More detailed
ansible-playbook 01-ping.yml -vvv   # Extensive detail
ansible-playbook 01-ping.yml -vvvv  # Extremely verbose
```

### Playbook Checks
```bash
ansible-playbook 01-ping.yml --syntax-check      # Syntax check
ansible-playbook 01-ping.yml --list-hosts        # List affected hosts
ansible-playbook 01-ping.yml --step              # Confirm before each task
ansible-playbook 01-ping.yml --check             # Dry-run (simulate execution)
```

---


## Playbook Examples

### 1. Ping All Managed Nodes
**File:** `01-ping.yml`
```yaml
- hosts: all
  tasks:
    - name: Ping all managed nodes
      ping:
```

### 2. Create File on All Managed Nodes
**File:** `02-create-file.yml`
```yaml
- hosts: all
  tasks:
    - name: Create file in all managed nodes
      file:
        path: /home/ansible/alien.txt
        state: touch
```

### 3. Install Git 
**File:** `03-install-git.yml`
```yaml
- hosts: all
  become: true
  tasks:
    - name: Installing Git in all managed nodes
      yum:
        name: git
        state: latest
```

### 4. Static Website Hosting on Webservers
**File:** `04-website-hosting.yml`
```yaml
- hosts: webservers
  become: true
  tasks:
    - name: Install httpd
      yum:
        name: httpd
        state: latest

    - name: Copy index.html
      copy:
        src: index.html
        dest: /var/www/html/index.html

    - name: Start httpd
      service:
        name: httpd
        state: started
```

Test Commands:
```bash
ansible-playbook 04-website-hosting.yml --list-hosts
ansible-playbook 04-website-hosting.yml --syntax-check
ansible-playbook 04-website-hosting.yml
```

---

## Variables in Ansible

### 1. Runtime Variable
```yaml
- hosts: webservers
  become: true
  tasks:
    - name: Install {{ package_name }}
      yum:
        name: "{{ package_name }}"
        state: latest
```
Command:
```bash
ansible-playbook runtime-example.yml --extra-vars "package_name=httpd"
```

### 2. Playbook Variable
```yaml
- hosts: webservers
  become: true
  vars:
    package_name: httpd
  tasks:
    - name: Install {{ package_name }}
      yum:
        name: "{{ package_name }}"
        state: latest
```

### 3. Group Variable
```bash
# Create group_vars directory
sudo mkdir /etc/ansible/group_vars

# File: /etc/ansible/group_vars/webservers.yml
package_name: java
```

### 4. Host Variable
```bash
# Create host_vars directory
sudo mkdir /etc/ansible/host_vars

# File: /etc/ansible/host_vars/172.16.0.10.yml
package_name: nginx
```

---

## Handlers and Tags
- **Handlers** : Run a task only when notified by another task.
- **Tags** : Run or skip specific tasks using tag names.
  
```yaml
- hosts: webservers
  become: true
  tasks:
    - name: Install httpd
      yum:
        name: httpd
        state: latest
      tags:
        - install

    - name: Copy index.html
      copy:
        src: index.html
        dest: /var/www/html/index.html
      tags:
        - copy
      notify: start httpd

  handlers:
    - name: start httpd
      service:
        name: httpd
        state: started
```

Test Commands:
```bash
ansible-playbook 05-handlers.yml --syntax-check
ansible-playbook 05-handlers.yml --list-tags
ansible-playbook 05-handlers.yml --tags "copy"
ansible-playbook 05-handlers.yml --skip-tags "install,copy"
```

---


## Ansible Vault
Ansible Vault is used to secure sensitive data within playbooks (e.g., passwords, keys).

### Basic Commands
```bash
ansible-vault encrypt secret.yml        # Encrypt a file
ansible-vault decrypt secret.yml        # Decrypt a file
ansible-vault view secret.yml           # View contents securely
ansible-vault edit secret.yml           # Edit an encrypted file
```

---

## Summary
Ansible is a powerful tool for automating infrastructure management through:
- Control/Managed Node architecture
- YAML-based Playbooks
- Ad-hoc commands for quick tasks
- Handlers & Tags for conditional execution
- Variables (Runtime, Playbook, Group, Host)
- Ansible Vault for securing sensitive configurations

Use Ansible to simplify and scale infrastructure automation seamlessly.

