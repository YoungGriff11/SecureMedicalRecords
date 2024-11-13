/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.securedocument;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.PrivateKey;
import java.security.Signature;
import java.util.Base64;

/**
 * This class provides functionality to sign a document using a private key 
 * A digital signature confirms the document's authenticity and integrity, 
 * as only the holder of the private key can generate a valid signature
 * 
 * Author: Aaron
 */
public class SignDocument {

    // Signs a documents content using the provided private key, producing 
    // a digital signature that verifies the document's authenticity
    // param filePath - The path to the document file that needs to be signed
    // param privateKey - The private key used to generate the documents signature
    // return - The digital signature as a Base64 encoded string, for easy storage and transmission
    // throws Exception - If any error occurs during the signing process
     
    public static String signDocument(String filePath, PrivateKey privateKey) throws Exception {
        // Read the documents content from the specified file path into a byte array
        byte[] data = Files.readAllBytes(Paths.get(filePath));

        // Create a Signature object initialized with the "SHA256withRSA" algorithm
        // This uses SHA-256 to hash the document and RSA to generate a secure signature
        Signature signature = Signature.getInstance("SHA256withRSA");

        // Initialize the Signature object for signing, setting it up with the private key
        signature.initSign(privateKey);

        // Pass the document data to the Signature object, preparing it to generate the signature
        signature.update(data);

        // Generate the digital signature in byte form, representing the hashed and signed document
        byte[] digitalSignature = signature.sign();

        // Encode the generated signature to a Base64 string for easy storage and transfer
        return Base64.getEncoder().encodeToString(digitalSignature);
    }
}
