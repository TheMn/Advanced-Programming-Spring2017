# Java Projects Collection - Spring 2017

This repository contains a collection of five Java projects, each demonstrating different programming concepts and skills.

## Projects

1.  **Roman Numerals**: A command-line application that processes financial data, including amounts in Roman numerals.
2.  **String Commands**: A command-line application that performs various string manipulations based on user commands.
3.  **Hangman Game**: A classic Hangman game with a command-line interface. It includes a ranking system.
4.  **Hospital Management System**: A command-line application for managing patients, doctors, diseases, and medicines in a hospital.
5.  **Flood-It Multiplayer**: A multiplayer version of the Flood-It game with a graphical user interface.

## How to Run

Each project is self-contained in its own directory. To run a project, you will need to have a Java Development Kit (JDK) installed.

### 1. Roman Numerals

To run this project, compile the `Programmer.java` file and then run the `Programmer` class:

```bash
cd "1. Roman Numerals/src"
javac poroje1/Programmer.java
java poroje1.Programmer
```

### 2. String Commands

To run this project, compile the `StringCommands.java` and `Methods.java` files and then run the `StringCommands` class:

```bash
cd "2. String Commands/src"
javac com/themn/stringCommands/Methods.java com/themn/stringCommands/StringCommands.java
java com.themn.stringCommands.StringCommands
```

### 3. Hangman Game

To run this project, compile all the `.java` files in the `poroje3` directory and then run the `Hangman` class:

```bash
cd "3. Hangman Game/src"
javac poroje3/*.java
java poroje3.Hangman
```

### 4. Hospital Management System

To run this project, compile all the `.java` files in the `poroje4` directory and then run the `HospitalManager` class:

```bash
cd "4. Hospital Management System/src"
javac poroje4/*.java
java poroje4.HospitalManager
```

### 5. Flood-It Multiplayer

This project has a client-server architecture. To run it, you first need to start the server and then connect one or more clients.

**To start the server:**

```bash
cd "5. Flood-It Multiplayer/src"
javac *.java
java MainClass
```

Then, in the GUI, select "MultiPlayer" and then "Host a server".

**To start a client:**

Run the `MainClass` again:

```bash
cd "5. Flood-It Multiplayer/src"
java MainClass
```

In the GUI, select "MultiPlayer" and then "Join a server". Enter the server address (e.g., "localhost:1973") to connect.
