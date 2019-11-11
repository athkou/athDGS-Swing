/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.kourtzis.dgs.util;

import java.awt.*;

/**
 * Utility-Klasse für das Swing-Framework.
 * 
 * @author javafish
 */
public final class SwingTools {

    private SwingTools() {}

    /**
     * Berechnet die Position, um den Frame der Größe {@code dimension} auf dem 
     * angebenen {@code screen} zu zentrieren.
     * 
     * @param screen trail-and-error
     * @param dimension die Größe des zu zetrierenden Frames
     * @return Point mit den x/y-Koordinaten
     */
    public static Point getCenterOnScreen(int screen, Dimension dimension) {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gs = ge.getScreenDevices();
//        System.out.println(gs.length);
        GraphicsDevice gd = gs[screen];
        GraphicsConfiguration[] gc = gd.getConfigurations();
//        System.out.println(gc.length);
        Rectangle screenBounds = gc[0].getBounds();
        
        Point pos = new Point();
        pos.x = screenBounds.x + (screenBounds.width / 2) - (dimension.width / 2);
        pos.y = screenBounds.y + (screenBounds.height / 2) - (dimension.height / 2);
        
        return pos;
    }

    /**
     * Positioniert den übergebenen {@code frame} auf dem mit {@code screen}
     * benannten Monitor.
     * @param screen
     * @param frame 
     */
    public static void centerOnScreen(int screen, Window frame) {
        frame.setLocation(getCenterOnScreen(screen, frame.getSize()));
    }
}
