package com.mycompany._cody_client;

import java.io.*;
import java.net.*;

public class Client {

    private static InetAddress host;
    private static final int PORT = 1234;

    public static void main(String[] args) {
        try {
            host = InetAddress.getLocalHost(); //gets local host adress
        } catch (UnknownHostException e) {
            System.out.println("Host ID not found!"); //error message if it cant find the host 
            System.exit(1);
        }
        run(); // runs the run method if host found
    }

    private static void run() {
        Socket link = null;
        try {
            link = new Socket(host, PORT);
            BufferedReader in = new BufferedReader(new InputStreamReader(link.getInputStream()));
            PrintWriter out = new PrintWriter(link.getOutputStream(), true);

            BufferedReader userEntry = new BufferedReader(new InputStreamReader(System.in));
            String message = null; //stores users messages
            String response = null; //stores server messages
            
            do {
                System.out.println("Add, Remove or STOP Dublin Events (add/remove; date; time; event details): ");
                message = userEntry.readLine(); //reads users inputs
                out.println(message); //sends message to the server
                response = in.readLine();
                System.out.println("\n SERVER RESPONSE> " + response+ "\n");
            } while (!message.equals("STOP")); //keeps looping untill the user writes STOP
        } catch (IOException e) {
            e.printStackTrace();
        } finally { 
            try {
                if (link != null) {
                    System.out.println("\n SERVER RESPONSE> TERMINATE\n"); //server reponds with terminate when the users writes STOP
                    System.out.println("\n* Closing connection... *");
                    link.close(); //closes server
                }
            } catch (IOException e) {
                System.out.println("Unable to disconnect/close!");
                System.exit(1);
            }
        }
    }
}
