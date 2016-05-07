/* TCSS 305 Assignment 5 PowerPaint - Fall 2014 */

package actions;

import gui.ColorIcon;
import gui.PaintPanel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JColorChooser;

/**
 * A color picker action that opens a Color chooser and sets a PaintPanels color field.
 * @author Mat Sharff
 * @version 1
 */
@SuppressWarnings("serial")
public class ColorPicker extends AbstractAction {

    /** An Icon that changes color to the newly selected color. */
    private final ColorIcon myIcon;
    
    /** The PaintPanel whose myColor field will change. */
    private final PaintPanel myPanel;
    
    /**
     * 
     * @param thePanel assigns myPanel
     */
    public ColorPicker(final PaintPanel thePanel) {
        super("Color...");
        putValue(Action.MNEMONIC_KEY, KeyEvent.VK_C);
        myPanel = thePanel;
        myIcon = new ColorIcon(myPanel.getColor());
    }
    
    @Override
    public void actionPerformed(final ActionEvent theAction) {
        
        final Color newColor = JColorChooser.showDialog(myPanel,
                                                        "Select a Color",
                                                        myPanel.getColor());
        if (newColor != null) {
            myPanel.setColor(newColor);
            myIcon.setColor(newColor);
        }
    }
    
    /**
     * Returns the icon.
     * @return returns myIcon
     */
    public ColorIcon getIcon() {
        return myIcon;
    }

}
