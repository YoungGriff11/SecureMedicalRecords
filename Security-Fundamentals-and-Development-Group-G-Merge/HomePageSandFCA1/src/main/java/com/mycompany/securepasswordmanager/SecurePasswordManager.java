/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.securepasswordmanager;

/**
 *
 * @author liamf
 */
public class SecurePasswordManager {

    public static void main(String[] args) {
        // This will invoke the GUI on the Event Dispatch Thread (EDT)
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PasswordManagerGUI().setVisible(true); // displaying the GUI when ran
            }
        });
    }
}
