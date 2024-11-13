package com.mycompany.server;

import java.io.*;
import java.net.*;
import java.util.*;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class Server {
    private static ServerSocket servSock; // accepts client connections
    private static final int PORT = 1234; // port number for connection
    private static int clientConnections = 0; // number of clients 
    private static List<ClientHandler> clients = new ArrayList<>(); // list for client handlers

    public static void main(String[] args) {
        System.out.println("Opening port...\n"); // server is ready to connect too
        try {
            servSock = new ServerSocket(PORT); // listening for the specfic port number (1234)
        } catch (IOException e) {
            System.out.println("Unable to attach to port!"); // prints error if it cant connect to prot
            System.exit(1); // closes program if it cant connect
        }

        while (true) {
            try {
                Socket link = servSock.accept(); //accepts clients joining connection
                clientConnections++; //adds one to the client count
                System.out.println("Client " + clientConnections + " connected."); //prints current connected clients 
                ClientHandler handler = new ClientHandler(link, clientConnections); //creats handler for the clients
                clients.add(handler); //ads handler to client list
                new Thread(handler).start();//new clients get a new handler
            } catch (IOException e) {
                e.printStackTrace();//prints stack trace if a error comes up
            }
        }
    }

    public static synchronized void broadcast(String message, int senderId) {
        for (ClientHandler client : clients) {
            client.sendMessage(message); // sends encrypted message
        }
    }
}

class ClientHandler implements Runnable {
    private static final String SECRET_KEY = "1234567890123456"; // encryption key can be any secure key
    private Socket client; //to communicate to client
    private int clientId; //client id
    private PrintWriter out; //sends message to clients

    public ClientHandler(Socket socket, int clientId) {
        this.client = socket; //initialises
        this.clientId = clientId; // initialises
    }

    private String decrypt(String encryptedData) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");//creates a secret key
        Cipher cipher = Cipher.getInstance("AES");//advanced encryption standard used by the us government
        cipher.init(Cipher.DECRYPT_MODE, secretKey); // gets ready to decrypt using aes
        byte[] decryptedData = cipher.doFinal(Base64.getDecoder().decode(encryptedData));//decrypts the encryptedData from base64
        return new String(decryptedData);//turns encrypted key into a string
    }

    public void sendMessage(String message) {
        out.println(message);//sends the message to the client
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()))) {
            out = new PrintWriter(client.getOutputStream(), true);

            String encryptedMessage; //to store encrypted message
            while ((encryptedMessage = in.readLine()) != null) {
                String message = decrypt(encryptedMessage); // Decrypt message from client
                System.out.println("Message received from client " + clientId + ": " + message); //puts client message in the log
                Server.broadcast(encryptedMessage, clientId); // Broadcast encrypted message to all clients
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                System.out.println("Closing connection with client " + clientId); //tells when clients disconnect
                client.close();
            } catch (IOException e) {
                System.out.println("Unable to disconnect from client " + clientId); //if clients cant disconnect
            }
        }
    }
}
