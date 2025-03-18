# AWS - Scheduling with CRON

## What is CRON?
- In Linux, **CRON** is used to schedule jobs/scripts for execution.
- It allows automated execution of scripts at specified time intervals.

## What is CRON Daemon (CROND)?
- **CROND** is a daemon process (background process).
- It runs in the background and checks every minute for scheduled **CRON jobs** to execute.

## What is a CRON Job?
- A scheduled job that allows running scripts or commands at specific intervals.

## Configuring CRON Jobs
- `crontab` is used to configure **CRON jobs** for execution.

## Checking CRON Status
- Use the following command to check if CRON is running:
  ```sh
  sudo systemctl status cron
  ```

## CRON Service Management
- Start CRON service:
  ```sh
  sudo systemctl start cron
  ```
- Stop CRON service:
  ```sh
  sudo systemctl stop cron
  ```
- Restart CRON service:
  ```sh
  sudo systemctl restart cron
  ```

## Managing CRON Jobs
- **List existing CRON jobs**:
  ```sh
  crontab -l
  ```
- **Remove all CRON jobs created by the user**:
  ```sh
  crontab -r
  ```

## CRON Job Syntax
```mermaid
graph TD;
    A[CRON Syntax] -->|Minute (0-59)| B
    A -->|Hour (0-23)| C
    A -->|Day of Month (1-31)| D
    A -->|Month (1-12)| E
    A -->|Day of Week (0-6, Sun=0)| F
    A -->|Command to Execute| G
```

- General syntax of a CRON job:
  ```sh
  * * * * * <script_file>
  ```
  Each `*` represents:
  - **Minute** (0-59)
  - **Hour** (0-23)
  - **Day of Month** (1-31)
  - **Month** (1-12)
  - **Day of Week** (0-6, where Sunday = 0)
  - **Command or script to execute**

### Examples of CRON Jobs
- Run every **15 minutes**:
  ```sh
  */15 * * * * <script_file>
  ```
- Run a script at **5:00 AM daily**:
  ```sh
  0 5 * * * <script_file>
  ```
- Run a script at **5:00 PM daily**:
  ```sh
  0 17 * * * <script_file>
  ```

 

