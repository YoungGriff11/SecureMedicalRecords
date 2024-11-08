package com.mycompany.server;

import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    private static ServerSocket servSock;
    private static final int PORT = 1234;
    private static int clientConnections = 0;
    private static List<ClientHandler> clients = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Opening port...\n");
        try {
            servSock = new ServerSocket(PORT);
        } catch (IOException e) {
            System.out.println("Unable to attach to port!");
            System.exit(1);
        }

        while (true) {
            try {
                Socket link = servSock.accept();
                clientConnections++;
                System.out.println("Client " + clientConnections + " connected.");
                ClientHandler handler = new ClientHandler(link, clientConnections);
                clients.add(handler);
                new Thread(handler).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static synchronized void broadcast(String message, int senderId) {
        for (ClientHandler client : clients) {
            client.sendMessage("Client " + senderId + ": " + message);
        }
    }
}

class ClientHandler implements Runnable {
    private Socket client;
    private int clientId;
    private PrintWriter out;

    public ClientHandler(Socket socket, int clientId) {
        this.client = socket;
        this.clientId = clientId;
    }

    public int getClientId() {
        return clientId;
    }

    public void sendMessage(String message) {
        out.println(message);
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()))) {
            out = new PrintWriter(client.getOutputStream(), true);

            String message;
            while ((message = in.readLine()) != null) {
                System.out.println("Message received from client " + clientId + ": " + message);
                Server.broadcast(message, clientId);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                System.out.println("Closing connection with client " + clientId);
                client.close();
            } catch (IOException e) {
                System.out.println("Unable to disconnect from client " + clientId);
            }
        }
    }
}
