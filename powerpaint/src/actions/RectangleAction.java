/* TCSS 305 Assignment 5 PowerPaint - Fall 2014 */

package actions;

import gui.PaintPanel;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.event.MouseInputAdapter;

/**
 * An action that draws an rectangle.
 * 
 * @author Mat Sharff
 * @version 5
 * {@inheritDoc}
 */
@SuppressWarnings("serial")
public class RectangleAction extends AbstractAction {
    
    /** The PaintPanel to draw on. */
    private final PaintPanel myPanel;
    
    /** The mouse adapter to use when Rectangle is the selected tool. */
    private final Rectangle myMouseAdapter;
    
    /**
     * Constructor that creates a rectangle action.
     * @param thePanel the PaintPanel to draw on.
     */
    public RectangleAction(final PaintPanel thePanel) {
        super("Rectangle");
        putValue(Action.MNEMONIC_KEY, KeyEvent.VK_R);
        putValue(Action.SELECTED_KEY, true);
        myPanel = thePanel;
        myMouseAdapter = new Rectangle();
    }

    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        myPanel.setMouseAdapter(myMouseAdapter);
    }
    
    /**
     * A mouse adapter that creates a rectangle and sends it to the JPanel list of shapes.
     * @author Mat
     * @version 2
     */
    private class Rectangle extends MouseInputAdapter {
        
        /** The X coordinate of the top left of the bounding box. */
        private double myStartX;
        
        /** The Y coordinate of the top left of the bounding box. */
        private double myStartY;
        
        /** Tracks the x coordinate of where the mouse was dragged from. */
        private double myFirstX;
        
        /** Tracks the y coordinate of where the mouse was dragged from. */
        private double myFirstY;
        
        /** The width of the shapes bounding box. */
        private double myWidth;
        
        /** The height of the shapes bounding box. */
        private double myHeight;
        
        @Override
        public void mousePressed(final MouseEvent theEvent) {
            myFirstX = theEvent.getX();
            myFirstY = theEvent.getY();
        }
        
        @Override
        public void mouseDragged(final MouseEvent theEvent) {
            
            final double endX;
            
            final double endY;
            
            if (theEvent.getX() < myFirstX) {
                myStartX = theEvent.getX();
                myWidth = myFirstX - myStartX;
            } else {
                myStartX = myFirstX;
                endX = theEvent.getX();
                myWidth = endX - myStartX;
            }
            if (theEvent.getY() < myFirstY) {
                myStartY = theEvent.getY();
                myHeight = myFirstY - myStartY;
            } else {
                myStartY = myFirstY;
                endY = theEvent.getY();
                myHeight = endY - myStartY;
            }
            
            myPanel.setCurrentShape(new Rectangle2D.Double(myStartX,
                                                         myStartY,
                                                         myWidth,
                                                         myHeight));
            myPanel.repaint();
        }
        
        @Override
        public void mouseReleased(final MouseEvent theEvent) {
            myPanel.setCurrentShape(null);
            myPanel.addShape(new Rectangle2D.Double(myStartX, myStartY, myWidth, myHeight));
            myPanel.repaint();
        }
    }

}