# Factory Production Order System

## About the Project

This project is a Java based Factory Production Order System. It is made to manage basic production activities in a manufacturing unit.

The system allows us to manage products, machines, operators and production orders. We can also assign machines and operators to orders and update the status of an order.

The project also has a GUI made using Java Swing.

## Features

- Add and view products
- Add and view machines
- Add and view operators
- Create production orders
- Search products and orders
- Assign machines and operators to orders
- Update order status
- Complete or cancel orders
- View production summary
- Save and load data using File I/O
- Validation for incorrect input
- Custom exceptions for different errors

## Technologies Used

- Java
- Java Swing
- Java Collections
- LocalDate
- File I/O
- Object Oriented Programming

## Project Structure

```text
src
│
├── Main.java
├── Product.java
├── Machine.java
├── Operator.java
├── ProductionOrder.java
├── OrderStatus.java
├── FactoryManager.java
├── FileManager.java
├── FactoryGUI.java
│
└── exceptions
    ├── InvalidQuantityException.java
    ├── MachineNotAvailableException.java
    └── ProductNotFoundException.java
