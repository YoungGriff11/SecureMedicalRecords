/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.securepasswordmanager;

import java.util.List;
import java.util.ArrayList;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author liamf
 */
public class PasswordManager {

    private List<PasswordEntry> passwordList; //list to hold PasswordEntry objects
    private DatabaseManager dbManager;

    public PasswordManager() {
        passwordList = new ArrayList<>();
        dbManager = new DatabaseManager();
        dbManager.connectToDatabase();
        dbManager.createTableIfNotExists();
        loadPasswordsFromDatabase();
    }

    //method to load passwords from DB to list
    private void loadPasswordsFromDatabase() {
        List<PasswordEntry> dbEntries = dbManager.getAllPasswordEntries();
        passwordList.addAll(dbEntries);
    }

    //method to add new password entry to list
    public void addPassword(String domain, String password) {
        String hashedPassword = hashPassword(password);//hashing password before proceeding, so no password availibility is breached

        PasswordEntry entry = new PasswordEntry(domain, hashedPassword);
        passwordList.add(entry);//adding password entry to the list
        dbManager.addPassword(domain, hashedPassword);
    }

    //hashing the inputted password
    private String hashPassword(String password) {
        //generate salt
        String salt = BCrypt.gensalt();
        //hashing pass with newly generated salt
        return BCrypt.hashpw(password, salt);
    }

    //method that gets and eventually dislayes all stored passwords
    public List<PasswordEntry> getAllPasswords() {
        return passwordList; //returning the entire list of password entries
    }

    public boolean verifyPassword(String domain, String inputPassword) {
        for (PasswordEntry entry : passwordList) {
            if (entry.getDomain().equals(domain)) {
                return BCrypt.checkpw(inputPassword, entry.getHashedPassword());
            }
        }
        return false;
    }

    public int getPasswordCount() {
        return passwordList.size();
    }

    public void deleteAllPasswords() {
        dbManager.deleteAllPasswords();
        passwordList.clear(); //clear the in-memory list }
    }
}
