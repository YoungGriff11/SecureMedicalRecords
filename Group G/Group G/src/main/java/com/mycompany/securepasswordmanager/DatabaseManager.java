/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.securepasswordmanager;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author liamf
 */
public class DatabaseManager {

    private Connection connection;

    //connect to the SQLite
    public void connectToDatabase() {
        try { //nsure the directory exists
            File directory = new File("src/passdata");
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Establish connection to SQLite database (file-based)
            connection = DriverManager.getConnection("jdbc:sqlite:src/passdata/passwordmanager.db"); //file alllocation of the table and password data
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            System.out.println("Error connecting to SQLite database: " + e.getMessage());
        }
    }

    /* i will be using internal db creation,
    create table to store password entries if it doesn't exist */
    public void createTableIfNotExists() {
        // sql command to create table if it doesn't already exist
        String createTableSQL = "CREATE TABLE IF NOT EXISTS passwords ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "domain TEXT NOT NULL, " // holds the name of the website or service
                + "hashedPassword TEXT NOT NULL)"; // stores the hashed version of the password
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createTableSQL);
            System.out.println("table created or already exists."); // msg to confirm table status
        } catch (SQLException e) {
            System.out.println("error creating table: " + e.getMessage()); // error if table creation fails
        }
    }

// inserting a new password entry into the database
    public void addPassword(String domain, String hashedPassword) {
        // sql command to add a password entry with domain name and hashed password
        String insertSQL = "INSERT INTO passwords (domain, hashedPassword) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(insertSQL)) {
            pstmt.setString(1, domain); // setting domain parameter
            pstmt.setString(2, hashedPassword); // setting hashed password parameter
            pstmt.executeUpdate();
            System.out.println("password inserted successfully."); // confirms successful insert
        } catch (SQLException e) {
            System.out.println("error inserting password: " + e.getMessage()); // error if insertion fails
        }
    }

// retrieve a password's hash by domain name
    public String getPassword(String domain) {
        // sql command to select hashed password where domain matches
        String selectSQL = "SELECT hashedPassword FROM passwords WHERE domain = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(selectSQL)) {
            pstmt.setString(1, domain); // setting domain parameter to search for
            ResultSet rs = pstmt.executeQuery(); // execute the query and store results
            if (rs.next()) {
                return rs.getString("hashedPassword"); // returns the hashed password
            }
        } catch (SQLException e) {
            System.out.println("error retrieving password: " + e.getMessage()); // error if retrieval fails
        }
        return null; // returns null if no result found or error occurred
    }

//retrieve all password entries from the database
    public List<PasswordEntry> getAllPasswordEntries() {
        List<PasswordEntry> entries = new ArrayList<>();
        // sql command to select all domains and their hashed passwords
        String selectSQL = "SELECT domain, hashedPassword FROM passwords";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(selectSQL)) {
            while (rs.next()) {
                String domain = rs.getString("domain"); //retrieve domain
                String hashedPassword = rs.getString("hashedPassword"); // retrieve hashed password
                entries.add(new PasswordEntry(domain, hashedPassword)); // add to list
            }
        } catch (SQLException e) {
            System.out.println("error retrieving passwords: " + e.getMessage()); // error if retrieval fails
        }
        return entries; // return list of password entries
    }

//delete all password entries from the database
    public void deleteAllPasswords() {
        //sql command to delete all entries from passwords table
        String deleteSQL = "DELETE FROM passwords";
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(deleteSQL);
            System.out.println("all password entries have been deleted."); //confirmation msg
        } catch (SQLException e) {
            System.out.println("error deleting all passwords: " + e.getMessage()); //error if deletion fails
        }
    }

//count all stored passwords in the database
    public int getPasswordCount() {
        //sql command to count all rows in the passwords table
        String countSQL = "SELECT COUNT(*) FROM passwords";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(countSQL)) {
            if (rs.next()) {
                return rs.getInt(1); //return the count of passwords
            }
        } catch (SQLException e) {
            System.out.println("error counting passwords: " + e.getMessage()); //error if count fails
        }
        return 0; //returns 0 if no passwords found or error occurred
    }

    // Close the database connection
    public void closeDatabase() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            System.out.println("Error closing the database: " + e.getMessage());
        }
    }
}
