/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.securedocument;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.PublicKey;
import java.security.Signature;
import java.util.Base64;

/**
 * This class provides functionality to verify a documents digital signature using a public key
 * Digital signatures are used to confirm the authenticity and integrity of a document
 * ensuring it has not been tampered with
 * 
 * Author: Aaron
 */
public class VerifySignature {
    // Verifies the digital signature of a document by comparing it to an expected signature
    // param filePath - The path to the document file that needs verification
    // param signatureStr - The digital signature in Base64 encoded string format, provided for verification
    // param publicKey - The public key used to verify the documents signature
    // return True - If the signature is valid, indicating the document is authentic; false otherwise
    // throws Exception - If any error occurs during the verification process
     
    public static boolean verifyDocument(String filePath, String signatureStr, PublicKey publicKey) throws Exception {
        // Read the content of the document from the specified file path into a byte array
        byte[] data = Files.readAllBytes(Paths.get(filePath));

        // Decode the digital signature from its Base64 encoded string form to its original byte form
        byte[] digitalSignature = Base64.getDecoder().decode(signatureStr.trim());

        // Create a Signature object configured with the "SHA256withRSA" algorithm,
        // which combines the SHA-256 hashing algorithm with RSA for secure signature verification
        Signature signature = Signature.getInstance("SHA256withRSA");

        // Initialize the Signature object for verification, setting the provided public key
        // to be used in this process
        signature.initVerify(publicKey);

        // Feed the documents data to the Signature object, preparing it for verification
        signature.update(data);

        // Perform the verification process by comparing the provided digital signature
        // with the documents data 
        // Returns true if the signature is valid (document is authentic)
        // otherwise returns false
        return signature.verify(digitalSignature);
    }
}
