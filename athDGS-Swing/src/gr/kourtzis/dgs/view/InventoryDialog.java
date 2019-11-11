/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.kourtzis.dgs.view;

import gr.kourtzis.dgs.ejb.InventoryAdministrationBeanRemote;
import gr.kourtzis.dgs.entity.Game;
import gr.kourtzis.dgs.entity.Inventory;
import gr.kourtzis.dgs.model.InventoryGamesTableModel;
import gr.kourtzis.dgs.util.SwingTools;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

/**
 *
 * @author Athanasios Kourtzis
 */
public class InventoryDialog extends javax.swing.JDialog {
    private final int ROW_IS_NOT_SELECTED = -1;
    
    private final InventoryGamesTableModel inventoryGameTableModel;
    
    private Map<Integer, Boolean> inventoryForGames;
    private InventoryCrudDialog inventoryCrudDialog;
    
    private List<Game> games;
    private Set<Integer> inventories;
    
    /**
     * Creates new form GameDialog
     */
    public InventoryDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        games = new ArrayList<>();
        inventoryForGames = new TreeMap<>();
        
        inventoryGameTableModel = new InventoryGamesTableModel();
        initMaps();
        initComponents();
        tableGames.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
       
    public void initMaps() {
        inventoryGameTableModel.readAllGames();
        games = inventoryGameTableModel.getGames();
        
        readInventories();
        initInventoryTreeMap();
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tableGames = new javax.swing.JTable();
        buttonUpdate = new javax.swing.JButton();
        buttonDelete = new javax.swing.JButton();
        buttonClose = new javax.swing.JButton();
        buttonRead = new javax.swing.JButton();
        buttonCreate = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        tableGames.setModel(inventoryGameTableModel);
        jScrollPane1.setViewportView(tableGames);

        buttonUpdate.setText("Update");
        buttonUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonUpdateActionPerformed(evt);
            }
        });

        buttonDelete.setText("Delete");
        buttonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDeleteActionPerformed(evt);
            }
        });

        buttonClose.setText("Close");
        buttonClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCloseActionPerformed(evt);
            }
        });

        buttonRead.setText("Read");
        buttonRead.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonReadActionPerformed(evt);
            }
        });

        buttonCreate.setText("Create");
        buttonCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCreateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(buttonCreate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                        .addComponent(buttonRead)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonUpdate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonDelete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonClose)
                        .addGap(14, 14, 14))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonUpdate)
                    .addComponent(buttonDelete)
                    .addComponent(buttonRead)
                    .addComponent(buttonClose)
                    .addComponent(buttonCreate))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonUpdateActionPerformed
        int row = tableGames.getSelectedRow();
        
        if(row != ROW_IS_NOT_SELECTED) {
            init();
            
            Game game = inventoryGameTableModel.getGame(row);
            if(gameHasAnInventory(game.getGameId())) {
                Inventory inventory = lookupInventoryAdministrationBeanRemote().readEntry(game);

                if (inventoryCrudDialog.showUpdateDialog(inventory, game, "Update the inventory for the game \"" + game.getTitle() + "\"")) {
                    lookupInventoryAdministrationBeanRemote().update(inventory);
                }
            }
            else
                JOptionPane.showMessageDialog(null, "Game has not an inventory. Please create a new inventory for \"" + game.getTitle() + "\"");
        }
    }//GEN-LAST:event_buttonUpdateActionPerformed
   
    private void buttonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDeleteActionPerformed
        int row = tableGames.getSelectedRow();
        
        if(row != ROW_IS_NOT_SELECTED) {
            init();
            
            Game game = inventoryGameTableModel.getGame(row);
            if(gameHasAnInventory(game.getGameId())) {
                Inventory inventory = lookupInventoryAdministrationBeanRemote().readEntry(game);

                if (inventoryCrudDialog.showDeleteDialog(inventory, game, "Delete the inventory for the game \"" + game.getTitle() + "\"")) {
                    lookupInventoryAdministrationBeanRemote().delete(inventory);
                    inventoryForGames.remove(game.getGameId());
                }
            }
            else
                JOptionPane.showMessageDialog(null, "\"" + game.getTitle() + "\" has not an inventory. A deletion is not possible");
        }
    }//GEN-LAST:event_buttonDeleteActionPerformed

    private void buttonCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCloseActionPerformed
        dispose();
    }//GEN-LAST:event_buttonCloseActionPerformed

    private void buttonReadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonReadActionPerformed
        
    }//GEN-LAST:event_buttonReadActionPerformed

    private void buttonCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCreateActionPerformed
        int row = tableGames.getSelectedRow();
        
        if(row != ROW_IS_NOT_SELECTED) {
            init();
            Game game = inventoryGameTableModel.getGame(row);

            if(!gameHasAnInventory(game.getGameId())) {
                Inventory inventory = new Inventory();
                inventory.setGame(game);

                if(inventoryCrudDialog.showCreateDialog(inventory, game, "Create a new inventory for the game \"" + game.getTitle() + "\"")) {
                    lookupInventoryAdministrationBeanRemote().create(inventory);
                    inventoryForGames.put(game.getGameId(), true);
                } 
            }
            else
                JOptionPane.showMessageDialog(null, "Game has already an inventory. Please select the options Update/Delete for \"" + game.getTitle() + "\"");
        }
    }//GEN-LAST:event_buttonCreateActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowActivated
    
    private void readInventories() {
        inventories = new TreeSet<>(lookupInventoryAdministrationBeanRemote().readIdEntries());
    }
    
    private void initInventoryTreeMap() {
        for(Game game : games) {
            int gameId = game.getGameId();
            if(!inventories.contains(gameId))
                inventoryForGames.put(gameId, false);
            else
                inventoryForGames.put(gameId, true);
        }
    }
    
    private boolean gameHasAnInventory(final Integer gameId) {
        if(inventoryForGames.containsKey(gameId))
            return inventoryForGames.get(gameId);
        
        return false;
    }
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonClose;
    private javax.swing.JButton buttonCreate;
    private javax.swing.JButton buttonDelete;
    private javax.swing.JButton buttonRead;
    private javax.swing.JButton buttonUpdate;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableGames;
    // End of variables declaration//GEN-END:variables

    private InventoryAdministrationBeanRemote lookupInventoryAdministrationBeanRemote() {
        InventoryAdministrationBeanRemote invRemote = null;
        try {
            Properties props = new Properties();
            props.setProperty(Context.INITIAL_CONTEXT_FACTORY, "com.sun.enterprise.naming.SerialInitContextFactory");
            props.setProperty(Context.URL_PKG_PREFIXES, "com.sun.enterprise.naming");
            props.setProperty(Context.STATE_FACTORIES, "com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");
            props.setProperty("org.omg.CORBA.ORBInitialHost", "localhost");
            props.setProperty("org.omg.CORBA.ORBInitialPort", "3700");

            Context context = new InitialContext(props);
            invRemote = (InventoryAdministrationBeanRemote) context.lookup("ejb/inventoryAdministration");
        }
        catch(NamingException ex) {
            throw new RuntimeException("Exception occured:" + ex.getMessage(), ex);
        }
        
        return invRemote;
    }
    
    private void init() {
        if(inventoryCrudDialog == null)
            inventoryCrudDialog = new InventoryCrudDialog(null, true);
        
        SwingTools.centerOnScreen(0, inventoryCrudDialog);
    }
}
