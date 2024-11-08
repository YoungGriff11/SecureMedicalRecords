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

    public PasswordManager() {
        passwordList = new ArrayList<>();
    }
    
    //method to add new password entry to list
    public void addPassword(String domain, String password) {
        String hashedPassword = hashPassword(password);//hashing password before proceeding, so no password availibility is breached
        
        PasswordEntry entry = new PasswordEntry(domain, hashedPassword);
        passwordList.add(entry);//adding password entry to the list
    }
    
    //hashing the inputted password
    private String hashPassword(String password){
        //generate salt
        String salt = BCrypt.gensalt();
        //hashing pass with newly generated salt
        return BCrypt.hashpw(password,salt);
    }
    
    //method that gets and eventually dislayes all stored passwords
    public List<PasswordEntry> getAllPasswords() {
        return passwordList; //returning the entire list of password entries
    }
    
    //used when retriecing specific password by domain
    public String getPassword(String domain) {
        //looping list to find domain
        for (PasswordEntry entry : passwordList) {
            if (entry.getDomain().equals(domain)) {
                return entry.getHashedPassword(); //return password if domain matches
            }
        }
        return null; //return nothing if no matching domain is found
    }
    
    //count all stored passwords
    public int getPasswordCount() {
        return passwordList.size();  //return the number of passwords stored
    }
    
    //method to check if a plain password matches the stored hash
    public boolean checkPassword(String domain, String password) {
        for (PasswordEntry entry : passwordList) {
            if (entry.getDomain().equals(domain)) {
                //compare the hashed password stored with the one entered
                return BCrypt.checkpw(password, entry.getHashedPassword());
            }
        }
        return false; //false if no matching domain is found
    }
}