/* TCSS 305 Assignment 5 PowerPaint - Fall 2014 */

package actions;

import gui.PaintPanel;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.event.MouseInputAdapter;

/**
 * An action that draws an line.
 * 
 * @author Mat Sharff
 * @version 5
 * {@inheritDoc}
 */
@SuppressWarnings("serial")
public class LineAction extends AbstractAction {
    
    /** The PaintPanel to draw on. */
    private final PaintPanel myPanel;
    
    /** The mouse adapter to use when Line is the selected tool. */
    private final Line myMouseAdapter;
    
    /**
     * Constructor that creates a line action.
     * @param thePanel the PaintPanel to draw on.
     */
    public LineAction(final PaintPanel thePanel) {
        super("Line");
        putValue(Action.MNEMONIC_KEY, KeyEvent.VK_L);
        putValue(Action.SELECTED_KEY, true);
        myPanel = thePanel;
        myMouseAdapter = new Line();
    }

    /** 
     * Sets myPanels mouse adapter to this actions myMouseAdapter.
     * {@inheritDoc}
     */
    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        myPanel.setMouseAdapter(myMouseAdapter);
    }
    
    /**
     * A mouse adapter that creates a line and sends it to the JPanel list of shapes.
     * @author Mat
     * @version 2
     */
    private class Line extends MouseInputAdapter {
        
        /** The starting point of the line. */
        private Point myStartPoint;
        
        /** The ending point of the line. */
        private Point myEndPoint;
        
        @Override
        public void mousePressed(final MouseEvent theEvent) {
            myStartPoint = theEvent.getPoint();

        }
        
        @Override
        public void mouseDragged(final MouseEvent theEvent) {
            myEndPoint = theEvent.getPoint();
            myPanel.setCurrentShape(new Line2D.Double(myStartPoint, myEndPoint));
            myPanel.repaint();
        }
        
        @Override
        public void mouseReleased(final MouseEvent theEvent) {
            myPanel.setCurrentShape(null);
            myPanel.addShape(new Line2D.Double(myStartPoint, myEndPoint));
            myPanel.repaint();
        }
    }

}
