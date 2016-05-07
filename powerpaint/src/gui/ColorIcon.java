/* TCSS 305 Assignment 5 PowerPaint - Fall 2014 */

package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.Icon;

/**
 * A custom icon that changes color according to the currently selected color.
 * @author Mat Sharff
 * @version 1
 */
public class ColorIcon implements Icon {
    
    /** Width of the icon. */
    private static final int ICON_WIDTH = 13;
    
    /** Height of the icon. */
    private static final int ICON_HEIGHT = 13;
    
    /** the color of the icon. */
    private Color myColor;
    
    /**
     * Constructs an icon and sets myColor.
     * @param theColor is a default color.
     */
    public ColorIcon(final Color theColor) {
        super();
        myColor = theColor;
    }

    /**
     * Sets the Color of the Icon.
     * @param theColor the color to set myColor to
     */
    public void setColor(final Color theColor) {
        myColor = theColor;
    }
    
    @Override
    public void paintIcon(final Component theComponent,
                          final Graphics theGraphics,
                          final int theX,
                          final int theY) {
        final Graphics2D new2DGraphics = (Graphics2D) theGraphics;
        
        new2DGraphics.setColor(myColor);
        new2DGraphics.fillRect(theX, theY, ICON_WIDTH, ICON_HEIGHT);
        new2DGraphics.setColor(Color.BLACK);
        new2DGraphics.drawRect(theX, theY, ICON_WIDTH, ICON_HEIGHT);
    }

    @Override
    public int getIconWidth() {
        return ICON_WIDTH;
    }

    @Override
    public int getIconHeight() {
        return ICON_HEIGHT;
    }
}
