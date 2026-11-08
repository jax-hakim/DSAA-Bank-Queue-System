# DSAA Bank Queue System

A Java-based bank queue simulation system developed as part of the Data Structures and Algorithms (DSAA) Final Personal Assignment at BINUS University.

## Project Overview

This project simulates a bank queue management system consisting of two customer categories: VIP and Regular.

The system uses manually implemented circular arrays to manage customer queues and a weighted Round Robin scheduling algorithm to determine service order. The scheduling mechanism is designed to maintain service efficiency while preventing starvation of Regular customers.

## Features

- Manual Circular Array implementation without Java Collection Framework.
- Separate queues for VIP and Regular customers.
- Weighted Round Robin scheduling with a 3:1 VIP-to-Regular service ratio.
- FIFO (First-In, First-Out) service within each customer category.
- Anti-starvation mechanism for Regular customers.
- Queue overflow and underflow exception handling.
- Input validation.
- Ticket number management.

## Data Structures and Algorithms

| Component | Implementation |
|---|---|
| Queue | Manual Circular Array |
| VIP Queue | Circular Array |
| Regular Queue | Circular Array |
| Scheduling Algorithm | Weighted Round Robin (3:1) |
| Customer Ordering | FIFO |
| Queue Access | Front and Rear Pointers |

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

### 1. Clone the Repository

```bash
git clone https://github.com/jax-hakim/DSAA-Bank-Queue-System.git
```

### 2. Navigate to the Project Directory

```bash
cd DSAA-Bank-Queue-System
```

### 3. Compile the Java Source Files

```bash
javac -d out src/com/dsaa/bankqueue/*.java
```

### 4. Run the Application

```bash
java -cp out com.dsaa.bankqueue.BankQueueApp
```

## Complexity Analysis

| Operation | Time Complexity |
|---|---|
| Enqueue | O(1) |
| Dequeue | O(1) |
| Display Queue | O(n) |
| Queue Storage | O(C) |

Where:
- `n` represents the number of customers currently in the queue.
- `C` represents the maximum queue capacity.

## Author

**Muhammad Taufiq Al Hakim**

BINUS University  
Data Structures and Algorithms (DSAA)

## License

This project was developed for academic purposes.
