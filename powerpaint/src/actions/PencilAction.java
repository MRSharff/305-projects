/* TCSS 305 Assignment 5 PowerPaint - Fall 2014 */

package actions;

import gui.PaintPanel;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Path2D;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.event.MouseInputAdapter;

/**
 * An freehand drawing action.
 * 
 * @author Mat Sharff
 * @version 5
 * {@inheritDoc}
 */
@SuppressWarnings("serial")
public class PencilAction extends AbstractAction {
    
    /** The PaintPanel to draw on. */
    private final PaintPanel myPanel;
    
    /** The mouse adapter to use when Pencil is the selected tool. */
    private final Pencil myMouseAdapter;
    
    /**
     * Constructor that creates a pencil action.
     * @param thePanel the PaintPanel to draw on.
     */
    public PencilAction(final PaintPanel thePanel) {
        super("Pencil");
        putValue(Action.MNEMONIC_KEY, KeyEvent.VK_P);
        putValue(Action.SELECTED_KEY, true);
        myPanel = thePanel;
        myMouseAdapter = new Pencil();
    }

    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        myPanel.setMouseAdapter(myMouseAdapter);
    }
    
    /**
     * A mouse adapter that creates a path and sends it to the JPanel list of shapes.
     * @author Mat
     * @version 2
     */
    private class Pencil extends MouseInputAdapter {
        
        /** The path to add points to and ultimately pass to the PaintPanels shapes list. */
        private Path2D myNewPath;
        
        @Override
        public void mousePressed(final MouseEvent theEvent) {
            myNewPath = new Path2D.Double();
            myNewPath.moveTo(theEvent.getX(), theEvent.getY());
            myNewPath.lineTo(theEvent.getX(), theEvent.getY());
            myPanel.setCurrentShape(myNewPath);
            myPanel.repaint();
        }
        
        @Override
        public void mouseDragged(final MouseEvent theEvent) {
            myNewPath.lineTo(theEvent.getX(), theEvent.getY());
            myPanel.repaint();
        }
        
        @Override
        public void mouseReleased(final MouseEvent theEvent) {
            myPanel.setCurrentShape(null);
            myPanel.addShape(myNewPath);
            myPanel.repaint();
        }
    }

}
