/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.securedocument;

/**
 * GUI to sign and verify documents using digital signatures
 * Users provide the file path to the document they want to sign and verify
 * 
 * Author: Aaron
 */
public class SecureDocument extends javax.swing.JFrame {

    private KeyPairGeneratorExample keyPairGenerator; // Manages key generation

    public SecureDocument() {
        initComponents();
        try {
            keyPairGenerator = new KeyPairGeneratorExample(); // Generate keys on startup
        } catch (Exception e) {
            e.printStackTrace();
        }
    }  

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TitleLbl = new javax.swing.JLabel();
        FilePathTf = new javax.swing.JTextField();
        FilePathLbl = new javax.swing.JLabel();
        SignDocumentBtn = new javax.swing.JButton();
        SignatureLbl = new javax.swing.JLabel();
        SignatureTf = new javax.swing.JTextField();
        VerifyDocumentBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        ResultTa = new javax.swing.JTextArea();
        ExitBtn = new javax.swing.JButton();
        HomeBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        TitleLbl.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        TitleLbl.setText("Secure Document Signing");

        FilePathTf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FilePathTfActionPerformed(evt);
            }
        });

        FilePathLbl.setText("File Path:");

        SignDocumentBtn.setText("Sign Document");
        SignDocumentBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SignDocumentBtnActionPerformed(evt);
            }
        });

        SignatureLbl.setText("Signature:");

        SignatureTf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SignatureTfActionPerformed(evt);
            }
        });

        VerifyDocumentBtn.setText("Verify Document");
        VerifyDocumentBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VerifyDocumentBtnActionPerformed(evt);
            }
        });

        ResultTa.setColumns(20);
        ResultTa.setRows(5);
        jScrollPane1.setViewportView(ResultTa);

        ExitBtn.setText("Exit");
        ExitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitBtnActionPerformed(evt);
            }
        });

        HomeBtn.setText("Home");
        HomeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HomeBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(FilePathLbl)
                    .addComponent(SignatureLbl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(FilePathTf, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SignatureTf, javax.swing.GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(HomeBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(VerifyDocumentBtn)
                .addGap(130, 130, 130)
                .addComponent(ExitBtn)
                .addGap(36, 36, 36))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 551, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(SignDocumentBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(213, 213, 213))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(TitleLbl)
                        .addGap(165, 165, 165))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(TitleLbl)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(FilePathTf, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                    .addComponent(FilePathLbl))
                .addGap(17, 17, 17)
                .addComponent(SignDocumentBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SignatureTf, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SignatureLbl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ExitBtn)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(VerifyDocumentBtn)
                        .addComponent(HomeBtn)))
                .addGap(35, 35, 35))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void VerifyDocumentBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VerifyDocumentBtnActionPerformed
        // Handler for the "Verify Document" button click event
        // Attempts to verify the signature on the document at the provided file path
      try {
            String filePath = FilePathTf.getText();
            String signature = SignatureTf.getText();
            boolean isVerified = VerifySignature.verifyDocument(filePath, signature, keyPairGenerator.getPublicKey());
            ResultTa.setText("Verification " + (isVerified ? "successful." : "failed."));
        } catch (Exception e) {
            ResultTa.setText("Error verifying document:\n" + e.getMessage());
        }
    }//GEN-LAST:event_VerifyDocumentBtnActionPerformed

    private void SignatureTfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SignatureTfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SignatureTfActionPerformed

    private void SignDocumentBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SignDocumentBtnActionPerformed
     
        // Handler for the "Sign Document" button click event
        // Signs the document and displays the Base64-encoded signature
     
       try {
            String filePath = FilePathTf.getText();
            String signature = SignDocument.signDocument(filePath, keyPairGenerator.getPrivateKey());
            ResultTa.setText("Document signed successfully.\nDigital Signature:\n" + signature);
        } catch (Exception e) {
            ResultTa.setText("Error signing document:\n" + e.getMessage());
        }
    }//GEN-LAST:event_SignDocumentBtnActionPerformed

    private void FilePathTfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FilePathTfActionPerformed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_FilePathTfActionPerformed

    private void ExitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitBtnActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_ExitBtnActionPerformed

    private void HomeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HomeBtnActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_HomeBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
    try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(SecureDocument.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> new SecureDocument().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ExitBtn;
    private javax.swing.JLabel FilePathLbl;
    private javax.swing.JTextField FilePathTf;
    private javax.swing.JButton HomeBtn;
    private javax.swing.JTextArea ResultTa;
    private javax.swing.JButton SignDocumentBtn;
    private javax.swing.JLabel SignatureLbl;
    private javax.swing.JTextField SignatureTf;
    private javax.swing.JLabel TitleLbl;
    private javax.swing.JButton VerifyDocumentBtn;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
