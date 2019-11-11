/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.kourtzis.dgs.view;

import gr.kourtzis.dgs.entity.Game;

/**
 *
 * @author Athanasios Kourtzis
 */
public class GameCrudDialog extends javax.swing.JDialog {
    private Game game;
    private boolean saved;
    /**
     * Creates new form gameCrudDialog
     */
    public GameCrudDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        saved = false;
    }
    
    /**
     * The method gets called when a new game should be created
     * and saved on the database.
     * @param game The game to be created
     * @param message The message shown in the title bar.
     * @return Returns true if the game was created or false if the 
     *         user aborts the dialog.
     */
    public boolean showNewDialog(final Game game, final String message) {
        textGameId.setText("");
        
        prepareFields("Create", true, true);
        return showDialog(game, message); 
    }
    
    /**
     * The method updates an existing game in the database
     * @param game The game to be edited.
     * @param message The message shown in the title bar.
     * @return True if the game was updated of false if 
     *         the user aborts the dialog.
     */
    public boolean showUpdateDialog(final Game game, final String message) {
        prepareFields("Update", true, true);
        return showDialog(game, message);
    }
    
    /**
     * The method deletes an existing game from the database.
     * @param game The game we want to delete.
     * @param message The message shown in the title bar.
     * @return True if the game was successfully deleted or false
     *         if the dialog was aborted.
     */
    public boolean showDeleteDialog(final Game game, final String message) {
        prepareFields("Delete", false, false);
        return showDialog(game, message);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textGameId = new javax.swing.JTextField();
        labelTitle = new javax.swing.JLabel();
        textTitle = new javax.swing.JTextField();
        labelDescription = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textDescription = new javax.swing.JTextArea();
        buttonCancel = new javax.swing.JButton();
        buttonConfirm = new javax.swing.JButton();
        labelGameId = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        textGameId.setEditable(false);

        labelTitle.setText("Title");

        labelDescription.setText("Description");

        textDescription.setColumns(20);
        textDescription.setRows(5);
        jScrollPane1.setViewportView(textDescription);

        buttonCancel.setText("Cancel");
        buttonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelActionPerformed(evt);
            }
        });

        buttonConfirm.setText("Save");
        buttonConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonConfirmActionPerformed(evt);
            }
        });

        labelGameId.setText("Game id");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(labelTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelGameId, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textTitle)
                            .addComponent(textGameId)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelDescription)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(buttonConfirm)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonCancel)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(textGameId)
                    .addComponent(labelGameId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(textTitle)
                    .addComponent(labelTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelDescription)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonConfirm)
                    .addComponent(buttonCancel))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelActionPerformed
        saved = false;
        dispose();
    }//GEN-LAST:event_buttonCancelActionPerformed

    private void buttonConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonConfirmActionPerformed
        saved = true;
        readTextFields();
        dispose();
    }//GEN-LAST:event_buttonConfirmActionPerformed

    private boolean showDialog(final Game game, final String title) {
        setTitle(title);
        this.game = game;
        fillTextFields();
        textTitle.requestFocus();
        setVisible(true);
        
        return saved;
    }
    
    private void prepareFields(final String buttonCaption, 
                               boolean genreButtonEnabled,
                               boolean descButtonEnabled) {
        buttonConfirm.setText(buttonCaption);
        textTitle.setEditable(genreButtonEnabled);
        textDescription.setEditable(descButtonEnabled);
        
    }
    private void fillTextFields() {
        if(game != null) {
            if (game.getGameId() != 0) {
                textGameId.setText(String.valueOf(game.getGameId()));
            }

            textTitle.setText(game.getTitle());
            textDescription.setText(game.getDescription());
        }
    }
    
    private void readTextFields() {
        if (game != null) {
            game.setTitle(textTitle.getText());
            game.setDescription(textDescription.getText());
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCancel;
    private javax.swing.JButton buttonConfirm;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelDescription;
    private javax.swing.JLabel labelGameId;
    private javax.swing.JLabel labelTitle;
    private javax.swing.JTextArea textDescription;
    private javax.swing.JTextField textGameId;
    private javax.swing.JTextField textTitle;
    // End of variables declaration//GEN-END:variables
}
