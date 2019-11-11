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
import javax.swing.AbstractListModel;

/**
 *
 * @author Athanasios Kourtzis
 */
public class CategoryListModel extends AbstractListModel {
    private List<Category> categories;
    
    public CategoryListModel(){
        categories = new ArrayList<>();
        contentChanged();
    }

    /**
     * Returns the length of the list.
     * @return The length of the list. 
     */
    @Override
    public int getSize() {
        return categories.size();
    }

    /**
     * Returns the value at the specified index.
     * @param index The requested index
     * @return The Category object at index.
     */
    @Override
    public Category getElementAt(int index) {
        return categories.get(index);
    }
    
    /**
     * Method is called after one or more elements of the list change.
     */
    public void contentChanged() {
        categories = lookupDigitalGameStoreBeanRemote().getCategories();
        fireContentsChanged(this, 0, categories.size());
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
}
