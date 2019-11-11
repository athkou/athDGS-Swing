/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.kourtzis.dgs.model;

import gr.kourtzis.dgs.ejb.DigitalGameStoreBeanRemote;
import gr.kourtzis.dgs.entity.Category;
import gr.kourtzis.dgs.entity.Game;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.table.AbstractTableModel;
import lombok.Getter;

/**
 *
 * @author Athanasios Kourtzis
 */
@Getter

public class GamesTableModel extends AbstractTableModel {
    private List<Game> games;
    private int gameCategoryId;
    
    final private String[] columns = {
        "Nr.",
        "Title",
        "Description"
    };
    
    public GamesTableModel() {
        games = new ArrayList<>();
    }
    
    /**
     * Accessor method. Sets the member variable games 
     * @param games The list who is passed to the member variable
     */
    public void setGames(final List<Game> games) {
        this.games = games;
        fireTableDataChanged();
    }
    
    /**
     * Returns the number of rows from the games list
     * @return The number of rows from the list
     */
    @Override
    public int getRowCount() {
        return games.size();
    }

    /**
     * Returns the number of columns in the model
     * @return The number of columns in the model
     */
    @Override
    public int getColumnCount() {
        return columns.length;
    }
    
    /**
     * Returns the name of the column at columnIndex. 
     * This is used to initialize the table's column header name. 
     * Note: this name does not need to be unique; two columns in a table can have the same name.
     * 
     * @param columnIndex the index of the column.
     * @return the name of the column.
     */
    @Override
    public String getColumnName(int columnIndex) {
        return columns[columnIndex];
    }

    /**
     * Returns the value for the cell at columnIndex and rowIndex.
     * @param rowIndex The row whose value is to be queried.
     * @param columnIndex The column whose value is to be queried.
     * @return The value Object at the specified cell.
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Game game = getGame(rowIndex);
        
        switch(columnIndex) {
            case 0: return game.getGameId();
            case 1: return game.getTitle();
            case 2: return game.getDescription();
            
            default: throw new AssertionError();
        }
    }
    
    /**
     * Returns the Game object at the specified index.
     * @param index The index position in the list.
     * @return The Game object at index.
     */
    public Game getGame(int index) {
        return games.get(index);
    }
    
    /**
     * The method returns list of games for a specified category
     * @param categoryId The id of the category which is used as a search parameter.
     * @return A list of Game objects.
     */
    public List<Game> readGamesByCategory(int categoryId) {
        return lookupDigitalGameStoreBeanRemote().getGamesByCategory(categoryId);
    }
    
    /**
     * The methods saves the category in the database.
     * @param category The category object to be saved.
     */
    public void save(final Category category) {
        try {
            gameCategoryId = category.getCategoryId();
            lookupDigitalGameStoreBeanRemote().addCategory(category);
        }
        finally {
            contentChanged();
        }
    }
    
    /**
     * The method updates an existing category object from the database.
     * @param category The Category object to be updated.
     */
    public void update(final Category category) {
        try {
            save(category);
        }
        finally {
            contentChanged();
        }
    }
    
    /**
     * The method deletes an existing Category object from the database.
     * @param category The Category object to be deleted.
     */
    public void delete(final Category category) {
        try {
            save(category);
        }
        finally {
            contentChanged();
        }
    }
    
    private DigitalGameStoreBeanRemote lookupDigitalGameStoreBeanRemote() {
        DigitalGameStoreBeanRemote dgsRemote = null;
        try {
            Properties props = new Properties();
            props.setProperty(Context.INITIAL_CONTEXT_FACTORY, "com.sun.enterprise.naming.SerialInitContextFactory");
            props.setProperty(Context.URL_PKG_PREFIXES, "com.sun.enterprise.naming");
            props.setProperty(Context.STATE_FACTORIES, "com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");
            props.setProperty("org.omg.CORBA.ORBInitialHost", "localhost");
            props.setProperty("org.omg.CORBA.ORBInitialPort", "3700");

            Context context = new InitialContext(props);
            dgsRemote = (DigitalGameStoreBeanRemote) context.lookup("ejb/athDigitalGameStore");
        }
        catch(NamingException ex) {
            throw new RuntimeException("Exception occured:" + ex.getMessage(), ex);
        }
        
        return dgsRemote;
    }
    
    private void contentChanged() {
        games = readGamesByCategory(gameCategoryId);
        fireTableDataChanged();
    }
}
