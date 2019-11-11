/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.kourtzis.dgs.model;

import gr.kourtzis.dgs.ejb.DigitalGameStoreBeanRemote;
import gr.kourtzis.dgs.entity.Category;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.table.AbstractTableModel;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Athanasios Kourtzis
 */
@Getter
@Setter

public class CategoryTableModel extends AbstractTableModel {
    private List<Category> categories;
    final private String[] columns = {
        "Nr.",
        "Genre",
        "Description"
    };
    
    /**
     * The constructor initializes the categories list and populates it
     * with values from the database.
     */
    public CategoryTableModel() {
        categories = new ArrayList<>();
        readCategories();
    }

    /**
     * The method returns the number of category objects in the model.
     * @return The number of the categories in the list.
     */
    @Override
    public int getRowCount() {
        return categories.size();
    }

    /**
     * The method returns the size of the columns table.
     * @return The size of the column table. 
     */
    @Override
    public int getColumnCount() {
        return columns.length;
    }

    /**
     * Returns the value for the cell at columnIndex and rowIndex.
     * @param rowIndex  the row whose value is to be queried
     * @param columnIndex  the column whose value is to be queried
     * @return the value Object at the specified cell
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Category category = getCategory(rowIndex);
        
        switch(columnIndex) {
            case 0: return category.getCategoryId();
            case 1: return category.getGenre();
            case 2: return category.getDescription();
            
            default: throw new AssertionError();
        }
    }
    
    /**
     * Returns the name of the column at columnIndex. 
     * This is used to initialize the table's column header name. 
     * Note: this name does not need to be unique; two columns in a table can have the same name.
     * 
     * @param columnIndex the index of the column
     * @return the name of the column
     */
    @Override
    public String getColumnName(int columnIndex) {
        return columns[columnIndex];
    }
    
    /**
     * Returns the Category object at index.
     * @param index The index position in the list.
     * @return a category object.
     */
    public Category getCategory(int index) {
        return categories.get(index);
    }
    
    /**
     * Saves the category object in the database and updates the list
     * @param category The object to be saved.
     */
    public void save(final Category category) {
        try {
            lookupDigitalGameStoreBeanRemote().addCategory(category);
        }
        finally {
            contentChanged();
        }
    }
    
    /**
     * Updates an edited Category object, saves it in the database
     * and updates the list
     * @param category The edited Category object.
     */
    public void update(final Category category) {
        save(category);
    }
    
    /**
     * Deletes the Category object from the database and updates the list.
     * @param category The Category object to be deleted.
     */
    public void delete(final Category category) {
        try {
            lookupDigitalGameStoreBeanRemote().delete(category);
        }
        finally {
            contentChanged();
        }
    }
    
    /**
     * The method gets called when the Category list should be updated from the database.
     */
    public void readCategories() {
        contentChanged();
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
        categories = lookupDigitalGameStoreBeanRemote().getCategories();
        fireTableDataChanged();
    }
}
