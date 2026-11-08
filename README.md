# DSAA Bank Queue System

A Java-based bank queue simulation system developed for the Data Structures and Algorithms (DSAA) final personal assignment.

## Features

- Manual Circular Array implementation
- Separate VIP and Regular customer queues
- Weighted Round Robin scheduling (3:1)
- FIFO service within each customer category
- Anti-starvation mechanism
- Queue overflow and underflow handling

## Technologies

- Java
- Data Structures
- Algorithms

## Project Structure

```text
src/
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
