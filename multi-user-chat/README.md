# Multi-User Chat Application

This is a simple multi-user chat application built in Java. It consists of a server and a client, allowing multiple users to connect to a chat room and exchange messages.

## Project Structure

- `src/`: Contains the Java source code for the server and client.
  - `Server.java`: The server application.
  - `Client.java`: The client application.
- `bin/`: Contains the compiled Java class files.

## How to Compile and Run

You can compile and run the application from the command line.

### Compile

To compile the server and client, navigate to the `multi-user-chat` directory and run the following command:

```bash
javac -d bin src/Server.java src/Client.java
```

This will compile the `.java` files from the `src` directory and place the resulting `.class` files in the `bin` directory.

### Run the Server

To run the server, execute the following command from the `multi-user-chat` directory:

```bash
java -cp bin Server
```

The server will start and print a message to the console, indicating that it is running.

### Run the Client

To run the client, execute the following command from the `multi-user-chat` directory:

```bash
java -cp bin Client
```

A GUI window will open. You will be prompted to enter the server's IP address. If you are running the client and server on the same machine, you can use `127.0.0.1` or `localhost`. After that, you will be prompted to choose a screen name.

You can run multiple instances of the client to have multiple users in the chat room.

## Communication Protocol

The client and server communicate over a socket connection using a simple text-based protocol. All messages are terminated by a newline character.

### Server to Client

- `SUBMIT-NAME`: The server prompts the client to submit a screen name.
- `JOINED <name>`: The server informs the client that a new user with the specified `<name>` has joined the chat room.
- `LEFT <name>`: The server informs the client that the user with the specified `<name>` has left the chat room.
- `MESSAGE <name>: <text>`: The server broadcasts a message from `<name>` with the content `<text>`.

### Client to Server

- `<name>`: The client sends its desired screen name to the server in response to the `SUBMIT-NAME` prompt.
- `<text>`: The client sends a chat message with the content `<text>` to be broadcast to all other clients.
