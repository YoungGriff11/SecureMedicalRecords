package com.mycompany._cody_server;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private static ServerSocket servSock;
    private static final int PORT = 1234;
    private static List<String> events = new ArrayList<>(); //array to store all the events

    public static void main(String[] args) {
        System.out.println("Opening port... \n");
        try {
            servSock = new ServerSocket(PORT);
        } catch (IOException e) {
            System.out.println("Unable to attach to port!");
            System.exit(1);
        }

        while (true) {
            try {
                Socket link = servSock.accept();
                Thread clientThread = new Thread(new ClientHandler(link, events));
                clientThread.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

class ClientHandler implements Runnable {
    private Socket client;
    private List<String> events; 
    private BufferedReader in;
    private PrintWriter out;

    public ClientHandler(Socket socket, List<String> events) {
        this.client = socket;
        this.events = events;
        try {
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintWriter(client.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            String message, response; //the message and response varibles
            do {
                message = in.readLine();//reads user message from client
                System.out.println("Message received from client: " + message); //prints th eclient message in the server terminal
                String[] parts = message.split(";"); //splits th emessage at each ; 
                String action = parts[0].trim(); //gets rid of any spaces at the spart and end of th emessage

                if (action.equalsIgnoreCase("add")) { //if the the first word is add it will add the clients message to the events array
                    String event = parts[1].trim() + "; " + parts[2].trim() + "; " + parts[3].trim();
                    synchronized (events) {
                        events.add(event); //add event to the list
                    }
                    response = "Current events on " + parts[1].trim() + ": " + getEventDate(parts[1].trim());

                } else if (action.equalsIgnoreCase("remove")) { //is the first word is remove is will remove the clients message from the array list
                    String event = parts[1].trim() + "; " + parts[2].trim() + "; " + parts[3].trim();
                    synchronized (events) {
                        events.remove(event); //removes event from the list
                    }
                    response = "Current events on " + parts[1].trim() + ": " + getEventDate(parts[1].trim()); //prints out the other events that are on th esame date are the recently added or removed event

                } else if (action.equalsIgnoreCase("STOP")) { //if the user enter STOP it terminates the connection
                    response = "SERVER RESPONSE> TERMINATE";
                } else { //if th euser inputs the events the wrong way it will show the right way to add or remove the event
                    response = "Enter event in the style - add/remove; date; time; event details";
                }
                out.println(response);//repond back to th eclient
            } while (!message.equalsIgnoreCase("STOP")); //keeps looping till it reads STOP
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (client != null) {
                    System.out.println("\n* Closing connection... *");
                    client.close();
                }
            } catch (IOException e) {
                System.out.println("Unable to disconnect!");
            }
        }
    }

    private String getEventDate(String date) {
        List<String> eventDate = new ArrayList<>();//new array list for all the events that have th esame date
        synchronized (events) {//only one thread can acces the event at a single time
            for (String event : events) {//looks through each event in the event array 
                if (event.startsWith(date + ";")) {//if th eevent starts with the same date are the recently added or removed
                    eventDate.add(event);//if it has th esame date it adds it to the eventdate list
                }
            }
        }
        return String.join("; ", eventDate); //adds all events in the events date list into one string
    }
}
