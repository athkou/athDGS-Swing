/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.kourtzis.dgs.view;

import gr.kourtzis.dgs.controller.SignInManager;
import gr.kourtzis.dgs.util.Util;
import javax.swing.JOptionPane;

/**
 *
 * @author Athanasios Kourtzis
 */
public class SignInDialog extends javax.swing.JDialog {
    private final static int ADMIN = 1;
    private final static int SUPPORT = 2;
    private final static int ERROR = -1;
    
    private SignInManager signInManager;
    private String username;
    private String encryptedPassword;
    
    boolean cancelled;

    /**
     * Creates new form SignInDialog
     */
    public SignInDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        cancelled = true;
        
        textUsername.setText("bb@test.de");
        textPassword.setText("passwordencrypted");
    }
    
    /**
     * 
     * @return 
     */
    public boolean isCancelled() {
        return cancelled;
    } 
    
    /**
     * 
     * @return 
     */
    public int userRegistered() {
        try {
            init();

            if (signInManager.credentialMatched(username, encryptedPassword)
                    && signInManager.getUser().getAppRole().equalsIgnoreCase("admin")) {
                return ADMIN;
            } else if (signInManager.credentialMatched(username, encryptedPassword)
                    && signInManager.getUser().getAppRole().equalsIgnoreCase("support")) {
                return SUPPORT;
            } else {
                return ERROR;
            }

        } catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(null, "Was not possible to authenticate the user. Please confirm that the application server is running properly");
            return -1;
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

        labelUsername = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        textUsername = new javax.swing.JTextField();
        textPassword = new javax.swing.JPasswordField();
        buttonSignIn = new javax.swing.JButton();
        buttonCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        labelUsername.setText("Username");

        jLabel1.setText("Password");

        buttonSignIn.setText("Sign in");
        buttonSignIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSignInActionPerformed(evt);
            }
        });

        buttonCancel.setText("Cancel");
        buttonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonSignIn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonCancel))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(labelUsername)
                            .addGap(18, 18, 18)
                            .addComponent(textUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addGap(18, 18, 18)
                            .addComponent(textPassword))))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelUsername)
                    .addComponent(textUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(textPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonSignIn)
                    .addComponent(buttonCancel))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelActionPerformed
        cancelled = true;
        clear();
        dispose();
    }//GEN-LAST:event_buttonCancelActionPerformed

    private void buttonSignInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSignInActionPerformed
        cancelled = false;
        getCredentials();
        clear();
        dispose();
    }//GEN-LAST:event_buttonSignInActionPerformed

    private void getCredentials() {
        username = textUsername.getText();
        encryptedPassword = encrypt(textPassword.getPassword());
    }
    
    private String encrypt(char[] password) {
        return Util.createPassword(String.valueOf(password));
                
    }
    
    private void init() {
        if(signInManager == null)
            signInManager = new SignInManager(username);
        else
            signInManager.readUserFromDb(username);
    }
    
    private void clear() {
        textUsername.setText("");
        textPassword.setText("");
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCancel;
    private javax.swing.JButton buttonSignIn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel labelUsername;
    private javax.swing.JPasswordField textPassword;
    private javax.swing.JTextField textUsername;
    // End of variables declaration//GEN-END:variables
}