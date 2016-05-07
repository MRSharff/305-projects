/* TCSS 305 Assignment 5 PowerPaint - Fall 2014 */

package gui;

import actions.ColorPicker;

import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

/**
 * A tool bar to attach a color button and drawing tools to.
 * @author Mat
 * @version 1
 */
@SuppressWarnings("serial")
public class ToolBar extends JToolBar {
    
    /** The button group to ensure only one tool is selected at a time. */
    private final ButtonGroup myButtonGroup;
    
    /**
     * Constructs a toolbar with a color button and it's icon.
     * @param theAction the color chooser action
     */
    public ToolBar(final ColorPicker theAction) {
        super();
        myButtonGroup = new ButtonGroup();
        final JButton colorSwatchButton = new JButton(theAction);
        
        //remove the weird selected border of the color button so it doesn't obscure the icon.
        colorSwatchButton.setFocusPainted(false);
        
        colorSwatchButton.setIcon(theAction.getIcon());
        add(colorSwatchButton);
        addSeparator();
    }

    /**
     * Creates buttons with actions and adds them to the button group.
     * @param theAction the action to assign to a button
     */
    public void createToolsButton(final Action theAction) {
        final JToggleButton toolButton = new JToggleButton(theAction);
        
        myButtonGroup.add(toolButton);
        myButtonGroup.clearSelection();
        
        add(toolButton);
    }
}