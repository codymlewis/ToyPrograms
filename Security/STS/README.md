# SENG2250 Assignment 3 Task 2
A java socket based messaging program using station-to-station protocol to authenticate,
Elliptic curve Diffie-Hellman is used in the STS to establish a shared secret key, RSA and
SHA-256 is used to handle the signiture, and triple DES in CTR mode is used for the encryption
both within the protocol and for sent messages.
## Requirements
* OpenJDK8
## Compiling
Run `javac *.java` in this root folder
## Running
Start the server with `java ChatServer`\
Then start the client with `java ChatClient`
