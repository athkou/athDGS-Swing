/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.kourtzis.dgs.view;

import gr.kourtzis.dgs.entity.Category;
import gr.kourtzis.dgs.entity.Game;
import gr.kourtzis.dgs.model.CategoryListModel;
import gr.kourtzis.dgs.model.GamesTableModel;
import gr.kourtzis.dgs.util.SwingTools;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

/**
 *
 * @author Athanasios Kourtzis
 */
public class GameDialog extends javax.swing.JDialog {
    private final int ROW_IS_NOT_SELECTED = -1;
    private final int NOT_FOUND = -1;
    
    private final GamesTableModel gameTableModel;
    private final CategoryListModel categoryListModel;
    
    private GameCrudDialog gameCrudDialog;
    
    private Category category;
    private List<Game> games;
    /**
     * Creates new form GameDialog
     */
    public GameDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        games = new ArrayList<>();
        
        categoryListModel =  new CategoryListModel();
        gameTableModel = new GamesTableModel();
        
        initComponents();
        tableGames.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        switchButtons(false);
        buttonCreate.setEnabled(false);
        
        listCategory.addListSelectionListener(event -> {
            if(!event.getValueIsAdjusting()) {
                JList source = (JList) event.getSource();
                category = (Category) source.getSelectedValue();
                
                printDebugInfo();
                contentChanged();
                
                if(!games.isEmpty())
                    switchButtons(true);
                else
                    switchButtons(false);
            }
        });
    }
    
    private void init() {
        if(gameCrudDialog == null)
            gameCrudDialog = new GameCrudDialog(null, true);
        
        SwingTools.centerOnScreen(0, gameCrudDialog);
    }
    
    private void contentChanged() {
        categoryListModel.contentChanged();
        games = gameTableModel.readGamesByCategory(category.getCategoryId());
        gameTableModel.setGames(games);
    }
    
    private void printDebugInfo() {
        System.out.println("Category: " + category.getGenre());
        System.out.println("Category id: " + category.getCategoryId());
        System.out.println("Available games:");
        category.getGames().forEach(System.out::println);
    }
    
    private void switchButtons(boolean buttonsEnabled) {
        buttonCreate.setEnabled(true);
        buttonDelete.setEnabled(buttonsEnabled);
        buttonRead.setEnabled(buttonsEnabled);
        buttonUpdate.setEnabled(buttonsEnabled);
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
        labelSelectCategory = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listCategory = new javax.swing.JList<>();
        buttonCreate = new javax.swing.JButton();
        buttonUpdate = new javax.swing.JButton();
        buttonDelete = new javax.swing.JButton();
        buttonClose = new javax.swing.JButton();
        buttonRead = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        tableGames.setModel(gameTableModel);
        jScrollPane1.setViewportView(tableGames);

        labelSelectCategory.setText("Select a category");

        listCategory.setModel(categoryListModel);
        listCategory.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listCategory.setLayoutOrientation(javax.swing.JList.HORIZONTAL_WRAP);
        jScrollPane2.setViewportView(listCategory);

        buttonCreate.setText("Create");
        buttonCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCreateActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelSelectCategory)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(buttonCreate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonRead)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonUpdate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonDelete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonClose)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2)
                    .addComponent(labelSelectCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonCreate)
                    .addComponent(buttonUpdate)
                    .addComponent(buttonDelete)
                    .addComponent(buttonClose)
                    .addComponent(buttonRead))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCreateActionPerformed
        Game game = new Game();
        init();
        
        if(gameCrudDialog.showNewDialog(game, "Adding a new game")) {
            category.addGame(game);
            gameTableModel.save(category);
        }
    }//GEN-LAST:event_buttonCreateActionPerformed

    private void buttonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonUpdateActionPerformed
        int row = tableGames.getSelectedRow();
        
        if(row != ROW_IS_NOT_SELECTED) {
            init();
            Game game = gameTableModel.getGame(row);
            
            if(gameCrudDialog.showUpdateDialog(game, "Updating an existing game")) {
                int index = findIndex(game);
                
                if(index != NOT_FOUND) 
                    updateCategory(index, game);
            }
        }
    }//GEN-LAST:event_buttonUpdateActionPerformed

    private void updateCategory(int index, final Game game) {
        if(game != null) {
            category.getGames().get(index).setTitle(game.getTitle());
            category.getGames().get(index).setDescription(game.getDescription());

            gameTableModel.update(category);
        }
    }
    
    private int findIndex(final Game game) {
        int index = NOT_FOUND;

        if(category != null && game != null) {
            for (int it = 0; it != category.getGames().size(); ++it) {
                if (category.getGames().get(it).equals(game)) {
                    index = it;
                    break;
                }
            }
        }

        return index;
    }
    
    private void buttonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDeleteActionPerformed
        int row = tableGames.getSelectedRow();
        
        if(row != ROW_IS_NOT_SELECTED) {
            init();
            Game game = gameTableModel.getGame(row);
            
            if(gameCrudDialog.showDeleteDialog(game, "Delete an existing category. Are you sure?")) {
                if(category.getGames().contains(game)) {
                    category.removeGame(game);
                    
                    gameTableModel.delete(category);
                }
            }
        }
    }//GEN-LAST:event_buttonDeleteActionPerformed

    private void buttonCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCloseActionPerformed
        dispose();
    }//GEN-LAST:event_buttonCloseActionPerformed

    private void buttonReadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonReadActionPerformed
        categoryListModel.contentChanged();
    }//GEN-LAST:event_buttonReadActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        categoryListModel.contentChanged();
    }//GEN-LAST:event_formWindowActivated
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonClose;
    private javax.swing.JButton buttonCreate;
    private javax.swing.JButton buttonDelete;
    private javax.swing.JButton buttonRead;
    private javax.swing.JButton buttonUpdate;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelSelectCategory;
    private javax.swing.JList<String> listCategory;
    private javax.swing.JTable tableGames;
    // End of variables declaration//GEN-END:variables
}