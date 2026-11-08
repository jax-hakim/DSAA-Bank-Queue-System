# DSAA Bank Queue System

A Java-based bank queue simulation system developed as part of the Data Structures and Algorithms (DSAA) Final Personal Assignment.

## Project Overview

This project simulates a bank queue management system with two customer categories: VIP and Regular. It implements manual circular arrays and a weighted Round Robin scheduling mechanism to manage customer service efficiently while preventing starvation.

## Features

- Manual Circular Array implementation without Java Collection Framework.
- Separate queues for VIP and Regular customers.
- Weighted Round Robin scheduling with a 3:1 VIP-to-Regular service ratio.
- FIFO (First-In, First-Out) service within each customer category.
- Anti-starvation mechanism for Regular customers.
- Queue overflow and underflow exception handling.
- Input validation and ticket number management.

## Data Structures and Algorithms

| Component | Implementation |
|---|---|
| Queue | Manual Circular Array |
| Scheduling | Weighted Round Robin (3:1) |
| Customer ordering | FIFO |
| VIP Queue | Circular Array |
| Regular Queue | Circular Array |

## Project Structure

```text
DSAA-Bank-Queue-System/
├── README.md
├── .gitignore
└── src/
    └── com/
        └── dsaa/
            └── bankqueue/
                ├── BankQueueApp.java
                ├── BankQueueSystem.java
                ├── CircularQueue.java
                ├── Customer.java
                ├── CustomerType.java
                ├── QueueEmptyException.java
                ├── QueueFullException.java
                └── QueueOverflowException.java
```

## Requirements

- Java Development Kit (JDK) 8 or later.
- Terminal or command prompt.

## How to Run

Clone this repository:

```bash
git clone https://github.com/jax-hakim/DSAA-Bank-Queue-System.git
```

Navigate to the project directory:

```bash
cd DSAA-Bank-Queue-System
```

Compile the Java source files:

```bash
javac -d out src/com/dsaa/bankqueue/*.java
```

Run the application:

```bash
java -cp out com.dsaa.bankqueue.BankQueueApp
```

## Author

**Muhammad Taufiq Al Hakim**

Data Structures and Algorithms (DSAA)  
BINUS University
